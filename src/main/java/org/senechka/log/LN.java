package org.senechka.log;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.math3.util.Precision;
import org.senechka.exceptions.CorruptetFilenameException;
import org.senechka.other.SIN;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    public void writeResultToCSV( final String filename, final Double from, final Double to, final Double step, final Double epsilon) throws IOException {
        final Path path = Paths.get(filename);
        final File file = new File(path.toUri());
        final LN ln = new LN();
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        final PrintWriter printWriter = new PrintWriter(file);
        for (Double current = from; current.compareTo(to) <= 0; current = current+step) {
            current = Precision.round(current, 2);
            printWriter.println(current + "," + ln.calculate(current, epsilon));
        }
        printWriter.close();
    }
}
