package com.openclassrooms.safetyNet.testcontroller;

import com.openclassrooms.safetyNet.model.FireStation;
import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

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
public class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @Test
    public void createPersonTest() throws Exception {
        List<Person> expectedPerson = new ArrayList<>();
        when(personService.savePerson(any(Person.class))).thenReturn(expectedPerson);

        mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON) // Utilisez MediaType.APPLICATION_JSON
                .content("{ \"firstName\" : \"fabien\", \"lastName\" : \"Bartez\", \"address\" : \" 1 address test\", \"city\" : \"Culver\", \"zip\" :  \"97451\", \"phone\" : \"841-874-6512\", \"email\" : \"jaboyd@email.com\"  }")
        ).andExpect(status().is2xxSuccessful());

        verify(personService).savePerson(any(Person.class));
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
                .content("{ \"firstName\" : \"fabien\", \"lastName\" : \"Bartez\", \"address\" : \" 1 address test\", \"city\" : \"Culver\", \"zip\" :  \"97451\", \"phone\" : \"841-874-6512\", \"email\" : \"jaboyd@email.com\"  }"))
                .andExpect(status().is2xxSuccessful());
    }
}