package com.cs.genericlib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * @author Meena Khan
 *
 */
public class Driver {

	public static WebDriver driver;
	String frameworkPath = System.getProperty("user.dir");

	public static String chromeDriverPath = System.getProperty("user.dir") + "/resources/chromedriver";

	public static WebDriver getDriver(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {
			System.out.println("path is " + chromeDriverPath);
			System.setProperty("webdriver.http.factory", "jdk-http-client");

			System.setProperty("webdriver.chrome.driver", chromeDriverPath);

			driver = new ChromeDriver();
			driver.manage().window().maximize();
			System.out.println("chrome is launched");

//			//This is used for adding extension

			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--start-maximized");
			chromeOptions.addArguments("--remote-allow-origins=*");

		}
		return driver;
	}
}
