package com.test.classes;

import java.io.*;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.Random;

import org.apache.log4j.BasicConfigurator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class AutomateiLab {
	
	
	static ExtentReports report;
	static ExtentTest logger;
	static ExtentTest logger2;
	static ExtentTest logger3;
	static ExtentTest logger4;
	
	@BeforeClass
	public static void startTest()
	{
		BasicConfigurator.configure();

	 report = new ExtentReports(System.getProperty(".\\Reports")+"\\ilabTestReport.html");
	logger = report.startTest("LauchIlab");
	logger2 = report.startTest("GoToCareers");
	logger3 = report.startTest("GoToSA");
	logger4 = report.startTest("ValidateError");
	}

	@Test
	public void launchiLabWeb() throws Exception {
		
		// Defining System Property for the ChromeDriver 
				 System.setProperty("webdriver.chrome.driver",
						"C:\\Users\\XY25626\\Desktop\\selenium_drivers\\chromedriver.exe");
				 WebDriver driver = new ChromeDriver();
			

		String url = "https://www.ilabquality.com/";

		driver.get(url);
		driver.manage().window().maximize();
		//System.out.println("application lauched successfully");
		if(driver.getTitle().equals("Home Page | iLAB - Software Quality Assurance"))
		{
		logger.log(LogStatus.PASS, "Launched successfully");
		}
		else
		{
		logger.log(LogStatus.FAIL, "Failed to launch");
		}
		report.endTest(logger);
		report.flush();

		//Using Excel data file
		File excelfile = new File("C:\\Users\\XY25626\\MavenAssessment\\IlabWebsite\\TestData\\TestData.xlsx");
		FileInputStream fileSt = new FileInputStream(excelfile);
		XSSFWorkbook workBook = new XSSFWorkbook(fileSt);
		XSSFSheet sheet1 = workBook.getSheetAt(0);// sheet1
		String name = sheet1.getRow(1).getCell(0).getStringCellValue();
		String email = sheet1.getRow(1).getCell(1).getStringCellValue();
		// String phoneNum = sheet1.getRow(1).getCell(2).getStringCellValue();
		
		//auto generate phone number
		Random generator = new Random();
		// First set of phone number
		String num1 = "0"; // will always be Zero
		int num2 = 8;
		int num3 = generator.nextInt(8);// 0-7
		int set2 = generator.nextInt(8999754) + 1000; // +1000 ensures a 3 digit number
		int phone = Integer
				.valueOf(String.valueOf(num1) + String.valueOf(num2) + String.valueOf(num3) + String.valueOf(set2));
		String phoneNumber = num1 + phone;
		// phoneNumber = phoneNumber.replaceAll("...", "$0 ");
		
		//navigate to careers
		WebElement btnCareers = driver.findElement(By.xpath("/html/body/header/div/div/div[3]/nav/ul/li[4]/a") );
		btnCareers.click();
		if(driver.getTitle().equals("CAREERS | iLAB Software Quality Assurance"))
		{
		logger2.log(LogStatus.PASS, "Launched Careers page");
		}
		else
		{
		logger2.log(LogStatus.FAIL, "Failed navigate to careers");
		}
		report.endTest(logger2);
		report.flush();

		//Navigate to South Africa 
		driver.findElement(By.cssSelector(
				"body > section > div.section-content > div > div > div > div:nth-child(3) > div.wpb_column.vc_column_container.vc_col-sm-12 > div > div > div:nth-child(3) > div.wpb_column.vc_column_container.vc_col-sm-4 > div > div > div:nth-child(12) > a"))
				.click();
		if(driver.getTitle().equals("SOUTH AFRICA | iLAB"))
		{
		logger3.log(LogStatus.PASS, "South Africa selected");
		}
		else
		{
		logger3.log(LogStatus.FAIL, "Failed to select South Africa");
		}
		report.endTest(logger3);
		report.flush();
		//Select the first job listing
		driver.findElement(By.xpath(
				"/html/body/section/div[2]/div/div/div/div[3]/div[2]/div/div/div/div/div/div[1]/div[1]/div[2]/div[1]/a"))
				.click();
		// click on apply
		driver.findElement(By.xpath("//*[@id=\"wpjb-scroll\"]/div[1]/a")).click();// navigate to apply
		driver.findElement(By.xpath("//*[@id=\"applicant_name\"]")).sendKeys(name);
		driver.findElement(By.id("email")).sendKeys(email);

		// ensure that phoneNumber has 10 digits
		if (phoneNumber.length() < 10) {
			phoneNumber = phoneNumber + "6";
		} else {
			driver.findElement(By.id("phone")).sendKeys(phoneNumber);

		}

		driver.findElement(By.id("wpjb_submit")).click();

		String actualErrorMessage;
		String expectedErrorMessage = "You need to upload at least one file.";
		// message = driver.findElement(By.cssSelector("#wpjb-form-job-apply > div >
		// span")).getText();

		actualErrorMessage = driver.findElement(By.cssSelector(
				"#wpjb-apply-form > fieldset.wpjb-fieldset-apply > div.wpjb-error.wpjb-element-input-file.wpjb-element-name-file > div > ul"))
				.getText();
		if (actualErrorMessage.equalsIgnoreCase(expectedErrorMessage)) {
			logger4.log(LogStatus.PASS, "Error message Verified");
	}
	else
	{
	logger4.log(LogStatus.FAIL, "Failed to verify error message");
	}
		report.endTest(logger4);
		report.flush();
		
	}
	

}
