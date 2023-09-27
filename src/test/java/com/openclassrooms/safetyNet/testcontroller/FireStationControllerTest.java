package com.openclassrooms.safetyNet.testcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

    @SpringBootTest
    @AutoConfigureMockMvc
    public class FireStationControllerTest {
        @Autowired
        private MockMvc mockMvc;

        static ObjectMapper mapper = new ObjectMapper();

        @Test
        public void testGetFirestations() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/firestation?stationNumber=1")) // Utilisez MockMvcRequestBuilders.get pour créer une requête GET
                    .andExpect(status().is2xxSuccessful());
        }
    }