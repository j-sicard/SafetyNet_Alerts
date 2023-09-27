package com.openclassrooms.safetyNet.testdto;

import com.openclassrooms.safetyNet.dto.ResidentInfoMedicalRecordsDTO;
import com.openclassrooms.safetyNet.dto.ResidentInfoMedicalRecordsStations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResidentInfoMedicalRecordsStationsDTOTest {
    private ResidentInfoMedicalRecordsStations residentInfoMedicalRecordsStations;

    @BeforeEach
    public void setUp() {
        List<ResidentInfoMedicalRecordsDTO> medicalRecordsList = new ArrayList<>();

        List<String> medications1 = new ArrayList<>();
        medications1.add("Medication1");
        medications1.add("Medication2");
        List<String> allergies1 = new ArrayList<>();
        allergies1.add("Allergy1");
        allergies1.add("Allergy2");

        ResidentInfoMedicalRecordsDTO medicalRecord1 = new ResidentInfoMedicalRecordsDTO("Doe", "555-123-4567", 30, medications1, allergies1);
        medicalRecordsList.add(medicalRecord1);

        List<String> medications2 = new ArrayList<>();
        medications2.add("Medication3");
        medications2.add("Medication4");
        List<String> allergies2 = new ArrayList<>();
        allergies2.add("Allergy3");
        allergies2.add("Allergy4");

        ResidentInfoMedicalRecordsDTO medicalRecord2 = new ResidentInfoMedicalRecordsDTO("Smith", "555-987-6543", 40, medications2, allergies2);
        medicalRecordsList.add(medicalRecord2);

        residentInfoMedicalRecordsStations = new ResidentInfoMedicalRecordsStations(medicalRecordsList, "Station123");
    }

    @Test
    public void testGetResidentInfoMedicalRecords() {
        List<ResidentInfoMedicalRecordsDTO> medicalRecords = residentInfoMedicalRecordsStations.getResidentInfoMedicalRecords();
        assertEquals(2, medicalRecords.size());

        // Test les détails du premier enregistrement médical
        ResidentInfoMedicalRecordsDTO medicalRecord1 = medicalRecords.get(0);
        assertEquals("Doe", medicalRecord1.getLastName());
        assertEquals("555-123-4567", medicalRecord1.getPhone());
        assertEquals(Integer.valueOf(30), medicalRecord1.getAge());
        assertEquals(2, medicalRecord1.getMedications().size());
        assertEquals(2, medicalRecord1.getAllergies().size());

        // Test les détails du deuxième enregistrement médical
        ResidentInfoMedicalRecordsDTO medicalRecord2 = medicalRecords.get(1);
        assertEquals("Smith", medicalRecord2.getLastName());
        assertEquals("555-987-6543", medicalRecord2.getPhone());
        assertEquals(Integer.valueOf(40), medicalRecord2.getAge());
        assertEquals(2, medicalRecord2.getMedications().size());
        assertEquals(2, medicalRecord2.getAllergies().size());
    }

    @Test
    public void testSetResidentInfoMedicalRecords() {
        List<ResidentInfoMedicalRecordsDTO> newMedicalRecordsList = new ArrayList<>();
        ResidentInfoMedicalRecordsDTO newMedicalRecord = new ResidentInfoMedicalRecordsDTO("Johnson", "555-555-5555", 50, new ArrayList<>(), new ArrayList<>());
        newMedicalRecordsList.add(newMedicalRecord);

        residentInfoMedicalRecordsStations.setResidentInfoMedicalRecords(newMedicalRecordsList);

        List<ResidentInfoMedicalRecordsDTO> medicalRecords = residentInfoMedicalRecordsStations.getResidentInfoMedicalRecords();
        assertEquals(1, medicalRecords.size());
        assertEquals("Johnson", medicalRecords.get(0).getLastName());
    }

    @Test
    public void testGetStationNumbers() {
        assertEquals("Station123", residentInfoMedicalRecordsStations.getStationNumbers());
    }

    @Test
    public void testSetStationNumbers() {
        residentInfoMedicalRecordsStations.setStationNumbers("Station456");
        assertEquals("Station456", residentInfoMedicalRecordsStations.getStationNumbers());
    }

    @Test
    public void testToString() {
        String expectedString = "ResidentInfoMedicalRecordsStations{" +
                "residentInfoMedicalRecords=" + residentInfoMedicalRecordsStations.getResidentInfoMedicalRecords() +
                ", stationNumbers='Station123'" +
                '}';
        assertEquals(expectedString, residentInfoMedicalRecordsStations.toString());
    }
}

