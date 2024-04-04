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
public class SEC {

    private final COS cos;

    public SEC(COS cos) {
        this.cos = cos;
    }

    public SEC() {
        this.cos = new COS();
    }

    public double calculate(double x, double epsilon) {
        double cosVal = cos.calculate(x, epsilon);
        double nan = Double.NaN;
        if (Double.isNaN(cosVal) || cosVal == 0) return nan;
        return 1 / cosVal;
    }

    public void writeResultToCSV( final String filename, final Double from, final Double to, final Double step, final Double epsilon) throws IOException {
            final Path path = Paths.get(filename);
            final File file = new File(path.toUri());
            final SEC sec = new SEC();
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            final PrintWriter printWriter = new PrintWriter(file);
            for (Double current = from; current.compareTo(to) <= 0; current = current+step) {
                current = Precision.round(current, 2);
                printWriter.println(current + "," + sec.calculate(current, epsilon));
            }
            printWriter.close();
        }
}
