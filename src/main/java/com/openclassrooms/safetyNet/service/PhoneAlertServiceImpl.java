package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.dao.FireStationDao;
import com.openclassrooms.safetyNet.dao.FireStationDaoImpl;
import com.openclassrooms.safetyNet.dao.PersonDao;
import com.openclassrooms.safetyNet.dao.PersonDaoImpl;
import com.openclassrooms.safetyNet.model.FireStation;
import com.openclassrooms.safetyNet.model.Person;

import org.codehaus.jackson.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("PhoneAlertService")
public class PhoneAlertServiceImpl implements PhoneAlertService {
    private FireStationDao fireStationDao = new FireStationDaoImpl("src/main/resources/data.json");
    private PersonDao personDao = new PersonDaoImpl("src/main/resources/data.json");

    public List<String> getPhoneNumberResidentServedByFirestation(String firestation) throws ClassNotFoundException, JsonProcessingException, IOException{

        List<String> stationsAddress = new ArrayList<>();
        for (FireStation fireStation: fireStationDao.list()){
            if(fireStation.getAddress().equals(firestation)){
                stationsAddress.add(fireStation.getAddress());
            }
        }
        List<String> phoneNumbers = new ArrayList<>();
        for (Person person:personDao.list()){
            if (stationsAddress.contains(person.getAddress())) {
                phoneNumbers.add(person.getPhone());
            }
        }
        return  phoneNumbers;
    }
}





