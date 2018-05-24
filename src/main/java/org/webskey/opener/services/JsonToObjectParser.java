package org.webskey.opener.services;

import java.io.IOException;

import org.webskey.opener.model.Program;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonToObjectParser {
	
	private Program program;
	private ObjectMapper mapper;

	public Program parse(String json) throws IOException {
		mapper = new ObjectMapper();		
		program  = mapper.readValue(json, Program.class);
		
		return program;
	}
}
