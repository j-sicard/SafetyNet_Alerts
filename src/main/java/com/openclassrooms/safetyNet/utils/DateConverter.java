package com.openclassrooms.safetyNet.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DateConverter {

    public static LocalDate convertStringToDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return LocalDate.parse(dateString, formatter);
    }

    public static List<LocalDate> convertStringListToDateList(List<String> dateStrings) {
        List<LocalDate> dateList = new ArrayList<>();

        for (String dateString : dateStrings) {
            LocalDate date = convertStringToDate(dateString);
            if (date != null) {
                dateList.add(date);
            }
        }
        System.out.println(dateList);
        return dateList;
    }

    public static int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

    public static void calculateAndPrintAges(List<String> dateStrings) {
        List<LocalDate> dateList = DateConverter.convertStringListToDateList(dateStrings);

        for (LocalDate date : dateList) {
            int age = calculateAge(date);
            System.out.println("Âge : " + age + " ans");
        }
    }


}
