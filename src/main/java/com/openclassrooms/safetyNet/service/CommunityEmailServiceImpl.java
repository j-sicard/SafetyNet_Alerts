package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.dao.PersonDao;
import com.openclassrooms.safetyNet.dao.PersonDaoImpl;
import com.openclassrooms.safetyNet.model.Person;
import org.codehaus.jackson.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("CommunityEmailServiceService")
public class CommunityEmailServiceImpl implements CommunityEmailService {

    private PersonDao personDao = new PersonDaoImpl("src/main/resources/data.json");

    public List<String> getPersonEmailFromCity(String city) throws ClassNotFoundException, JsonProcessingException, IOException {
        List<String> persons = new ArrayList<String>();
        for(Person person:personDao.list()) {
            if(person.getCity().equals(city)) {
                persons.add(person.getEmail());
            }
        }
        return persons;
    }

}


