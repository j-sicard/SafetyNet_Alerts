package com.openclassrooms.safetyNet.dao;

import com.openclassrooms.safetyNet.model.FireStation;
import org.codehaus.jackson.JsonProcessingException;

import java.io.IOException;
import java.util.List;

public interface FireStationDao {
    public List<FireStation> list()throws ClassNotFoundException, JsonProcessingException, IOException;
}
