package com.TechM.TimeTracker.Tests;

import com.techm.timetracker.base.TestBase;
import com.techm.timetracker.pages.HomePage;
import com.techm.timetracker.pages.LoginPage;
import com.techm.timetracker.pages.TimeSheetPage;
import com.techm.timetracker.utils.TestUtils;

import static org.testng.Assert.assertEquals;

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

	@Test (priority=1, enabled = false)
	public void checkAndFillPresentWeekTest() throws InterruptedException
	{
		int currentWeekNumber = tsPage.listOfWeeks().size()-1;
		String presentWeekRange = tsPage.listOfWeeks().get(currentWeekNumber).getText();
		System.out.println("Present week range: " + presentWeekRange);
		
		if(tsPage.bTimesheetsFilledSelectedForWeek())
		{
			System.out.println("Timesheets filled for current week");
		}
		else
		{
			System.out.println("Timesheets not filled/partially filled for current week");
			for(int dayNumber=0; dayNumber <5; dayNumber++)
			{
				if(!tsPage.bDayTimesheetFilled(dayNumber))
				{
					tsPage.enterTimeForADay(dayNumber);
				}
			}			
		}
	}

	@Test (priority=2, enabled = true)
	public void checkAndFillTSFromASpecificWeekTest() throws InterruptedException
	{
		int currentWeekNumber = tsPage.listOfWeeks().size()-1;
		
		tsPage.selectRequiredWeek(TestUtils.REQUIRED_WEEK_NUMBER);
		System.out.println("Week number " + TestUtils.REQUIRED_WEEK_NUMBER + " selected !!! ");
		
		for(int i = TestUtils.REQUIRED_WEEK_NUMBER; i <= currentWeekNumber; i++)
		{
			if(tsPage.bTimesheetsFilledSelectedForWeek())
			{
				System.out.println("Timesheets filled for week" + i);
				tsPage.clickNextWeek();				
			}
			else
			{
				System.out.println("Timesheets not filled/partially filled for week" + TestUtils.REQUIRED_WEEK_NUMBER);
				for(int dayNumber=0; dayNumber <5; dayNumber++)
				{
					if(!tsPage.bDayTimesheetFilled(dayNumber))
					{
						tsPage.enterTimeForADay(dayNumber);
					}
				}			
			}
		}
	}


	@Test (priority=3, enabled = true)
	public void signOutTest() throws InterruptedException
	{
		tsPage.signOut();			
		assertEquals(driver.getCurrentUrl().equals(TestUtils.LOGIN_URL), true, "Login URL NOT matched");
		System.out.println("Sign out successful");
	}
	
	@AfterTest
	public void teardown()
	{
		System.out.println("testing success");
		driver.quit();
	}
}
