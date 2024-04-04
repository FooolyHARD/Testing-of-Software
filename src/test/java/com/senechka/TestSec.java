package com.senechka;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.senechka.other.COS;
import org.senechka.other.SEC;
import org.senechka.other.SIN;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TestSec {
    public  List<List<String>> parseMocksCos() {
        List<List<String>> recordsCos = new ArrayList<>();
        try (
                BufferedReader br = new BufferedReader(new FileReader("/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/COS_values.csv"))) {
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
    public  List<List<String>> parseMocksSin() {
        List<List<String>> recordsSin = new ArrayList<>();
        try (
                BufferedReader br = new BufferedReader(new FileReader("/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/SIN_values.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                recordsSin.add(Arrays.asList(values));

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return recordsSin;
    }

    ArrayList<Double> setMock(double value) {
        ArrayList<Double> res = new ArrayList<>();
        int index = 0;
        double curr = 0;
        for (int i = 0; i < 22; i++){
            curr = Double.parseDouble(parseMocksSin().get(i).get(0));
            if (curr == value){
                index = i;
            }
        }

        double mockValueSin = Double.parseDouble(parseMocksSin().get(index).get(1));
        double mockValueCos = Double.parseDouble(parseMocksCos().get(index).get(1));
        res.add(mockValueCos);
        res.add(mockValueSin);

        return res;
    }
    @Nested
    class testWithMock{

        COS cosMock;
        SIN sinMock;


        SEC sec;
        @BeforeEach

        public void setUp(){
            sinMock = Mockito.mock(SIN.class);
            cosMock = Mockito.mock(COS.class);

            List<List<String>> mockSinValues = parseMocksSin();
            List<List<String>> mockCosValues = parseMocksCos();

            for (int i = 0; i < mockSinValues.size(); i++) {
                when(sinMock.calculate(Double.parseDouble(mockSinValues.get(i).get(0)), 0.00001)).thenReturn(Double.parseDouble(mockSinValues.get(i).get(1)));
                when(cosMock.calculate(Double.parseDouble(mockCosValues.get(i).get(0)), 0.00001)).thenReturn(Double.parseDouble(mockCosValues.get(i).get(1)));
            }
        }

        @ParameterizedTest
        @CsvFileSource(
                files = "/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/SEC_values.csv")
        void checkCsvFileSource(double number, double expected) {
            COS cos = new COS(sinMock);
            SEC sec = new SEC(cosMock);
            assertEquals(sec.calculate(number, 0.00001), expected);
        }
    }
}
