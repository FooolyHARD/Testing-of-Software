package com.senechka;


import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.junit.jupiter.api.extension.ExtendWith;
import org.senechka.other.COS;
import org.senechka.other.SIN;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

public class TestCos {
    @Nested
    class testWithMock {
        SIN sinMock = Mockito.mock(SIN.class);
        COS cos = new COS(sinMock);
        @BeforeEach
        void setUp() {

            when(sinMock.calculate(-1.1, 0.00001)).thenReturn(-0.8912073043284693);
            when(sinMock.calculate(-1.0, 0.00001)).thenReturn(-0.8414709903007478);
            when(sinMock.calculate(-0.9, 0.00001)).thenReturn(-0.7833269177114994);
            when(sinMock.calculate(-0.8, 0.00001)).thenReturn(-0.7173561027556451);
            when(sinMock.calculate(-0.7, 0.00001)).thenReturn(-0.6442177045741119);
            when(sinMock.calculate(-0.6, 0.00001)).thenReturn(-0.564642498656174);
            when(sinMock.calculate(-0.5, 0.00001)).thenReturn(-0.47942557530646146);
            when(sinMock.calculate(-0.4, 0.00001)).thenReturn(-0.38941839546148715);
            when(sinMock.calculate(-0.3, 0.00001)).thenReturn(-0.29552020129380757);
            when(sinMock.calculate(-0.2, 0.00001)).thenReturn(-0.1986693229438234);
            when(sinMock.calculate(-0.1, 0.00001)).thenReturn(-0.0998334051888718);
            when(sinMock.calculate(0.0, 0.00001)).thenReturn(0.0);
            when(sinMock.calculate(0.1, 0.00001)).thenReturn(0.09983341666666667);
            when(sinMock.calculate(0.2, 0.00001)).thenReturn(0.19866933079365082);
            when(sinMock.calculate(0.3, 0.00001)).thenReturn(0.29552020660714284);
            when(sinMock.calculate(0.4, 0.00001)).thenReturn(0.3894183415873016);
            when(sinMock.calculate(0.5, 0.00001)).thenReturn(0.4794255386164159);
            when(sinMock.calculate(0.6, 0.00001)).thenReturn(0.5646424734857143);
            when(sinMock.calculate(0.7, 0.00001)).thenReturn(0.6442176877315008);
            when(sinMock.calculate(0.8, 0.00001)).thenReturn(0.7173560930426808);
            when(sinMock.calculate(0.9, 0.00001)).thenReturn(0.7833269095868205);
            when(sinMock.calculate(1.0, 0.00001)).thenReturn(0.841470984648068);
            when(sinMock.calculate(1.1, 0.00001)).thenReturn(0.8912073595102139);
            when(sinMock.calculate(Math.PI / 4, 0.00001)).thenReturn(0.8912073595102139);
            when(sinMock.calculate(-Math.PI / 4, 0.00001)).thenReturn(0.8912073595102139);
            when(sinMock.calculate(Double.POSITIVE_INFINITY, 0.00001)).thenReturn(Double.NaN);
            when(sinMock.calculate(Double.NEGATIVE_INFINITY, 0.00001)).thenReturn(Double.NaN);
            when(sinMock.calculate(Double.POSITIVE_INFINITY, 0.001)).thenReturn(Double.NaN);
            when(sinMock.calculate(Double.NEGATIVE_INFINITY, 0.001)).thenReturn(Double.NaN);
            when(sinMock.calculate(Double.NaN, 0.00001)).thenReturn(Double.NaN);
            when(sinMock.calculate(Double.NaN, 0.001)).thenReturn(Double.NaN);
        }

        @Test
        void calculate_ValidInput_ReturnsCorrectResult() {
            SIN sinMock = Mockito.mock(SIN.class);
            when(sinMock.calculate(anyDouble(), anyDouble())).thenReturn(0.5);

            COS cos = new COS(sinMock);
            double result = cos.calculate(0, 0.00001);
            assertEquals(0.8660254037844386, result, 0.00001);
        }

        @Test
        @DisplayName("Range test")
        void testCalculateInRange() {
            double result = cos.calculate(Math.PI / 4, 0.001);
            assertEquals(1, result, 0.001);
        }

        @Test
        void testCalculateNegativeX() {
            double result = cos.calculate(-Math.PI / 4, 0.001);
            assertEquals(1, result, 0.001);
        }

        @Test
        void testCalculatePositiveInfinity() {
            double result = cos.calculate(Double.POSITIVE_INFINITY, 0.001);
            assertTrue(Double.isNaN(result));
        }

        @Test
        void testCalculateNegativeInfinity() {
            double result = cos.calculate(Double.NEGATIVE_INFINITY, 0.001);
            assertTrue(Double.isNaN(result));
        }

        @Test
        void testCalculateNaN() {
            double result = cos.calculate(Double.NaN, 0.001);
            assertTrue(Double.isNaN(result));
        }

        @Test
        void testCalculateZeroEpsilon() {
            double result = cos.calculate(Math.PI / 4, 0);
            assertEquals(1, result, 0.001);
        }

        @Test
        void testCalculatePositiveEpsilon() {
            double result = cos.calculate(Math.PI / 4, 0.1);
            assertEquals(1, result, 0.1);
        }

        @ParameterizedTest
        @CsvFileSource(
                files = "/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/COS_values.csv")
        void checkCsvFileSource(double number, double expected) {

            assertEquals(cos.calculate(number, 0.00001), expected);
        }
    }
}
