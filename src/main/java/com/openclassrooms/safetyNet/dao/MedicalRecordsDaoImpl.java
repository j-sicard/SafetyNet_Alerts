package com.openclassrooms.safetyNet.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetyNet.model.MedicalRecords;
import com.openclassrooms.safetyNet.utils.DataExtractor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordsDaoImpl implements MedicalRecordsDao{
    private String jsonFilePath;

    public MedicalRecordsDaoImpl(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
    }
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<MedicalRecords> list() {
        try {
            return DataExtractor.parseJsonArray(mapper.readTree(new File(this.jsonFilePath)).get("medicalrecords").toString(), MedicalRecords.class);
        } catch (ClassNotFoundException | IOException e) {
            return new ArrayList<MedicalRecords>();
        }
    }
}
