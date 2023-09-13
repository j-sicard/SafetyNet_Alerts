package com.openclassrooms.safetyNet;

import com.openclassrooms.safetyNet.dao.FireStationDao;
import com.openclassrooms.safetyNet.dao.FireStationDaoImpl;
import org.codehaus.jackson.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class FireStationDaoTest {
    private FireStationDao fireStationDao = new FireStationDaoImpl("src/test/resources/data.json");
    @Test
    void testListFireStation() throws ClassNotFoundException, JsonProcessingException, IOException {
        assertTrue(!CollectionUtils.isEmpty(fireStationDao.list()));
    }
}


