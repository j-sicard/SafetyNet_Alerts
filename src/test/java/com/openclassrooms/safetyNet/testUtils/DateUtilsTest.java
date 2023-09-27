package com.openclassrooms.safetyNet.testUtils;

import com.openclassrooms.safetyNet.utils.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DateUtilsTest {
    @Test
    public void testCalculateAge() {
        // Définissez la date de naissance que vous souhaitez tester
        LocalDate birthDate = LocalDate.of(1990, Month.JANUARY, 1);

        // Obtenez la date actuelle
        LocalDate currentDate = LocalDate.now();

        // Calculez l'âge attendu en soustrayant la date de naissance de la date actuelle
        int expectedAge = currentDate.getYear() - birthDate.getYear();

        // Appelez la méthode calculateAge avec la date de naissance
        int age = DateUtils.calculateAge(birthDate);

        // Vérifiez que l'âge calculé est correct
        assertEquals(expectedAge, age);
    }

/*    @Test
    public void testConvertStringListToDateList() {
        // Créez une liste de dates au format String
        List<String> stringDates = new ArrayList<>();
        stringDates.add("2023-09-26");
        stringDates.add("2023-09-27");
        stringDates.add("2023-09-28");

        // Créez une liste d'objets LocalDate attendue
        List<LocalDate> expectedDates = new ArrayList<>();
        expectedDates.add(LocalDate.of(2023, 9, 26));
        expectedDates.add(LocalDate.of(2023, 9, 27));
        expectedDates.add(LocalDate.of(2023, 9, 28));

        // Appelez la méthode convertStringListToDateList
        List<LocalDate> convertedDates = DateUtils.convertStringListToDateList(stringDates);

        // Vérifiez que la liste convertie correspond à la liste attendue
        assertEquals(expectedDates, convertedDates);
    }*/
}
