package org.webskey.services;

import java.io.IOException;

public class RunTimeRunner {
	
	public void run() {
		FileTextReader fr = new FileTextReader();
		String s = fr.getFile2();
		String[] command = {s, "http://magiafutbolu.pl/", "http://www.blaugrana.pl/"};
		
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
}
