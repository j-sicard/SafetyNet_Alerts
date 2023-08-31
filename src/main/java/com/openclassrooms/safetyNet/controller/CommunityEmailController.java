package com.openclassrooms.safetyNet.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetyNet.dao.CommunityEmailDAO;
import com.openclassrooms.safetyNet.model.CommunityEmail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CommunityEmailController {

    private final CommunityEmailDAO communityEmailDAO;

    public CommunityEmailController(CommunityEmailDAO communityEmailDAO) {
        this.communityEmailDAO = communityEmailDAO;
    }



    @GetMapping("/communityEmail/all")
    public List<CommunityEmail> listCommunityEmail(){
        return communityEmailDAO.findAll();
    }

    @GetMapping("/communityEmail")
    public List<CommunityEmail> emailAddressesOfEveryoneInTheCity(@RequestParam(required = true) String city){
        return communityEmailDAO.findByCity(city);
    }
}
