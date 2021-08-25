package com.fhfelipefh.cloud.tema8;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.fhfelipefh.cloud.tema8")
public class Config {

    @Bean
    public HealthcheckResource healthCheckResource() {
        return new HealthcheckResource();
    }

    @Bean
    public Calculator calculator() {
        return new Calculator();
    }

    @Bean
    public Sum sum() {
        return new Sum();
    }

    @Bean
    public Sub sub() {
        return new Sub();
    }

    @Bean
    public Mult mult() {
        return new Mult();
    }

    @Bean
    public Div div() {
        return new Div();
    }

    @Bean
    public Pow pow() {
        return new Pow();
    }

}
