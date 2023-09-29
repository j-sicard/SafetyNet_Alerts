package com.openclassrooms.safetyNet.service.impl;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetyNet.dao.FireStationDao;
import com.openclassrooms.safetyNet.dao.impl.FireStationDaoImpl;
import com.openclassrooms.safetyNet.model.FireStation;
import com.openclassrooms.safetyNet.service.StationService;

import javax.xml.crypto.Data;

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

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<FireStation> updateStationByAddressStationNumber(String address, String station, FireStation updatedFireStation) throws ClassNotFoundException, IOException {

            // Charger le fichier JSON existant
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

            File jsonFile = new File("src/main/resources/data.json");
            ObjectNode rootNode = (ObjectNode) objectMapper.readTree(jsonFile);
            // Récupérer le tableau "firestations"
            ArrayNode stationsArray = (ArrayNode) rootNode.get("firestations");
            // Parcourir le tableau "firestations" pour trouver la caserne à mettre à jour

            for (JsonNode stationNode : stationsArray) {
                String stationAddress = stationNode.get("address").asText();
                String stationNumber = stationNode.get("station").asText();

                if (stationAddress.equals(address) && stationNumber.equals(station)) {
                    // Mettre à jour la caserne d'incendie si elle correspond aux critères
                    ((ObjectNode) stationNode).put("station", updatedFireStation.getStation()); // Mettez à jour le numéro de station
                    break; // Sortez de la boucle une fois que la mise à jour est effectuée
                }
            }
            // Réécrire le fichier JSON avec la caserne d'incendie mise à jour
            objectMapper.writeValue(jsonFile, rootNode);
        return null;
    }

    public List<FireStation> deleteStation(String station, String address) throws IOException, ClassNotFoundException {
        // Charger le fichier JSON existant
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        File jsonFile = new File("src/main/resources/data.json");
        ObjectNode rootNode = (ObjectNode) objectMapper.readTree(jsonFile);

        // Récupérer le tableau "firestations"
        ArrayNode stationsArray = (ArrayNode) rootNode.get("firestations");

        // Utiliser un itérateur pour parcourir le tableau "firestations"
        Iterator<JsonNode> stationIterator = stationsArray.elements();
        while (stationIterator.hasNext()) {
            JsonNode stationNode = stationIterator.next();
            String stationAddress = stationNode.get("address").asText();
            String stationNumber = stationNode.get("station").asText();

            if (stationAddress.equals(address) && stationNumber.equals(station)) {
                // Supprimer la caserne d'incendie si elle correspond aux critères
                stationIterator.remove();
                break; // Sortez de la boucle une fois que la suppression est effectuée
            }
        }

        // Réécrire le fichier JSON sans la caserne d'incendie supprimée
        objectMapper.writeValue(jsonFile, rootNode);
        return null;
    }

}
