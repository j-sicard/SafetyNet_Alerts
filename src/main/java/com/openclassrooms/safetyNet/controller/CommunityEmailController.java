package com.openclassrooms.safetyNet.controller;


import com.openclassrooms.safetyNet.service.PersonService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class CommunityEmailController {
    @Autowired
    PersonService personService;

    private static final Logger LOGGER =  LogManager.getLogger( ChildrenAlertController.class );

    @GetMapping(value = "/communityEmail")
    public List<String> getCommunityEmails(@RequestParam String city){
        try {
            LOGGER.info(personService.getPersonEmailFromCity(city));
            return personService.getPersonEmailFromCity(city);
        }catch (ClassNotFoundException | IOException e){
            LOGGER.error("Impossible de lire le fichier data.json");
            return null;
        }
    }
}
