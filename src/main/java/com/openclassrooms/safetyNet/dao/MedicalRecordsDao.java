package com.openclassrooms.safetyNet.dao;

import com.openclassrooms.safetyNet.model.MedicalRecords;
import org.codehaus.jackson.JsonProcessingException;

import java.io.IOException;
import java.util.List;

public interface MedicalRecordsDao {
    public List<MedicalRecords> list() throws ClassNotFoundException, JsonProcessingException, IOException;
}
