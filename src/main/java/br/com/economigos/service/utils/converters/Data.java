package br.com.economigos.service.utils.converters;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Data {
    public static LocalDateTime converterDateTime(String stringData){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        formatter = formatter.withLocale(Locale.ENGLISH);

        return LocalDateTime.parse(stringData, formatter);
    }
    public static LocalDate converterDate(String stringData){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale(Locale.ENGLISH);

        return LocalDate.parse(stringData, formatter);
    }
}