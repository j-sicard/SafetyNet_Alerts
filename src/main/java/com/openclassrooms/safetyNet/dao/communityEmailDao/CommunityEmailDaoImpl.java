package com.openclassrooms.safetyNet.dao.communityEmailDao;

import com.openclassrooms.safetyNet.dao.communityEmailDao.CommunityEmailDAO;
import com.openclassrooms.safetyNet.model.CommunityEmail;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Repository;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CommunityEmailDaoImpl implements CommunityEmailDAO {

    private final ObjectMapper objectMapper = new ObjectMapper();
    public static final String JSON_FILE_PATH = "src/main/resources/data.json";

    @Override
    public  List<CommunityEmail>  findByCity(String city) {
        List<CommunityEmail> communityEmails = new ArrayList<>();

        try {
            org.codehaus.jackson.JsonNode root = objectMapper.readTree(new File(JSON_FILE_PATH));
            org.codehaus.jackson.JsonNode personsNode = root.get("persons");

            for (JsonNode personNode : personsNode) {
                if (personNode.get("city").asText().equals(city)){
                    CommunityEmail communityEmail = new CommunityEmail();
                    communityEmail.setEmail(personNode.get("email").asText());
                    communityEmails.add(communityEmail);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return communityEmails;
    }
}

