package org.senechka.other;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.math3.util.Precision;
import org.senechka.exceptions.CorruptetFilenameException;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    public void writeResultToCSV( final String filename, final Double from, final Double to, final Double step, final Double epsilon) throws IOException {
        final Path path = Paths.get(filename);
        final File file = new File(path.toUri());
        final SIN sin = new SIN();
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        final PrintWriter printWriter = new PrintWriter(file);
        for (Double current = from; current.compareTo(to) <= 0; current = current+step) {
            current = Precision.round(current, 2);
            printWriter.println(current + "," + sin.calculate(current, epsilon));
        }
        printWriter.close();
    }
}
