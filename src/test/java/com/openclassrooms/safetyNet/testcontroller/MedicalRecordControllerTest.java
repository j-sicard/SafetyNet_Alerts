package com.openclassrooms.safetyNet.testcontroller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createMedicalRecordTest() throws Exception {
        mockMvc.perform(post("/medicalRecord")
                .contentType(MediaType.APPLICATION_JSON) // Utilisez MediaType.APPLICATION_JSON
                .content("{ \"firstName\" : \"fabien\", \"lastName\" : \"Bartez\", \"birthdate\" : \"10/11/1968\", \"medications\" : [\"Doliprane\"], \"allergies\" : [ \"cacahuete\" ] }")
        ).andExpect(status().is2xxSuccessful());
    }


    @Test
    public void deletePersonTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/medicalRecord")
                .contentType(MediaType.APPLICATION_JSON)
                .param("firstName", "Tim")
                .param("lastName", "Burton")
        ).andExpect(status().is2xxSuccessful());
    }

}
