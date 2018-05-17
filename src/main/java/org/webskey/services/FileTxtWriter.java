package org.webskey.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileTxtWriter {

	public static void main(String[] args) throws IOException {
		FileTxtWriter fw = new FileTxtWriter();
		fw.getFile("q.txt");
	}
	public void getFile(String fileName) {
		File file = new File("src/main/resources/q.txt");
		
		try (FileWriter  output = new FileWriter(file, true);) {			
			output.append("\nTadn"); 
			output.flush();			
		} catch (IOException e) {
			e.printStackTrace();
		}
		  

	}
}
