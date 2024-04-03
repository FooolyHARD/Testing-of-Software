package org.senechka.other;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.senechka.exceptions.CorruptetFilenameException;

import java.io.IOException;
import java.io.Writer;

public class COS {

    double pos_inf = Double.POSITIVE_INFINITY;
    double neg_inf = Double.NEGATIVE_INFINITY;
    double nan = Double.NaN;

    private final SIN sin;

    public COS(SIN sin) {
        this.sin = sin;
    }

    public COS() {
        this.sin = new SIN();
    }

    public double calculate(double x, double epsilon) {
        double x_init = x;
        double pi = Math.PI;
        x %= pi * 2;
        if (pos_inf == x || neg_inf == x) {
            return nan;
        }
        if (x < -pi) {
            while (x < -pi) x += 2 * pi;
        }
        if (x > pi) {
            while (x > pi) x -= 2 * pi;
        }
        double result;
        if (x > pi / 2 || x < -pi / 2) {
            result = -1 * Math.sqrt(1 - sin.calculate(x_init, epsilon) * sin.calculate(x_init, epsilon));
        } else result = Math.sqrt(1 - sin.calculate(x_init, epsilon) * sin.calculate(x_init, epsilon));
        if (Math.abs(result) > 1) return nan;
        if (Math.abs(result) <= epsilon) return 0;
        return result;
    }

    public double writeResultToCSV(double x, double epsilon, Writer out) {
        double res = calculate(x, epsilon);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {
            printer.printRecord(x, res);
        } catch (IOException e) {
            throw new CorruptetFilenameException("Corrupted filename");
        }
        return res;
    }
}
