package com.openclassrooms.safetyNet.controller;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger LOGGER =  LogManager.getLogger( PersonInfoController.class );

    @GetMapping
    public List<PersonMedicalRecordDTO> getPersonsMedicalRecords(@RequestParam String firstName, @RequestParam String lastName){
        try {
            List<Person> persons = personService.getByFirstNameAndLastName(lastName);
            LOGGER.info(medicalRecordService.getPersonsMedicalRecords(persons));
            return medicalRecordService.getPersonsMedicalRecords(persons);
        }catch(ClassNotFoundException | IOException e){
            LOGGER.error("Impossible de lire le fichier data.json");
            return null;
        }

    }
}
