package org.webskey.opener.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.webskey.opener.dao.FileCrud;
import org.webskey.opener.model.Program;

public class RunningCommandsProvider {
	
	private Program program;
	private FileCrud fileCrud;
	
	public RunningCommandsProvider() {
		fileCrud = new FileCrud();
	}
	
	public void parse(File file) throws IOException {
		program = fileCrud.read(file);		
	}
	
	public String[] getPathOptions() {
		List<String> list = new ArrayList<>();
		list.add(program.getPath());
		if(!program.getOptions().equals(""))
		list.addAll(Arrays.asList(program.getOptions().split(" ")));
		
		return list.toArray(new String[0]);
	}
	
	public String getDirectory() {
		return program.getDirectory();
	}
}
