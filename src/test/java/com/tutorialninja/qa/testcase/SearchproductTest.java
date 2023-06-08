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

public class SearchproductTest extends TestBase{
	public  WebDriver driver;
	SoftAssert softassert= new SoftAssert();
	@BeforeMethod
	public void setUp() {
		driver=initializeBrowserAndOpenApplication("chrome");
	}
	@Test(priority=1)
	public void verifyValidProduct() {
		driver.findElement(By.name("search")).sendKeys("HP");
		driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		softassert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
		softassert.assertAll();
	}
	
	@Test(priority=2)
	public void verifyInvalidProduct() {
		driver.findElement(By.name("search")).sendKeys("dell");
		driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		softassert.assertTrue(driver.findElement(By.xpath("//p[text()='There is no product that matches the search criteria.']")).isDisplayed());
		softassert.assertAll();
}
	@Test(priority=3)
	public void verifySearchWithoutProduct() {
		driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		softassert.assertTrue(driver.findElement(By.xpath("//p[text()='There is no product that matches the search criteria.']")).isDisplayed());
		softassert.assertAll();
		
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	}

