package com.openclassrooms.safetyNet.testdto;

import com.openclassrooms.safetyNet.dto.ResidentInfoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ResidentInfoDTOTest {
    private ResidentInfoDTO residentInfo;

    @BeforeEach
    public void setUp() {
        residentInfo = new ResidentInfoDTO("John", "Doe", "123 Main St", "555-123-4567");
    }

    @Test
    public void testGetFirstName() {
        assertEquals("John", residentInfo.getFirstName());
    }

    @Test
    public void testSetFirstName() {
        residentInfo.setFirstName("Jane");
        assertEquals("Jane", residentInfo.getFirstName());
    }

    @Test
    public void testGetLastName() {
        assertEquals("Doe", residentInfo.getLastName());
    }

    @Test
    public void testSetLastName() {
        residentInfo.setLastName("Smith");
        assertEquals("Smith", residentInfo.getLastName());
    }

    @Test
    public void testGetAddress() {
        assertEquals("123 Main St", residentInfo.getAddress());
    }

    @Test
    public void testSetAddress() {
        residentInfo.setAddress("456 Elm St");
        assertEquals("456 Elm St", residentInfo.getAddress());
    }

    @Test
    public void testGetPhone() {
        assertEquals("555-123-4567", residentInfo.getPhone());
    }

    @Test
    public void testSetPhone() {
        residentInfo.setPhone("555-987-6543");
        assertEquals("555-987-6543", residentInfo.getPhone());
    }

}