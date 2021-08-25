package com.fhfelipefh.cloud.tema8;

import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("+")
public class Sum implements Operations {

    @Override
    public float calculate(float value1, float value2) {
        return value1 + value2;
    }
}
