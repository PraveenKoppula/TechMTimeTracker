package com.TechM.TimeTracker.Tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.techm.timetracker.base.TestBase;
import com.techm.timetracker.pages.HomePage;
import com.techm.timetracker.pages.LoginPage;

public class LoginPageTest extends TestBase{

	LoginPage loginPage; 
	HomePage homePage;
	
	public LoginPageTest()
	{
		super();
	}
	
	@BeforeTest
	public void setup()
	{
		initialization();
		loginPage = new LoginPage();
	}
	
	@Test
	public void loginTest()
	{
		homePage =  loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		assertEquals(homePage.checkIfFillTSImagePresent(),true,"Fill TimeSheet Not displayed");
	}
	
	@AfterTest
	public void tearDown()
	{
		System.out.println("Login success");
		driver.quit();
	}
	
}
