package org.senechka.other;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.senechka.exceptions.CorruptetFilenameException;

import java.io.IOException;
import java.io.Writer;

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
