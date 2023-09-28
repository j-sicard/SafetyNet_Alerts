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
public class PhoneAlertControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetResidentPhoneNumbersByStation() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/phoneAlert?firestation=1")) // Utilisez MockMvcRequestBuilders.get pour créer une requête GET
                .andExpect(status().is2xxSuccessful());
    }
}
