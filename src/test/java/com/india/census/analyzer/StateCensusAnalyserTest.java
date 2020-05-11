package com.india.census.analyzer;

import com.opencsv.exceptions.CsvRuntimeException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class StateCensusAnalyserTest {
    @Test
    public void check_number_records_loaded() throws IOException {
            StateCensusAnalyser analyser = new StateCensusAnalyser("./stateCensusData.csv", "./stateCode.csv");
            Assert.assertEquals(29, analyser.getStateCensusMap().size());
            Assert.assertEquals(37, analyser.getStateCodeMap().size());
    }

    @Test
    public void for_wrong_file_name_should_throw_exception() {
        try {
            new StateCensusAnalyser("./stateCensuata.csv","./stateCode.csv");
        } catch (Exception e) {
            Assert.assertEquals(NoSuchFileException.class, e.getClass());
        }
    }

    @Test
    public void for_wrong_file_extension_should_throw_exception() {
        try {
            new StateCensusAnalyser("./stateCensusData.cv","./stateCode.csv");
        } catch (Exception e) {
            Assert.assertEquals(NoSuchFileException.class, e.getClass());
        }
    }

    @Test
    public void for_wrong_delimiter_should_throw_exception() {
        try {
            new StateCensusAnalyser("./stateCensusData.csv","./stateCode.csv");
        } catch (Exception e) {
            Assert.assertEquals(CsvRuntimeException.class, e.getClass());
            Assert.assertEquals("Number of data fields does not match number of headers.", e.getLocalizedMessage());
        }
    }

    @Test
    public void for_wrong_header_should_throw_exception() {
        try {
            new StateCensusAnalyser("./stateCensusData.csv","./stateCode.csv");
        } catch (Exception e) {
            Assert.assertEquals(CsvRuntimeException.class, e.getClass());
            Assert.assertEquals("Error capturing CSV header!", e.getLocalizedMessage());
        }
    }
}
