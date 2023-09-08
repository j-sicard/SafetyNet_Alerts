package com.openclassrooms.safetyNet.dao;

import com.openclassrooms.safetyNet.model.Person;
import org.codehaus.jackson.JsonProcessingException;

import java.io.IOException;

import java.util.List;

public interface PersonDao {
    public List<Person> list() throws ClassNotFoundException, JsonProcessingException, IOException;

}

