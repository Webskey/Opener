package org.webskey.opener.model;

public class FilesPaths {
	
	private static String projectPath;
	private static String programPath;
	
	public static String getPath() {
		return "data/Projects/";
	}
	
	public static String getProjectPath() {
		return projectPath;
	}
	
	public static void setProjectPath(String projectName) {
		projectPath = getPath() + projectName + "/";
	}
	
	public static String getProgramPath() {
		return programPath;
	}
	
	public static void setProgramPath(String programName) {
		programPath = getProjectPath() + programName + ".txt";
	}
}
