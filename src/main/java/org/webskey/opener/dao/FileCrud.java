package org.webskey.opener.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.webskey.opener.model.Program;
import org.webskey.opener.services.JsonObjectsParser;

public class FileCrud {

	private JsonObjectsParser jsonParser;

	public FileCrud() {
		jsonParser = new JsonObjectsParser();	
	}

	public void write(String path, Program program) {
		String filePath = path + program.getName() + ".txt";
		File file = new File(filePath);
		try (FileWriter  output = new FileWriter(file, true);) {	
			output.append(jsonParser.parse(program)); 
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

			return jsonParser.parse(sb.toString());
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

			return jsonParser.parse(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
			return new Program();
		}
	}	
	
	public void createDirectory(String path, String name) {
		try {
			Files.createDirectories(Paths.get(path + name));
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
	
	public void deleteDirectory(String path) {
		try {
			FileUtils.deleteDirectory(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update(String path, Program program) {
		File file = new File(path);	
		try (FileWriter  output = new FileWriter(file, true);
				PrintWriter writer = new PrintWriter(file);) {
			writer.print("");
			output.append(jsonParser.parse(program)); 
			output.flush();			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void replace(String path, String fileName, Program program) {
		delete(path + fileName +".txt");		
		write(path, program);
	}
	
	public void renameDirectory(String path, String oldName, String newName) {
		File file = new File(path + oldName);
		file.renameTo(new File(path + newName));
	}
}
