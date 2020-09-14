package tepDefinations;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.Alert;
import io.cucumber.java.en.*;


public class ApplySteps {

	WebDriver driver =null;
	
	@Given("the browser is open")
	public void the_browser_is_open() {
		
		
		// Defining System Property for the ChromeDriver 
		 System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\XY25626\\Desktop\\selenium_drivers\\chromedriver.exe");
		 driver = new ChromeDriver();
		
		
		
		/* Defining System Property for the IEDriver 
		System.setProperty("webdriver.ie.driver",
				"C:\\Users\\XY25626\\Desktop\\selenium_drivers\\IEDriverServer.exe");
		// Instantiate a IEDriver class. 
		driver=new InternetExplorerDriver();*/
		
		String url = "https://www.ilabquality.com/";

		driver.get(url);
		System.out.println("application lauched successfully");

		driver.manage().window().maximize();
	
	}

	@And("User navigates to Apply in Careers page South Africa")
	public void user_navigates_to_apply_in_careers_page_south_africa() {
		
		driver.findElement(By.xpath("/html/body/header/div/div/div[3]/nav/ul/li[4]/a") ).click();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.cssSelector(
				"body > section > div.section-content > div > div > div > div:nth-child(3) > div.wpb_column.vc_column_container.vc_col-sm-12 > div > div > div:nth-child(3) > div.wpb_column.vc_column_container.vc_col-sm-4 > div > div > div:nth-child(12) > a"))
				.click();
		driver.findElement(By.xpath(
				"/html/body/section/div[2]/div/div/div/div[3]/div[2]/div/div/div/div/div/div[1]/div[1]/div[2]/div[1]/a"))
				.click();
		//WebElement clickBtn =
			driver.findElement(By.xpath("//*[@id=\"wpjb-scroll\"]/div[1]/a")).click();// apply
		

	
	}

	@When("^User enters (.*) (.*) and (.*)$")
	public void user_enters_name_email_and_phone(String name, String email,String phone) {
		driver.findElement(By.xpath("//*[@id=\"applicant_name\"]")).sendKeys(name);
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("phone")).sendKeys(phone);
		
		
		
	}
	
	@And("user clicks on apply")
	public void user_clicks_on_apply() {
		driver.findElement(By.id("wpjb_submit")).click();
		
		
	   
	}




	@Then("ErrorMessage text is displayed")
	public void errorMessage_text_is_displayed() {
		String actualErrorMessage;
		String expectedErrorMessage = "You need to upload at least one file.";
		
		actualErrorMessage = driver.findElement(By.cssSelector(
				"#wpjb-apply-form > fieldset.wpjb-fieldset-apply > div.wpjb-error.wpjb-element-input-file.wpjb-element-name-file > div > ul"))
				.getText();
		//Assert.assertTrue(expectedErrorMessage.contains(actualErrorMessage));

		// Displaying alert message
		System.out.println(actualErrorMessage);
		
		driver.quit();
	}




}
