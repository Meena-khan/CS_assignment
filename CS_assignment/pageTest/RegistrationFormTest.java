package com.ether.pageTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.ether.genericLibrary.Driver;
import com.ether.genericLibrary.ExcelLib;
import com.ether.genericLibrary.WebDriverBusinessLib;

import junit.framework.Assert;

/**
 * @author Meena Khan
 *
 */
public class RegistrationFormTest {

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

	/**
	 * @param paramConfig
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	@BeforeMethod
	public void configBeforeMtd(XmlTest paramConfig) throws InvalidFormatException, IOException {
		// read data from workbook
		excelLib = new ExcelLib();
		userName = excelLib.getCellData("Ether", 2, 1);
		email = excelLib.getCellData("Ether", 2, 2);
		password = excelLib.getCellData("Ether", 2, 3);
		confirmPassword = excelLib.getCellData("Ether", 2, 4);
	}

	/**
	 * First Test case Scenario
	 * 
	 * @throws Exception
	 */
	@Test(description = "1.	Validate that page with url i.e https://etherscan.io/register is up and displaying properly.", priority = 1)
	public void validateNavigationToEtherscanPage() throws Exception {

		Assert.assertTrue(webDriverBusinessLib.navigateToAPP());
	}

	/**
	 * Second Test case Scenario
	 * 
	 * @throws Exception
	 */
	@Test(description = "2.	Validate that when all the fields entered, the registration is getting successful.", priority = 2)
	public void ValidateSuccessfulRegistrationOfEtherscanForm() throws Exception {

		Assert.assertEquals(true,
				webDriverBusinessLib.fillRegistrationForm(userName, email, password, confirmPassword, true));
	}

	/**
	 * Third Test case Scenario
	 * 
	 * @throws Exception
	 */
	@Test(description = "3.	Validate if all fields are not given (considering all are mandatory) and user clicks on ‘create an account’, the validation message should be shown individually", priority = 3)
	public void validateErrorMessageOnRegistrationForm() throws Exception {

		Assert.assertTrue(webDriverBusinessLib.navigateToAPP());
		Assert.assertEquals(true, webDriverBusinessLib.validateErrorMessagesOnRegistrationForm());
	}

	/**
	 * Fourth Test case Scenario
	 * 
	 * @throws Exception
	 */
	@Test(description = "4.	Validate the links available on the page i.e ‘Term and Conditions’ and ‘unsubscribe’ are not broken and navigates to the corresponding page on link when clicked.", priority = 4)
	public void validateLinksOnRegistrationForm() throws Exception {

		Assert.assertTrue(webDriverBusinessLib.navigateToAPP());
		Assert.assertEquals(true, webDriverBusinessLib.validateLinksOnRegistrationPage());
	}

	/**
	 * Fifth Test case Scenario
	 * 
	 * @throws Exception
	 */
	@Test(description = "5.	Validate if incomplete information is given and the user clicks on ‘Create an account’ button, then only the remaining unfilled fields should show the validation message.", priority = 5)
	public void validateRegistrationFormValidationForIncompleteForm() throws Exception {

		Assert.assertTrue(webDriverBusinessLib.navigateToAPP());
		Assert.assertEquals(true,
				webDriverBusinessLib.validateErrorMessageForIncompleteRegistrationForm(userName, email));
	}

	/**
	 * Seventh Test case Scenario
	 * 
	 * @throws Exception
	 */
	@Test(description = "7.	Validate without completing the captcha the user is not able to register to etherscan form registration and the validation message is shown correctly for captcha.", priority = 6)
	public void validateRegistrationFormValidationWithoutCaptchaFill() throws Exception {

		Assert.assertTrue(webDriverBusinessLib.navigateToAPP());
		Assert.assertEquals(true,
				webDriverBusinessLib.fillRegistrationForm(userName, email, password, confirmPassword, false));
	}

	@AfterClass
	public void quitBrowser() {
		System.out.println("Quit Browser");
		Driver.driver.quit();
	}

}
