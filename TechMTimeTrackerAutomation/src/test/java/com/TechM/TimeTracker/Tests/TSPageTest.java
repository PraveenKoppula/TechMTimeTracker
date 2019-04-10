package com.TechM.TimeTracker.Tests;

import com.techm.timetracker.base.TestBase;
import com.techm.timetracker.pages.HomePage;
import com.techm.timetracker.pages.LoginPage;
import com.techm.timetracker.pages.TimeSheetPage;
import com.techm.timetracker.utils.TestUtils;

import static org.testng.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TSPageTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	TimeSheetPage tsPage;

	public TSPageTest() {
		super();
	}

	@BeforeTest
	public void setup() {
		initialization();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		tsPage = homePage.enterToTSPage();
	}

	@Test(priority = 1, enabled = true)
	public void verifyCurrentWeekIsdisplayed() throws InterruptedException {
		boolean bCurrentWeekIsdisplayed = false;
		// To get the text of last option from the week range combo
		int currentWeekNumber = tsPage.listOfWeeks().size() - 1;
		String presentWeekRange = tsPage.listOfWeeks().get(currentWeekNumber).getText();
		System.out.println("Present week range: " + presentWeekRange);
		// Trim week number from the complete text
		String weekNumberFromUI = presentWeekRange.substring(9, 11);
		System.out.println("Week number from site: " + weekNumberFromUI);

		Calendar cal = Calendar.getInstance();
		// Get WEEK_OF_YEAR from Calendar class
		System.out.println("Week of the year from calendar obj : " + cal.get(Calendar.WEEK_OF_YEAR));

		if (Integer.parseInt(weekNumberFromUI) == cal.get(Calendar.WEEK_OF_YEAR)) {
			System.out.println("Week number matched. Current week displayed !!!");
			bCurrentWeekIsdisplayed = true;
		} else {
			System.out.println("Week number NOT matched. Current week NOT displayed !!!");
		}

		assertEquals(bCurrentWeekIsdisplayed, true, "Week number NOT matched");
	}

	@Test(priority = 2, enabled = true)
	public void checkAndFillPresentWeekTest() throws InterruptedException {
		int currentWeekNumber = tsPage.listOfWeeks().size() - 1;
		String presentWeekRange = tsPage.listOfWeeks().get(currentWeekNumber).getText();
		System.out.println("Present week range: " + presentWeekRange);

		if (tsPage.bTimesheetsFilledSelectedForWeek()) {
			System.out.println("Timesheets filled for current week");
		} else {
			System.out.println("Timesheets not filled/partially filled for current week");
			for (int dayNumber = 0; dayNumber < 5; dayNumber++) {
				
				if (!tsPage.isItCurrentDay(dayNumber)) {
					if (!tsPage.bDayTimesheetFilled(dayNumber)) {
						tsPage.clickDayToExpand(dayNumber);
						if (tsPage.submitBtnPresent(dayNumber)) {
							tsPage.enterTimeForADay(dayNumber);
						} else {
							System.out.println("Timesheets filled for all days...");
							break;
						}
					}
				} else {
					if (!tsPage.bDayTimesheetFilled(dayNumber)) {
						tsPage.clickDayToExpand(dayNumber);
						tsPage.enterTimeForADay(dayNumber);
						break;
					}
				}
			}
		}
	}

	@Test(priority = 3, enabled = true)
	public void checkAndFillTSFromASpecificWeekTest() throws InterruptedException {
		int currentWeekNumber = tsPage.listOfWeeks().size() - 1;

		tsPage.selectRequiredWeek(TestUtils.REQUIRED_WEEK_NUMBER);
		System.out.println("Week number " + TestUtils.REQUIRED_WEEK_NUMBER + " selected !!! ");

		for (int i = TestUtils.REQUIRED_WEEK_NUMBER; i <= currentWeekNumber; i++) {
			if (tsPage.bTimesheetsFilledSelectedForWeek()) {
				System.out.println("Timesheets filled for week" + i);
				tsPage.clickNextWeek();
			} else {
				System.out.println("Timesheets not filled/partially filled for week" + i);
				for (int dayNumber = 0; dayNumber < 5; dayNumber++) {
					if (!tsPage.isItCurrentDay(dayNumber)) {
						if (!tsPage.bDayTimesheetFilled(dayNumber)) {
							tsPage.clickDayToExpand(dayNumber);
							if (tsPage.submitBtnPresent(dayNumber)) {
								tsPage.enterTimeForADay(dayNumber);
							} else {
								System.out.println("Timesheets filled for all days...");
								break;
							}
						}
					} else {
						if (!tsPage.bDayTimesheetFilled(dayNumber)) {
							tsPage.clickDayToExpand(dayNumber);
							tsPage.enterTimeForADay(dayNumber);
							break;
						}
					}
				}
			}
		}
	}

	@Test(priority = 4, enabled = true)
	public void signOutTest() throws InterruptedException {
		tsPage.signOut();
		assertEquals(driver.getCurrentUrl().equals(TestUtils.LOGIN_URL), true, "Login URL NOT matched");
		System.out.println("Sign out successful");
	}

	@AfterTest
	public void teardown() {
		System.out.println("testing success");
		driver.quit();
	}
}
