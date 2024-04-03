package org.senechka.other;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;
public class SIN {

    public double calculate(double x, double epsilon) {
        double pos_inf = Double.POSITIVE_INFINITY;
        double neg_inf = Double.NEGATIVE_INFINITY;
        double nan = Double.NaN;
        if (pos_inf == x || neg_inf == x) {
            return nan;
        }
        double fact = 1, result_l = 1, result = 0, xx, pow;
        int sign = 1, cnt = 1;

        if (x >= 0) {
            while (x > Math.PI * 2) {
                x -= Math.PI * 2;
            }
        } else if (x < 0) {
            while (x < Math.PI * 2) {
                x += Math.PI * 2;
            }
        }
        xx = x * x;
        pow = x;

        while (Math.abs(result_l - result) > epsilon) {
            fact /= cnt;
            result_l = result;
            result += sign * pow * fact;
            sign = -sign;
            fact /= (cnt + 1);
            pow *= xx;
            cnt += 2;
        }
        if (Math.abs(result) > 1) return nan;
        if (Math.abs(result) < epsilon) return 0;
        return result;
    }

    public double writeResultToCSV(double x, double epsilon, Writer out) {
        double res = calculate(x, epsilon);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {
            printer.printRecord(x, res);
        } catch (IOException e) {
            System.out.println("Wrong filename");
        }
        return res;
    }
}
