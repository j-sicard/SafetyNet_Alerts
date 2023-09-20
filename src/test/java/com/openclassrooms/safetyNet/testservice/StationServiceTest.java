package com.openclassrooms.safetyNet.testservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.openclassrooms.safetyNet.service.StationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

@SpringBootTest
public class StationServiceTest {
    @Autowired
    StationService stationService;

    @Test
    public void testListStationAddresses() throws ClassNotFoundException, JsonProcessingException, IOException {
        assertTrue(stationService.listStationAddresses("1").contains("644 Gershwin Cir"));
    }
    @Test
    public void testGetStationByAddress() throws ClassNotFoundException, JsonProcessingException, IOException {
        assertTrue(stationService.getStationByAddress("1509 Culver St").contains("3"));
    }
}
