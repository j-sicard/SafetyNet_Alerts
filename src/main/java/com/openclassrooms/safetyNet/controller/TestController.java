package com.openclassrooms.safetyNet.controller;

import com.openclassrooms.safetyNet.dto.ResidentInfoDao;
import com.openclassrooms.safetyNet.dto.model.ResidentInfoModel;
import com.openclassrooms.safetyNet.service.MedicalRecordsService;
import com.openclassrooms.safetyNet.service.ResidentInfoService;
import org.codehaus.jackson.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class TestController {
        @Autowired
        ResidentInfoDao residentInfoDao;

        @Autowired
        MedicalRecordsService medicalRecordsService;

        @Autowired
        ResidentInfoService residentInfoService;

        @GetMapping(value = "/test")
        public List<String> test(@RequestParam String stationAdresses)
                throws ClassNotFoundException, JsonProcessingException, IOException {

            return medicalRecordsService.getPersonBirthDates(residentInfoService.getResidentByAdresses(stationAdresses));
    }
}


