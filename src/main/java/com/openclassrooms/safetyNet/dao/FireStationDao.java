package com.openclassrooms.safetyNet.dao;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.openclassrooms.safetyNet.model.FireStation;

public interface FireStationDao {
	public List<FireStation> list() throws ClassNotFoundException, JsonProcessingException, IOException;

}
