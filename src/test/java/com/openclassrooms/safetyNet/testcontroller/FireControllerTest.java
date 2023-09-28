package com.openclassrooms.safetyNet.testcontroller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FireControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetPersonsAndMedicalRecordsAndStationNumberOfAddress() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/fire?address=1509%20Culver%20St"))
                .andExpect(status().is2xxSuccessful());
    }
}