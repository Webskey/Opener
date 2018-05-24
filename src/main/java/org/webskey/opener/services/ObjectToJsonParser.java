package org.webskey.opener.services;

import java.io.IOException;

import org.webskey.opener.model.Program;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectToJsonParser {

	private ObjectMapper mapper;

	public String parse(Program program) throws IOException {
		mapper = new ObjectMapper();
		String json  = mapper.writeValueAsString(program);
		
		return json;
	}
}
