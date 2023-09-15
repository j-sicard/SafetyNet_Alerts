package com.openclassrooms.safetyNet.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringUtils {

    public static LocalDate toDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return LocalDate.parse(date, formatter);
    }
}

