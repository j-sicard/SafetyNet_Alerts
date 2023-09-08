package com.openclassrooms.safetyNet.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetyNet.model.FireStation;
import com.openclassrooms.safetyNet.utils.DataExtractor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FireStationDaoImpl implements FireStationDao {
    private String jsonFilePath;

    public FireStationDaoImpl(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
    }

    private final ObjectMapper mapper = new ObjectMapper();
 @Override
    public List<FireStation> list() {
        try {
            return DataExtractor.parseJsonArray(mapper.readTree(new File(this.jsonFilePath)).get("firestations").toString(), FireStation.class);
        } catch (ClassNotFoundException | IOException e) {
            return new ArrayList<FireStation>();
        }
    }

}

