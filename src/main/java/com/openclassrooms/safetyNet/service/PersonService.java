package com.openclassrooms.safetyNet.service;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonProcessingException;

public interface PersonService {
	public List<String> getPersonPhoneNumberFromAddresses(List<String> addresses)
			throws ClassNotFoundException, JsonProcessingException, IOException;
	public List<String> getPersonEmailFromCity(String city) throws ClassNotFoundException, IOException;

	public List<String> getPersonInfoFromAdresses(List<String> addresses)throws ClassNotFoundException,JsonProcessingException, IOException;

	public List<String> getPersonFirstNameFromAdresses(List<String> addresses)throws ClassNotFoundException,JsonProcessingException, IOException;




}
