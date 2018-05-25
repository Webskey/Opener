package org.webskey.opener.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.webskey.opener.model.Program;

public class MajorClass {
	RuntimeRunner runtime = new RuntimeRunner();
	JsonToObjectParser jsonToObjectParser;
	FileTextReader fileTextReader;
	File folder;
	 
	 public void setFolder(String path) {
		 this.folder = new File(path);
	 }
	 
	 public File getFoldero() {
		 return folder;
	 }
	 
	 public Optional<File> getFolder() {
		 return Optional.ofNullable(folder);
	 }
	 
	 public List<Program> getProgramList() throws IOException{
		 List<Program> list = new ArrayList<>();
		 jsonToObjectParser = new JsonToObjectParser();
		 fileTextReader = new FileTextReader();
		 //File f = getFolder().orElse(new File("src/main/resources/first"));
		 File f = getFolder().orElse(new File("data/sth"));
		 for (File fileEntry : f.listFiles()) {
				list.add(jsonToObjectParser.parse(fileTextReader.getFile(fileEntry)));
			}
		 return list;
	 }
}
