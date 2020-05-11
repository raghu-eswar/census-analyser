package com.india.census.analyzer;

import java.io.IOException;

public class StateCensusAnalyser {

    private final StatesCSVDataBuilder statesCSVDataBuilder;

    public StateCensusAnalyser(String stateCensusDataPath, String stateCodeDataPath) throws IOException {
        this.statesCSVDataBuilder = StatesCSVDataBuilder.getInstance(stateCensusDataPath, stateCodeDataPath);
    }

    public StatesCSVDataBuilder getStatesCSVDataBuilder() {
        return statesCSVDataBuilder;
    }
}

