package br.com.vitor.usercrud.utils;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import br.com.vitor.usercrud.model.Gasto;
import br.com.vitor.usercrud.model.Renda;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;


public class CsvHelper {

    public static ByteArrayInputStream rendasToCSV(List<Renda> rendas) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for (Renda renda : rendas) {
                List<? extends Serializable> data = Arrays.asList(
                        renda.getValor(),
                        renda.getDescricao());

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream gastosToCSV(List<Gasto> gastos) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for (Gasto gasto : gastos) {
                List<? extends Serializable> data = Arrays.asList(
                        gasto.getValor(),
                        gasto.getDescricao());

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }

}
