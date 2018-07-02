package org.webskey.opener.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.webskey.opener.model.Program;
import org.webskey.opener.services.JsonToObjectParser;
import org.webskey.opener.services.ObjectToJsonParser;

public class FileCrud {

	private ObjectToJsonParser objectToJsonParser;
	private JsonToObjectParser jsonToObjectParser;

	public FileCrud() {
		objectToJsonParser = new ObjectToJsonParser();	
		jsonToObjectParser = new JsonToObjectParser();	
	}

	public String getPath() {
		return "src/main/resources/Projects/";
		//return "data/Projects/";
	}
	
	public File dataFile() {
		return new File(getPath());
	}

	public void write(String path, Program program) {
		String filePath = path + program.getName() + ".txt";
		File file = new File(filePath);
		try (FileWriter  output = new FileWriter(file, true);) {	
			output.append(objectToJsonParser.parse(program)); 
			output.flush();			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Program read(File file) {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){			
			String line="";
			while ((line = bufferedReader.readLine()) != null) 
				sb.append(line);

			return jsonToObjectParser.parse(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
			return new Program();
		}
	}	

	public Program read(String path) {
		StringBuilder sb = new StringBuilder();
		File file = new File(path);
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){			
			String line="";
			while ((line = bufferedReader.readLine()) != null) 
				sb.append(line);

			return jsonToObjectParser.parse(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
			return new Program();
		}
	}	

	public void create(String path) {		
		try {
			Files.createFile(Paths.get(path));
			System.out.println("CREATED");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void delete(String path) {
		try {
			File file = new File(path);
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(String path, Program program) {
		File file = new File(path + program.getName() + ".txt");	
		try (FileWriter  output = new FileWriter(file, true);
				PrintWriter writer = new PrintWriter(file);) {
			writer.print("");
			output.append(objectToJsonParser.parse(program)); 
			output.flush();			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void replace(String path, String fileName, Program program) {
		delete(path + fileName +".txt");		
		write(path, program);
	}
}
