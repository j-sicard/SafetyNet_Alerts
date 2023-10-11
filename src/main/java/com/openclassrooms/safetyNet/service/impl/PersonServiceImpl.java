package com.openclassrooms.safetyNet.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
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
import com.openclassrooms.safetyNet.model.MedicalRecord;
import com.openclassrooms.safetyNet.utils.DateUtils;

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

	/*public List<String> getPersonEmailFromCity(String city)
			throws ClassNotFoundException, JsonProcessingException, IOException{
		List<Person> persons = personDao.list().stream().filter(o -> (city.contains(o.getCity())))
				.toList();
		List<String> emails = persons.stream().map(o -> o.getEmail()).collect(Collectors.toList());
		return emails;
	}*/

	public List<String> getPersonEmailFromCity(String city)
			throws ClassNotFoundException, JsonProcessingException, IOException {
		List<Person> personsInCity = personDao.list().stream()
				.filter(person -> city.equals(person.getCity())) // Compare les villes
				.collect(Collectors.toList());

		List<String> emails = personsInCity.stream()
				.map(Person::getEmail) // Utilisation de la référence de méthode
				.collect(Collectors.toList());

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
			throws ClassNotFoundException, IOException, JsonProcessingException {
		return personDao.list().stream()
				.filter(person -> {
					String personAddress = person.getAddress();
					return personAddress != null && personAddress.equalsIgnoreCase(address);
				})
				.collect(Collectors.toList());
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

	public List<Person> savePerson(Person person) throws ClassNotFoundException, IOException{
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
		return null;
	}

	public List<Person>  updateperson(String firstName, String lastName, Person updatedperson)throws ClassNotFoundException, IOException{
			// Charger le fichier JSON existant
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

			File jsonFile = new File("src/main/resources/data.json");
			ObjectNode rootNode = (ObjectNode) objectMapper.readTree(jsonFile);

			// Récupérer le tableau "persons"
			ArrayNode personsArray = (ArrayNode) rootNode.get("persons");

			// Parcourir le tableau "personsArray" pour trouver la caserne à mettre à jour
			for (JsonNode stationNode : personsArray) {
				String personFirstName = stationNode.get("firstName").asText();
				String personLastName = stationNode.get("lastName").asText();

				if (personFirstName.equals(firstName) && personLastName.equals(lastName)) {
					// Mettre à jour du profile de la personne si elle correspond aux critères
					((ObjectNode) stationNode).put("address", updatedperson.getAddress());
					((ObjectNode) stationNode).put("city", updatedperson.getCity());
					((ObjectNode) stationNode).put("zip", updatedperson.getZip());
					((ObjectNode) stationNode).put("phone", updatedperson.getPhone());
					((ObjectNode) stationNode).put("email", updatedperson.getEmail());
					break;
				}
			}
			// Réécrire le fichier JSON avec le profile de la personne mise à jour
			objectMapper.writeValue(jsonFile, rootNode);
		return null;
	}

	public List<Person> deletePerson(String firstName,String lastName)throws ClassNotFoundException, IOException{
		// Charger le fichier JSON existant
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

		File jsonFile = new File("src/main/resources/data.json");
		ObjectNode rootNode = (ObjectNode) objectMapper.readTree(jsonFile);

		// Récupérer le tableau "Persons"
		ArrayNode personsArray = (ArrayNode) rootNode.get("persons");

		// Utiliser un itérateur pour parcourir le tableau "persons"
		Iterator<JsonNode> personIterator = personsArray.elements();
		while (personIterator.hasNext()) {
			JsonNode personNode = personIterator.next();
			String personFirstName = personNode.get("firstName").asText();
			String personLastName = personNode.get("lastName").asText();

			if (personFirstName.equals(firstName) && personLastName.equals(lastName)) {
				// Supprimer la person si elle correspond aux critères
				personIterator.remove();
				break;
			}
		}
		// Réécrire le fichier JSON sans le Dossier médicale supprimé
		objectMapper.writeValue(jsonFile, rootNode);
		System.out.println("Le profile de la personne a été supprimé avec succès du fichier JSON !!");
		return null;
	}
}


