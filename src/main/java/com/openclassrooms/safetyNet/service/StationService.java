package com.openclassrooms.safetyNet.service;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonProcessingException;

public interface StationService {
	public List<String> listStationAddresses(String firestation)
			throws ClassNotFoundException, JsonProcessingException, IOException;

	public String GetStationByAddress(String address) throws ClassNotFoundException, JsonProcessingException, IOException;
}
