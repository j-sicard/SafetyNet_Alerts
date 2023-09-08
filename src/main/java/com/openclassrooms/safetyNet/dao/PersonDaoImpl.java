package com.openclassrooms.safetyNet.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.utils.DataExtractor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonDaoImpl implements PersonDao {

    private String jsonFilePath;

    public PersonDaoImpl(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
    }
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<Person> list() {
        try {
            return DataExtractor.parseJsonArray(mapper.readTree(new File(this.jsonFilePath)).get("persons").toString(), Person.class);
        } catch (ClassNotFoundException | IOException e) {
            return new ArrayList<Person>();
        }
    }

}

