package com.openclassrooms.safetyNet.controller;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.safetyNet.dto.ResidentAndAgesDTO;
import com.openclassrooms.safetyNet.dto.ResidentInfoDTO;
import com.openclassrooms.safetyNet.service.MedicalRecordService;
import com.openclassrooms.safetyNet.service.PersonService;
import com.openclassrooms.safetyNet.service.StationService;
import com.openclassrooms.safetyNet.utils.DateUtils;

@RestController
public class FireStationController {

        @Autowired
        MedicalRecordService medicalRecordsService;

        @Autowired
        StationService stationService;

        @Autowired
        PersonService personService;

        @GetMapping(value = "/firestation")
        public ResidentAndAgesDTO getResidentsWithNumberOfAdultsAndChildrenByStation(@RequestParam String stationNumber)
                throws ClassNotFoundException, JsonProcessingException, IOException {

                List<String> personBirthDates = medicalRecordsService.getPersonBirthDates(personService.getByAdresses(stationService.listStationAddresses(stationNumber)));
                List<Integer> ages = DateUtils.getAges(personBirthDates);
                int nbAdult = personService.getNbAdult(ages);
                int nbChildren = personService.getNbChildren(ages);
                List<ResidentInfoDTO> residents = personService.getByAdresses(stationService.listStationAddresses(stationNumber));

                return new ResidentAndAgesDTO(residents, nbAdult, nbChildren);
        }
}


