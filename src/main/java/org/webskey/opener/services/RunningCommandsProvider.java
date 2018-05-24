package org.webskey.opener.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.webskey.opener.model.Program;

public class RunningCommandsProvider {
	
	private JsonToObjectParser jsonToObjectParser;
	private Program program;
	private FileTextReader fileTextReader;
	
	public RunningCommandsProvider() {
		jsonToObjectParser = new JsonToObjectParser();
		fileTextReader = new FileTextReader();
	}
	
	public void parse(File file) throws IOException {
		program = jsonToObjectParser.parse(fileTextReader.getFile(file));		
	}
	
	public String[] getPathOptions() {
		List<String> list = new ArrayList<>();
		list.add(program.getPath());
		list.addAll(Arrays.asList(program.getOptions()));
		
		return list.toArray(new String[0]);
	}
	
	public String getDirectory() {
		return program.getDirectory();
	}
	
	public String[] getRuns(File file) throws IOException {
		
		program = jsonToObjectParser.parse(fileTextReader.getFile(file));		
		List<String> list = new ArrayList<>();
		list.add(program.getPath());
		list.addAll(Arrays.asList(program.getOptions()));
		
		return list.toArray(new String[0]);
	}
}
