package com.cs.genericlib;

/**
 * @author Meena Khan
 *
 */
public interface Constants {

	String frameworkPath = System.getProperty("user.home");

	String screenShotFolderPath = frameworkPath + "\\ScreenShot\\";
	String excelFilePath = "";
	String generateapiUrl = "https://retool.com/api-generator";
	Long implicitWait = (long) 30;
	Long pageLoadTime = (long) 50;

	String registerPath = frameworkPath
			+ "/eclipse-workspace/CS_assignment/src/main/java/com/cs/pageLibrary/generateAPI.properties";

}
