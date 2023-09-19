package com.openclassrooms.safetyNet.service;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonProcessingException;

import com.openclassrooms.safetyNet.dto.ResidentInfoDTO;
import com.openclassrooms.safetyNet.model.Person;

public interface PersonService {
	public List<String> getPersonPhoneNumberFromAddresses(List<String> addresses)
			throws ClassNotFoundException, JsonProcessingException, IOException;
	public List<String> getPersonEmailFromCity(String city) throws ClassNotFoundException, IOException;

	public List<String> getPersonInfoFromAdresses(List<String> addresses)throws ClassNotFoundException,JsonProcessingException, IOException;

	public List<String> getPersonFirstNameFromAdresses(List<String> addresses)throws ClassNotFoundException,JsonProcessingException, IOException;

	public List<Person> getPersonInfoFromAddress(String address)throws ClassNotFoundException, JsonProcessingException, IOException;

	public List<ResidentInfoDTO> getByAdresses(List<String> adresses) throws ClassNotFoundException, IOException, JsonProcessingException;

	public int getNbAdult(List<Integer> ages);

	public int getNbChildren(List<Integer> ages);

	public List<Person> getByFirstNameAndLastName(String lastName) throws ClassNotFoundException, JsonProcessingException, IOException;
}
