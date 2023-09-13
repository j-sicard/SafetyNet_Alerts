package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.dto.model.ResidentInfoModel;
import org.codehaus.jackson.JsonProcessingException;

import java.io.IOException;
import java.util.List;

public interface MedicalRecordsService {
    public List<String> getPersonBirthDates(List<ResidentInfoModel> persons)  throws  ClassNotFoundException, JsonProcessingException, IOException;
}
