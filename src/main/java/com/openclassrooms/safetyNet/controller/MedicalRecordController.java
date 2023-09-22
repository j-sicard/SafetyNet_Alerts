package com.openclassrooms.safetyNet.controller;

import com.openclassrooms.safetyNet.model.MedicalRecord;
import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("/medicalRecord")
@RestController
public class MedicalRecordController {
    @Autowired
    MedicalRecordService medicalRecordService;
    @PostMapping
    public ResponseEntity<String> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) throws ClassNotFoundException, IOException {
        medicalRecordService.saveMedicalRecord(medicalRecord);
        String responseMessage = "Nouveau dossier médical créé avec succès!";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

}
