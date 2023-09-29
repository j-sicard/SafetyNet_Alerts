package com.openclassrooms.safetyNet.controller;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.openclassrooms.safetyNet.model.FireStation;
import com.openclassrooms.safetyNet.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.openclassrooms.safetyNet.dto.ResidentAndAgesDTO;
import com.openclassrooms.safetyNet.dto.ResidentInfoDTO;
import com.openclassrooms.safetyNet.service.MedicalRecordService;
import com.openclassrooms.safetyNet.service.PersonService;
import com.openclassrooms.safetyNet.service.StationService;
import com.openclassrooms.safetyNet.utils.DateUtils;


@RequestMapping("/firestation")
@RestController
public class FireStationController {
        @Autowired
        MedicalRecordService medicalRecordsService;
        @Autowired
        StationService stationService;
        @Autowired
        PersonService personService;

        private static final Logger LOGGER =  LogManager.getLogger( FireStationController.class );

        @GetMapping
        public ResidentAndAgesDTO getResidentsWithNumberOfAdultsAndChildrenByStation(@RequestParam String stationNumber){
                try {
                    List<String> personBirthDates = medicalRecordsService.getPersonBirthDates(personService.getByAdresses(stationService.listStationAddresses(stationNumber)));
                    List<Integer> ages = DateUtils.getAges(personBirthDates);
                    int nbAdult = personService.getNbAdult(ages);
                    int nbChildren = personService.getNbChildren(ages);
                    List<ResidentInfoDTO> residents = personService.getByAdresses(stationService.listStationAddresses(stationNumber));
                    LOGGER.info(residents);
                    return new ResidentAndAgesDTO(residents, nbAdult, nbChildren);
                } catch (ClassNotFoundException | IOException e) {
                    LOGGER.error("Impossible de lire le fichier data.json");
                    return null;
                }
        }

      @PostMapping
        public ResponseEntity<String> createStation(@RequestBody FireStation fireStation) {
            try {
                stationService.saveStation(fireStation);
                LOGGER.info(ResponseEntity.status(HttpStatus.CREATED).body("Nouvelle caserne créée avec succès!"));
                return ResponseEntity.status(HttpStatus.CREATED).body("Nouvelle caserne créée avec succès!");
            }catch (ClassNotFoundException | IOException e){
                LOGGER.error("Impossible de crée une nouvelle caserne dans le fichier data.json");
                return null;
            }
        }



        @PutMapping
        public ResponseEntity<String> updateStation(
                @RequestParam String address, @RequestParam String station ,@RequestBody FireStation fireStation){
            try {
                stationService.updateStationByAddressStationNumber(address, station, fireStation);
                LOGGER.info(ResponseEntity.status(HttpStatus.CREATED).body("Caserne mise à jour avec succès!"));
                return  ResponseEntity.status(HttpStatus.CREATED).body("Caserne mise à jour avec succès!");
            }catch (ClassNotFoundException | IOException e ){
                LOGGER.error("Impossible de modifiée la nouvelle caserne dans le fichier data.json");
                return null;
            }
        }

        @DeleteMapping
        public ResponseEntity<String> deleteStation(@RequestParam String station, @RequestParam String address){
                try {
                 stationService.deleteStation(station, address);
                 return ResponseEntity.status(HttpStatus.CREATED).body("Caserne suprimée avec succès!");
             }catch (ClassNotFoundException | IOException e ){
                    LOGGER.error("Impossible de suprimée la nouvelle caserne dans le fichier data.json");
                 return null;
             }
        }
}


