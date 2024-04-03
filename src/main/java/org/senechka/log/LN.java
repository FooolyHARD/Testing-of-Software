package org.senechka.log;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class LN {
    double pos_inf = Double.POSITIVE_INFINITY;
    double neg_inf = Double.NEGATIVE_INFINITY;
    double nan = Double.NaN;
    public double calculate(double x, double eps) {
        if (Double.isNaN(x) || x < (double) 0) {
            return nan;
        } else if (x == pos_inf) {
            return pos_inf;
        } else if (x == 0.0) {
            return neg_inf;
        }

        double constant = ((x - 1) * (x - 1)) / ((x + 1) * (x + 1));

        double sum = 0;
        double currentValue = (x - 1) / (x + 1);
        int step = 1;
        while (Math.abs(currentValue) > eps / 2) {
            sum += currentValue;
            currentValue = (2 * step - 1) * currentValue * constant / (2 * step + 1);
            step++;
        }
        sum *= 2;
        return sum;
    }

    public double writeResultToCSV(double x, double eps, Writer out) {
        double res = calculate(x, eps);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {
            printer.printRecord(x, res);
        } catch (IOException e) {
            System.out.println("Corrupted filename");
        }
        return res;
    }
}
