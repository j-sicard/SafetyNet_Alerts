package com.openclassrooms.safetyNet.testservice;

import static com.openclassrooms.safetyNet.utils.DateUtils.getAge;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.openclassrooms.safetyNet.dto.PersonInfoForChildAlertDTO;
import com.openclassrooms.safetyNet.model.MedicalRecord;
import com.openclassrooms.safetyNet.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.safetyNet.dto.ResidentInfoDTO;
import com.openclassrooms.safetyNet.service.PersonService;

@SpringBootTest
public class PersonServiceImplTest {

	@Autowired
	private PersonService personneService;

	@Test
	public void testGetPersonPhoneNumberFromAddresses() throws ClassNotFoundException, IOException {
		List<String> phoneNumbers = personneService
				.getPersonPhoneNumberFromAddresses(Collections.singletonList("1509 Culver St"));
		assertEquals("841-874-6512", phoneNumbers.get(0));
	}

	@Test
	public void testGetPersonEmailFromCity() throws ClassNotFoundException, IOException {
		assertTrue(personneService.getPersonEmailFromCity("Culver").contains("jaboyd@email.com"));
	}

	@Test
	public void testGetPersonInfoFromAdresses() throws ClassNotFoundException, IOException {
		assertTrue(personneService.getPersonInfoFromAdresses(Arrays.asList("1509 Culver St")).contains("John, Boyd, 1509 Culver St, 841-874-6512"));
	}


	@Test
	public void testGetPersonFirstNameFromAdresses() throws ClassNotFoundException, IOException {
		assertTrue(personneService.getPersonFirstNameFromAdresses(Collections.singletonList("1509 Culver St"))
				.contains("John"));
	}

	@Test
	public void testGetByAdresses() throws ClassNotFoundException, IOException {
		List<ResidentInfoDTO> residents = personneService
				.getByAdresses(Arrays.asList("1509 Culver St", "123 Main St"));
// a changer //
		ResidentInfoDTO residentInfoDTO = residents.get(0);
		assertEquals("John", residentInfoDTO.getFirstName());
		assertEquals("Boyd", residentInfoDTO.getLastName());
		assertEquals("1509 Culver St", residentInfoDTO.getAddress());
		assertEquals("841-874-6512", residentInfoDTO.getPhone());
	}

	@Test
	public void testGetNbChildrenNoChildren() {
		assertEquals(2, personneService.getNbChildren(Arrays.asList(18, 5, 30, 40, 15)));
	}

	@Test
	public void testGetNbAdult() {
		assertEquals(3, personneService.getNbAdult(Arrays.asList(18, 5, 30, 40, 15)));
	}


	@Test
	public void testGetByFirstNameAndLastName() throws ClassNotFoundException, IOException {
		assertTrue(personneService.getByFirstNameAndLastName("Boyd").stream().anyMatch(person -> "Boyd".equalsIgnoreCase(person.getLastName())));
	}

	@Test
	public void testGetFromAddresses() throws ClassNotFoundException, IOException{
		List<String> addresses = new ArrayList<>();
		addresses.add("1509 Culver St");
		assertTrue(personneService.getFromAddresses(addresses).stream()
				.anyMatch(person -> "Boyd".equalsIgnoreCase(person.getLastName())));
	}

	@Test
	public void testGetPersonInfoForChildAlert() throws ClassNotFoundException, IOException {
		List<Person> testPersons = new ArrayList<>();
		testPersons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com"));
		PersonInfoForChildAlertDTO personInfo = personneService.getPersonInfoForChildAlert(testPersons).get(0);
		assertEquals("John", personInfo.getFirstName());
		assertEquals("Boyd", personInfo.getLastName());
		assertEquals(getAge("03/06/1984"), personInfo.getAge());
	}
}

