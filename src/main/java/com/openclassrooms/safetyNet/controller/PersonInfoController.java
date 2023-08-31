package com.openclassrooms.safetyNet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonInfoController {
    @GetMapping("/personInfo")
    public String personalInformationAndMedicalHistoryOfEachResident
            (@RequestParam(required = true) String  firstName, @RequestParam(required = true) String  lastName){
        return "info : " + firstName + lastName;
    }
}

