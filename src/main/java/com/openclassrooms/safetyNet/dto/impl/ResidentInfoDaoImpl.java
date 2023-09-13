package com.openclassrooms.safetyNet.dto.impl;

import com.openclassrooms.safetyNet.dto.ResidentInfoDao;
import com.openclassrooms.safetyNet.dto.model.ResidentInfoModel;;
import com.openclassrooms.safetyNet.dao.PersonDao;
import com.openclassrooms.safetyNet.dao.PersonDaoImpl;
import com.openclassrooms.safetyNet.model.Person;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;


@Service("ResidentInfoDao")
public class ResidentInfoDaoImpl implements ResidentInfoDao {
    private PersonDao personDao = new PersonDaoImpl("src/main/resources/data.json");
    public List<ResidentInfoModel> list() throws IOException, ClassNotFoundException {

     List<ResidentInfoModel> listResidents = new ArrayList<>();
     for(Person person: personDao.list()){
         ResidentInfoModel residentInfo = new ResidentInfoModel(person.getFirstName(), person.getLastName(), person.getAddress(), person.getPhone());
         listResidents.add(residentInfo);
     }
        return   listResidents;
    }

}


