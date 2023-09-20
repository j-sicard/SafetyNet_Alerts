package com.openclassrooms.safetyNet.testdao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.openclassrooms.safetyNet.dao.MedicalRecordDao;
import com.openclassrooms.safetyNet.dao.impl.MedicalRecordDaoImpl;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class MedicalRecordDaoTest {
    private MedicalRecordDao medicalRecordsDao = new MedicalRecordDaoImpl("src/test/resources/data.json");

    @Test
    void testListMedicalRecords() throws ClassNotFoundException, JsonProcessingException, IOException {
        assertTrue(!CollectionUtils.isEmpty(medicalRecordsDao.list()));
    }
}


