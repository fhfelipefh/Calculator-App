package com.fhfelipefh.cloud.tema8;

public class Calculator {

    CalculationsHistory calculationsHistory = new CalculationsHistory();

    public float calculate(float value1, float value2, Operations operation) {
        calculationsHistory.addOperation(operation);
        return operation.calculate(value1, value2);
    }

}
