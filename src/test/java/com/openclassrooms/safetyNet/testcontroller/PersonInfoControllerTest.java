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
public class PersonInfoControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testGetPersonInfo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/personInfo?firstName=John&lastName=Boyd"))
                .andExpect(status().is2xxSuccessful());
    }
}