package com.openclassrooms.safetyNet.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.codehaus.jackson.JsonProcessingException;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetyNet.dao.PersonDao;
import com.openclassrooms.safetyNet.dao.impl.PersonDaoImpl;
import com.openclassrooms.safetyNet.dto.ResidentInfoDTO;
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

	public List<ResidentInfoDTO> getByAdresses(List<String> adresses) throws ClassNotFoundException, IOException, JsonProcessingException{
		List<ResidentInfoDTO> residents = new ArrayList<>();

		for (Person person : personDao.list()) {
			if (adresses.contains(person.getAddress())) {
				residents.add(new ResidentInfoDTO(person.getFirstName(), person.getLastName(), person.getAddress(), person.getPhone()));
			}
		}
		return residents;
	}

	public int getNbChildren(List<Integer> ages) {
		int nbChildren = 0;
		for (int age : ages){
			if (age <= 17){
				nbChildren++;
			}
		}
		return nbChildren;
	}

	public int getNbAdult(List<Integer> ages) {
		int nbAdult = 0;
		for (int age : ages){
			if (age > 17){
				nbAdult++;
			}
		}
		return nbAdult;
	}

	@Override
	public List<Person> getByFirstNameAndLastName(String lastName) throws ClassNotFoundException, JsonProcessingException, IOException {
		List<Person> personsToFind = new ArrayList<Person>();
		List<Person> persons = personDao.list();
		for(Person person: persons) {
			if(person.getLastName().toUpperCase().equals(lastName.toUpperCase())) personsToFind.add(person);
		}
		return personsToFind;
	}
}


