package com.openclassrooms.safetyNet.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.openclassrooms.safetyNet.model.MedicalRecord;

import java.io.IOException;
import java.util.List;

public interface MedicalRecordDao {
    public List<MedicalRecord> list() throws ClassNotFoundException, JsonProcessingException, IOException;
}
