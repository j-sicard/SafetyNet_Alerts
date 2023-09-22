package com.openclassrooms.safetyNet.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.openclassrooms.safetyNet.dao.MedicalRecordDao;
import com.openclassrooms.safetyNet.dao.impl.MedicalRecordDaoImpl;
import com.openclassrooms.safetyNet.dto.PersonInfoForChildAlertDTO;
import com.openclassrooms.safetyNet.dto.ResidentInfoMedicalRecordsDTO;
import com.openclassrooms.safetyNet.model.MedicalRecord;
import com.openclassrooms.safetyNet.utils.DateUtils;

import org.json.JSONException;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetyNet.dao.PersonDao;
import com.openclassrooms.safetyNet.dao.impl.PersonDaoImpl;
import com.openclassrooms.safetyNet.dto.ResidentInfoDTO;
import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.service.PersonService;

@Service("PersonService")
public class PersonServiceImpl implements PersonService {
	private PersonDao personDao = new PersonDaoImpl("src/main/resources/data.json");
	private MedicalRecordDao medicalRecordDao = new MedicalRecordDaoImpl("src/main/resources/data.json");

	@Override
	public List<String> getPersonPhoneNumberFromAddresses(List<String> addresses)
			throws ClassNotFoundException, JsonProcessingException, IOException {
		List<Person> persons = personDao.list().stream().filter(o -> (addresses.contains(o.getAddress())))
				.toList();
		List<String> phoneNumbers = persons.stream().map(o -> o.getPhone()).collect(Collectors.toList());
		return phoneNumbers;
	}

	public List<String> getPersonEmailFromCity(String city)
			throws ClassNotFoundException, JsonProcessingException, IOException{
		List<Person> persons = personDao.list().stream().filter(o -> (city.contains(o.getCity())))
				.toList();
		List<String> emails = persons.stream().map(o -> o.getEmail()).collect(Collectors.toList());
		return emails;
	}

	public  List<String> getPersonInfoFromAdresses(List<String> addresses)
			throws ClassNotFoundException, JsonProcessingException, IOException {
		List<Person> persons = personDao.list().stream().filter(o -> (addresses.contains(o.getAddress())))
				.toList();
		List<String> personInfos = persons.stream()
				.map(o -> o.getFirstName() + ", " + o.getLastName() + ", " + o.getAddress() + ", " + o.getPhone())
				.collect(Collectors.toList());
		return personInfos;
	}

	public  List<String> getPersonFirstNameFromAdresses(List<String> addresses)
			throws ClassNotFoundException, JsonProcessingException, IOException {
		List<Person> persons = personDao.list().stream().filter(o -> (addresses.contains(o.getAddress())))
				.toList();
		List<String> personInfos = persons.stream().map(o -> o.getFirstName()).collect(Collectors.toList());
		return personInfos;
	}

	public List<ResidentInfoDTO> getByAdresses(List<String> addresses)
			throws ClassNotFoundException, IOException, JsonProcessingException{
		List<ResidentInfoDTO> residents = new ArrayList<>();

		for (Person person : personDao.list()) {
			if (addresses.contains(person.getAddress())) {
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
	public List<Person> getByFirstNameAndLastName(String lastName)
			throws ClassNotFoundException, JsonProcessingException, IOException {
		List<Person> personsToFind = new ArrayList<Person>();
		List<Person> persons = personDao.list();
		for(Person person: persons) {
			if(person.getLastName().toUpperCase().equals(lastName.toUpperCase())) personsToFind.add(person);
		}
		return personsToFind;
	}

	public List<Person> getPersonInfoFromAddress(String address)
			throws ClassNotFoundException, IOException, JsonProcessingException{
		List<ResidentInfoMedicalRecordsDTO> residents = new ArrayList<>();

		List<Person> personsToFind = new ArrayList<Person>();
		List<Person> persons = personDao.list();
		for(Person person: persons) {
			if(person.getAddress().toUpperCase().equals(address.toUpperCase())) personsToFind.add(person);
		}
		return personsToFind;
	}

	public List<Person> getFromAddresses(List<String> stationsAddresses )
			throws ClassNotFoundException, JsonProcessingException, IOException{
		List<Person> persons = new ArrayList<>();
		for (Person person : personDao.list()){
			if (stationsAddresses.contains(person.getAddress())){
				persons.add(person);
			}
		}
		return persons;
	}

	public List<Person> sortPeopleByAddress(List<Person> people) throws
			ClassNotFoundException, JsonProcessingException, IOException {
		Comparator<Person> addressComparator = Comparator.comparing(Person::getAddress);

		List<Person> sortedPeople = people.stream()
				.sorted(addressComparator)
				.collect(Collectors.toList());

		return sortedPeople;
	}

	public List<PersonInfoForChildAlertDTO>getPersonInfoForChildAlert(List<Person> persons)
			throws ClassNotFoundException, JsonProcessingException, IOException{
		List<PersonInfoForChildAlertDTO>infoForChildAlerts= new ArrayList<>();
		for (Person person: persons){
			for (MedicalRecord record: medicalRecordDao.list()){
				if(person.getFirstName().toUpperCase().equals(record.getFirstName().toUpperCase()) && person.getLastName().toUpperCase().equals(record.getLastName().toUpperCase())) {
					infoForChildAlerts.add(new PersonInfoForChildAlertDTO(person.getFirstName(), person.getLastName(), DateUtils.getAge(record.getBirthdate())));
				}
			}
		}
		return infoForChildAlerts;
	}
	public List<PersonInfoForChildAlertDTO> getChildren(List<PersonInfoForChildAlertDTO> persons){
		List<PersonInfoForChildAlertDTO> children = new ArrayList<>();
		for (PersonInfoForChildAlertDTO person: persons){
			if (person.getAge() < 18 ){
				children.add(person); 
			}
			
		}
		return children; 
	}

	public List<PersonInfoForChildAlertDTO> getAdult(List<PersonInfoForChildAlertDTO> persons){
		List<PersonInfoForChildAlertDTO> adult = new ArrayList<>();
		for (PersonInfoForChildAlertDTO person: persons){
			if (person.getAge() >= 18 ){
				adult.add(person);
			}

		}
		return adult;
	}

	public List<Person> savePerson(Person person){

		try {
			// Charger le fichier JSON existant
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Pour une meilleure lisibilité

			File jsonFile = new File("src/main/resources/data.json");
			ObjectNode rootNode = (ObjectNode) objectMapper.readTree(jsonFile);

			// Récupérer le tableau "persons"
			ArrayNode personsArray = (ArrayNode) rootNode.get("persons");

			// Convertir l'objet Person en objet JSON
			ObjectNode newPersonNode = objectMapper.valueToTree(person);

			// Ajouter le nouvel objet "person" au tableau
			personsArray.add(newPersonNode);

			// Réécrire le fichier JSON avec le nouvel objet ajouté
			objectMapper.writeValue(jsonFile, rootNode);

			System.out.println("Nouvelle personne ajoutée avec succès au fichier JSON !!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}


