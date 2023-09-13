package com.openclassrooms.safetyNet.controller;


import com.openclassrooms.safetyNet.service.MedicalRecordsService;
import com.openclassrooms.safetyNet.service.PersonService;
import com.openclassrooms.safetyNet.service.StationService;
import com.openclassrooms.safetyNet.utils.DateConverter;
import org.codehaus.jackson.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static com.openclassrooms.safetyNet.utils.DateConverter.calculateAge;
import static com.openclassrooms.safetyNet.utils.DateConverter.convertStringListToDateList;

@RestController
public class FireStationController {

    @Autowired
    StationService stationService;

    @Autowired
    PersonService personService;

    @Autowired
    MedicalRecordsService medicalRecordsService;

    @GetMapping("/firestation")
    public List<String> getResidentInformationsByStation(@RequestParam String stationNumber) throws ClassNotFoundException, JsonProcessingException, IOException {
        return personService.getPersonInfoFromAdresses(stationService.listStationAddresses(stationNumber));
    }
}
