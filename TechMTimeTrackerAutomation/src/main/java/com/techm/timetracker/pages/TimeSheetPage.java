package com.techm.timetracker.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.techm.timetracker.base.TestBase;

public class TimeSheetPage extends TestBase {

	// Initializing the Page Objects:
	public TimeSheetPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="ddlWeek")
	WebElement weekCombo;

	@FindBy(id="lnkFind")
	WebElement findBtn;

	@FindBy(id="lblCompHours")
	WebElement compensationHours;
	
	@FindBy(id="gvTimesheetDay0")
	WebElement timehsheet0;
	
	@FindBy(id="lblName")
	WebElement employeeName;
	
	@FindBy(xpath="//*[@href='Logout.aspx']")
	WebElement logOut;

	@FindBy(id="lblMsgDay1")
	WebElement tsSubmittedForTuesday;
	
	@FindBy(id="lnkNextWeek")
	WebElement nextWeekBtn;	
	
	Select s;

	public List<WebElement> listOfWeeks()
	{
		s = new Select(weekCombo);
		List<WebElement> weeksList = s.getOptions();
		return weeksList;
	}

	public void selectRequiredWeek(int weekNumber)
	{
		s = new Select(weekCombo);
		s.selectByIndex(weekNumber);
		findBtn.click();		
	}

	public void clickNextWeek()
	{
		nextWeekBtn.click();
	}
	
	public boolean bTimesheetsFilledSelectedForWeek()
	{
		if(compensationHours.getText().equals("40.00")){
			return true;
		}else {
			return false;
		}
	}
	
	//Check whether timesheet filled for a day - if it says 9hrs =filled or else not filled
	public boolean bDayTimesheetFilled(int dayNumber)
	{
		if(driver.findElement(By.id("lblDay"+dayNumber+"_Total")).getText().equals("09.00"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//Checks whether timesheet is filled for a day and calls enterTimeForADay() method to fill data
	public void checkAndFillTimesheets(int dayNumber) throws InterruptedException
	{
		if(driver.findElement(By.id("lblDay"+dayNumber+"_Total")).getText().equals("09.00")) {
			System.out.println("DAY"+dayNumber+" Timesheet already filled.");			
		}
		else {
			enterTimeForADay(dayNumber);
		}
		
	}
	
	//Enters time data for a day
	public void enterTimeForADay(int i) throws InterruptedException {
		driver.findElement(By.id("lblDay"+i+"")).click();
		System.out.println("DAY-"+i+" CLICKED ");

		Thread.sleep(3000);
		driver.findElement(By.id("lnkAddDay"+i+"")).click();
		 Thread.sleep(3000);
		 
		s = new Select(driver.findElement(By.id("gvTimesheetDay"+i+"_ctl02_ddlPANPA")));
		s.selectByIndex(1); Thread.sleep(3000);

		s = new Select(driver.findElement(By.id("gvTimesheetDay"+i+"_ctl02_ddlProject")));
		s.selectByIndex(1); Thread.sleep(3000);

		s = new Select(driver.findElement(By.id("gvTimesheetDay"+i+"_ctl02_ddlPayCode")));
		s.selectByIndex(2); Thread.sleep(3000);

		driver.findElement(By.id("gvTimesheetDay"+i+"_ctl02_txtSignIn")).sendKeys("08:00");
		driver.findElement(By.id("gvTimesheetDay"+i+"_ctl02_txtSignOut")).sendKeys("12:00");
		Thread.sleep(3000);
		
		s = new Select(driver.findElement(By.id("gvTimesheetDay"+i+"_ctl03_ddlPANPA")));
		s.selectByIndex(1); Thread.sleep(3000);

		s = new Select(driver.findElement(By.id("gvTimesheetDay"+i+"_ctl03_ddlProject")));
		s.selectByIndex(1); Thread.sleep(3000);

		s = new Select(driver.findElement(By.id("gvTimesheetDay"+i+"_ctl03_ddlPayCode")));
		s.selectByIndex(2); Thread.sleep(3000);

		driver.findElement(By.id("gvTimesheetDay"+i+"_ctl03_txtSignIn")).sendKeys("13:00");
		driver.findElement(By.id("gvTimesheetDay"+i+"_ctl03_txtSignOut")).sendKeys("17:00");

		driver.findElement(By.id("txtMBSignIn"+i+"")).sendKeys("12:00");
		driver.findElement(By.id("txtMBSignOut"+i+"")).sendKeys("13:00");
		driver.findElement(By.id("lnkSubmit"+i+"")).click();
		
		driver.switchTo().alert().accept(); Thread.sleep(2000);
		driver.switchTo().alert().accept(); Thread.sleep(5000);
		
		System.out.println(tsSubmittedForTuesday.getText());
	}

	public void signOut()
	{
		employeeName.click();
		logOut.click();
		
	}

}

//*************IDs of timesheet screens fields***************************
// lblDay0
// lnkAddDay0

// gvTimesheetDay0_ctl02_ddlPayCodeType
// gvTimesheetDay0_ctl02_ddlPANPA
// gvTimesheetDay0_ctl02_ddlProject
// gvTimesheetDay0_ctl02_ddlPayCode
// gvTimesheetDay0_ctl02_txtSignIn
// gvTimesheetDay0_ctl02_txtSignOut

// gvTimesheetDay0_ctl03_ddlPayCodeType
// gvTimesheetDay0_ctl03_ddlPANPA
// gvTimesheetDay0_ctl03_ddlProject
// gvTimesheetDay0_ctl03_ddlPayCode
// gvTimesheetDay0_ctl03_txtSignIn
// gvTimesheetDay0_ctl03_txtSignOut

// txtMBSignIn0
// txtMBSignOut0
// txtRemarks0
// lnkSubmit0

//lblDay1
//****************************************

