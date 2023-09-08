package com.openclassrooms.safetyNet.service;

import org.codehaus.jackson.JsonProcessingException;


import java.io.IOException;
import java.util.List;

public interface PhoneAlertService {
    public List<String> getPhoneNumberResidentServedByFirestation(String firestation) throws ClassNotFoundException, JsonProcessingException, IOException;

}


