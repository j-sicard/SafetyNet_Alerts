package com.openclassrooms.safetyNet.testcontroller;

import com.openclassrooms.safetyNet.controller.MedicalRecordController;
import com.openclassrooms.safetyNet.model.MedicalRecord;
import com.openclassrooms.safetyNet.service.MedicalRecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

  /*  @Test
    public void createMedicalRecordTest() throws Exception {


        mockMvc.perform(post("/medicalRecord")
                .contentType(MediaType.APPLICATION_JSON) // Utilisez MediaType.APPLICATION_JSON
                .content("{ \"firstName\" : \"fabien\", \"lastName\" : \"Bartez\", \"birthdate\" : \"10/11/1968\", \"medications\" : [\"Doliprane\"], \"allergies\" : [ \"cacahuete\" ] }")
        ).andExpect(status().is2xxSuccessful());
    }*/


    @MockBean
    private MedicalRecordService medicalRecordService;
   /* @Test
    public void createMedicalRecordTest() throws Exception {
        List<MedicalRecord> medicalRecords = new ArrayList<>();

        when(medicalRecordService.saveMedicalRecord(any(MedicalRecord.class))).thenReturn(medicalRecords);

        mockMvc.perform(post("/medicalRecord")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"firstName\" : \"fabien\", \"lastName\" : \"Bartez\", \"birthdate\" : \"10/11/1968\", \"medications\" : [\"Doliprane\"], \"allergies\" : [ \"cacahuete\" ] }")
        ).andExpect(status().is2xxSuccessful());

        verify(medicalRecordService).saveMedicalRecord(any(MedicalRecord.class));
    }
*/

    @Test
    public void deletePersonTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/medicalRecord")
                .contentType(MediaType.APPLICATION_JSON)
                .param("firstName", "Tim")
                .param("lastName", "Burton")
        ).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void updatePersonTest() throws Exception {
        mockMvc.perform(put("/medicalRecord")
                        .contentType("application/json")
                        .param("firstName", "Fabien")
                        .param("lastName", "Barteze")
                        .content("{ \"firstName\" : \"Fabien\", \"lastName\" : \"Barteze\", \"birthdate\" : \"12/10/1968\", \"medications\" : [\"Tramadol\"], \"allergies\" : [ \"shellfish\" ] }"))
                .andExpect(status().is2xxSuccessful());
    }

}
