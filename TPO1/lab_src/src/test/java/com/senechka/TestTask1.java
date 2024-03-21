package com.senechka;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.senechka.task1.Arcsin;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static java.lang.Math.PI;
public class TestTask1 {

    @ParameterizedTest(name = "arcsin({0})")
    @DisplayName("TestTask1")
    @ValueSource(doubles = {-2 * PI, -PI, -0.5 * PI, 0, 0.5 * PI, PI, 1.5 * PI, 2 * PI})
    void checkPoints(double param) {
        assertAll(
                () -> assertEquals(Math.asin(param), Arcsin.calculate(param,  15), 0.001)
        );
    }
}
