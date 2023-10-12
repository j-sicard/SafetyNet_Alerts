package com.openclassrooms.safetyNet.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.openclassrooms.safetyNet.dto.ResidentInfoMedicalRecordsDTO;
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

    public List<MedicalRecord> saveMedicalRecord(MedicalRecord medicalRecord, String jsonFile) throws IOException {
        // Charger le fichier JSON existant
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Pour une meilleure lisibilité

        // Lire le fichier JSON existant
        JsonNode rootNode = objectMapper.readTree(new File(jsonFile));

        // Récupérer le tableau "medicalrecords"
        ArrayNode medicalRecordsArray = (ArrayNode) rootNode.withArray("medicalrecords");

        // Convertir l'objet medicalRecord en objet JSON
        JsonNode newMedicalRecordNode = objectMapper.valueToTree(medicalRecord);

        // Ajouter le nouvel objet "medicalrecord" au tableau
        medicalRecordsArray.add(newMedicalRecordNode);

        // Réécrire le fichier JSON avec le nouvel objet ajouté
        objectMapper.writeValue(new File(jsonFile), rootNode);
        return null;
    }

    public List<MedicalRecord> updateMedicalRecord(String firstName, String lastName, MedicalRecord updatedMedicalRecord, String jsonFile) throws IOException, ClassNotFoundException{
        // Charger le fichier JSON existant
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        File file = new File(jsonFile); // Créez un objet File à partir de la chaîne de chemin

        ObjectNode rootNode = (ObjectNode) objectMapper.readTree(file);

        // Parcourir le tableau "medicalrecords" pour trouver le dossier médical à mettre à jour
        ArrayNode medicationsNode = objectMapper.createArrayNode();
        ArrayNode allergiesNode = objectMapper.createArrayNode();

        for (JsonNode medicalRecordNode : (ArrayNode) rootNode.get("medicalrecords")) {
            if (medicalRecordNode.get("firstName").asText().equals(firstName) && medicalRecordNode.get("lastName").asText().equals(lastName)) {
                for (String medication : updatedMedicalRecord.getMedications()) {
                    medicationsNode.add(medication);
                }
                for (String allergies : updatedMedicalRecord.getAllergies()) {
                    allergiesNode.add(allergies);
                }
                ((ObjectNode) medicalRecordNode).put("birthdate", updatedMedicalRecord.getBirthdate()); // Mettez à jour la date de naissance
                ((ObjectNode) medicalRecordNode).set("medications", medicationsNode); // Mettez à jour les médicaments
                ((ObjectNode) medicalRecordNode).set("allergies", allergiesNode); // Mettez à jour les allergies
                break;
            }
        }
        // Réécrivez le fichier JSON avec l'enregistrement médical mis à jour
        objectMapper.writeValue(file, rootNode);
        return null;
    }

    public List<MedicalRecord> deleteMedicalRecord(String firstName, String lastName, String jsonFile) throws IOException, ClassNotFoundException {
        // Charger le fichier JSON existant
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Convertir le chemin du fichier en objet File
        File jsonFileObj = new File(jsonFile);
        ObjectNode rootNode = (ObjectNode) objectMapper.readTree(jsonFileObj);

        // Récupérer le tableau "medicalrecords"
        ArrayNode medicalRecordsArray = (ArrayNode) rootNode.get("medicalrecords");

        // Utiliser un itérateur pour parcourir le tableau "medicalrecords"
        Iterator<JsonNode> medicalRecordIterator = medicalRecordsArray.elements();
        while (medicalRecordIterator.hasNext()) {
            JsonNode medicalRecordNode = medicalRecordIterator.next();
            String medicalRecordFirstName = medicalRecordNode.get("firstName").asText();
            String medicalRecordLastName = medicalRecordNode.get("lastName").asText();

            if (medicalRecordFirstName.equals(firstName) && medicalRecordLastName.equals(lastName)) {
                // Supprimer le Dossier médical s'il correspond aux critères
                medicalRecordIterator.remove();
                break;
            }
        }

        // Réécrire le fichier JSON sans le Dossier médical supprimé
        objectMapper.writeValue(jsonFileObj, rootNode);
        return null;
    }
}