package com.techm.timetracker.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.techm.timetracker.utils.TestUtils;
import com.techm.timetracker.utils.WebEventListener;

public class TestBase {

	public static Properties prop;
	public static WebDriver driver;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = null;

			if(System.getProperty("os.name").contains("Windows"))
			{
				System.out.println("OS is Windows");
				ip = new FileInputStream(System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\techm\\timetracker\\config\\config.properties");
			}else if(System.getProperty("os.name").contains("Linux")) {
				System.out.println("OS is Linux");
				ip = new FileInputStream("src/main/java/com/techm/timetracker/config/config.properties");
			}

			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void initialization()
	{
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome"))
		{
			if(System.getProperty("os.name").contains("Linux")) {
				System.out.println("OS is Linux");
				System.setProperty("webdriver.chrome.driver", "chromedriver");
			}else if(System.getProperty("os.name").contains("Windows")) {
				System.out.println("OS is Windows");
				System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			}
			driver = new ChromeDriver();
		}
		else if(browserName.equals("FF")){
		System.setProperty("webdriver.gecko.driver", "/Users/naveenkhunteta/Documents/SeleniumServer/geckodriver");	
		driver = new FirefoxDriver(); 
		}
	
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver; 
	
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
	}
	
	
}
