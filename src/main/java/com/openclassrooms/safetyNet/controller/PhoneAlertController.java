package com.openclassrooms.safetyNet.controller;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
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

	@GetMapping(value = "/phoneAlert")
	public List<String> getResidentPhoneNumbersByStation(@RequestParam String firestation)
			throws ClassNotFoundException, JsonProcessingException, IOException {

		return personService.getPersonPhoneNumberFromAddresses(stationService.listStationAddresses(firestation));
	}
}
