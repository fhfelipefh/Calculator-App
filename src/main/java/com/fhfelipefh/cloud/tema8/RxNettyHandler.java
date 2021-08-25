package com.fhfelipefh.cloud.tema8;

import com.netflix.karyon.transport.http.health.HealthCheckEndpoint;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import io.reactivex.netty.protocol.http.server.RequestHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import rx.Observable;

public class RxNettyHandler implements RequestHandler<ByteBuf, ByteBuf> {

    private final String healthCheckUri;
    private final HealthCheckEndpoint healthCheckEndpoint;
    private final Calculator calculator;
    private final ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);


    public RxNettyHandler(String healthCheckUri, HealthCheckEndpoint healthCheckEndpoint, Calculator calculator) {
        this.healthCheckUri = healthCheckUri;
        this.healthCheckEndpoint = healthCheckEndpoint;
        this.calculator = calculator;
    }

    @Override
    public Observable<Void> handle(HttpServerRequest<ByteBuf> request, HttpServerResponse<ByteBuf> response) {
        if (request.getUri().startsWith(healthCheckUri)) {
            return healthCheckEndpoint.handle(request, response);
        } else if (request.getUri().startsWith("/calculate/")) {
            String result;
            float num1 = 0;
            float num2 = 0;
            if (request.getUri().contains("num1=") && request.getUri().contains("num2=")) {
                if (verifyNumbers(request.getQueryParameters().get("num1").get(0)) && verifyNumbers(request.getQueryParameters().get("num2").get(0))) {
                    num1 = Float.parseFloat(request.getQueryParameters().get("num1").get(0));
                    num2 = Float.parseFloat(request.getQueryParameters().get("num2").get(0));
                } else {
                    response.setStatus(HttpResponseStatus.BAD_REQUEST);
                    return response.writeStringAndFlush("Erro ao efetuar calculo, caracteres inválidos informados ou campos vazios!");
                }
            }
            String operation = String.valueOf((request.getPath()));
            String op = operation.substring(11).toLowerCase();
            if (!op.isEmpty()) {
                try {
                    result = String.valueOf(calculator.calculate(num1, num2, (Operations) context.getBean(op)));
                } catch (RuntimeException e) {
                    response.setStatus(HttpResponseStatus.NOT_FOUND);
                    return response.writeStringAndFlush("Está operação não foi encontrada, verifique a operação informada e tente novamente.");
                }
            } else {
                response.setStatus(HttpResponseStatus.BAD_REQUEST);
                return response.writeStringAndFlush("Operação não foi informada! tente novamente com: sum,sub,mult,div,pow.");
            }
            response.setStatus(HttpResponseStatus.OK);
            return response.writeStringAndFlush(result);
        } else {
            response.setStatus(HttpResponseStatus.INTERNAL_SERVER_ERROR);
            return response.close();
        }
    }

    private boolean verifyNumbers(String number) {
        return number.matches("-?\\d+(\\.\\d+)?");
    }

}