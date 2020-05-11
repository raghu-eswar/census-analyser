package com.india;

import com.opencsv.bean.CsvBindByName;

public class StateCode implements Comparable{
    @CsvBindByName(column = "State" , required = true)
    private String stateName;
    @CsvBindByName(column = "TIN" , required = true)
    private int TIN;
    @CsvBindByName(column = "StateCode" , required = true)
    private String stateCode;

    public String getStateName() {
        return stateName;
    }

    public int getTIN() {
        return TIN;
    }

    public String getStateCode() {
        return stateCode;
    }

    @Override
    public String toString() {
        return "StateCode{" +
                "stateName='" + stateName + '\'' +
                ", TIN=" + TIN +
                ", stateCode='" + stateCode + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        StateCode that = (StateCode) o;
        return this.stateCode.compareTo(that.stateCode);
    }
}
