package com.openclassrooms.safetyNet.service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.openclassrooms.safetyNet.dto.ResidentInfoMedicalRecordsDTO;

import com.openclassrooms.safetyNet.dto.PersonMedicalRecordDTO;
import com.openclassrooms.safetyNet.dto.ResidentInfoDTO;
import com.openclassrooms.safetyNet.model.MedicalRecord;
import com.openclassrooms.safetyNet.model.Person;

public interface MedicalRecordService {
    public List<String> getPersonBirthDates(List<ResidentInfoDTO> persons)  throws  ClassNotFoundException, JsonProcessingException, IOException;

    public List<PersonMedicalRecordDTO> getPersonsMedicalRecords(List<Person> persons) throws ClassNotFoundException, JsonProcessingException, IOException;

    public List<ResidentInfoMedicalRecordsDTO> getResidentMedicalRecords(List<Person> persons) throws ClassNotFoundException, JsonProcessingException, IOException;

    public List<ResidentInfoMedicalRecordsDTO> sortPeople(List<ResidentInfoMedicalRecordsDTO> people)throws ClassNotFoundException, JsonProcessingException, IOException;

    public List<MedicalRecord> saveMedicalRecord(MedicalRecord medicalRecord, String jsonFile) throws ClassNotFoundException, IOException;

    public List<MedicalRecord>updateMedicalRecord(String firstName, String lastName, MedicalRecord updatedMedicalRecord, String filePath)throws ClassNotFoundException, IOException;

    public List<MedicalRecord>deleteMedicalRecord(String firstName, String lastName, String filePath)throws  ClassNotFoundException, IOException;
}
