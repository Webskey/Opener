package org.webskey.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class FileTxtWriter {

	public void write(String text, Path path) {
		File file = new File(path.toString());
		
		try (FileWriter  output = new FileWriter(file, true);) {			
			output.append("\n" + text); 
			output.flush();			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
