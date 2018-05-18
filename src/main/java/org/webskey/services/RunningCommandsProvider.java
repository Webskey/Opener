package org.webskey.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.webskey.model.Program;

public class RunningCommandsProvider {
	
	private JsonToObjectParser jsonToObjectParser;
	private Program program;
	private FileTextReader fileTextReader;
	
	public String[] getRuns(File file) throws IOException {
		jsonToObjectParser = new JsonToObjectParser();
		fileTextReader = new FileTextReader();
		program = jsonToObjectParser.parse(fileTextReader.getFile(file));
		
		List<String> list = new ArrayList<>();
		list.add(program.getPath());
		list.addAll(Arrays.asList(program.getOptions()));
		
		return list.toArray(new String[0]);
	}
}
