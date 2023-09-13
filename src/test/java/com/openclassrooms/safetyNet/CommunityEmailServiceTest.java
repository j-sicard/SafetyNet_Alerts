package com.openclassrooms.safetyNet;

import com.openclassrooms.safetyNet.dao.PersonDao;
import com.openclassrooms.safetyNet.dao.PersonDaoImpl;

import jdk.security.jarsigner.JarSignerException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommunityEmailServiceTest {
    private PersonDao personDao = new PersonDaoImpl(String.valueOf(new ArrayList<>()));


    @Test
    void testCommunityEmailService() throws ClassNotFoundException, JarSignerException, IOException {


      CommunityEmailService communityEmailService = new CommunityEmailServiceImpl();


        assertTrue(communityEmailService.equals(false));
    }
}
