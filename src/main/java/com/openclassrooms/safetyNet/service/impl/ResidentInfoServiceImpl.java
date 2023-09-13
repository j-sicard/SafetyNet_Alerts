package com.openclassrooms.safetyNet.service.impl;


import com.openclassrooms.safetyNet.dao.MedicalRecordsDao;
import com.openclassrooms.safetyNet.dao.MedicalRecordsDaoImpl;
import com.openclassrooms.safetyNet.dto.ResidentInfoDao;
import com.openclassrooms.safetyNet.dto.impl.ResidentInfoDaoImpl;
import com.openclassrooms.safetyNet.dto.model.ResidentInfoModel;

import com.openclassrooms.safetyNet.service.ResidentInfoService;
import org.codehaus.jackson.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

@Service("ResidentInfoService")
public class ResidentInfoServiceImpl implements ResidentInfoService {

    MedicalRecordsDao medicalRecordsDao = new MedicalRecordsDaoImpl("src/main/resources/data.json");

    private ResidentInfoDao residentInfoDao = new ResidentInfoDaoImpl();

    @Override
    public List<ResidentInfoModel> getResidentByAdresses(List<String> stationAddresses) throws ClassNotFoundException, IOException, JsonProcessingException{
        List<ResidentInfoModel> residentByAdresses = new ArrayList<>();

        for (ResidentInfoModel infoResident : residentInfoDao.list()) {
            if (stationAddresses.contains(infoResident.getAddress())) {
                residentByAdresses.add(infoResident);
            }
        }
        return residentByAdresses;
    }
}
