package com.openclassrooms.safetyNet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChildAlertController {
    @GetMapping("/childAlert")
    public String childrenLivingAtThisAddress(@RequestParam(required = true) String  address){
        return "adresse des enfants = " + address;
    }
}
