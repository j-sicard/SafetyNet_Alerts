/*
package com.openclassrooms.safetyNet.testcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetyNet.controller.FireController;
import com.openclassrooms.safetyNet.dto.ResidentInfoMedicalRecordsDTO;
import com.openclassrooms.safetyNet.dto.ResidentInfoMedicalRecordsStations;
import com.openclassrooms.safetyNet.service.MedicalRecordService;
import com.openclassrooms.safetyNet.service.PersonService;
import com.openclassrooms.safetyNet.service.StationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FireController.class)
public class FireControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StationService stationService;

    @MockBean
    private PersonService personService;

    @MockBean
    private MedicalRecordService medicalRecordService;

    @Test
    public void testGetPersonInfoAndMedicalRecordsByFireStationAddress() throws Exception {
        // Créez des données fictives pour le test
        String address = "123 Main St";
        String stationNumber = "Station 1";
        List<String> medication = new ArrayList<>();
        List<String> allergie = new ArrayList<>();

        ResidentInfoMedicalRecordsDTO resident1 = new ResidentInfoMedicalRecordsDTO("John", "Doe", 30,  medication, allergie);
        List<ResidentInfoMedicalRecordsDTO> residentInfoMedicalRecords = Arrays.asList(resident1);

        // Configurez le comportement attendu des services mockés
        when(stationService.getStationByAddress(address)).thenReturn(stationNumber);

        // Utilisez thenReturn avec la liste de personnes fictives pour personService
        when(personService.getPersonInfoFromAddress(address)).thenReturn(persons);

        // Utilisez thenReturn avec la liste de personnes fictives également pour medicalRecordService
        when(medicalRecordService.getResidentMedicalRecords(persons)).thenReturn(Arrays.asList(*/
/* vos objets ResidentInfoMedicalRecordsDTO ici *//*
));

        // Effectuez une requête GET vers le contrôleur avec le paramètre "address"
        mockMvc.perform(MockMvcRequestBuilders.get("/fire")
                        .param("address", address)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new ResidentInfoMedicalRecordsStations(residentInfoMedicalRecords, stationNumber))));
    }
}*/
