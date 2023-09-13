package com.openclassrooms.safetyNet.dao;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonProcessingException;

import com.openclassrooms.safetyNet.model.FireStation;

public interface FireStationDao {
	public List<FireStation> list() throws ClassNotFoundException, JsonProcessingException, IOException;

}
