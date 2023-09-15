package com.openclassrooms.safetyNet.dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetyNet.dao.FireStationDao;
import com.openclassrooms.safetyNet.model.FireStation;
import com.openclassrooms.safetyNet.utils.DataExtractor;

public class FireStationDaoImpl implements FireStationDao {
	private String jsonFilePath;

	public FireStationDaoImpl(String jsonFilePath) {
		this.jsonFilePath = jsonFilePath;
	}

	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public List<FireStation> list() {
		try {
			return DataExtractor.parseJsonArray(
					mapper.readTree(new File(this.jsonFilePath)).get("firestations").toString(), FireStation.class);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return new ArrayList<FireStation>();
		}
	}

}
