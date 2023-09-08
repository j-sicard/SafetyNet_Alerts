package com.openclassrooms.safetyNet.controller;

import com.openclassrooms.safetyNet.service.PhoneAlertService;

import org.codehaus.jackson.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class PhoneAlertController{
 @Autowired
 PhoneAlertService phoneAlertService;

    @GetMapping(value = "/phoneAlert")
    public List<String> getPhoneNumberResidentServedByFirestation(@RequestParam String firestation) throws ClassNotFoundException, JsonProcessingException, IOException {

        return phoneAlertService.getPhoneNumberResidentServedByFirestation(firestation);
    }
}

