package org.webskey.services;

import java.io.File;
import java.io.IOException;

public class RunTimeRunner {
	
	private RunningCommandsProvider runningCommandsProvider;
	
	public void run(File file) {
		runningCommandsProvider = new RunningCommandsProvider();				
		try {
			String[] command = runningCommandsProvider.getRuns(file);
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
/*	public void run() {
		FileTextReader fr = new FileTextReader();
		String s = fr.getFile();
		String[] command = {s, "http://magiafutbolu.pl/", "http://www.blaugrana.pl/"};
		
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}*/
}
