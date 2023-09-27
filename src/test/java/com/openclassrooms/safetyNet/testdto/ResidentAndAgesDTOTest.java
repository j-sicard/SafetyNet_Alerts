package com.openclassrooms.safetyNet.testdto;

import com.openclassrooms.safetyNet.dto.ResidentAndAgesDTO;
import com.openclassrooms.safetyNet.dto.ResidentInfoDTO;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResidentAndAgesDTOTest {
    @Test
    public void testGettersAndSetters() {
        // Créez un objet ResidentAndAgesDTO avec des données de test
        List<ResidentInfoDTO> residents = Arrays.asList(
                new ResidentInfoDTO("John", "Doe", "30", "05 57 52 25 25"),
                new ResidentInfoDTO("Alice", "Smith", "40", "05 57 52 25 25")
        );
        ResidentAndAgesDTO residentAndAges = new ResidentAndAgesDTO(residents, 2, 0);

        // Vérifiez que les getters renvoient les bonnes données
        assertEquals(residents, residentAndAges.getResidents());
        assertEquals(2, residentAndAges.getNbAdult());
        assertEquals(0, residentAndAges.getNbChildren());

        // Modifiez les données à l'aide des setters
        List<ResidentInfoDTO> newResidents = Arrays.asList(
                new ResidentInfoDTO("Bob", "Johnson", "25", "05 57 52 25 25" ),
                new ResidentInfoDTO("Eve", "Williams", "35",  "05 57 52 25 25")
        );
        residentAndAges.setResidents(newResidents);
        residentAndAges.setNbAdult(3);
        residentAndAges.setNbChildren(1);

        // Vérifiez que les getters renvoient les nouvelles données
        assertEquals(newResidents, residentAndAges.getResidents());
        assertEquals(3, residentAndAges.getNbAdult());
        assertEquals(1, residentAndAges.getNbChildren());
    }
}
