package com.openclassrooms.safetyNet.testdto;

import com.openclassrooms.safetyNet.dto.ChildrenAdultDTO;
import com.openclassrooms.safetyNet.dto.PersonInfoForChildAlertDTO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChildrenAdultDTOTest {
    @Test
    public void testGettersAndSetters() {
        // Créez des données de test
        List<PersonInfoForChildAlertDTO> children = new ArrayList<>();
        List<PersonInfoForChildAlertDTO> adults = new ArrayList<>();

        PersonInfoForChildAlertDTO child1 = new PersonInfoForChildAlertDTO();
        child1.setFirstName("John");
        child1.setLastName("Doe");
        children.add(child1);

        PersonInfoForChildAlertDTO adult1 = new PersonInfoForChildAlertDTO();
        adult1.setFirstName("Alice");
        adult1.setLastName("Smith");
        adults.add(adult1);

        // Créez un objet ChildrenAdultDTO
        ChildrenAdultDTO childrenAdultDTO = new ChildrenAdultDTO();
        childrenAdultDTO.setChildren(children);
        childrenAdultDTO.setAdult(adults);

        // Vérifiez que les getters renvoient les bonnes données
        assertEquals(children, childrenAdultDTO.getChildren());
        assertEquals(adults, childrenAdultDTO.getAdult());
    }
}
