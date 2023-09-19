package com.openclassrooms.safetyNet.dto;

import java.util.List;

public class ResidentInfoMedicalRecordsStations {
    public List<ResidentInfoMedicalRecordsDTO> residentInfoMedicalRecords;
    public String stationNumbers;

    public ResidentInfoMedicalRecordsStations(List<ResidentInfoMedicalRecordsDTO> residentInfoMedicalRecords, String stationNumbers) {
        this.residentInfoMedicalRecords = residentInfoMedicalRecords;
        this.stationNumbers = stationNumbers;
    }

    public List<ResidentInfoMedicalRecordsDTO> getResidentInfoMedicalRecords() {
        return residentInfoMedicalRecords;
    }

    public void setResidentInfoMedicalRecords(List<ResidentInfoMedicalRecordsDTO> residentInfoMedicalRecords) {
        this.residentInfoMedicalRecords = residentInfoMedicalRecords;
    }

    public String getStationNumbers() {
        return stationNumbers;
    }

    public void setStationNumbers(String stationNumbers) {
        this.stationNumbers = stationNumbers;
    }

    @Override
    public String toString() {
        return "ResidentInfoMedicalRecordsStations{" +
                "residentInfoMedicalRecords=" + residentInfoMedicalRecords +
                ", stationNumbers='" + stationNumbers + '\'' +
                '}';
    }
}
