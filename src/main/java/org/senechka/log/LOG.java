package org.senechka.log;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.senechka.exceptions.CorruptetFilenameException;

import java.io.IOException;
import java.io.Writer;

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

    public double writeResultToCSV(double a, double x, double epsilon, Writer out) {
        double res = calculate(a, x, epsilon);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {
            printer.printRecord(x, res);
        } catch (IOException e) {
            throw new CorruptetFilenameException("Corrupted filename");
        }
        return res;
    }
}
