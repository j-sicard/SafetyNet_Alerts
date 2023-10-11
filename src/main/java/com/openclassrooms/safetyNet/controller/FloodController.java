package com.openclassrooms.safetyNet.controller;

import com.openclassrooms.safetyNet.dto.ResidentInfoMedicalRecordsDTO;
import com.openclassrooms.safetyNet.service.MedicalRecordService;
import com.openclassrooms.safetyNet.service.PersonService;
import com.openclassrooms.safetyNet.service.StationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RequestMapping("/flood")
@RestController
public class FloodController {
    @Autowired
    MedicalRecordService medicalRecordService;
    @Autowired
    PersonService personService;
    @Autowired
    StationService stationService;

    private static final Logger LOGGER =  LogManager.getLogger( FloodController.class );

   @GetMapping(value = "/station")
    public List<ResidentInfoMedicalRecordsDTO> getStations(@RequestParam(value="stations") List<String> stations){
       try {
           List<ResidentInfoMedicalRecordsDTO> residentInfoMedicalRecords = medicalRecordService.sortPeople(medicalRecordService.getResidentMedicalRecords(
                   personService.getFromAddresses(
                           stationService.getAddressStationbyStationNumber(stations))));
           LOGGER.info(String.valueOf(String.valueOf(residentInfoMedicalRecords)));
           return residentInfoMedicalRecords;
       }catch (ClassNotFoundException | IOException e){
           LOGGER.error("Impossible de lire le fichier data.json");
           return null;
       }

    }
}
