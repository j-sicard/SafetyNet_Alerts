package com.openclassrooms.safetyNet.testservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.openclassrooms.safetyNet.model.Person;
import org.codehaus.jackson.JsonProcessingException;
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
		List<String> phoneNumbers = this.personneService
				.getPersonPhoneNumberFromAddresses(Collections.singletonList("1509 Culver St"));
		assertEquals("841-874-6512", phoneNumbers.get(0));
	}

	@Test
	public void testGetPersonEmailFromCity() throws ClassNotFoundException, IOException {
		assertTrue(this.personneService.getPersonEmailFromCity("Culver").contains("jaboyd@email.com"));
	}

	@Test
	public void testGetPersonInfoFromAdresses() throws ClassNotFoundException, IOException {
		List<String> personInfos = this.personneService
				.getPersonInfoFromAdresses(Arrays.asList("1509 Culver St", "123 Main St"));
		assertTrue(personInfos.contains("nom : John, Boyd, 1509 Culver St, 841-874-6512"));
	}

	@Test
	public void testGetPersonFirstNameFromAdresses() throws ClassNotFoundException, IOException {
		assertTrue(this.personneService.getPersonFirstNameFromAdresses(Collections.singletonList("1509 Culver St"))
				.contains("John"));
	}

	@Test
	public void testGetByAdressesGetFirstName() throws ClassNotFoundException, IOException {
		List<ResidentInfoDTO> residents = this.personneService
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
		assertEquals(2, this.personneService.getNbChildren(Arrays.asList(18, 5, 30, 40, 15)));
	}

	@Test
	public void testGetNbAdult() {
		assertEquals(3, this.personneService.getNbAdult(Arrays.asList(18, 5, 30, 40, 15)));
	}
}
