package com.openclassrooms.safetyNet.utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetyNet.controller.PhoneAlertController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataExtractor {

	private static final Logger LOGGER =  LogManager.getLogger( DataExtractor.class );

	public static <T> List<T> parseJsonArray(String json,
			Class<T> classOnWhichArrayIsDefined)
			throws IOException, ClassNotFoundException {
		ObjectMapper mapper = new ObjectMapper();
		Class<T[]> arrayClass = (Class<T[]>) Class.forName("[L" + classOnWhichArrayIsDefined.getName() + ";");
		LOGGER.debug("Data EXTRACTED : " + json);
		return Arrays.asList(mapper.readValue(json, arrayClass));
	}

}
