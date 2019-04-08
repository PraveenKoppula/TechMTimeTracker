package com.techm.timetracker.pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

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
		if(nextWeekBtn.isEnabled())
		{
			System.out.println("Next week button enabled");
			nextWeekBtn.click();
		}
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
	
	public boolean isItCurrentDay(int dayNumber)
	{
		//lblDay0
		String dateToBeClicked = driver.findElement(By.id("lblDay"+dayNumber+"")).getText();
		System.out.println("Text of Date to be clicked: " + dateToBeClicked);		
		String dateFromUI= dateToBeClicked.substring(0,10);
		System.out.println(dateFromUI);
		
		Calendar cal = Calendar.getInstance();		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");		
		//Setting the time zone to IST
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
		//Formatting IST Date&Time as required
		System.out.println("Actual date in IST with formatting: " + simpleDateFormat.format(cal.getTime()));
		
		if(dateFromUI.equals(simpleDateFormat.format(cal.getTime())))
		{
			return true;
		}
		else {
			return false;	
		}
	}
	
	
	//Enters time data for a day
	public void enterTimeForADay(int i) throws InterruptedException {
		//boolean bFutureDateClicked = false;
		driver.findElement(By.id("lblDay"+i+"")).click();
		System.out.println("DAY-"+i+" CLICKED ");

//		if (!submitBtnPresent(i))
//		{
//			System.out.println("All timesheets filled. Have a great day !!! ");
//			bFutureDateClicked = true;
//		}
//		else
//		{		
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
		//}
		
		//return bFutureDateClicked;
	}

	//Returns true if submit button is present for a day
	public boolean submitBtnPresent(int dayNumber)
	{
		if(driver.findElements(By.id("lnkSubmit"+dayNumber+"")).size() > 0){
			return true;
		}else {
			return false;
		}
		
	}

	//Returns true if submitted successfully message displayed after submitting the time
	public boolean submittedsucccessfullyDisplayed(int dayNumber)
	{	
		if(driver.findElements(By.id("lblMsgDay"+dayNumber+"")).size() > 0 ){
			return true;
		}else{
			return false;
		}
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

