package com.openclassrooms.safetyNet.service;

import org.codehaus.jackson.JsonProcessingException;

import java.io.IOException;
import java.util.List;

public interface CommunityEmailService {
    public List<String> getPersonEmailFromCity(String city) throws ClassNotFoundException, JsonProcessingException, IOException;
}
