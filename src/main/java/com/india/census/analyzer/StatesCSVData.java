package com.india.census.analyzer;

import com.india.census.StateCensus;
import com.india.StateCode;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvRuntimeException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class StatesCSVDataBuilder {
    private static StatesCSVDataBuilder statesCSVDataBuilder;
    private Map<String, StateCensus> stateCensusMap;
    private Map<String, StateCode> stateCodeMap;

    private StatesCSVDataBuilder(String stateCensusDataPath, String stateCodeDataPath) throws IOException {
        this.stateCensusMap = readStateCensusData(stateCensusDataPath);
        this.stateCodeMap = readStateCodeData(stateCodeDataPath);
    }

    public Map<String, StateCensus> getStateCensusMap() {
        return stateCensusMap;
    }

    public Map<String, StateCode> getStateCodeMap() {
        return stateCodeMap;
    }

    private Map<String, StateCode> readStateCodeData(String path) throws IOException {
        Map<String, StateCode> stringStateCodeMap = new HashMap<>();
        try {
            CsvToBean<StateCode> csvToBean = readBeans(StateCode.class, path);
            for (StateCode data : csvToBean) {
                stringStateCodeMap.put(data.getStateName(), data);
            }
        } catch (RuntimeException e) {
        String message[] = e.getLocalizedMessage().split(":");
        throw new CsvRuntimeException(message[message.length-1].trim());
    }
        return stringStateCodeMap;
    }

    private Map<String, StateCensus> readStateCensusData(String path) throws IOException {

        Map<String, StateCensus> stringStateCensusMap = new HashMap<>();
        try {
            CsvToBean<StateCensus> csvToBean = readBeans(StateCensus.class, path);
            for (StateCensus data : csvToBean) {
                stringStateCensusMap.put(data.getStateName(), data);
            }
        } catch (RuntimeException e) {
            String message[] = e.getLocalizedMessage().split(":");
            throw new CsvRuntimeException(message[message.length-1].trim());
        }
        return stringStateCensusMap;
    }

    private CsvToBean readBeans(Class cls, String path) throws IOException {
        if (!(path.matches("[A-Za-z0-9./]+\\.csv$"))) {
            throw new NoSuchFileException("file extension must be of .csv type ");
        }
        try {
            Reader reader = Files.newBufferedReader(Paths.get(path));
            return new CsvToBeanBuilder(reader).withType(cls).withIgnoreLeadingWhiteSpace(true).build();
        }catch (NoSuchFileException e){
            throw new NoSuchFileException(path+" no such file in directory");
        }
    }

    public static StatesCSVDataBuilder getInstance(String stateCensusDataPath, String stateCodeDataPath ) throws IOException {
        if (statesCSVDataBuilder == null)
            statesCSVDataBuilder = new StatesCSVDataBuilder(stateCensusDataPath, stateCodeDataPath);
        return statesCSVDataBuilder;
    }
}











