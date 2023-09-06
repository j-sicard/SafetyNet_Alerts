package com.openclassrooms.safetyNet.dao.childAlertDao;

import com.openclassrooms.safetyNet.model.ChildrenAndTheirFamily;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*@Repository
public class ChildAlertImpl implements ChildAlertDAO {

   private final ObjectMapper objectMapper = new ObjectMapper();
    public static final String JSON_FILE_PATH = "src/main/resources/data.json";

    @Override
    public List<ChildrenAndTheirFamily> findByChildAddress(String address) {
        List<ChildrenAndTheirFamily> ChildrenAndTheirFamilyInformations = new ArrayList<>();

        try {
            org.codehaus.jackson.JsonNode root = objectMapper.readTree(new File(JSON_FILE_PATH));
            org.codehaus.jackson.JsonNode personsNode = root.get("persons");
            org.codehaus.jackson.JsonNode medicalrecordsNode = root.get("medicalrecords");
            for (JsonNode personNode: personsNode){

            }

            return "";
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}*/
