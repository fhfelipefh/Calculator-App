package com.fhfelipefh.cloud.tema8;

import com.netflix.karyon.transport.http.health.HealthCheckEndpoint;
import io.reactivex.netty.RxNetty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        HealthcheckResource healthCheckRe = (HealthcheckResource) applicationContext.getBean("healthCheckResource");

        RxNetty.createHttpServer(8081, new RxNettyHandler("/healthCheck",
                new HealthCheckEndpoint(healthCheckRe),
                (Calculator) applicationContext.getBean("calculator")))
                .startAndWait();

    }
}
