package org.webskey.opener.services;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.webskey.opener.model.Program;

public class DesktopRunner implements Runner{

	@Override
	public void run(Program program) throws IOException {
		Desktop.getDesktop().open(new File(program.getPath()));
	}
}
