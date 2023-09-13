package com.openclassrooms.safetyNet.service.impl;

import com.openclassrooms.safetyNet.dto.model.ResidentInfoModel;
import com.openclassrooms.safetyNet.dao.MedicalRecordsDao;
import com.openclassrooms.safetyNet.dao.MedicalRecordsDaoImpl;
import com.openclassrooms.safetyNet.model.MedicalRecords;
import com.openclassrooms.safetyNet.service.MedicalRecordsService;
import org.codehaus.jackson.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service("MedicalRecordsService")
public class MedicalRecordsServiceImpl implements MedicalRecordsService {
    private MedicalRecordsDao medicalRecordsDao = new MedicalRecordsDaoImpl("src/main/resources/data.json");

    /*public  List<String> getPersonBirthDates(List<String> persons) throws  ClassNotFoundException, JsonProcessingException, IOException {
        List<MedicalRecords> medicalRecordsList = medicalRecordsDao.list().stream().filter(o -> (persons.contains(o.getFirstName())))
                .toList();
        List<String> birthDates = medicalRecordsList.stream().map(o -> o.getBirthdate()).collect(Collectors.toList());
        return birthDates;
    }*/

    public List<String> getPersonBirthDates(List<ResidentInfoModel> personInfos)throws ClassNotFoundException, JsonProcessingException, IOException{
        List<String>birthDates = new ArrayList<String>();

        Set<String> personFirstNames = personInfos.stream()
                .map(ResidentInfoModel::getFirstName)
                .collect(Collectors.toSet());

        for(MedicalRecords medicalRecords: medicalRecordsDao.list()){
            if (personFirstNames.contains(medicalRecords.getFirstName())){
                birthDates.add(medicalRecords.getBirthdate());
            }
        }
        return birthDates;
    }
}
