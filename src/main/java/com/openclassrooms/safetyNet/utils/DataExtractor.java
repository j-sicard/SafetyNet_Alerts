package com.openclassrooms.safetyNet.utils;



import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class DataExtractor {

    public static <T> List<T> parseJsonArray(String json,
            Class<T> classOnWhichArrayIsDefined)
            throws IOException, ClassNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
        Class<T[]> arrayClass = (Class<T[]>) Class.forName("[L" + classOnWhichArrayIsDefined.getName() + ";");
        T[] objects = mapper.readValue(json, arrayClass);
        return Arrays.asList(objects);
    }

}

