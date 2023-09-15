package com.openclassrooms.safetyNet.dao;

import com.openclassrooms.safetyNet.model.MedicalRecord;
import org.codehaus.jackson.JsonProcessingException;

import java.io.IOException;
import java.util.List;

public interface MedicalRecordDao {
    public List<MedicalRecord> list() throws ClassNotFoundException, JsonProcessingException, IOException;
}
