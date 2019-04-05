package com.TechM.TimeTracker.Tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.techm.timetracker.base.TestBase;
import com.techm.timetracker.pages.HomePage;
import com.techm.timetracker.pages.LoginPage;

public class LoginPageTests extends TestBase{

	LoginPage login; 
	HomePage homePage;
	
	public LoginPageTests()
	{
		super();
	}
	
	@BeforeTest
	public void setup()
	{
		initialization();
		login = new LoginPage();
	}
	
	@Test
	public void loginTest()
	{
		homePage =  login.login(prop.getProperty("username"), "password");
		
	}
	
	@AfterTest
	public void tearDown()
	{
		System.out.println("Login success");
		//driver.close();
	}
	
}
