package com.senechka;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.senechka.log.LN;

import static org.junit.jupiter.api.Assertions.*;

public class TestLn {
    @Test
    void calculate_ValidInput_ReturnsCorrectResult() {
        LN ln = new LN();
        double result = ln.calculate(2.0, 0.00001);
        assertEquals(0.69314718056, result, 0.00001);
    }

    @Test
    void calculate_NaNInput_ReturnsNaN() {
        LN ln = new LN();
        double result = ln.calculate(Double.NaN, 0.00001);
        assertTrue(Double.isNaN(result));
    }

    @Test
    void calculate_PositiveInfinityInput_ReturnsPositiveInfinity() {
        LN ln = new LN();
        double result = ln.calculate(Double.POSITIVE_INFINITY, 0.00001);
        assertEquals(Double.POSITIVE_INFINITY, result);
    }

    @Test
    void calculate_ZeroInput_ReturnsNegativeInfinity() {
        LN ln = new LN();
        double result = ln.calculate(0.0, 0.00001);
        assertEquals(Double.NEGATIVE_INFINITY, result);
    }

    @ParameterizedTest
    @CsvFileSource(
            files = "/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/LN_values.csv")
    void checkCsvFileSource(double number, double expected) {
        LN ln = new LN();
        assertEquals(expected, ln.calculate(number, 0.000001) );
    }
}
