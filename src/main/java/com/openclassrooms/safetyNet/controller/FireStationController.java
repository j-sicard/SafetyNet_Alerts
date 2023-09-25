package com.openclassrooms.safetyNet.controller;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.openclassrooms.safetyNet.model.FireStation;
import com.openclassrooms.safetyNet.model.Person;
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

        @GetMapping
        public ResidentAndAgesDTO getResidentsWithNumberOfAdultsAndChildrenByStation(@RequestParam String stationNumber)
                throws ClassNotFoundException, JsonProcessingException, IOException {

                List<String> personBirthDates = medicalRecordsService.getPersonBirthDates(personService.getByAdresses(stationService.listStationAddresses(stationNumber)));
                List<Integer> ages = DateUtils.getAges(personBirthDates);
                int nbAdult = personService.getNbAdult(ages);
                int nbChildren = personService.getNbChildren(ages);
                List<ResidentInfoDTO> residents = personService.getByAdresses(stationService.listStationAddresses(stationNumber));

                return new ResidentAndAgesDTO(residents, nbAdult, nbChildren);
        }

      @PostMapping
        public ResponseEntity<String> createStation(@RequestBody FireStation fireStation) throws ClassNotFoundException, IOException {
                stationService.saveStation(fireStation);
                return ResponseEntity.status(HttpStatus.CREATED).body("Nouvelle caserne créée avec succès!");
        }


        @PutMapping
        public ResponseEntity<String> updateStation(@RequestParam String address, @RequestParam String station ,@RequestBody FireStation fireStation)
                throws IOException, ClassNotFoundException {
                stationService.updateStationByAddressStationNumber(address, station, fireStation);
                return  ResponseEntity.status(HttpStatus.CREATED).body("Caserne mise à jour avec succès!");
        }

    @DeleteMapping
    public ResponseEntity<String> deleteStation(@RequestParam String station, @RequestParam String address)
            throws  IOException, ClassNotFoundException {
            stationService.deleteStation(station, address);
            return ResponseEntity.status(HttpStatus.CREATED).body("Caserne suprimée avec succès!");
    }
}


