package com.openclassrooms.safetyNet.service.impl;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.openclassrooms.safetyNet.model.Person;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetyNet.dao.FireStationDao;
import com.openclassrooms.safetyNet.dao.impl.FireStationDaoImpl;
import com.openclassrooms.safetyNet.model.FireStation;
import com.openclassrooms.safetyNet.service.StationService;

@Service("StationService")
public class StationServiceImpl implements StationService {
    private FireStationDao fireStationDao = new FireStationDaoImpl("src/main/resources/data.json");

    public List<String> listStationAddresses(String station)
            throws ClassNotFoundException, JsonProcessingException, IOException {
        List<FireStation> fireStations = fireStationDao.list().stream().filter(o -> (o.getStation().equals(station)))
                .toList();
        List<String> stationAddresses = fireStations.stream().map(o -> o.getAddress()).collect(Collectors.toList());
        return stationAddresses;
    }

    public String getStationByAddress(String address) throws ClassNotFoundException, JsonProcessingException, IOException{
        List<FireStation> fireStations = fireStationDao.list().stream().filter(o -> (o.getAddress().equals(address)))
                .toList();
        String stationNumber = fireStations.stream().map(o -> o.getStation()).collect(Collectors.toList()).toString();
        return stationNumber;
    }

    public List<String> getAddressStationbyStationNumber(List<String> station)
            throws ClassNotFoundException, JsonProcessingException, IOException {
        List<String> stationAddresses = new ArrayList<>();
        for (FireStation stationService : fireStationDao.list()){
            if (station.contains(stationService.getStation())){
                stationAddresses.add(stationService.getAddress());
            }
        }
        return stationAddresses;
    }

    public List<FireStation> saveStation(FireStation fireStation){

        try {
            // Charger le fichier JSON existant
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Pour une meilleure lisibilité

            File jsonFile = new File("src/main/resources/data.json");
            ObjectNode rootNode = (ObjectNode) objectMapper.readTree(jsonFile);

            // Récupérer le tableau "persons"
            ArrayNode stationsArray = (ArrayNode) rootNode.get("firestations");

            // Convertir l'objet Person en objet JSON
            ObjectNode newStationNode = objectMapper.valueToTree(fireStation);

            // Ajouter le nouvel objet "fireStation" au tableau
            stationsArray.add(newStationNode);

            // Réécrire le fichier JSON avec le nouvel objet ajouté
            objectMapper.writeValue(jsonFile, rootNode);

            System.out.println("Nouvelle caserne ajoutée avec succès au fichier JSON !!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
