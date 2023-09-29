package com.openclassrooms.safetyNet.controller;


import com.openclassrooms.safetyNet.model.FireStation;
import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger LOGGER =  LogManager.getLogger( PersonController.class );

    @PostMapping
    public ResponseEntity<String> createPerson(@RequestBody Person person){
        try {
            personService.savePerson(person);
            LOGGER.info(ResponseEntity.status(HttpStatus.CREATED).body( "Nouvelle personne créée avec succès!"));
            LOGGER.info(person);
            return ResponseEntity.status(HttpStatus.CREATED).body( "Nouvelle personne créée avec succès!");
        }catch(ClassNotFoundException | IOException e){
            LOGGER.error("Impossible de crée la personne dans le fichier data.json");
            return null;
        }
    }

    @PutMapping
    public ResponseEntity<String> updatePerson(@RequestParam String firstName, @RequestParam String lastName ,@RequestBody Person person){
        try {
            personService.updateperson(firstName, lastName, person);
            LOGGER.info(ResponseEntity.status(HttpStatus.CREATED).body("profile de la personne mise à jour avec succès!"));
            LOGGER.info(person);
            return  ResponseEntity.status(HttpStatus.CREATED).body("profile de la personne mise à jour avec succès!");
        }catch(ClassNotFoundException | IOException e){
            LOGGER.error("Impossible de modifiée la personne dans le fichier data.json");
            return null;
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deletePerson(@RequestParam String firstName, @RequestParam String lastName){
        try {
            personService.deletePerson(firstName, lastName);
            LOGGER.info(ResponseEntity.status(HttpStatus.CREATED).body("Le profile de la personne a été suprimée avec succès!"));
            return ResponseEntity.status(HttpStatus.CREATED).body("Le profile de la personne a été suprimée avec succès!");
        }catch(ClassNotFoundException | IOException e){
            LOGGER.error("Impossible de supprimée la personne dans le fichier data.json");
            return null;
        }
    }
}