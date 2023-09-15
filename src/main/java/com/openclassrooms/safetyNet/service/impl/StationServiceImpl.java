package com.openclassrooms.safetyNet.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.codehaus.jackson.JsonProcessingException;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetyNet.dao.FireStationDao;
import com.openclassrooms.safetyNet.dao.impl.FireStationDaoImpl;
import com.openclassrooms.safetyNet.model.FireStation;
import com.openclassrooms.safetyNet.service.StationService;

@Service("StationService")
public class StationServiceImpl implements StationService {
    private FireStationDao fireStationDao = new FireStationDaoImpl("src/main/resources/data.json");

    public List<String> listStationAddresses(String station)
            throws ClassNotFoundException, JsonProcessingException, IOException {
        List<FireStation> fireStations = fireStationDao.list().stream().filter(o -> (o.getStation().equals(station)))
                .toList();
        List<String> stationAddresses = fireStations.stream().map(o -> o.getAddress()).collect(Collectors.toList());
        return stationAddresses;
    }

}
