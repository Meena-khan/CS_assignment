package com.cs.genericlib;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * @author Meena Khan
 *
 */
public class WebDriverBusinessLib {

	ObjectRepository obj = new ObjectRepository();
	JavascriptExecutor js = (JavascriptExecutor) Driver.driver;
	static String apiUrl;
	static String firstName;

	public void clearData(String web) {

		WebElement textdata = Driver.driver.findElement(By.xpath(web));
		String textValue = textdata.getAttribute("value");

		System.out.println(textValue);

		int len_of_textbox = textValue.length();
		for (int i = 0; i <= len_of_textbox; i++) {
			Driver.driver.findElement(By.xpath(web)).sendKeys(Keys.CONTROL, Keys.chord("a"), Keys.BACK_SPACE);
		}

	}

	// implicit wait
	@SuppressWarnings("deprecation")
	public void implicitLoadTime() {
		Driver.driver.manage().timeouts().implicitlyWait(Constants.implicitWait, TimeUnit.SECONDS);
	}

	// navigate to url
	public Boolean navigateToAPP() throws IOException {
		Boolean flag = false;
		Properties registrationPage = obj.getObjectPage(Constants.registerPath);
		System.out.println(Constants.registerPath);

		String registrationValidationText = registrationPage.getProperty("registrationPageTxt");

		Driver.driver.get(Constants.generateapiUrl);
		System.out.println(Constants.generateapiUrl);

		implicitLoadTime();
		if (Driver.driver.findElement(By.xpath(registrationValidationText)).isDisplayed()) {
			flag = true;
		}
		return flag;
	}

	// method to generate the api
	public boolean fillGenerateApiForm(String columnName1, String columnName2, String columnName3, String columnName4,
			String apiName, String apiRow) throws IOException, InterruptedException {

		WebElement frame = Driver.driver.findElement(By.xpath("//iframe[@title='REST API Generator']"));

		Boolean flag = false;
		Properties registrationPage = obj.getObjectPage(Constants.registerPath);

		String columnTitleEdt0 = registrationPage.getProperty("columnTitleEdt0");
		String columnTitleEdt1 = registrationPage.getProperty("columnTitleEdt1");
		String columnTitleEdt2 = registrationPage.getProperty("columnTitleEdt2");
		String columnTitleEdt3 = registrationPage.getProperty("columnTitleEdt3");

		String dataTypeEdt0 = registrationPage.getProperty("dataTypeEdt0");
		String peopleLnk = registrationPage.getProperty("peopleLnk");

		String addColumnLnk = registrationPage.getProperty("addColumnLnk");
		String buildTxt = registrationPage.getProperty("buildTxt");

		String apiNameEdt = registrationPage.getProperty("apiNameEdt");
		String apiRowsEdt = registrationPage.getProperty("apiRowsEdt");
		String generateApiBtn = registrationPage.getProperty("generateApiBtn");
		String apiPreviewTxt = registrationPage.getProperty("apiPreviewTxt");

		String resultsTxt = registrationPage.getProperty("resultsTxt");
		String apiViewTbl = registrationPage.getProperty("apiViewTbl");
		String fullNameLnk = registrationPage.getProperty("fullNameLnk");
		String createdApi = registrationPage.getProperty("createdApi");
		String dataTypeEdt1 = registrationPage.getProperty("dataTypeEdt1");
		String dataTypeEdt2 = registrationPage.getProperty("dataTypeEdt2");
		String dataTypeEdt3 = registrationPage.getProperty("dataTypeEdt3");

		String numberTxt = registrationPage.getProperty("numberTxt");
		String emailTxt = registrationPage.getProperty("emailTxt");
		String productIdTxt = registrationPage.getProperty("productIdTxt");


		Thread.sleep(7000);
		frame = Driver.driver.findElement(By.xpath("//iframe[@title='REST API Generator']"));
		Driver.driver.switchTo().frame(frame);

		if (Driver.driver.findElement(By.xpath(buildTxt)).isDisplayed()) {

			System.out.println("page is loaded");

			clearData(columnTitleEdt0);

			Driver.driver.findElement(By.xpath(columnTitleEdt0)).sendKeys(columnName1);
			Driver.driver.findElement(By.xpath(dataTypeEdt0)).click();
			Driver.driver.findElement(By.xpath(dataTypeEdt0)).click();

			Driver.driver.findElement(By.xpath(peopleLnk)).click();
			Driver.driver.findElement(By.xpath(fullNameLnk)).click();

			JavascriptExecutor jsa = (JavascriptExecutor) Driver.driver;
			jsa.executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath(addColumnLnk)));

			clearData(columnTitleEdt1);
			Driver.driver.findElement(By.xpath(columnTitleEdt1)).sendKeys(columnName2);

			Driver.driver.findElement(By.xpath(dataTypeEdt1)).click();
			Driver.driver.findElement(By.xpath(dataTypeEdt1)).sendKeys("Numbers");

			Driver.driver.findElement(By.xpath(numberTxt)).click();

			JavascriptExecutor jsaa = (JavascriptExecutor) Driver.driver;
			jsaa.executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath(addColumnLnk)));

			clearData(columnTitleEdt2);
			Driver.driver.findElement(By.xpath(columnTitleEdt2)).sendKeys(columnName3);
			Driver.driver.findElement(By.xpath(dataTypeEdt2)).click();
			Driver.driver.findElement(By.xpath(dataTypeEdt2)).sendKeys("People");

			Driver.driver.findElement(By.xpath(emailTxt)).click();

			JavascriptExecutor js1 = (JavascriptExecutor) Driver.driver;
			js1.executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath(addColumnLnk)));

			clearData(columnTitleEdt3);
			Driver.driver.findElement(By.xpath(columnTitleEdt3)).sendKeys(columnName4);
			Driver.driver.findElement(By.xpath(dataTypeEdt3)).click();
			Driver.driver.findElement(By.xpath(dataTypeEdt3)).sendKeys("Numbers");
			

			JavascriptExecutor js2 = (JavascriptExecutor) Driver.driver;
			js2.executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath(productIdTxt)));

			Driver.driver.findElement(By.xpath(apiNameEdt)).click();

			clearData(apiNameEdt);
			Driver.driver.findElement(By.xpath(apiNameEdt)).sendKeys(apiName);

			clearData(apiRowsEdt);
			Driver.driver.findElement(By.xpath(apiRowsEdt)).sendKeys(apiRow);

			Driver.driver.findElement(By.xpath(generateApiBtn)).click();

			if (Driver.driver.findElement(By.xpath(apiPreviewTxt)).isDisplayed()
					&& Driver.driver.findElement(By.xpath(resultsTxt)).isDisplayed()) {

				Dimension dimensions = Driver.driver.findElement(By.xpath(apiViewTbl)).getSize();

				int width = dimensions.getWidth();
				int height = dimensions.getHeight();
				System.out.println("Width: " + width + ", Height: " + height + " of the table ");
				flag = true;

			}

			apiUrl = Driver.driver.findElement(By.xpath(createdApi)).getText();
			System.out.println(apiUrl);

		}
		captureTableData();
		return flag;
	}

	public boolean validateCreatedAPI() {

		Boolean statusflag = false;

		Response response = RestAssured.given().when().get(apiUrl).then().extract().response();

		System.out.println(response.asString());
		int statuscode = response.statusCode();

		if (statuscode == 200) {
			System.out.println("get request passed");
			statusflag = true;

		}

		ResponseBody body = response.getBody();
		String bodyAsString = body.asString();

		try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("response.json"), "UTF-8")) {
			if (body != null) {
				String responseBodyString = body.asString();
				writer.write(responseBodyString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		Assert.assertEquals(bodyAsString.contains(firstName), true, firstName);
		return statusflag;

	}

	// method to capture the table data
	public String captureTableData() throws IOException {

		ObjectRepository obj = new ObjectRepository();
		Properties registrationPage = obj.getObjectPage(Constants.registerPath);

		String firstUserNameTxt = registrationPage.getProperty("firstUserNameTxt");

		firstName = Driver.driver.findElement(By.xpath(firstUserNameTxt)).getText();
		System.out.println("First username " + firstName);
		return firstName;
	}

}
