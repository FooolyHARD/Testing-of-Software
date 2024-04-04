package org.senechka;

import org.senechka.log.LN;
import org.senechka.log.LOG;
import org.senechka.other.SEC;
import org.senechka.other.SIN;
import org.senechka.other.COS;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Filler {
    public static void main(String[] args) throws IOException {
        final double START_X = -1.1;
        final double END_X = 1.1;
        final double STEP = 0.1;

        final String FILE_PATH_SIN = "/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/SIN_values.csv";
        final String FILE_PATH_COS = "/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/COS_values.csv";
        final String FILE_PATH_SEC = "/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/SEC_values.csv";
        final String FILE_PATH_LN = "/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/LN_values.csv";
        final String FILE_PATH_LOG2 = "/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/LOG2_values.csv";
        final String FILE_PATH_LOG3 = "/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/LOG3_values.csv";
        final String FILE_PATH_LOG5 = "/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/LOG5_values.csv";
        final String FILE_PATH_Func = "/Users/arsenykonovalov/programming_itmo/tpo/lab2/src/main/resources/csv/Func_values.csv";

        final SIN sin = new SIN();
        final COS cos = new COS();
        final SEC sec = new SEC();
        final LN ln = new LN();
        final LOG log = new LOG();
        final Function function = new Function();

        sin.writeResultToCSV(FILE_PATH_SIN, START_X, END_X, STEP, 0.000001);
        cos.writeResultToCSV(FILE_PATH_COS, START_X, END_X, STEP, 0.000001);
        sec.writeResultToCSV(FILE_PATH_SEC, START_X, END_X, STEP, 0.000001);
        ln.writeResultToCSV(FILE_PATH_LN, START_X, END_X, STEP, 0.000001);
        function.writeResultToCSV(FILE_PATH_Func, START_X, END_X, STEP, 0.000001);
        log.writeResultToCSV(FILE_PATH_LOG2, START_X, END_X, STEP, 0.000001, 2.0);
        log.writeResultToCSV(FILE_PATH_LOG3, START_X, END_X, STEP, 0.000001, 3.0);
        log.writeResultToCSV(FILE_PATH_LOG5, START_X, END_X, STEP, 0.000001, 5.0);
    }
}
