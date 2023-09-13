package com.openclassrooms.safetyNet.controller;

import com.openclassrooms.safetyNet.dto.model.ResidentInfoModel;
import com.openclassrooms.safetyNet.service.MedicalRecordsService;
import com.openclassrooms.safetyNet.service.ResidentInfoService;
import com.openclassrooms.safetyNet.service.StationService;
import com.openclassrooms.safetyNet.utils.AgesAdministrator;
import org.codehaus.jackson.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;

import static com.openclassrooms.safetyNet.utils.AgesAdministrator.adultChildrenCount;
import com.openclassrooms.safetyNet.dto.model.ResidentAndAgeInfo;

@RestController
public class FireStationController {

        @Autowired
        MedicalRecordsService medicalRecordsService;

        @Autowired
        ResidentInfoService residentInfoService;

        @Autowired
        StationService stationService;

        @GetMapping(value = "/firestation")
        public ResidentAndAgeInfo test(@RequestParam String stationNumber)
                throws ClassNotFoundException, JsonProcessingException, IOException {

                List<String> dateStrings = medicalRecordsService.getPersonBirthDates(residentInfoService.getResidentByAdresses(stationService.listStationAddresses(stationNumber)));
                List<Integer> ages = AgesAdministrator.calculatetAges(dateStrings);
                HashMap<String, Integer> ageInfo = adultChildrenCount(ages);
                List<ResidentInfoModel> residents = residentInfoService.getResidentByAdresses(stationService.listStationAddresses(stationNumber));

                return new ResidentAndAgeInfo(residents, ageInfo);
        }
}


