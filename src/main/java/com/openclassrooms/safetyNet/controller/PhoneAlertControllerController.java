package com.openclassrooms.safetyNet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhoneAlertControllerController {
    @GetMapping("/phoneAlert")
    public String listOfTelephoneNumbersOfResidentsServedByTheFireStation(
            @RequestParam(required = true) String  firestation){
        return "Numero des résitant décervis par la sation numero = " + firestation;
    }
}
