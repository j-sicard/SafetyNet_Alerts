package com.openclassrooms.safetyNet.controller;


import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("/person")
@RestController
public class PersonController {

    @Autowired
    PersonService personService;
    @PostMapping("/createPerson")
    public ResponseEntity<String> createPerson(@RequestBody Person person) throws ClassNotFoundException, IOException {
        personService.savePerson(person);
        String responseMessage = "Ressource créée avec succès!";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }
}