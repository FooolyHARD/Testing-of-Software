package com.senechka;

import org.junit.jupiter.api.Test;
import org.senechka.other.SIN;
import static org.junit.jupiter.api.Assertions.*;

public class TestSin {
    @Test
    void testCalculateInRange() {
        SIN sin = new SIN();
        double result = sin.calculate(Math.PI / 4, 0.001);
        assertEquals(0.707, result, 0.001);
    }

    @Test
    void testCalculateNegativeX() {
        SIN sin = new SIN();
        double result = sin.calculate(-Math.PI / 4, 0.001);
        assertEquals(-0.707, result, 0.001);
    }

    @Test
    void testCalculatePositiveInfinity() {
        SIN sin = new SIN();
        double result = sin.calculate(Double.POSITIVE_INFINITY, 0.001);
        assertTrue(Double.isNaN(result));
    }

    @Test
    void testCalculateNegativeInfinity() {
        SIN sin = new SIN();
        double result = sin.calculate(Double.NEGATIVE_INFINITY, 0.001);
        assertTrue(Double.isNaN(result));
    }

    @Test
    void testCalculateNaN() {
        SIN sin = new SIN();
        double result = sin.calculate(Double.NaN, 0.001);
        assertTrue(Double.isNaN(result));
    }

    @Test
    void testCalculateZeroEpsilon() {
        SIN sin = new SIN();
        double result = sin.calculate(Math.PI / 4, 0);
        assertEquals(0.707, result, 0.001);
    }

    @Test
    void testCalculatePositiveEpsilon() {
        SIN sin = new SIN();
        double result = sin.calculate(Math.PI / 4, 0.1);
        assertEquals(0.707, result, 0.1);
    }
}
