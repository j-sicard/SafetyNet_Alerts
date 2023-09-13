package com.openclassrooms.safetyNet.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AgesAdministrator {

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
        return dateList;
    }

    public static int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

    public static List<Integer> calculatetAges(List<String> dateStrings) {
        List<LocalDate> dateList = AgesAdministrator.convertStringListToDateList(dateStrings);
        List<Integer> ages = new ArrayList<>();
        for (LocalDate date : dateList) {
            ages.add( calculateAge(date));
        }
       return ages;
    }

    public static HashMap<String, Integer> adultChildrenCount(List<Integer> ages){
        HashMap<String, Integer> adultChildrenCount = new HashMap<>();
        int adult = 0;
        int children = 0;
        for (int age : ages){
            if (age <= 17){
                children++;
            }else {
                adult++;
            }
            adultChildrenCount.put("adult: ", adult);
            adultChildrenCount.put("children: ", children);
        }
        return adultChildrenCount;
    }

}
