package com.meanmachines.MeanStreamMachine.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatterUtil {
    private static final SimpleDateFormat TS_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    public static Date stringToDate(String dateStr) throws ParseException {
        return TS_FORMAT.parse(dateStr);
    }
    public static String dateToString(Date date){
        return TS_FORMAT.format(date);
    }
}
