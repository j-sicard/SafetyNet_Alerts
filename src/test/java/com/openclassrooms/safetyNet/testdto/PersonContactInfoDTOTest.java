package com.openclassrooms.safetyNet.testdto;

import com.openclassrooms.safetyNet.dto.PersonContactInfoDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonContactInfoDTOTest {
    @Test
    public void testGettersAndSetters() {
        // Créez un objet PersonContactInfoDTO
        PersonContactInfoDTO personContactInfoDTO = new PersonContactInfoDTO("John", "Doe", "123 Main St", "555-123-4567", "30");

        // Vérifiez que les getters renvoient les bonnes données
        assertEquals("John", personContactInfoDTO.getFirstName());
        assertEquals("Doe", personContactInfoDTO.getLastName());
        assertEquals("123 Main St", personContactInfoDTO.getAddress());
        assertEquals("555-123-4567", personContactInfoDTO.getPhone());
        assertEquals("30", personContactInfoDTO.getAge());

        // Modifiez les données à l'aide des setters
        personContactInfoDTO.setFirstName("Alice");
        personContactInfoDTO.setLastName("Smith");
        personContactInfoDTO.setAddress("456 Elm St");
        personContactInfoDTO.setPhone("555-987-6543");
        personContactInfoDTO.setAge("25");

        // Vérifiez que les getters renvoient les nouvelles données
        assertEquals("Alice", personContactInfoDTO.getFirstName());
        assertEquals("Smith", personContactInfoDTO.getLastName());
        assertEquals("456 Elm St", personContactInfoDTO.getAddress());
        assertEquals("555-987-6543", personContactInfoDTO.getPhone());
        assertEquals("25", personContactInfoDTO.getAge());
    }
}
