package org.senechka;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

import org.senechka.exceptions.CorruptetFilenameException;
import org.senechka.log.LN;
import org.senechka.log.LOG;
import org.senechka.other.COS;
import org.senechka.other.SEC;
import org.senechka.other.SIN;

public class Function {
    SEC sec;
    LOG log;
    LN ln;
    SIN sin;
    COS cos;

    public Function() {
        this.sec = new SEC();
        this.ln = new LN();
        this.log = new LOG(ln);
        this.sin = new SIN();
        this.cos = new COS();
    }

    public Function(SEC sec, LN ln, LOG log, SIN sin, COS cos) {
        this.sec = sec;
        this.log = log;
        this.ln = ln;
        this.sin = sin;
        this.cos = cos;
    }

    public double solve(double x, double epsilon) {
        if (x <= 0) return ((power(power(((sin.calculate(x, epsilon)
                / sec.calculate(x, epsilon)) * sin.calculate(x, epsilon)), 3),3) -
                power(cos.calculate(x, epsilon), 2)));
        else
            return ((((((log.calculate(2, x, epsilon) - log.calculate(10, x, epsilon)) /
                    log.calculate(3, x, epsilon)) /
                    (log.calculate(5, x, epsilon) + log.calculate(3, x, epsilon))) -
                    log.calculate(3, x, epsilon)) - (((log.calculate(5, x, epsilon) /
                    log.calculate(2, x, epsilon)) - (log.calculate(2, x, epsilon) + log.calculate(2, x, epsilon)))
                    / log.calculate(5, x, epsilon))));
    }

    public double power(double x, int  degree){
        int i = 1;
        double res = x;
        while (i != degree){
            res = res*x;
            degree--;
        }
        return res;
    }

    public double writeResultToCSV(double x, double eps, Writer out) {
        double res = solve(x, eps);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {
            printer.printRecord(x, res);
        } catch (IOException e) {
            throw new CorruptetFilenameException("Corrupted filename");
        }
        return res;
    }
}
