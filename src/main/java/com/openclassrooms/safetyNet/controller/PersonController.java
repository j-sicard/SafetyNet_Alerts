package com.openclassrooms.safetyNet.controller;


import com.openclassrooms.safetyNet.model.FireStation;
import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("/person")
@RestController
public class PersonController {
    @Autowired
    PersonService personService;

    @PostMapping
    public ResponseEntity<String> createPerson(@RequestBody Person person) throws ClassNotFoundException, IOException {
        personService.savePerson(person);
        String responseMessage = "Nouvelle personne créée avec succès!";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    @PutMapping
    public ResponseEntity<String> updatePerson(@RequestParam String firstName, @RequestParam String lastName ,@RequestBody Person person) throws IOException, ClassNotFoundException {
        personService.updateperson(firstName, lastName, person);
        return  ResponseEntity.status(HttpStatus.CREATED).body("profile de la personne mise à jour avec succès!");
    }
}