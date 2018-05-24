package org.webskey.opener.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileTextReader {	
	
	public String getFile(File file) {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){			
			String line="";
			while ((line = bufferedReader.readLine()) != null) 
				sb.append(line);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}	
}