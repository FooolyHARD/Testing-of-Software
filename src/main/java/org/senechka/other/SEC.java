package org.senechka.other;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

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

    public double sec(double x, double epsilon) {
        double cosVal = cos.calculate(x, epsilon);
        double nan = Double.NaN;
        if (Double.isNaN(cosVal) || cosVal == 0) return nan;
        return 1 / cosVal;
    }

    public double writeResultToCSV(double x, double epsilon, Writer out) {
        double res = sec(x, epsilon);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {
            printer.printRecord(x, res);
        } catch (IOException e) {
            System.out.println("Corrupted filename");
        }
        return res;
    }
}
