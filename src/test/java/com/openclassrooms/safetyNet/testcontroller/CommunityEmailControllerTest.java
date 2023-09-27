package com.openclassrooms.safetyNet.testcontroller;

import com.openclassrooms.safetyNet.controller.CommunityEmailController;
import com.openclassrooms.safetyNet.service.PersonService;
import org.junit.jupiter.api.Test;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(CommunityEmailController.class)
public class CommunityEmailControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

  /*  Ce test vérifie que lorsque j'envoye une requête GET à l'URL /communityEmail?city=Culver,
    le contrôleur répond avec un code de statut HTTP dans la plage 2xx, ce qui indique que
     la requête a été traitée avec succès. Cela permet de s'assurer
      que le point de terminaison /communityEmail fonctionne correctement lorsque le paramètre city est fixé à "Culver".*/
    @Test
    public void testGetCommunityEmails() throws Exception {
        mockMvc.perform(get("/communityEmail?city=Culver"))
                .andExpect(status().is2xxSuccessful());
    }
}

