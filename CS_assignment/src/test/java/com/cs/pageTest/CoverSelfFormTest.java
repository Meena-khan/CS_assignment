package com.cs.pageTest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.cs.genericlib.Driver;
import com.cs.genericlib.ExcelLib;
import com.cs.genericlib.WebDriverBusinessLib;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 * @author Meena Khan
 *
 */
public class CoverSelfFormTest {

	WebDriver driver;
	ExcelLib excelLib;
	String userName;
	String email;
	String password;
	String confirmPassword;

	WebDriverBusinessLib webDriverBusinessLib = new WebDriverBusinessLib();

	/**
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	@BeforeClass
	public void configBeforeClass() throws InvalidFormatException, IOException {
		driver = Driver.getDriver("chrome");
	}

	
//	@BeforeMethod
//	public void configBeforeMtd(XmlTest paramConfig) throws InvalidFormatException, IOException {
//		// read data from workbook
//		excelLib = new ExcelLib();
//		userName = excelLib.getCellData("cs", 2, 1);
//		email = excelLib.getCellData("cs", 2, 2);
//		password = excelLib.getCellData("cs", 2, 3);
//		confirmPassword = excelLib.getCellData("cs", 2, 4);
//	}

	// NOTE- Could not make use of excel as my I dont have the license of the same
	// in my office laptop

	@Test(description = "1.	Validate the generate url loads.", priority = 1)
	public void validateNavigationToAppPage() throws Exception {

		Assert.assertTrue(webDriverBusinessLib.navigateToAPP());
	}

	@Test(description = "2.	Validate that when all the fields entered, the generate api is successful.", priority = 2)
	public void ValidateSuccessfulAPIGenerate() throws Exception {

		AssertJUnit.assertEquals(true,
				webDriverBusinessLib.fillGenerateApiForm("Name", "OrderCount", "Email", "ProductId", "order", "5"));
	}

	@Test(description = "3.	Validate if get request goes through with validation of data and saving the json file", priority = 3)
	public void validateGeneartedApiDataWithOutputFileSave() throws Exception {

		AssertJUnit.assertEquals(true, webDriverBusinessLib.validateCreatedAPI());
	}

	@AfterClass
	public void quitBrowser() {
		System.out.println("Quit Browser");
		Driver.driver.quit();
	}

}
