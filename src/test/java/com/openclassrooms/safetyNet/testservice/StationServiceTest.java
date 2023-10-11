package com.openclassrooms.safetyNet.testservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.openclassrooms.safetyNet.model.FireStation;
import com.openclassrooms.safetyNet.service.StationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StationServiceTest {
    @Autowired
    StationService stationService;

    String jsonFilePath = "./src/test/resources/data.json";

    @Test
    public void testListStationAddresses() throws ClassNotFoundException, JsonProcessingException, IOException {
        assertTrue(stationService.listStationAddresses("1").contains("644 Gershwin Cir"));
    }
    @Test
    public void testGetStationByAddress() throws ClassNotFoundException, JsonProcessingException, IOException {
        assertTrue(stationService.getStationByAddress("1509 Culver St").contains("3"));
    }

    @Test
    public void testSaveStation() throws ClassNotFoundException, IOException{
        stationService.saveStation(new FireStation("AddressTest", "StationTest"), jsonFilePath);

        // Rechargez le fichier JSON pour vérifier les données
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode updatedData = objectMapper.readTree(new File(jsonFilePath));

        // Vérifiez que les données de l'enregistrement ajouté correspondent à ce que vous attendiez
        JsonNode addedStation = updatedData.get("firestations").get(updatedData.get("firestations").size() - 1);
        assertEquals("AddressTest", addedStation.get("address").asText());
        assertEquals("StationTest", addedStation.get("station").asText());

        stationService.deleteStation("StationTest", "AddressTest", jsonFilePath);
    }

    @Test
    public void testUpdateStationByAddressStationNumber() throws ClassNotFoundException, IOException {
        stationService.saveStation(new FireStation("AddressTest", "StationTest"), jsonFilePath);

        stationService.updateStationByAddressStationNumber(
                "AddressTest", "StationTest", new FireStation("AddressTest", "UpDatestation"), jsonFilePath);

        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File(jsonFilePath);

        // Rechargez le fichier JSON pour vérifier les données
        JsonNode updatedData = objectMapper.readTree(jsonFile);

        // Vérifiez que la station a été mise à jour correctement
        ArrayNode firestations = (ArrayNode) updatedData.get("firestations");
        for (JsonNode firestationNode : firestations) {
            String address = firestationNode.get("address").asText();
            String station = firestationNode.get("station").asText();
            if (address.equals("AddressTest") && station.equals("UpDatestation")) {
                // Trouvé la station mise à jour, vérifiez ses données
                assertEquals("AddressTest", address);
                assertEquals("UpDatestation", station);
                return;

            }
            stationService.deleteStation("UpDatestation", "AddressTest", jsonFilePath);
        }
        fail("FireStation was not updated in the JSON file.");
    }


    @Test
    public void testDeleteStation()throws ClassNotFoundException, IOException{
        stationService.saveStation(new FireStation("AddressTest", "StationTest"), jsonFilePath);
        stationService.deleteStation("StationTest", "AddressTest", jsonFilePath);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode updatedData = objectMapper.readTree(new File(jsonFilePath));

        ArrayNode firestations = (ArrayNode) updatedData.get("firestations");
        boolean found = false;
        for (JsonNode record : firestations) {
            String address = record.get("address").asText();
            String station = record.get("station").asText();
            if (address.equals("AddressTest") && station.equals("StationTest")) {
                found = true;
                break;
            }
        }
        assertFalse(found, "L'enregistrement de la station supprimé ne doit pas être présent.");
    }


}
