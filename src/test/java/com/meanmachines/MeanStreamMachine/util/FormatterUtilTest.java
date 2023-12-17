package com.meanmachines.MeanStreamMachine.util;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FormatterUtilTest {
    ZonedDateTime dateTime = ZonedDateTime.of(2023, 2,15,22,35,15,0, ZoneId.of("-5"));
    String dateStr = "2023-02-15T22:35:15-05:00";
    @Test
    void stringToDate() throws ParseException {
        assertEquals(dateTime,FormatterUtil.stringToDate(dateStr));
    }

    @Test
    void dateToString() {
;
        assertEquals(dateStr, FormatterUtil.dateToString(dateTime));
    }
}