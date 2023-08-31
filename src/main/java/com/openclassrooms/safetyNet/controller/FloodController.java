package com.openclassrooms.safetyNet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FloodController {
    @GetMapping("/flood/stations")
    public String listOfAllHouseholdsServedByTheFireStation(@RequestParam(required = true) String  stations){
        return "La liste de tous les foyers desservis par la caserne: " + stations;
    }
}

