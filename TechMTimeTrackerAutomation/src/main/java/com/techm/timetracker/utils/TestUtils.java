package com.techm.timetracker.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.techm.timetracker.base.TestBase;

public class TestUtils extends TestBase{

	public static long PAGE_LOAD_TIMEOUT = 30;
	public static long IMPLICIT_WAIT = 20;
	public static int REQUIRED_WEEK_NUMBER = 27;
	public static String LOGIN_URL = "https://timetracker.techmahindra.com/mytime/Login.aspx";
	public static int i_workSELECTION =1;
	public static int i_projectSelection =1;
	public static int i_taskSelection =1;


	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}
}
