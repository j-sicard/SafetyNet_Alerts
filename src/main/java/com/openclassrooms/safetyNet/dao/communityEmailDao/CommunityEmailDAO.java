package com.openclassrooms.safetyNet.dao.communityEmailDao;

import com.openclassrooms.safetyNet.model.CommunityEmail;

import java.util.List;

public interface CommunityEmailDAO {
         List<CommunityEmail> findByCity(String city);

    }

