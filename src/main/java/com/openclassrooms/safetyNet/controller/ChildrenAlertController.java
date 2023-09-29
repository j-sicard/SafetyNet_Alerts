package com.openclassrooms.safetyNet.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.openclassrooms.safetyNet.dto.ChildrenAdultDTO;
import com.openclassrooms.safetyNet.dto.PersonInfoForChildAlertDTO;
import com.openclassrooms.safetyNet.service.PersonService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RequestMapping("/childAlert")
@RestController
public class ChildrenAlertController {
    @Autowired
    PersonService personService;

    private static final Logger LOGGER =  LogManager.getLogger( ChildrenAlertController.class );

    @GetMapping
    public ChildrenAdultDTO getChildFromAddress(@RequestParam String address){
        try {
            List<PersonInfoForChildAlertDTO> Persons = personService.getPersonInfoForChildAlert(personService.getPersonInfoFromAddress(address));
            LOGGER.info(Persons);
            return new ChildrenAdultDTO(personService.getChildren(Persons) , personService.getAdult(Persons));
        }catch (ClassNotFoundException | IOException e){
            LOGGER.error("Impossible de lire le fichier data.json");
            return null;
        }
    }
}
