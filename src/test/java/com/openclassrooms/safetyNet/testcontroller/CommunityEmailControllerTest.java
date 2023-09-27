package com.openclassrooms.safetyNet.testcontroller;


import com.openclassrooms.safetyNet.controller.CommunityEmailController;
import com.openclassrooms.safetyNet.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(CommunityEmailController.class)
public class CommunityEmailControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @BeforeEach
    public void setUp() {
        // Configurez le comportement simulé pour personService ici
    }

    @Test
    public void testGetCommunityEmails() throws Exception {
        // Créez des données fictives pour votre service
        List<String> emails = Arrays.asList("email1@example.com", "email2@example.com");

        // Définissez le comportement simulé pour le service
        when(personService.getPersonEmailFromCity("Example City")).thenReturn(emails);

        // Effectuez la requête GET simulée
        mockMvc.perform(get("/communityEmail")
                        .param("city", "Example City"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(emails.size()));
    }
}

