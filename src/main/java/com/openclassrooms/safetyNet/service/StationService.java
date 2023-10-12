package com.openclassrooms.safetyNet.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.openclassrooms.safetyNet.model.FireStation;

import java.io.IOException;
import java.util.List;

public interface StationService {
	public List<String> listStationAddresses(String firestation)
			throws ClassNotFoundException, JsonProcessingException, IOException;

	public String getStationByAddress(String address) throws ClassNotFoundException, JsonProcessingException, IOException;

	public List<String> getAddressStationbyStationNumber(List<String> stationNumbers)
			throws ClassNotFoundException, JsonProcessingException, IOException;

	public List<FireStation> saveStation(FireStation fireStation, String jsonFile)throws ClassNotFoundException,  IOException;

	public List<FireStation> updateStationByAddressStationNumber(String address, String station, FireStation fireStation, String jsonFile)throws ClassNotFoundException, IOException;

	public List<FireStation> deleteStation( String stationNumber, String addressStation, String jsonFile) throws IOException, ClassNotFoundException;

}
