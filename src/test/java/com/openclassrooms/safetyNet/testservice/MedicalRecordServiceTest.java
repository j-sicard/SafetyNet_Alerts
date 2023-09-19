package com.openclassrooms.safetyNet.testservice;

import com.openclassrooms.safetyNet.dto.ResidentInfoDTO;
import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.service.MedicalRecordService;

import org.codehaus.jackson.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class MedicalRecordServiceTest {
    @Autowired
    MedicalRecordService medicalRecordService;

    @Test
    public void testGetPersonBirthDates() throws IOException, ClassNotFoundException, JsonProcessingException {
        ResidentInfoDTO residentInfo = new ResidentInfoDTO("Jacob", "Boyd", "1509 Culver St Culver", "97451");
        assertTrue(medicalRecordService.getPersonBirthDates(Collections.singletonList(residentInfo)).contains("03/06/1989"));
    }

    @Test
    public void testGetPersonsMedicalRecords() throws IOException, ClassNotFoundException, JsonProcessingException {
        List<Person> persons = Arrays.asList(
                new Person("Jacob", "Boyd", "1509 Culver St Culver", "Culver", "97451",
                        "841-874-6512", "jaboyd@email.com")
        );
        System.out.println(medicalRecordService.getPersonsMedicalRecords((List<Person>) persons));
        assertEquals(1, medicalRecordService.getPersonsMedicalRecords((List<Person>) persons).size());
    }
}
