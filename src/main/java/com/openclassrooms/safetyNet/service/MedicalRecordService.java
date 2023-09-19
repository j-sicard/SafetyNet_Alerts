package com.openclassrooms.safetyNet.service;

import java.io.IOException;
import java.util.List;

import com.openclassrooms.safetyNet.dto.ResidentInfoMedicalRecordsDTO;
import org.codehaus.jackson.JsonProcessingException;

import com.openclassrooms.safetyNet.dto.PersonMedicalRecordDTO;
import com.openclassrooms.safetyNet.dto.ResidentInfoDTO;
import com.openclassrooms.safetyNet.model.Person;

public interface MedicalRecordService {
    public List<String> getPersonBirthDates(List<ResidentInfoDTO> persons)  throws  ClassNotFoundException, JsonProcessingException, IOException;

    public List<PersonMedicalRecordDTO> getPersonsMedicalRecords(List<Person> persons) throws ClassNotFoundException, JsonProcessingException, IOException;

    public List<ResidentInfoMedicalRecordsDTO> getResidentMedicalRecords(List<Person> persons) throws ClassNotFoundException, JsonProcessingException, IOException;
}
