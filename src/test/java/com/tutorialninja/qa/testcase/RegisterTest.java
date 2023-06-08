package com.tutorialninja.qa.testcase;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorialninja.qa.testBase.TestBase;
import com.tutorialninja.qa.utilities.Utils;

public class RegisterTest extends TestBase{
	public  WebDriver driver;
	public SoftAssert softassert;
	@BeforeMethod
	public void setup() {
		driver=initializeBrowserAndOpenApplication("chrome");
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();	
	}
	@Test(priority=1)
	public void registerWithManadatoryFieldds() {
		driver.findElement(By.id("input-firstname")).sendKeys("Dirgha");
		driver.findElement(By.id("input-lastname")).sendKeys("Mishra");
		driver.findElement(By.id("input-email")).sendKeys(Utils.emailWithDateTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("9876543210");
		driver.findElement(By.id("input-password")).sendKeys("Selenium!123");
		driver.findElement(By.id("input-confirm")).sendKeys("Selenium!123");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		softassert = new SoftAssert();
		softassert.assertTrue(driver.findElement(By.xpath("//p[text()='Congratulations! Your new account has been successfully created!']")).isDisplayed());
		softassert.assertAll();
	}
	@Test(priority=2)
	public void registerWithAllfields() {
		driver.findElement(By.id("input-firstname")).sendKeys("Dirgha");
		driver.findElement(By.id("input-lastname")).sendKeys("Mishra");
		driver.findElement(By.id("input-email")).sendKeys(Utils.emailWithDateTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("9876543210");
		driver.findElement(By.id("input-password")).sendKeys("Selenium!123");
		driver.findElement(By.id("input-confirm")).sendKeys("Selenium!123");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='0']")).click();
		driver.findElement(By.name("agree")).click();
		 
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		softassert = new SoftAssert();
		softassert.assertTrue(driver.findElement(By.xpath("//p[text()='Congratulations! Your new account has been successfully created!']")).isDisplayed());
		softassert.assertAll();
		
	}
	@Test(priority=3)
	public void registerWithExistingEmailid() {
		driver.findElement(By.id("input-firstname")).sendKeys("Dirgha");
		driver.findElement(By.id("input-lastname")).sendKeys("Mishra");
		driver.findElement(By.id("input-email")).sendKeys("dirgha123@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("9876543210");
		driver.findElement(By.id("input-password")).sendKeys("Selenium!123");
		driver.findElement(By.id("input-confirm")).sendKeys("Selenium!123");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='0']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		softassert = new SoftAssert();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert alert-danger alert-dismissible')]")).getText();
		String expectedWarningMessage = "Warning: E-Mail Address is already registered!";
		softassert.assertEquals(actualWarningMessage,expectedWarningMessage);
		softassert.assertAll();
	}
	@Test(priority=4)
	public void registerWithNoFields() {
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualFirstnameWarningMessage = driver.findElement(By.xpath("//div[text()='First Name must be between 1 and 32 characters!']")).getText();
		String expectedFirstnamewarningMessage = "First Name must be between 1 and 32 characters!";
		softassert = new SoftAssert();
		softassert.assertEquals(actualFirstnameWarningMessage, expectedFirstnamewarningMessage);
		
		String actualLastnameWarningMessage = driver.findElement(By.xpath("//div[text()='Last Name must be between 1 and 32 characters!']")).getText();
		String expectedLastnamewarningMessage = "Last Name must be between 1 and 32 characters!";
		softassert = new SoftAssert();
		softassert.assertEquals(actualLastnameWarningMessage, expectedLastnamewarningMessage);
		
		String actualEmailWarningMessage = driver.findElement(By.xpath("//div[text()='E-Mail Address does not appear to be valid!']")).getText();
		String expectedEmailWarningMessage = "E-Mail Address does not appear to be valid!";
		softassert.assertTrue(actualEmailWarningMessage.contains(expectedEmailWarningMessage));
		
		String actualTelephoneWarningMessage = driver.findElement(By.xpath("//div[text()='Telephone must be between 3 and 32 characters!']")).getText();
		String expectedTelephonewarningmessage = "Telephone must be between 3 and 32 characters!";
		softassert.assertTrue(actualTelephoneWarningMessage.contains(expectedTelephonewarningmessage));
		
		String actualPasswordwarningmessage = driver.findElement(By.xpath("//div[text()='Password must be between 4 and 20 characters!']")).getText();
		String expectedPasswordwarningmessage = "Password must be between 4 and 20 characters!";
		softassert.assertTrue(actualPasswordwarningmessage.contains(expectedPasswordwarningmessage));
		
		String actualPrivacyWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert alert-danger alert-dismissible')]")).getText();
		String expectedPrivacyWarningMessage = "Warning: You must agree to the Privacy Policy!";
		softassert.assertTrue(actualPrivacyWarningMessage.contains(expectedPrivacyWarningMessage));
		softassert.assertAll();	
		
	}
		
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
