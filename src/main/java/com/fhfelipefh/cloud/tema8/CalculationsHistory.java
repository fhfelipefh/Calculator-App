package com.fhfelipefh.cloud.tema8;

import java.util.ArrayList;

public class CalculationsHistory {

    private ArrayList<Operations> calcHistory = new ArrayList<Operations>();

    public void addOperation(Operations operation) {
        calcHistory.add(operation);
    }

    public ArrayList<Operations> getCalcHistory() {
        return calcHistory;
    }
}
