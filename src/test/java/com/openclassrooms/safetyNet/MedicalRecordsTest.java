package com.openclassrooms.safetyNet;

import com.openclassrooms.safetyNet.dao.MedicalRecordsDao;
import com.openclassrooms.safetyNet.dao.MedicalRecordsDaoImpl;
import org.codehaus.jackson.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class MedicalRecordsTest {
    private MedicalRecordsDao medicalRecordsDao = new MedicalRecordsDaoImpl("src/test/resources/data.json");

    @Test
    void testListMedicalRecords() throws ClassNotFoundException, JsonProcessingException, IOException {
        assertTrue(!CollectionUtils.isEmpty(medicalRecordsDao.list()));
    }
}


