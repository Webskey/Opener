package org.webskey.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileTextReader {
	/*public static void main(String[] args) {
		FileTextReader fr = new FileTextReader();
		String s = fr.getFile("q.txt");
		System.out.println(s);
	}*/
	
	public String getFile2() {
		StringBuilder sb = new StringBuilder();
		try {
			File file = new File("src/main/resources/q.txt");
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			String line="";
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
			bufferedReader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}	
}