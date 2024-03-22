package org.senechka.task1;

import static java.lang.Double.NaN;

public class Arcsin {
    public static double calculate(double x, int terms) {
        if (Math.abs(x) > 1) {
            // Обработка значений, для которых обратный синус не существует
            return NaN;
        }
        double arcsinValue = 0.0;
        for (int n = 0; n < terms; n++) {
            // Коэффициент перед n-м членом ряда
            double coefficient = factorial(2 * n) / (Math.pow(4, n) * Math.pow(factorial(n), 2) * (2 * n + 1));
            // Вычисление n-го члена ряда
            double term = coefficient * power(x, 2 * n + 1);
            // Добавление члена ряда к общему значению обратного синуса
            arcsinValue += term;
        }
        return arcsinValue;
    }
    public static int factorial(int n) {
        if (n == 0)
            return 1;
        else
            return n * factorial(n - 1);
    }

    // Функция для вычисления степени
    public static double power(double base, int exponent) {
        double result = 1.0;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }

    // Функция для вычисления синуса через ряд Тейлора

}


