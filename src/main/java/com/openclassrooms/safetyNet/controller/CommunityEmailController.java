package com.openclassrooms.safetyNet.controller;

import com.openclassrooms.safetyNet.view.LinkUrl;
import com.openclassrooms.safetyNet.dao.communityEmailDao.CommunityEmailDAO;
import com.openclassrooms.safetyNet.model.CommunityEmail;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommunityEmailController {
    private final CommunityEmailDAO communityEmailDAO;
    public CommunityEmailController(CommunityEmailDAO communityEmailDAO) {
        this.communityEmailDAO = communityEmailDAO;
    }

    @GetMapping(LinkUrl.URL_FOR_EMAIL_OF_AL_CITY_RESIDENTS)
    public String emailAddressesOfEveryoneInTheCity(@RequestParam(required = true) String city){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(communityEmailDAO.findByCity(city));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
