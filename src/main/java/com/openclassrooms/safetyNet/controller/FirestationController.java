package com.openclassrooms.safetyNet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirestationController {
    @GetMapping("/firestation")
    public String
    listOfPeopleCoveredByTheCorrespondingFireStation(@RequestParam(required = true) int station_number){
        return "Numero de la station = " + station_number;
    }
}
