package com.openclassrooms.safetyNet.controller;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.safetyNet.service.PersonService;
import com.openclassrooms.safetyNet.service.StationService;

@RestController
public class PhoneAlertController {
	@Autowired
	PersonService personService;
	@Autowired
	StationService stationService;

	private static final Logger LOGGER =  LogManager.getLogger( PhoneAlertController.class );

	@GetMapping(value = "/phoneAlert")
	public List<String> getResidentPhoneNumbersByStation(@RequestParam String firestation){
		try {
			List<String> PersonPhoneNumberFromAddresses = personService.getPersonPhoneNumberFromAddresses(stationService.listStationAddresses(firestation));
			LOGGER.info(PersonPhoneNumberFromAddresses);
			return PersonPhoneNumberFromAddresses;
			}catch(ClassNotFoundException | IOException e){
			LOGGER.error("Impossible de lire le fichier data.json");
			return null;
		}
	}
}
