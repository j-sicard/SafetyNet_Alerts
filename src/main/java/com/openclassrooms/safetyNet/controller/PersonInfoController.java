package com.openclassrooms.safetyNet.controller;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.safetyNet.dto.PersonMedicalRecordDTO;
import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.service.MedicalRecordService;
import com.openclassrooms.safetyNet.service.PersonService;

@RestController
@RequestMapping("/personInfo")
public class PersonInfoController {

    @Autowired
    PersonService personService;

    @Autowired
    MedicalRecordService medicalRecordService;

    @GetMapping
    public List<PersonMedicalRecordDTO> getPersonsMedicalRecords(@RequestParam String firstName, @RequestParam String lastName) throws ClassNotFoundException, JsonProcessingException, IOException {
        List<Person> persons = personService.getByFirstNameAndLastName(lastName);
        return medicalRecordService.getPersonsMedicalRecords(persons);
    }
}
