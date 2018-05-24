package org.webskey.opener.services;

import java.io.File;
import java.io.IOException;

public class RuntimeRunner {

	private RunningCommandsProvider runningCommandsProvider;

	public RuntimeRunner() {
		runningCommandsProvider = new RunningCommandsProvider();			
	}

	public void run(File file) {			
		try {
			runningCommandsProvider.parse(file);
			String[] command = runningCommandsProvider.getPathOptions();
			File directory = null;
			if(runningCommandsProvider.getDirectory() != null)
				directory = new File(runningCommandsProvider.getDirectory());
			if(command.length < 2)
				Runtime.getRuntime().exec(command[0], null, directory);
			else
				Runtime.getRuntime().exec(command, null, directory);
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
}
