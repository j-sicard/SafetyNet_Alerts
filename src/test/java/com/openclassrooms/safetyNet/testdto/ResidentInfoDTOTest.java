package com.openclassrooms.safetyNet.testdto;

import com.openclassrooms.safetyNet.dto.ResidentInfoDTO;
import com.openclassrooms.safetyNet.dto.ResidentInfoMedicalRecordsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ResidentInfoDTOTest {
    private ResidentInfoMedicalRecordsDTO residentInfoMedicalRecords;

    @BeforeEach
    public void setUp() {
        List<String> medications = new ArrayList<>();
        medications.add("Medication1");
        medications.add("Medication2");

        List<String> allergies = new ArrayList<>();
        allergies.add("Allergy1");
        allergies.add("Allergy2");

        residentInfoMedicalRecords = new ResidentInfoMedicalRecordsDTO("Doe", "555-123-4567", 30, medications, allergies);
    }

    @Test
    public void testGetLastName() {
        assertEquals("Doe", residentInfoMedicalRecords.getLastName());
    }

    @Test
    public void testSetLastName() {
        residentInfoMedicalRecords.setLastName("Smith");
        assertEquals("Smith", residentInfoMedicalRecords.getLastName());
    }

    @Test
    public void testGetPhone() {
        assertEquals("555-123-4567", residentInfoMedicalRecords.getPhone());
    }

    @Test
    public void testSetPhone() {
        residentInfoMedicalRecords.setPhone("555-987-6543");
        assertEquals("555-987-6543", residentInfoMedicalRecords.getPhone());
    }

    @Test
    public void testGetAge() {
        assertEquals(Integer.valueOf(30), residentInfoMedicalRecords.getAge());
    }

    @Test
    public void testSetAge() {
        residentInfoMedicalRecords.setAge(40);
        assertEquals(Integer.valueOf(40), residentInfoMedicalRecords.getAge());
    }

    @Test
    public void testGetMedications() {
        List<String> medications = residentInfoMedicalRecords.getMedications();
        assertEquals(2, medications.size());
        assertEquals("Medication1", medications.get(0));
        assertEquals("Medication2", medications.get(1));
    }

    @Test
    public void testSetMedications() {
        List<String> newMedications = new ArrayList<>();
        newMedications.add("Medication3");
        newMedications.add("Medication4");

        residentInfoMedicalRecords.setMedications(newMedications);

        List<String> medications = residentInfoMedicalRecords.getMedications();
        assertEquals(2, medications.size());
        assertEquals("Medication3", medications.get(0));
        assertEquals("Medication4", medications.get(1));
    }

    @Test
    public void testGetAllergies() {
        List<String> allergies = residentInfoMedicalRecords.getAllergies();
        assertEquals(2, allergies.size());
        assertEquals("Allergy1", allergies.get(0));
        assertEquals("Allergy2", allergies.get(1));
    }

    @Test
    public void testSetAllergies() {
        List<String> newAllergies = new ArrayList<>();
        newAllergies.add("Allergy3");
        newAllergies.add("Allergy4");

        residentInfoMedicalRecords.setAllergies(newAllergies);

        List<String> allergies = residentInfoMedicalRecords.getAllergies();
        assertEquals(2, allergies.size());
        assertEquals("Allergy3", allergies.get(0));
        assertEquals("Allergy4", allergies.get(1));
    }

    @Test
    public void testToString() {
        String expectedString = "ResidentInfoMedicalRecordsDTO{" +
                "LastName='Doe', " +
                "phone='555-123-4567', " +
                "age=30, " +
                "medications=[Medication1, Medication2], " +
                "allergies=[Allergy1, Allergy2]" +
                '}';
        assertEquals(expectedString, residentInfoMedicalRecords.toString());
    }
}
