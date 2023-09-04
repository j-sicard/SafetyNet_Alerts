

package com.openclassrooms.safetyNet.dao.phoneAlertDao;

import com.openclassrooms.safetyNet.model.PhoneAlert;

import java.util.List;

public interface PhoneAlertDAO {
    List<PhoneAlert> findByFireSationNumber(String station_number);

}


