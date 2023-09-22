package com.openclassrooms.safetyNet.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.openclassrooms.safetyNet.model.Person;

import java.io.IOException;

import java.util.List;

public interface PersonDao {
    public List<Person> list() throws ClassNotFoundException, JsonProcessingException, IOException;
}

