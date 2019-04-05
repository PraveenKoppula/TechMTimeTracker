package com.techm.timetracker.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.techm.timetracker.base.TestBase;

public class HomePage extends TestBase {

	// Initializing the Page Objects:
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="ctl00_CPH_chkAcknow")
	WebElement AcknowledgeChkBox;
	
	@FindBy(id="ctl00_CPH_imgFill")
	WebElement fillTimesheetImage;
	
	public TimeSheetPage enterToTSPage()
	{
		AcknowledgeChkBox.click();
		fillTimesheetImage.click();
		
		return new TimeSheetPage();
	}
	
	public boolean checkIfFillTSImagePresent()
	{
		if(driver.findElements(By.id("ctl00_CPH_imgFill")).size() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
}
