package com.openclassrooms.safetyNet.testmodel;

import com.openclassrooms.safetyNet.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PersonTest {
    private Person person;

    @BeforeEach
    public void setUp() {
        person = new Person("John", "Doe", "123 Main St", "Cityville", "12345", "555-123-4567", "john@example.com");
    }

    @Test
    public void testGetFirstName() {
        assertEquals("John", person.getFirstName());
    }

    @Test
    public void testSetFirstName() {
        person.setFirstName("Jane");
        assertEquals("Jane", person.getFirstName());
    }

    @Test
    public void testGetLastName() {
        assertEquals("Doe", person.getLastName());
    }

    @Test
    public void testSetLastName() {
        person.setLastName("Smith");
        assertEquals("Smith", person.getLastName());
    }

    @Test
    public void testGetAddress() {
        assertEquals("123 Main St", person.getAddress());
    }

    @Test
    public void testSetAddress() {
        person.setAddress("456 Elm St");
        assertEquals("456 Elm St", person.getAddress());
    }

    @Test
    public void testGetCity() {
        assertEquals("Cityville", person.getCity());
    }

    @Test
    public void testSetCity() {
        person.setCity("Townsville");
        assertEquals("Townsville", person.getCity());
    }

    @Test
    public void testGetZip() {
        assertEquals("12345", person.getZip());
    }

    @Test
    public void testSetZip() {
        person.setZip("54321");
        assertEquals("54321", person.getZip());
    }

    @Test
    public void testGetPhone() {
        assertEquals("555-123-4567", person.getPhone());
    }

    @Test
    public void testSetPhone() {
        person.setPhone("555-987-6543");
        assertEquals("555-987-6543", person.getPhone());
    }

    @Test
    public void testGetEmail() {
        assertEquals("john@example.com", person.getEmail());
    }

    @Test
    public void testSetEmail() {
        person.setEmail("jane@example.com");
        assertEquals("jane@example.com", person.getEmail());
    }

    @Test
    public void testToString() {
        String expectedString = "Person{" +
                "firstName='John', " +
                "lastName='Doe', " +
                "address='123 Main St', " +
                "city='Cityville', " +
                "zip='12345', " +
                "phone='555-123-4567', " +
                "email='john@example.com'" +
                '}';
        assertEquals(expectedString, person.toString());
    }
}
