package com.openclassrooms.safetyNet.service.impl;

import java.io.IOException;

import java.util.List;
import java.util.stream.Collectors;

import org.codehaus.jackson.JsonProcessingException;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetyNet.dao.PersonDao;
import com.openclassrooms.safetyNet.dao.PersonDaoImpl;
import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.service.PersonService;

@Service("PersonService")
public class PersonServiceImpl implements PersonService {
	private PersonDao personDao = new PersonDaoImpl("src/main/resources/data.json");

	@Override
	public List<String> getPersonPhoneNumberFromAddresses(List<String> addresses)
			throws ClassNotFoundException, JsonProcessingException, IOException {
		List<Person> persons = personDao.list().stream().filter(o -> (addresses.contains(o.getAddress())))
				.toList();
		List<String> phoneNumbers = persons.stream().map(o -> o.getPhone()).collect(Collectors.toList());
		return phoneNumbers;
	}

	public List<String> getPersonEmailFromCity(String city) throws ClassNotFoundException, JsonProcessingException, IOException{
		List<Person> persons = personDao.list().stream().filter(o -> (city.contains(o.getCity())))
				.toList();
		List<String> emails = persons.stream().map(o -> o.getEmail()).collect(Collectors.toList());
		return emails;
	}


	public  List<String> getPersonInfoFromAdresses(List<String> addresses) throws ClassNotFoundException, JsonProcessingException, IOException {
		List<Person> persons = personDao.list().stream().filter(o -> (addresses.contains(o.getAddress())))
				.toList();
		List<String> personInfos = persons.stream()
				.map(o -> "nom : " + o.getFirstName() + ", " + o.getLastName() + ", " + o.getAddress() + ", " + o.getPhone())
				.collect(Collectors.toList());
		return personInfos;
	}


	public  List<String> getPersonFirstNameFromAdresses(List<String> addresses) throws ClassNotFoundException, JsonProcessingException, IOException {
		List<Person> persons = personDao.list().stream().filter(o -> (addresses.contains(o.getAddress())))
				.toList();
		List<String> personInfos = persons.stream().map(o -> o.getFirstName()).collect(Collectors.toList());
		return personInfos;
	}
}


