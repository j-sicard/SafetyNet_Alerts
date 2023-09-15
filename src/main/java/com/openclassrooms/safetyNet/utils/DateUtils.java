package com.openclassrooms.safetyNet.utils;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class DateUtils {

    public static int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

    public static List<LocalDate> convertStringListToDateList(List<String> dates) {
        List<LocalDate> dateList = new ArrayList<>();
        for (String date : dates) {
            LocalDate localDate = StringUtils.toDate(date);
            if (date != null) {
                dateList.add(localDate);
            }
        }
        return dateList;
    }

    public static List<Integer> getAges(List<String> date) {
        List<LocalDate> dateList = DateUtils.convertStringListToDateList(date);
        List<Integer> ages = new ArrayList<>();
        for (LocalDate localDate : dateList) {
            ages.add(calculateAge(localDate));
        }
        return ages;
    }

    public static int getAge(String birthdate) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(StringUtils.toDate(birthdate), currentDate).getYears();
    }

}
