package com.techm.timetracker.pages;

import java.util.List;

import org.openqa.selenium.By;
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
	WebElement WeekCombo;



//	Select s = new Select(driver.findElement(By.id("ddlWeek")));
//	List<WebElement> weeksList = s.getOptions();
//	int presentWeekNumber = weeksList.size()-1;
//	System.out.println("Present week number: " + presentWeekNumber);

	Select s;

	public List<WebElement> listOfWeeks()
	{
		s = new Select(WeekCombo);
		List<WebElement> weeksList = s.getOptions();
		
		return weeksList;
	}

	public void selectRequiredWeek(int weekNumber)
	{
			s.selectByIndex(weekNumber);
	}

}
