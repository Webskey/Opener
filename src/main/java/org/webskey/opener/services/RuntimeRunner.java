package org.webskey.opener.services;

import java.io.IOException;

import org.webskey.opener.model.Program;

public class RuntimeRunner implements Runner{

	@Override
	public void run(Program program) throws IOException {
		String[] options = program.getOptions().split(" ");
		String[] commands = new String[1 + options.length];
		commands[0] = program.getPath();

		for(int i = 1; i < commands.length; i++)
			commands[i] = options[i - 1];
		
		Runtime.getRuntime().exec(commands);
	}
}