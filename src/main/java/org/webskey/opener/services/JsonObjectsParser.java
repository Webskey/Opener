package org.webskey.opener.services;

import java.io.IOException;

import org.webskey.opener.model.Program;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonObjectsParser {
	
	public String parse(Program program) throws IOException {		
		return new ObjectMapper().writeValueAsString(program);
	}
	
	public Program parse(String json) throws IOException {		
		return new ObjectMapper().readValue(json, Program.class);
	}
}
