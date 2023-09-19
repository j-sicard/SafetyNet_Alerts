package com.openclassrooms.safetyNet.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.openclassrooms.safetyNet.dto.ResidentInfoMedicalRecordsDTO;
import org.codehaus.jackson.JsonProcessingException;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetyNet.dao.MedicalRecordDao;
import com.openclassrooms.safetyNet.dao.impl.MedicalRecordDaoImpl;
import com.openclassrooms.safetyNet.dto.PersonMedicalRecordDTO;
import com.openclassrooms.safetyNet.dto.ResidentInfoDTO;
import com.openclassrooms.safetyNet.model.MedicalRecord;
import com.openclassrooms.safetyNet.model.Person;
import com.openclassrooms.safetyNet.service.MedicalRecordService;
import com.openclassrooms.safetyNet.utils.DateUtils;


@Service("MedicalRecordService")
public class MedicalRecordServiceImpl implements MedicalRecordService {
    private MedicalRecordDao medicalRecordDao = new MedicalRecordDaoImpl("src/main/resources/data.json");

    public List<String> getPersonBirthDates(List<ResidentInfoDTO> personInfos)throws ClassNotFoundException, JsonProcessingException, IOException{
        List<String>birthDates = new ArrayList<String>();

        Set<String> personFirstNames = personInfos.stream()
                .map(ResidentInfoDTO::getFirstName)
                .collect(Collectors.toSet());

        for(MedicalRecord medicalRecords: medicalRecordDao.list()){
            if (personFirstNames.contains(medicalRecords.getFirstName())){
                birthDates.add(medicalRecords.getBirthdate());
            }
        }
        return birthDates;
    }

    @Override
    public List<PersonMedicalRecordDTO> getPersonsMedicalRecords(List<Person> persons) throws ClassNotFoundException, JsonProcessingException, IOException {
        List<PersonMedicalRecordDTO> personsMedicalRecords = new ArrayList<PersonMedicalRecordDTO>();
        for(Person person : persons) {
            for(MedicalRecord record : medicalRecordDao.list()) {
                if(person.getFirstName().toUpperCase().equals(record.getFirstName().toUpperCase()) && person.getLastName().toUpperCase().equals(record.getLastName().toUpperCase())) {
                    personsMedicalRecords.add(new PersonMedicalRecordDTO(person.getFirstName(), person.getLastName(), person.getAddress(), DateUtils.getAge(record.getBirthdate()), person.getEmail(), record.getMedications(), record.getAllergies()));
                }
            }
        }
        return personsMedicalRecords;
    }

    @Override
    public List<ResidentInfoMedicalRecordsDTO> getResidentMedicalRecords(List<Person> persons) throws ClassNotFoundException, JsonProcessingException, IOException {
        List<ResidentInfoMedicalRecordsDTO> personsMedicalRecords = new ArrayList<ResidentInfoMedicalRecordsDTO>();
        for(Person person : persons) {
            for(MedicalRecord record : medicalRecordDao.list()) {
                if(person.getFirstName().toUpperCase().equals(record.getFirstName().toUpperCase()) && person.getLastName().toUpperCase().equals(record.getLastName().toUpperCase())) {
                    personsMedicalRecords.add(new ResidentInfoMedicalRecordsDTO(person.getLastName(), person.getPhone(), DateUtils.getAge(record.getBirthdate()), record.getMedications(), record.getAllergies()));
                }
            }
        }
        return personsMedicalRecords;
    }
}
