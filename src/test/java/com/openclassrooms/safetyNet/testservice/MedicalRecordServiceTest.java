package com.openclassrooms.safetyNet.testservice;

import com.openclassrooms.safetyNet.dto.PersonMedicalRecordDTO;
import com.openclassrooms.safetyNet.dto.ResidentInfoDTO;

import com.openclassrooms.safetyNet.model.Person;

import com.openclassrooms.safetyNet.service.MedicalRecordService;
import com.openclassrooms.safetyNet.service.impl.MedicalRecordServiceImpl;
import org.codehaus.jackson.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MedicalRecordServiceTest {

    MedicalRecordService medicalRecordService = new MedicalRecordServiceImpl();
    @Test
    public void testGetPersonBirthDates() throws IOException, ClassNotFoundException, JsonProcessingException {
        ResidentInfoDTO residentInfo = new ResidentInfoDTO("Jacob", "Boyd", "1509 Culver St Culver", "97451");
        List<String> result = medicalRecordService.getPersonBirthDates(Collections.singletonList(residentInfo));

        assertTrue(result.contains("03/06/1989"));
    }


    @Test
    public void testGetPersonsMedicalRecords() throws IOException, ClassNotFoundException, JsonProcessingException {

        List<Person> persons = Arrays.asList(
                new Person("Jacob", "Boyd", "1509 Culver St Culver", "Culver", "97451",
                        "841-874-6512", "jaboyd@email.com")
        );
        MedicalRecordService medicalRecordService = new MedicalRecordServiceImpl();
        List<PersonMedicalRecordDTO> result = medicalRecordService.getPersonsMedicalRecords((List<Person>) persons);

        System.out.println(result);
        assertEquals(1, result.size());
    }

}
