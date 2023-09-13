package com.openclassrooms.safetyNet.dto;

import com.openclassrooms.safetyNet.dto.model.ResidentInfoModel;

import java.io.IOException;
import java.util.List;

public interface ResidentInfoDao {
    public List<ResidentInfoModel> list() throws IOException, ClassNotFoundException;
}
