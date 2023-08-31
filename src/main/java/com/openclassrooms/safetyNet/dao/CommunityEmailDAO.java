package com.openclassrooms.safetyNet.dao;

import com.openclassrooms.safetyNet.model.CommunityEmail;

import java.util.List;

public interface CommunityEmailDAO {
        List<CommunityEmail> findAll();
    List<CommunityEmail> findByCity(String city);

    }

