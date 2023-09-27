package com.openclassrooms.safetyNet.testmodel;

import com.openclassrooms.safetyNet.model.FireStation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FireStationTest {
    private FireStation fireStation;

    @BeforeEach
    public void setUp() {
        fireStation = new FireStation("123 Main St", "Station1");
    }

    @Test
    public void testGetAddress() {
        assertEquals("123 Main St", fireStation.getAddress());
    }

    @Test
    public void testSetAddress() {
        fireStation.setAddress("456 Elm St");
        assertEquals("456 Elm St", fireStation.getAddress());
    }

    @Test
    public void testGetStation() {
        assertEquals("Station1", fireStation.getStation());
    }

    @Test
    public void testSetStation() {
        fireStation.setStation("Station2");
        assertEquals("Station2", fireStation.getStation());
    }
}
