package com.senechka;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.senechka.other.SIN;
import static org.junit.jupiter.api.Assertions.*;

public class TestSin {

    private final SIN sin = new SIN();

    @Test
    void testCalculateInRange() {
        double result = sin.calculate(Math.PI / 4, 0.001);
        assertEquals(0.707, result, 0.001);
    }

    @Test
    void testCalculateNegativeX() {
        double result = sin.calculate(-Math.PI / 4, 0.001);
        assertEquals(-0.707, result, 0.001);
    }

    @Test
    void testCalculatePositiveInfinity() {
        double result = sin.calculate(Double.POSITIVE_INFINITY, 0.001);
        assertTrue(Double.isNaN(result));
    }

    @Test
    void testCalculateNegativeInfinity() {
        double result = sin.calculate(Double.NEGATIVE_INFINITY, 0.001);
        assertTrue(Double.isNaN(result));
    }

    @Test
    void testCalculateNaN() {
        double result = sin.calculate(Double.NaN, 0.001);
        assertTrue(Double.isNaN(result));
    }

    @Test
    void testCalculateZeroEpsilon() {
        double result = sin.calculate(Math.PI / 4, 0);
        assertEquals(0.707, result, 0.001);
    }

    @Test
    void testCalculatePositiveEpsilon() {
        double result = sin.calculate(Math.PI / 4, 0.1);
        assertEquals(0.707, result, 0.1);
    }

    @ParameterizedTest
    @CsvFileSource(
            files = "/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/SIN_values.csv")
    void checkCsvFileSource(double number, double expected) {
        assertEquals(expected, sin.calculate(number, 0.000001) );
    }
}
