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

public class LOG {
    private final LN ln;

    public LOG(LN ln) {
        this.ln = ln;
    }

    public LOG() {
        this.ln = new LN();
    }

    public double calculate(double a, double b, double epsilon) {
        return ln.calculate(b, epsilon) / ln.calculate(a, epsilon);
    }

    public void writeResultToCSV( final String filename, final Double from, final Double to, final Double step, final Double epsilon, double degree) throws IOException {
        final Path path = Paths.get(filename);
        final File file = new File(path.toUri());
        final LOG log = new LOG();
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        final PrintWriter printWriter = new PrintWriter(file);
        for (Double current = from; current.compareTo(to) <= 0; current = current+step) {
            current = Precision.round(current, 2);
            printWriter.println(current + "," + log.calculate(degree, current, epsilon ));
        }
        printWriter.close();
    }
}
