package org.webskey.opener.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.webskey.opener.model.Program;

public class MajorClass {
	RuntimeRunner runtime = new RuntimeRunner();
	JsonToObjectParser jsonToObjectParser;
	FileTextReader fileTextReader;
	File folder;
	 
	 public void setFolder(String path) {
		 this.folder = new File(path);
	 }
	 
	 public File getFolder() {
		 return folder;
	 }
	 
	 public List<Program> getProgramList() throws IOException{
		 List<Program> list = new ArrayList<>();
		 jsonToObjectParser = new JsonToObjectParser();
		 fileTextReader = new FileTextReader();
		 File file = getFolder();
		 for (File fileEntry : file.listFiles()) {
				list.add(jsonToObjectParser.parse(fileTextReader.getFile(fileEntry)));
			}
		 return list;
	 }
}
