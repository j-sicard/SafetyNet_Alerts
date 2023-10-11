package com.openclassrooms.safetyNet.testservice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.openclassrooms.safetyNet.dto.ResidentInfoDTO;
import com.openclassrooms.safetyNet.dto.ResidentInfoMedicalRecordsDTO;
import com.openclassrooms.safetyNet.model.MedicalRecord;
import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.service.MedicalRecordService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MedicalRecordServiceTest {
    @Autowired
    MedicalRecordService medicalRecordService;

    @Autowired
    ObjectMapper objectMapper;

    String jsonFilePath = "./src/test/resources/data.json";

    @Test
    public void testGetPersonBirthDates() throws IOException, ClassNotFoundException {
        ResidentInfoDTO residentInfo = new ResidentInfoDTO("Jacob", "Boyd", "1509 Culver St Culver", "97451");
        assertTrue(medicalRecordService.getPersonBirthDates(Collections.singletonList(residentInfo)).contains("03/06/1989"));
    }

    @Test
    public void testGetPersonsMedicalRecords() throws IOException, ClassNotFoundException {
        List<Person> persons = Arrays.asList(
                new Person("Jacob", "Boyd", "1509 Culver St Culver", "Culver", "97451",
                        "841-874-6512", "jaboyd@email.com")
        );
        assertEquals(1, medicalRecordService.getPersonsMedicalRecords((List<Person>) persons).size());
    }

    @Test
    public void testGetResidentMedicalRecords() throws ClassNotFoundException, IOException {
        List<ResidentInfoMedicalRecordsDTO> result = medicalRecordService.getResidentMedicalRecords( Arrays.asList(
                new Person("Jacob", "Boyd", "1509 Culver St Culver", "Culver", "97451",
                        "841-874-6512", "jaboyd@email.com")));

        ResidentInfoMedicalRecordsDTO resident1 = result.get(0);
        String medicationsString = resident1.getMedications().toString().replaceAll("\\[|\\]|\\s", "");
        assertEquals("pharmacol:5000mg,terazine:10mg,noznazol:250mg", medicationsString);
    }

    @Test
    public void testSaveMedicalRecord() throws IOException, ClassNotFoundException {
        // Créez un enregistrement médical
        MedicalRecord medicalRecord = new MedicalRecord("FirstNameTest", "LastNameTest", "01/01/1980",
                Collections.singletonList("medication"), Collections.singletonList("allergie"));

        medicalRecordService.saveMedicalRecord(medicalRecord, jsonFilePath);

        // Rechargez le fichier JSON pour vérifier les données
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode updatedData = objectMapper.readTree(new File(jsonFilePath));

        // Vérifiez que les données de l'enregistrement ajouté correspondent à ce que vous attendiez
        JsonNode addedRecord = updatedData.get("medicalrecords").get(updatedData.get("medicalrecords").size() - 1);
        assertEquals("FirstNameTest", addedRecord.get("firstName").asText());
        assertEquals("LastNameTest", addedRecord.get("lastName").asText());
        assertEquals("01/01/1980", addedRecord.get("birthdate").asText());
    }

    @Test
    public void testUpDateMedicalRecord() throws IOException, ClassNotFoundException{
        MedicalRecord medicalRecord = new MedicalRecord("FirstNameTest", "LastNameTest", "11/11/1980",
                Collections.singletonList("upDateMedication"), Collections.singletonList("UpDateAllergie"));

        medicalRecordService.updateMedicalRecord("FirstNameTest", "LastNameTest", medicalRecord, jsonFilePath);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode updatedData = objectMapper.readTree(new File(jsonFilePath));

        // Vérifiez que les données de l'enregistrement ajouté correspondent à ce que vous attendiez
        JsonNode addedRecord = updatedData.get("medicalrecords").get(updatedData.get("medicalrecords").size() - 1);
        assertEquals("FirstNameTest", addedRecord.get("firstName").asText());
        assertEquals("LastNameTest", addedRecord.get("lastName").asText());
        assertEquals("11/11/1980", addedRecord.get("birthdate").asText());

        ArrayNode medications = (ArrayNode) addedRecord.get("medications");
        assertEquals(1, medications.size());
        assertEquals("upDateMedication", medications.get(0).asText());

        ArrayNode allergies = (ArrayNode) addedRecord.get("allergies");
        assertEquals(1, allergies.size());
        assertEquals("UpDateAllergie", allergies.get(0).asText());
    }

    @Test
    public void testDeleteMedicalRecord() throws IOException, ClassNotFoundException {
        medicalRecordService.deleteMedicalRecord("FirstNameTest", "LastNameTest", jsonFilePath);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode updatedData = objectMapper.readTree(new File(jsonFilePath));

        ArrayNode medicalRecords = (ArrayNode) updatedData.get("medicalrecords");
        boolean found = false;
        for (JsonNode record : medicalRecords) {
            String firstName = record.get("firstName").asText();
            String lastName = record.get("lastName").asText();
            if (firstName.equals("FirstNameToDelete") && lastName.equals("LastNameToDelete")) {
                found = true;
                break;
            }
        }
        assertFalse(found, "L'enregistrement médical supprimé ne doit pas être présent.");
    }







}
