package com.fhfelipefh.cloud.tema8;

public class Div implements Operations {
    @Override
    public float calculate(float dividendo, float divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("Divisor 0 não permitido!");
        }
        return dividendo / divisor;
    }
}
