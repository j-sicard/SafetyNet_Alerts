package com.openclassrooms.safetyNet.service;

import com.openclassrooms.safetyNet.dto.model.ResidentInfoModel;
import org.codehaus.jackson.JsonProcessingException;

import java.io.IOException;
import java.util.List;

public interface ResidentInfoService {
    List<ResidentInfoModel> getResidentByAdresses(String stationAdresses)throws ClassNotFoundException, JsonProcessingException, IOException;

    /*List<String> getBirthDatesbyFirstname(List<ResidentInfoModel> ResidentInfo)throws ClassNotFoundException, JsonProcessingException, IOException;*/
}
