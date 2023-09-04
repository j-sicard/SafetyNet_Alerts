package com.openclassrooms.safetyNet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetyNet.dao.phoneAlertDao.PhoneAlertDAO;
import com.openclassrooms.safetyNet.model.PhoneAlert;
import com.openclassrooms.safetyNet.view.LinkUrl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PhoneAlertController{

    private final PhoneAlertDAO phoneAlertDAO;

    public PhoneAlertController(PhoneAlertDAO phoneAlertDAO) {
        this.phoneAlertDAO = phoneAlertDAO;
    }

    @GetMapping(LinkUrl.URL_FOR_PHONE_NUMBERS_OF_RESIDENTS_SERVED_BY_THE_FIRE_STATION)
    public String addressOfSation(@RequestParam(required = true) String station_number) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<PhoneAlert> phoneAlerts = phoneAlertDAO.findByFireSationNumber(station_number);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(phoneAlerts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

