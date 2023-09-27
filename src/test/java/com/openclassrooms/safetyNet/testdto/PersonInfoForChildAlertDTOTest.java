package com.openclassrooms.safetyNet.testdto;

import com.openclassrooms.safetyNet.dto.PersonInfoForChildAlertDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonInfoForChildAlertDTOTest {
    @Test
    public void testGettersAndSetters() {
        // Créez un objet PersonInfoForChildAlertDTO
        PersonInfoForChildAlertDTO personInfo = new PersonInfoForChildAlertDTO("John", "Doe", 8);

        // Vérifiez que les getters renvoient les bonnes données
        assertEquals("John", personInfo.getFirstName());
        assertEquals("Doe", personInfo.getLastName());
        assertEquals(8, personInfo.getAge());

        // Modifiez les données à l'aide des setters
        personInfo.setFirstName("Alice");
        personInfo.setLastName("Smith");
        personInfo.setAge(12);

        // Vérifiez que les getters renvoient les nouvelles données
        assertEquals("Alice", personInfo.getFirstName());
        assertEquals("Smith", personInfo.getLastName());
        assertEquals(12, personInfo.getAge());
    }
}
