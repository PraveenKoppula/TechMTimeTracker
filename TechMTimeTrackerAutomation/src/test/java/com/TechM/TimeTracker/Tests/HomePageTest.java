package com.TechM.TimeTracker.Tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.techm.timetracker.base.TestBase;
import com.techm.timetracker.pages.HomePage;
import com.techm.timetracker.pages.LoginPage;
import com.techm.timetracker.pages.TimeSheetPage;

public class HomePageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
	TimeSheetPage tsPage; 
	
	public HomePageTest()
	{
		super();
	}
	
	@BeforeTest
	public void setup()
	{
		initialization();
		loginPage = new LoginPage();
		homePage =  loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void NavigateToTSPage()
	{
		tsPage = homePage.enterToTSPage();		
	}

	@Test
	public void chekLogo()
	{
		System.out.println("logo checked");
	}
	
	@AfterTest
	public void teardown()
	{
		driver.quit();
	}
}
