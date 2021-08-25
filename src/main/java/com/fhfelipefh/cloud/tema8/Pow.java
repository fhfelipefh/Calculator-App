package com.fhfelipefh.cloud.tema8;

public class Pow implements Operations{

    @Override
    public float calculate(float base, float power) {
        return (float) Math.pow(base,power);
    }
}
