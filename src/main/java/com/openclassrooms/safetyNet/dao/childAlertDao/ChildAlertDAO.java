package com.openclassrooms.safetyNet.dao.childAlertDao;


import com.openclassrooms.safetyNet.model.ChildrenAndTheirFamily;

import java.util.List;

public interface ChildAlertDAO {
    List<ChildrenAndTheirFamily> findByChildAddress(String address);
}
