package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ExtentReportManager;
import utils.Log;

public class LoginTest extends BaseTest {
	
	@Test
	public void testValidLogin() {
		
		Log.info("Starting login test...");
		test = ExtentReportManager.createTest("Login Test");
		
		test.info("Naviagtion to URL");
		LoginPage loginPage = new LoginPage(driver);
		
		Log.info("Adding credentials...");
		test.info("Adding credentials...");
		loginPage.enterUsername("admin@yourstore.com");
		loginPage.enterPassword("admin");
		test.info("Clicking on Login Button");
		loginPage.clickLogin();
	
		
		System.out.println("Title of the page is " + driver.getTitle());
		Log.info("Verifying page title...");
		test.info("Verifying Page Title...");
		Assert.assertEquals(driver.getTitle(), "Just a moment...");
		
		test.pass("Login Successful");
	
	}
	
	@Test
	
public void testLoginInvalidCredentials() {
		
		Log.info("Starting login test...");
		test = ExtentReportManager.createTest("Login Test with invalid Credentials");
		
		test.info("Naviagtion to URL");
		LoginPage loginPage = new LoginPage(driver);
		
		Log.info("Adding credentials...");
		test.info("Adding credentials...");
		loginPage.enterUsername("admin1234@yourstore.com");
		loginPage.enterPassword("admin123");
		test.info("Clicking on Login Button");
		loginPage.clickLogin();
	
		
		System.out.println("Title of the page is " + driver.getTitle());
		Log.info("Verifying page title...");
		test.info("Verifying Page Title...");
		Assert.assertEquals(driver.getTitle(), "Just a moment...123");
		
		test.pass("Login Successful");
	
	}
}

