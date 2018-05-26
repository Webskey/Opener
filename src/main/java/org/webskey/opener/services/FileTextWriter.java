package org.webskey.opener.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.webskey.opener.model.Program;

public class FileTextWriter {

	private ObjectToJsonParser objectToJsonParser;

	public FileTextWriter() {
		objectToJsonParser = new ObjectToJsonParser();	
	}

	public void write(String path, Program program) {
		//String path = "data/" + program.getName() + ".txt";
		String filePath = path + program.getName() + ".txt";

		create(program.getName());

		File file = new File(filePath.toString());
		try (FileWriter  output = new FileWriter(file, true);) {	
			output.append(objectToJsonParser.parse(program)); 
			output.flush();			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void create(String path) {		
		try {
			Files.createFile(Paths.get(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void delete(String path, String fileName) {
		try {
			File file = new File(path + "\\" + fileName + ".txt.");
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
