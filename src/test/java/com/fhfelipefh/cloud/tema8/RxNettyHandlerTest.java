package com.fhfelipefh.cloud.tema8;

import com.netflix.karyon.transport.http.health.HealthCheckEndpoint;
import io.reactivex.netty.RxNetty;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Config.class})
public class RxNettyHandlerTest {

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
    HealthcheckResource healthCheckRe = (HealthcheckResource) applicationContext.getBean("healthCheckResource");


    @Test
    public void shouldReturnStatusSuccessToSum() throws IOException {
        RxNetty.createHttpServer(8082, new RxNettyHandler("/healthCheck",
                new HealthCheckEndpoint(healthCheckRe),
                (Calculator) applicationContext.getBean("calculator"))).start();
        HttpUriRequest requestURL = new HttpGet("http://localhost:8082/calculate/sum?num1=1&num2=1");
        HttpResponse response = HttpClientBuilder.create().build().execute(requestURL);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
    }

    @Test
    public void shouldReturnStatusSuccessToSub() throws IOException {
        RxNetty.createHttpServer(8083, new RxNettyHandler("/healthCheck",
                new HealthCheckEndpoint(healthCheckRe),
                (Calculator) applicationContext.getBean("calculator"))).start();
        HttpUriRequest requestURL = new HttpGet("http://localhost:8083/calculate/sub?num1=1&num2=1");
        HttpResponse response = HttpClientBuilder.create().build().execute(requestURL);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
    }


    @Test
    public void shouldReturnStatusSuccessToDiv() throws IOException {
        RxNetty.createHttpServer(8084, new RxNettyHandler("/healthCheck",
                new HealthCheckEndpoint(healthCheckRe),
                (Calculator) applicationContext.getBean("calculator"))).start();
        HttpUriRequest requestURL = new HttpGet("http://localhost:8084/calculate/div?num1=1&num2=1");
        HttpResponse response = HttpClientBuilder.create().build().execute(requestURL);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
    }


    @Test
    public void shouldReturnStatusSuccessToMult() throws IOException {
        RxNetty.createHttpServer(8085, new RxNettyHandler("/healthCheck",
                new HealthCheckEndpoint(healthCheckRe),
                (Calculator) applicationContext.getBean("calculator"))).start();
        HttpUriRequest requestURL = new HttpGet("http://localhost:8085/calculate/mult?num1=1&num2=1");
        HttpResponse response = HttpClientBuilder.create().build().execute(requestURL);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
    }


    @Test
    public void shouldReturnStatusSuccessToPow() throws IOException {
        RxNetty.createHttpServer(8087, new RxNettyHandler("/healthCheck",
                new HealthCheckEndpoint(healthCheckRe),
                (Calculator) applicationContext.getBean("calculator"))).start();
        HttpUriRequest requestURL = new HttpGet("http://localhost:8087/calculate/pow?num1=1&num2=1");
        HttpResponse response = HttpClientBuilder.create().build().execute(requestURL);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
    }

    @Test
    public void shouldNotReturnStatusSuccessToRequest() throws IOException {
        RxNetty.createHttpServer(8088, new RxNettyHandler("/healthCheck",
                new HealthCheckEndpoint(healthCheckRe),
                (Calculator) applicationContext.getBean("calculator"))).start();
        HttpUriRequest requestURL = new HttpGet("http://localhost:8088/calculate/p?num1=1&num2=1");
        HttpResponse response = HttpClientBuilder.create().build().execute(requestURL);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void shouldReturnStatusBadRequest() throws IOException {
        RxNetty.createHttpServer(8089, new RxNettyHandler("/healthCheck",
                new HealthCheckEndpoint(healthCheckRe),
                (Calculator) applicationContext.getBean("calculator"))).start();
        HttpUriRequest requestURL = new HttpGet("http://localhost:8089/calculate/?num1=1&num2=1");
        HttpResponse response = HttpClientBuilder.create().build().execute(requestURL);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void shouldReturnStatusServerError() throws IOException {
        RxNetty.createHttpServer(8090, new RxNettyHandler("/healthCheck",
                new HealthCheckEndpoint(healthCheckRe),
                (Calculator) applicationContext.getBean("calculator"))).start();
        HttpUriRequest requestURL = new HttpGet("http://localhost:8090/calculate");
        HttpResponse response = HttpClientBuilder.create().build().execute(requestURL);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

}