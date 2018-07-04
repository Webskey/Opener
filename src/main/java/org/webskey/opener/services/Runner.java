package org.webskey.opener.services;

import java.io.IOException;

import org.webskey.opener.model.Program;

public interface Runner {
	public void run(Program program) throws IOException;
}
