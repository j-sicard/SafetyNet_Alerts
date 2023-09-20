package com.openclassrooms.safetyNet.controller;

import com.openclassrooms.safetyNet.dto.ResidentInfoMedicalRecordsDTO;
import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.service.MedicalRecordService;
import com.openclassrooms.safetyNet.service.PersonService;
import com.openclassrooms.safetyNet.service.StationService;
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

   @GetMapping(value = "/station")
    public List<ResidentInfoMedicalRecordsDTO> getStations(@RequestParam(value="stations") List<String> stations)
           throws IOException, ClassNotFoundException {
        return medicalRecordService.sortPeople(medicalRecordService.getResidentMedicalRecords(
                personService.sortPeopleByAddress(personService.getFromAddresses(
                stationService.getAddressStationbyStationNumber(stations)))));
    }
}
