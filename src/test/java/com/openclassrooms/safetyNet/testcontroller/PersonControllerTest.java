package com.openclassrooms.safetyNet.testcontroller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPostMedicalRecord() throws Exception {
        mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON) // Utilisez MediaType.APPLICATION_JSON
                .content("{ \"firstName\" : \"Rayane\", \"lastName\" : \"Berrada\", \"birthdate\" : \"26/12/1994\", \"medications\" : [\"Morphine\"], \"allergies\" : [ \"shellfish\" ] }")
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
}