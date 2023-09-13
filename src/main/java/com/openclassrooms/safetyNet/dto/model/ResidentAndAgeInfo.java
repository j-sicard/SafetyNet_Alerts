package com.openclassrooms.safetyNet.dto.model;

import java.util.HashMap;
import java.util.List;

public class ResidentAndAgeInfo {
    private List<ResidentInfoModel> residents;
    private HashMap<String, Integer> ageInfo;

    public ResidentAndAgeInfo(List<ResidentInfoModel> residents, HashMap<String, Integer> ageInfo) {
        this.residents = residents;
        this.ageInfo = ageInfo;
    }

    public ResidentAndAgeInfo() {
    }

    public List<ResidentInfoModel> getResidents() {
        return residents;
    }

    public void setResidents(List<ResidentInfoModel> residents) {
        this.residents = residents;
    }

    public HashMap<String, Integer> getAgeInfo() {
        return ageInfo;
    }

    public void setAgeInfo(HashMap<String, Integer> ageInfo) {
        this.ageInfo = ageInfo;
    }
}
