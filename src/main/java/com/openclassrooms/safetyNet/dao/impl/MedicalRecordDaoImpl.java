package com.openclassrooms.safetyNet.dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetyNet.dao.MedicalRecordDao;
import com.openclassrooms.safetyNet.model.MedicalRecord;
import com.openclassrooms.safetyNet.utils.DataExtractor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDaoImpl implements MedicalRecordDao {
    private String jsonFilePath;

    public MedicalRecordDaoImpl(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
    }
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<MedicalRecord> list() {
        try {
            return DataExtractor.parseJsonArray(mapper.readTree(new File(this.jsonFilePath)).get("medicalrecords").toString(), MedicalRecord.class);
        } catch (ClassNotFoundException | IOException e) {
            return new ArrayList<MedicalRecord>();
        }
    }
}
