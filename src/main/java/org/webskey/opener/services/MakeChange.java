package org.webskey.opener.services;

import java.io.File;

import org.webskey.opener.gui.ChangeWindow;
import org.webskey.opener.model.Program;

public class MakeChange {
	
	public void changing(String path) {
		FileTextReader ftr = new FileTextReader();
		JsonToObjectParser jsonParser = new JsonToObjectParser();
		
		Program program;
		try {
			File file = new File(path);
			program = jsonParser.parse(ftr.getFile(file));			
		} catch (Exception e) {
			program = new Program();
			e.printStackTrace();
		}
		ChangeWindow addWindow = new ChangeWindow(program, path);  
		addWindow.show();
		
	}
}
