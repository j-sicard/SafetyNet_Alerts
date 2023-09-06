
package com.openclassrooms.safetyNet.dao.phoneAlertDao;

import com.openclassrooms.safetyNet.model.FireSationInformation;
import com.openclassrooms.safetyNet.model.PhoneAlert;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



@Repository
public class PhoneAlertDaoImpl implements PhoneAlertDAO {
    private final ObjectMapper objectMapper = new ObjectMapper();
    public static final String JSON_FILE_PATH = "src/main/resources/data.json";

    @Override
    public List<PhoneAlert> findByFireSationNumber(String station_number){
        List<FireSationInformation> adressOfFireSations = new ArrayList<>();
        List<PhoneAlert> phonesOfPersonnesForAlert = new ArrayList<>();
        try {
            org.codehaus.jackson.JsonNode root = objectMapper.readTree(new File(JSON_FILE_PATH));
            org.codehaus.jackson.JsonNode firestationsNode = root.get("firestations");
            org.codehaus.jackson.JsonNode personsNode = root.get("persons");

            for(JsonNode firestationNode : firestationsNode){
                if (firestationNode.get("station").asText().equals(station_number)){
                    FireSationInformation fireSationInformation = new FireSationInformation();
                    fireSationInformation.setAddress(firestationNode.get("address").asText());
                    adressOfFireSations.add(fireSationInformation);
                }
            }

            for(JsonNode person : personsNode){
               for (FireSationInformation fireSation : adressOfFireSations){
                   if (person.get("address").asText().equals(fireSation.getAddress())){
                       PhoneAlert phoneAlert = new PhoneAlert();
                       phoneAlert.setPhone(person.get("phone").asText());
                           phonesOfPersonnesForAlert.add(phoneAlert);
                   }
               }
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return  phonesOfPersonnesForAlert;
    }
}



