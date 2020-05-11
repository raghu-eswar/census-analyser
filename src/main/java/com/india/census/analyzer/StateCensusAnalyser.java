package com.india.census.analyzer;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class StateCensusAnalyser {

    private final StatesCSVData statesCSVData;

    public StateCensusAnalyser(String stateCensusDataPath, String stateCodeDataPath) throws IOException {
        this.statesCSVData = StatesCSVData.getInstance(stateCensusDataPath, stateCodeDataPath);
    }

    public StatesCSVData getStatesCSVData() {
        return statesCSVData;
    }

    public void sortData(boolean customSort) {
        statesCSVData.setStateCensusMap(sort(statesCSVData.getStateCensusMap(), customSort));
        statesCSVData.setStateCodeMap(sort(statesCSVData.getStateCodeMap(), customSort));
    }

    public void sortData() {
        sortData(false);
    }

    private <K extends Comparable, V extends Comparable> Map<K, V> sort(Map<K, V> map, boolean customSort) {
        if (customSort) {
            Map<V, K> keyMap = new TreeMap<>((o1, o2) -> {
                return o1.compareTo(o2);
            });
            for (K key : map.keySet()) {
                keyMap.put(map.get(key), key);
            }
            Map<K, V> customSortedMap = new TreeMap<>((o1, o2) -> -1);
            for (V key : keyMap.keySet()) {
                customSortedMap.put(keyMap.get(key), key);
            }
            return customSortedMap;
        }
        return new TreeMap<>(map);
    }
}

