package com.openclassrooms.safetyNet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FireController {
    @GetMapping("/fire")
    public String
    listOfResidentsLivingAtTheGivenAddressAsWellAsTheNumberOfFireStationServingIt
            (@RequestParam(required = true) String  address){
        return "La liste des habitants vivant ainsi que le numéro" +
                " de la caserne de pompiers la desservant a l'address" + address;
    }
}
