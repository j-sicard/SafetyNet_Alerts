package com.openclassrooms.safetyNet.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.openclassrooms.safetyNet.dto.ResidentInfoMedicalRecordsDTO;
import com.openclassrooms.safetyNet.dto.ResidentInfoMedicalRecordsStations;
import com.openclassrooms.safetyNet.service.MedicalRecordService;
import com.openclassrooms.safetyNet.service.PersonService;
import com.openclassrooms.safetyNet.service.StationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class FireController {

    @Autowired
    StationService stationService;

    @Autowired
    PersonService personService;

    @Autowired
    MedicalRecordService medicalRecordService;

    @GetMapping(value = "/fire")
    public ResidentInfoMedicalRecordsStations getPersonInfoAndMedicalRecordsByFireStationAddresse(@RequestParam String address)
    throws ClassNotFoundException, JsonProcessingException, IOException
    {
        List<ResidentInfoMedicalRecordsDTO> ResidentInfoMedicalRecords  = medicalRecordService.getResidentMedicalRecords(personService.getPersonInfoFromAddress(address)) ;
        String StationNumber = stationService.getStationByAddress(address);

        return new ResidentInfoMedicalRecordsStations(ResidentInfoMedicalRecords, StationNumber);
    }
}
