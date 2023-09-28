package com.openclassrooms.safetyNet.testcontroller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createPersonTest() throws Exception {
        mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON) // Utilisez MediaType.APPLICATION_JSON
                .content("{ \"firstName\" : \"fabien\", \"lastName\" : \"Bartez\", \"birthdate\" : \"10/11/1968\", \"medications\" : [\"Doliprane\"], \"allergies\" : [ \"cacahuete\" ] }")
        ).andExpect(status().is2xxSuccessful());
    }


    @Test
    public void deletePersonTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .param("firstName", "Tim")
                .param("lastName", "Burton")
        ).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void updatePersonTest() throws Exception {
        mockMvc.perform(put("/person")
                        .contentType("application/json")
                        .param("firstName", "Fabien")
                        .param("lastName", "Barteze")
                        .content("{ \"firstName\" : \"Fabien\", \"lastName\" : \"Barteze\", \"birthdate\" : \"12/10/1968\", \"medications\" : [\"Tramadol\"], \"allergies\" : [ \"shellfish\" ] }"))
                .andExpect(status().is2xxSuccessful());
    }
}