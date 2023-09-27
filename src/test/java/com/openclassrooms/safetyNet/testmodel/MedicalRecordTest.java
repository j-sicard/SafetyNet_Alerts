package com.openclassrooms.safetyNet.testmodel;

import com.openclassrooms.safetyNet.model.MedicalRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MedicalRecordTest {
    private MedicalRecord medicalRecord;

    @BeforeEach
    public void setUp() {
        List<String> medications = new ArrayList<>();
        medications.add("Medication1");
        medications.add("Medication2");

        List<String> allergies = new ArrayList<>();
        allergies.add("Allergy1");
        allergies.add("Allergy2");

        medicalRecord = new MedicalRecord("John", "Doe", "2000-01-01", medications, allergies);
    }

    @Test
    public void testGetFirstName() {
        assertEquals("John", medicalRecord.getFirstName());
    }

    @Test
    public void testSetFirstName() {
        medicalRecord.setFirstName("Jane");
        assertEquals("Jane", medicalRecord.getFirstName());
    }

    @Test
    public void testGetLastName() {
        assertEquals("Doe", medicalRecord.getLastName());
    }

    @Test
    public void testSetLastName() {
        medicalRecord.setLastName("Smith");
        assertEquals("Smith", medicalRecord.getLastName());
    }

    @Test
    public void testGetBirthdate() {
        assertEquals("2000-01-01", medicalRecord.getBirthdate());
    }

    @Test
    public void testSetBirthdate() {
        medicalRecord.setBirthdate("1990-05-15");
        assertEquals("1990-05-15", medicalRecord.getBirthdate());
    }

    @Test
    public void testGetMedications() {
        List<String> medications = medicalRecord.getMedications();
        assertEquals(2, medications.size());
        assertEquals("Medication1", medications.get(0));
        assertEquals("Medication2", medications.get(1));
    }

    @Test
    public void testSetMedications() {
        List<String> newMedications = new ArrayList<>();
        newMedications.add("Medication3");
        newMedications.add("Medication4");

        medicalRecord.setMedications(newMedications);

        List<String> medications = medicalRecord.getMedications();
        assertEquals(2, medications.size());
        assertEquals("Medication3", medications.get(0));
        assertEquals("Medication4", medications.get(1));
    }

    @Test
    public void testGetAllergies() {
        List<String> allergies = medicalRecord.getAllergies();
        assertEquals(2, allergies.size());
        assertEquals("Allergy1", allergies.get(0));
        assertEquals("Allergy2", allergies.get(1));
    }

    @Test
    public void testSetAllergies() {
        List<String> newAllergies = new ArrayList<>();
        newAllergies.add("Allergy3");
        newAllergies.add("Allergy4");

        medicalRecord.setAllergies(newAllergies);

        List<String> allergies = medicalRecord.getAllergies();
        assertEquals(2, allergies.size());
        assertEquals("Allergy3", allergies.get(0));
        assertEquals("Allergy4", allergies.get(1));
    }
}