package com.senechka;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;
import org.senechka.Function;
import org.senechka.log.LN;
import org.senechka.log.LOG;
import org.senechka.other.COS;
import org.senechka.other.SEC;
import org.senechka.other.SIN;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFunc {
    @Nested
    class FunctionTest {

        static double functionEps = 0.000001;
        double eps = 0.1;

        static SEC secMock;
        static COS cosMock;
        static SIN sinMock;
        static LN lnMock;
        static LOG logMock;

        static Reader secIn;
        static Reader cosIn;
        static Reader sinIn;
        static Reader lnIn;
        static Reader log2In;
        static Reader log3In;
        static Reader log5In;

        static Reader log10In;


        @BeforeAll
        static void setUp() {
            secMock = Mockito.mock(SEC.class);
            cosMock = Mockito.mock(COS.class);
            sinMock = Mockito.mock(SIN.class);
            lnMock = Mockito.mock(LN.class);
            logMock = Mockito.mock(LOG.class);
            try {
                sinIn = new FileReader("/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/SIN_values.csv");
                cosIn = new FileReader("/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/COS_values.csv");
                secIn = new FileReader("/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/SEC_values.csv");
                lnIn = new FileReader("/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/LN_values.csv");
                log2In = new FileReader("/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/LOG2_values.csv");
                log3In = new FileReader("/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/LOG3_values.csv");
                log5In = new FileReader("/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/LOG5_values.csv");
                log10In = new FileReader("/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/LOG10_values.csv");

                Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(secIn);
                for (CSVRecord record : records) {
                    Mockito.when(secMock.calculate(Mockito.eq(Double.parseDouble(record.get(0))), Mockito.anyDouble())).thenReturn(Double.valueOf(record.get(1)));
                }
                records = CSVFormat.DEFAULT.parse(cosIn);
                for (CSVRecord record : records) {
                    Mockito.when(cosMock.calculate(Mockito.eq(Double.parseDouble(record.get(0))), Mockito.anyDouble())).thenReturn(Double.valueOf(record.get(1)));
                }
                records = CSVFormat.DEFAULT.parse(sinIn);
                for (CSVRecord record : records) {
                    Mockito.when(sinMock.calculate(Mockito.eq(Double.parseDouble(record.get(0))), Mockito.anyDouble())).thenReturn(Double.valueOf(record.get(1)));
                }
                records = CSVFormat.DEFAULT.parse(lnIn);
                for (CSVRecord record : records) {
                    Mockito.when(lnMock.calculate(Mockito.eq(Double.parseDouble(record.get(0))), Mockito.anyDouble())).thenReturn(Double.valueOf(record.get(1)));
                }
                records = CSVFormat.DEFAULT.parse(log2In);
                for (CSVRecord record : records) {
                    Mockito.when(logMock.calculate(2, Double.parseDouble(record.get(0)), functionEps)).thenReturn(Double.valueOf(record.get(1)));
                }
                records = CSVFormat.DEFAULT.parse(log3In);
                for (CSVRecord record : records) {
                    Mockito.when(logMock.calculate(3, Double.parseDouble(record.get(0)), functionEps)).thenReturn(Double.valueOf(record.get(1)));
                }records = CSVFormat.DEFAULT.parse(log5In);
                for (CSVRecord record : records) {
                    Mockito.when(logMock.calculate(5, Double.parseDouble(record.get(0)), functionEps)).thenReturn(Double.valueOf(record.get(1)));
                }
                records = CSVFormat.DEFAULT.parse(log10In);
                for (CSVRecord record : records) {
                    Mockito.when(logMock.calculate(10, Double.parseDouble(record.get(0)), functionEps)).thenReturn(Double.valueOf(record.get(1)));
                }
            } catch (IOException ex) {
                System.err.println("Ты как в тесте IOE поймал?!");
            }

        }

        @ParameterizedTest
        @CsvFileSource(files = "/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/Func_values.csv")
        void testSystemWithMocks(double value, double expected) {
            Function function = new Function(secMock, lnMock, new LOG(lnMock), sinMock, cosMock);
            assertEquals(expected, function.solve(value, functionEps), eps*100);

        }

        @ParameterizedTest
        @CsvFileSource(files =  "/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/Func_values.csv")
        void testWithSec(double value, double expected) {
            Function function = new Function(new SEC(cosMock), lnMock, logMock, sinMock, cosMock);
            assertEquals(expected, function.solve(value, functionEps), eps*100);
        }

        @ParameterizedTest
        @CsvFileSource(files =  "/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/Func_values.csv")
        void testWithCos(double value, double expected) {
            Function function = new Function(new SEC(new COS(sinMock)), lnMock, logMock, sinMock, cosMock);
            assertEquals(expected, function.solve(value, functionEps), eps * 100);
        }

        @ParameterizedTest
        @CsvFileSource(files =  "/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/Func_values.csv")
        void testWithSin(double value, double expected) {
            Function function = new Function(new SEC(new COS(new SIN())), lnMock, logMock, sinMock, cosMock);
            assertEquals(expected, function.solve(value, functionEps), eps* 100);
        }

        @ParameterizedTest
        @CsvFileSource(files =  "/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/Func_values.csv")
        void testWithLog(double value, double expected) {
            Function function = new Function(secMock, lnMock, new LOG(lnMock), sinMock, cosMock);
            assertEquals(expected, function.solve(value, functionEps), eps* 100);
        }

        @ParameterizedTest
        @CsvFileSource(files =  "/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/Func_values.csv")
        void testWithLn(double value, double expected) {
            Function function = new Function(secMock, new LN(), new LOG(), sinMock, cosMock);
            assertEquals(expected, function.solve(value, functionEps), eps*100);
        }

        @ParameterizedTest
        @CsvFileSource(files =  "/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/Func_values.csv")
        void testWithSinAndLn(double value, double expected) {
            Function function = new Function();
            assertEquals(expected, function.solve(value, functionEps), eps*100);
        }
    }
}
