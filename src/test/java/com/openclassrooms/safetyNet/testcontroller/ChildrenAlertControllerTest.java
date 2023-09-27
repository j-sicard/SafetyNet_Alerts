/*
package com.openclassrooms.safetyNet.testcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetyNet.controller.ChildrenAlertController;
import com.openclassrooms.safetyNet.dto.ChildrenAdultDTO;
import com.openclassrooms.safetyNet.dto.PersonInfoForChildAlertDTO;
import com.openclassrooms.safetyNet.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ChildrenAlertControllerTest {
    @Test
    public void testToString() {
        // Créer des données fictives pour le test
        PersonInfoForChildAlertDTO child1 = new PersonInfoForChildAlertDTO("John", "Doe", 5, "123 Main St");
        PersonInfoForChildAlertDTO child2 = new PersonInfoForChildAlertDTO("Jane", "Smith", 7, "456 Elm St");
        List<PersonInfoForChildAlertDTO> children = Arrays.asList(child1, child2);

        PersonInfoForChildAlertDTO adult1 = new PersonInfoForChildAlertDTO("Alice", "Johnson", 35, "789 Oak St");
        List<PersonInfoForChildAlertDTO> adults = Arrays.asList(adult1);

        ChildrenAdultDTO dto = new ChildrenAdultDTO(children, adults);

        // La chaîne de caractères attendue
        String expectedString = "ChildrenAdultDTO{" +
                "children=" + children +
                ", adult=" + adults +
                '}';

        // Vérifier que la méthode toString() génère la chaîne de caractères attendue
        assertEquals(expectedString, dto.toString());
    }
}*/
