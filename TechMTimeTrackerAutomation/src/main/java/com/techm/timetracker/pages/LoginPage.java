package com.techm.timetracker.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.techm.timetracker.base.TestBase;

public class LoginPage extends TestBase {

	// Initializing the Page Objects:
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//*[@name='txtUser']")
	WebElement userID;
	
	@FindBy(xpath = "//*[@name='txtPwd']")
	WebElement userPwd;
	
	@FindBy(id="btnSubmit")
	WebElement loginBtn;
	
//	driver.findElement(By.xpath("//*[@name='txtUser']")).sendKeys("kp42371");
//	driver.findElement(By.xpath("//*[@name='txtPwd']")).sendKeys("Password@666");
//	driver.findElement(By.id("btnSubmit")).click();
	
	public void login(String id, String pwd )
	{
		userID.sendKeys(id);
		userPwd.sendKeys(pwd);
		loginBtn.click();
	}
	
	
}
