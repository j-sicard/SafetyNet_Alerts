package com.openclassrooms.safetyNet.utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DataExtractor {

	public static <T> List<T> parseJsonArray(String json,
			Class<T> classOnWhichArrayIsDefined)
			throws IOException, ClassNotFoundException {
		ObjectMapper mapper = new ObjectMapper();
		Class<T[]> arrayClass = (Class<T[]>) Class.forName("[L" + classOnWhichArrayIsDefined.getName() + ";");
		return Arrays.asList(mapper.readValue(json, arrayClass));
	}

}
