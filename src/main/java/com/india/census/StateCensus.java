package com.india.census;

import com.opencsv.bean.CsvBindByName;

public class StateCensus implements Comparable {
    @CsvBindByName(column = "State" , required = true)
    private String stateName;
    @CsvBindByName(column = "Population", required = true)
    private long population;
    @CsvBindByName(column = "AreaInSqKm", required = true)
    private long areaInSqKm;
    @CsvBindByName(column = "DensityPerSqKm", required = true)
    private int densityPerSqKm;

    public String getStateName() {
        return stateName;
    }

    public long getPopulation() {
        return population;
    }

    public long getAreaInSqKm() {
        return areaInSqKm;
    }

    public int getDensityPerSqKm() {
        return densityPerSqKm;
    }

    @Override
    public String toString() {
        return "StateCensus{" +
                "stateName='" + stateName + '\'' +
                ", population=" + population +
                ", areaInSqKm=" + areaInSqKm +
                ", densityPerSqKm=" + densityPerSqKm +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        StateCensus that = (StateCensus) o;
        return (this.population > that.population);
    }

    @Override
    public int compareTo(Object o) {
        StateCensus that = (StateCensus) o;
        return (int) (this.population - that.population);
    }
}
