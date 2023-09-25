package com.openclassrooms.safetyNet.controller;

import com.openclassrooms.safetyNet.model.FireStation;
import com.openclassrooms.safetyNet.model.MedicalRecord;
import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping
    public ResponseEntity<String> updateMedicalRecord(@RequestParam String firstName, @RequestParam String lastName ,@RequestBody MedicalRecord updatedMedicalRecord) throws IOException, ClassNotFoundException {
        medicalRecordService.updateMedicalRecord(firstName, lastName, updatedMedicalRecord);
        return  ResponseEntity.status(HttpStatus.CREATED).body("dossier médical mise à jour avec succès!");
    }

}
