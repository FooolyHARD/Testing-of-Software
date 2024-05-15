package com.senechka;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.senechka.log.LN;
import org.senechka.log.LOG;
import org.senechka.other.COS;
import org.senechka.other.SIN;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class TestLog {

    public  List<List<String>> parseMocksLn() {
        List<List<String>> recordsCos = new ArrayList<>();
        try (
                BufferedReader br = new BufferedReader(new FileReader("/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/LN_values.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                recordsCos.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return recordsCos;
    }
    LN lnMock;
    @BeforeEach
    void setUp() {
        lnMock = Mockito.mock(LN.class);

        List<List<String>> mocklnValues = parseMocksLn();

        for (List<String> mocklnValue : mocklnValues) {
            when(lnMock.calculate(eq(Double.parseDouble(mocklnValue.get(0))), anyDouble())).thenReturn(Double.parseDouble(mocklnValue.get(1)));
        }
    }

    @ParameterizedTest
    @CsvFileSource(
            files = "/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/LOG2_values.csv")
    void checkCsvFileSource2(double number, double expected) {
        LOG log = new LOG(lnMock);
        assertEquals(expected, log.calculate(2.0, number, 0.000001));
    }
    @ParameterizedTest
    @CsvFileSource(
            files = "/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/LOG3_values.csv")
    void checkCsvFileSource3(double number, double expected) {
        LOG log = new LOG(lnMock);
        assertEquals(expected, log.calculate(3, number, 0.00001));
    }
    @ParameterizedTest
    @CsvFileSource(
            files = "/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/LOG5_values.csv")
    void checkCsvFileSource5(double number, double expected) throws IOException {

        LOG log = new LOG(lnMock);
        when(lnMock.calculate(0.2,0.0000001)).thenReturn(-1.6094369871783438);
        assertEquals(expected, log.calculate(5, number, 0.000001));
    }
}

