package org.webskey.opener.services;

import java.io.IOException;

import org.webskey.opener.model.Program;

public class ProgramsRunner {

	private Runner runner;

	public void run(Program program) {

		if(program.getOptions().equals("")) {
			runner = new DesktopRunner();
		} else {
			runner = new RuntimeRunner();
		}

		try {
			runner.run(program);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
