package org.webskey.services;

import java.io.IOException;

import org.webskey.model.Program;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectToJsonParser {

	private ObjectMapper mapper;

	public String parse(Program program) throws IOException {
		mapper = new ObjectMapper();
		String json  = mapper.writeValueAsString(program);
		
		return json;
	}
}
