package com.meanmachines.MeanStreamMachine.util;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class FormatterUtil {
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");

    public static ZonedDateTime stringToDate(String dateStr) throws ParseException {
        return ZonedDateTime.parse(dateStr);
    }
    public static String dateToString(ZonedDateTime date){
        return date.format(formatter);
    }
}
