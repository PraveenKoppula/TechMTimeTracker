package com.TechM.TimeTracker.Tests;

import com.techm.timetracker.base.TestBase;
import com.techm.timetracker.pages.HomePage;
import com.techm.timetracker.pages.LoginPage;
import com.techm.timetracker.pages.TimeSheetPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TSPageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
	TimeSheetPage tsPage;

	public TSPageTest()
	{
		super();
	}
	
	@BeforeTest
	public void setup()
	{
		initialization();
		loginPage = new LoginPage();
		homePage =  loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		tsPage = homePage.enterToTSPage();
	}

	@Test (priority=1)
	public void selectPresentWeek()
	{
		int weeksPassed = tsPage.listOfWeeks().size();
		String presentWeekRange = tsPage.listOfWeeks().get(weeksPassed-1).getText();
		System.out.println("Present week range: " + presentWeekRange);
	}

	@Test (priority=2)
	public void selectRequiredWeek()
	{
		tsPage.selectRequiredWeek(6);
	}


	@AfterTest
	public void teardown()
	{
    System.out.println("testing success");
	  driver.quit();
	}
}
