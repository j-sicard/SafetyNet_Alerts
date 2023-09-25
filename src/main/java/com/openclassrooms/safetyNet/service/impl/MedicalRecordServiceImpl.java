package com.openclassrooms.safetyNet.service.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.openclassrooms.safetyNet.dto.ResidentInfoMedicalRecordsDTO;
import com.openclassrooms.safetyNet.model.FireStation;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetyNet.dao.MedicalRecordDao;
import com.openclassrooms.safetyNet.dao.impl.MedicalRecordDaoImpl;
import com.openclassrooms.safetyNet.dto.PersonMedicalRecordDTO;
import com.openclassrooms.safetyNet.dto.ResidentInfoDTO;
import com.openclassrooms.safetyNet.model.MedicalRecord;
import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.service.MedicalRecordService;
import com.openclassrooms.safetyNet.utils.DateUtils;


@Service("MedicalRecordService")
public class MedicalRecordServiceImpl implements MedicalRecordService {
    private MedicalRecordDao medicalRecordDao = new MedicalRecordDaoImpl("src/main/resources/data.json");

    public List<String> getPersonBirthDates(List<ResidentInfoDTO> personInfos)
            throws ClassNotFoundException, JsonProcessingException, IOException{
        List<String>birthDates = new ArrayList<String>();

        Set<String> personFirstNames = personInfos.stream()
                .map(ResidentInfoDTO::getFirstName)
                .collect(Collectors.toSet());

        for(MedicalRecord medicalRecords: medicalRecordDao.list()){
            if (personFirstNames.contains(medicalRecords.getFirstName())){
                birthDates.add(medicalRecords.getBirthdate());
            }
        }
        return birthDates;
    }

    @Override
    public List<PersonMedicalRecordDTO> getPersonsMedicalRecords(List<Person> persons)
            throws ClassNotFoundException, JsonProcessingException, IOException {
        List<PersonMedicalRecordDTO> personsMedicalRecords = new ArrayList<PersonMedicalRecordDTO>();
        for(Person person : persons) {
            for(MedicalRecord record : medicalRecordDao.list()) {
                if(person.getFirstName().toUpperCase().equals(record.getFirstName().toUpperCase()) && person.getLastName().toUpperCase().equals(record.getLastName().toUpperCase())) {
                    personsMedicalRecords.add(new PersonMedicalRecordDTO(person.getFirstName(), person.getLastName(), person.getAddress(), DateUtils.getAge(record.getBirthdate()), person.getEmail(), record.getMedications(), record.getAllergies()));
                }
            }
        }
        return personsMedicalRecords;
    }

    @Override
    public List<ResidentInfoMedicalRecordsDTO> getResidentMedicalRecords(List<Person> persons)
            throws ClassNotFoundException, JsonProcessingException, IOException {
        List<ResidentInfoMedicalRecordsDTO> personsMedicalRecords = new ArrayList<ResidentInfoMedicalRecordsDTO>();
        for(Person person : persons) {
            for(MedicalRecord record : medicalRecordDao.list()) {
                if(person.getFirstName().toUpperCase().equals(record.getFirstName().toUpperCase()) && person.getLastName().toUpperCase().equals(record.getLastName().toUpperCase())) {
                    personsMedicalRecords.add(new ResidentInfoMedicalRecordsDTO(person.getLastName(), person.getPhone(), DateUtils.getAge(record.getBirthdate()), record.getMedications(), record.getAllergies()));
                }
            }
        }
        return personsMedicalRecords;
    }

    public List<ResidentInfoMedicalRecordsDTO> sortPeople(List<ResidentInfoMedicalRecordsDTO> people) {

        Collections.sort(people, Comparator
                .comparing(ResidentInfoMedicalRecordsDTO::getLastName)
                .thenComparing(ResidentInfoMedicalRecordsDTO::getPhone)
                .thenComparing(ResidentInfoMedicalRecordsDTO::getAge)
                .thenComparing(residentInfoMedicalRecordsDTO -> residentInfoMedicalRecordsDTO.getMedications().toString())
                .thenComparing(residentInfoMedicalRecordsDTO -> residentInfoMedicalRecordsDTO.getAllergies().toString()));

        return people;
    }

    public List<MedicalRecord> saveMedicalRecord(MedicalRecord medicalRecord){

        try {
            // Charger le fichier JSON existant
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Pour une meilleure lisibilité

            File jsonFile = new File("src/main/resources/data.json");
            ObjectNode rootNode = (ObjectNode) objectMapper.readTree(jsonFile);

            // Récupérer le tableau "medicalrecords"
            ArrayNode medicalRecordsArray = (ArrayNode) rootNode.get("medicalrecords");

            // Convertir l'objet medicalrecord en objet JSON
            ObjectNode newMedicalrecordNode = objectMapper.valueToTree(medicalRecord);

            // Ajouter le nouvel objet "medicalrecord" au tableau
            medicalRecordsArray.add(newMedicalrecordNode);

            // Réécrire le fichier JSON avec le nouvel objet ajouté
            objectMapper.writeValue(jsonFile, rootNode);

            System.out.println("Nouveau dossier médical ajoutait avec succès au fichier JSON!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MedicalRecord>updateMedicalRecord(String firstName, String lastName, MedicalRecord updatedMedicalRecord){
        try {
            // Charger le fichier JSON existant
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

            File jsonFile = new File("src/main/resources/data.json");
            ObjectNode rootNode = (ObjectNode) objectMapper.readTree(jsonFile);
            ArrayNode medicationsNode = objectMapper.createArrayNode();
            ArrayNode allergiesNode = objectMapper.createArrayNode();

            // Parcourir le tableau "medicalrecords" pour trouver le dossier médical à mettre à jour
            for (JsonNode medicalRecordNode : (ArrayNode) rootNode.get("medicalrecords")) {
                if (medicalRecordNode.get("firstName").asText().equals(firstName) && medicalRecordNode.get("lastName").asText().equals(lastName)) {
                    for (String medication : updatedMedicalRecord.getMedications()) {
                        medicationsNode.add(medication);
                    }
                    for (String allergies : updatedMedicalRecord.getAllergies()) {
                        allergiesNode.add(allergies);
                    }
                    ((ObjectNode) medicalRecordNode).put("birthdate", updatedMedicalRecord.getBirthdate()); // Mettez à jour la date de naissance
                    ((ObjectNode) medicalRecordNode).set("medications", medicationsNode);// Mettez à jour les médicaments
                    ((ObjectNode) medicalRecordNode).set("allergies", allergiesNode);// Mettez à jour les allergies
                    break;
                }
            }
            objectMapper.writeValue(jsonFile, rootNode);
            System.out.println("Dossier médical mis à jour avec succès dans le fichier JSON !!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MedicalRecord> deleteMedicalRecord(String firstName, String lastName) throws IOException, ClassNotFoundException {
        // Charger le fichier JSON existant
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        File jsonFile = new File("src/main/resources/data.json");
        ObjectNode rootNode = (ObjectNode) objectMapper.readTree(jsonFile);

        // Récupérer le tableau "medicalrecords"
        ArrayNode medicalRecordsArray = (ArrayNode) rootNode.get("medicalrecords");

        // Utiliser un itérateur pour parcourir le tableau "medicalrecords"
        Iterator<JsonNode> MedicalRecordIterator = medicalRecordsArray.elements();
        while (MedicalRecordIterator.hasNext()) {
            JsonNode stationNode = MedicalRecordIterator.next();
            String medicalRecordFirstName = stationNode.get("firstName").asText();
            String medicalRecordLastName = stationNode.get("lastName").asText();

            if (medicalRecordFirstName.equals(firstName) && medicalRecordLastName.equals(lastName)) {
                // Supprimer le Dossier médicale si il correspond aux critères
                MedicalRecordIterator.remove();
                break;
            }
        }
        // Réécrire le fichier JSON sans le Dossier médicale supprimé
        objectMapper.writeValue(jsonFile, rootNode);
        System.out.println("Dossier médicale supprimé avec succès du fichier JSON !!");
        return null;
    }
}