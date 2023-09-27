package com.openclassrooms.safetyNet.testdto;

import com.openclassrooms.safetyNet.dto.PersonMedicalRecordDTO;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonMedicalRecordDTOTest {
    @Test
    public void testGettersAndSetters() {
        // Créez un objet PersonMedicalRecordDTO avec des données de test
        List<String> medications = Arrays.asList("Medication1", "Medication2");
        List<String> allergies = Arrays.asList("Allergy1", "Allergy2");
        PersonMedicalRecordDTO personMedicalRecord = new PersonMedicalRecordDTO(
                "John", "Doe", "123 Main St", 30, "john.doe@example.com", medications, allergies);

        // Vérifiez que les getters renvoient les bonnes données
        assertEquals("John", personMedicalRecord.getFirstName());
        assertEquals("Doe", personMedicalRecord.getLastName());
        assertEquals("123 Main St", personMedicalRecord.getAddress());
        assertEquals(30, personMedicalRecord.getAge());
        assertEquals("john.doe@example.com", personMedicalRecord.getEmail());
        assertEquals(medications, personMedicalRecord.getMedications());
        assertEquals(allergies, personMedicalRecord.getAllergies());

        // Modifiez les données à l'aide des setters
        personMedicalRecord.setFirstName("Alice");
        personMedicalRecord.setLastName("Smith");
        personMedicalRecord.setAddress("456 Elm St");
        personMedicalRecord.setAge(40);
        personMedicalRecord.setEmail("alice.smith@example.com");
        personMedicalRecord.setMedications(Arrays.asList("Medication3", "Medication4"));
        personMedicalRecord.setAllergies(Arrays.asList("Allergy3", "Allergy4"));

        // Vérifiez que les getters renvoient les nouvelles données
        assertEquals("Alice", personMedicalRecord.getFirstName());
        assertEquals("Smith", personMedicalRecord.getLastName());
        assertEquals("456 Elm St", personMedicalRecord.getAddress());
        assertEquals(40, personMedicalRecord.getAge());
        assertEquals("alice.smith@example.com", personMedicalRecord.getEmail());
        assertEquals(Arrays.asList("Medication3", "Medication4"), personMedicalRecord.getMedications());
        assertEquals(Arrays.asList("Allergy3", "Allergy4"), personMedicalRecord.getAllergies());
    }
}
