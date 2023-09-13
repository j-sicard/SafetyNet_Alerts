package com.openclassrooms.safetyNet.controller;


import com.openclassrooms.safetyNet.service.PersonService;
import org.codehaus.jackson.JsonProcessingException;

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

    @GetMapping(value = "/communityEmail")
    public List<String> getCommunityEmails(@RequestParam String city) throws ClassNotFoundException, JsonProcessingException, IOException {
        return personService.getPersonEmailFromCity(city);
    }
}
