package com.openclassrooms.safetyNet.controller;

import com.openclassrooms.safetyNet.model.MedicalRecord;
import com.openclassrooms.safetyNet.service.MedicalRecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger LOGGER =  LogManager.getLogger( ChildrenAlertController.class );

    @PostMapping
    public ResponseEntity<String> createMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        try {
            medicalRecordService.saveMedicalRecord(medicalRecord);
            LOGGER.info(ResponseEntity.status(HttpStatus.CREATED).body("Nouveau dossier médical créé avec succès!"));
            LOGGER.info("Body :" + medicalRecord);
            return ResponseEntity.status(HttpStatus.CREATED).body("Nouveau dossier médical créé avec succès!");
        }catch(ClassNotFoundException | IOException e){
            LOGGER.error("Impossible de crée le dossier médical dans le fichier data.json");
            return null;
        }
    }

    @PutMapping
    public ResponseEntity<String> updateMedicalRecord(
            @RequestParam String firstName, @RequestParam String lastName ,@RequestBody MedicalRecord updatedMedicalRecord){
        try {
            medicalRecordService.updateMedicalRecord(firstName, lastName, updatedMedicalRecord);
            LOGGER.info(ResponseEntity.status(HttpStatus.CREATED).body("dossier médical mise à jour avec succès!"));
            LOGGER.info("body :" + updatedMedicalRecord);
            return  ResponseEntity.status(HttpStatus.CREATED).body("dossier médical mise à jour avec succès!");
        }catch(ClassNotFoundException | IOException e){
            LOGGER.error("Impossible de modifié le dossier médical dans le fichier data.json");
            return null;
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteMedicalRecord(@RequestParam String firstName, @RequestParam String lastName){
        try {
            medicalRecordService.deleteMedicalRecord(firstName, lastName);
            LOGGER.info(ResponseEntity.status(HttpStatus.CREATED).body("le dossier médical a été suprimée avec succès!"));
            return ResponseEntity.status(HttpStatus.CREATED).body("le dossier médical a été suprimée avec succès!");
        }catch (ClassNotFoundException | IOException e){
            LOGGER.error("Impossible de supprimé le dossier médical dans le fichier data.json");
            return null;
        }
    }
}
