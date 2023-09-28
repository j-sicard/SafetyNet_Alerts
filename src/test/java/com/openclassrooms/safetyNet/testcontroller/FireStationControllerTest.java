package com.openclassrooms.safetyNet.testcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetyNet.model.FireStation;
import com.openclassrooms.safetyNet.service.StationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

    @SpringBootTest
    @AutoConfigureMockMvc
    public class FireStationControllerTest {
        @Autowired
        private MockMvc mockMvc;

        static ObjectMapper mapper = new ObjectMapper();

        @MockBean // Mock de FireStationService pour l'injection
        private StationService fireStationService;


        @Test
        public void testGetFirestations() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/firestation?stationNumber=1"))
                    .andExpect(status().is2xxSuccessful());
        }

        @Test
        public void createStationTest() throws Exception {
            // Configuration du comportement du mock
            List<FireStation> expectedFireStation = new ArrayList<>();
            when(fireStationService.saveStation(any(FireStation.class))).thenReturn(expectedFireStation);

            mockMvc.perform(post("/firestation")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{ \"address\" : \"addressTest\", \"station\" : \"stationTest\"}")
            ).andExpect(status().is2xxSuccessful());// Vérification du status

            // Vérification que la méthode saveFireStation du service a été appelée avec les bons arguments
            verify(fireStationService).saveStation(any(FireStation.class));
        }

        @Test
        public void updatePersonTest() throws Exception {
            mockMvc.perform(put("/firestation")
                            .contentType("application/json")
                            .param("address", "addressTest")
                            .param("station", "stationTest")
                            .content("{ \"address\" : \"addressTest\", \"station\" : \"stationTest2\"}"))
                    .andExpect(status().is2xxSuccessful());
        }

        @Test
        public void deleteStationTest() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                    .delete("/firestation")
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("address", "addressTest")
                    .param("station", "stationTest")
            ).andExpect(status().is2xxSuccessful());
        }
    }