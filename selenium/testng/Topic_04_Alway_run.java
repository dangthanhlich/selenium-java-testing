package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Alway_run {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass(alwaysRun=true)
	public void initBrowser() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		System.out.println("Open browser");
		
	}
	//before fail -> after k chạy được
	//tase case fail -?after chạy được
	@Test(groups="user")
	public void TC_01_Create_User()
	{
		
	}

	@Test(groups= {"user","admin"})
	public void TC_02_Create_User()
	{
		
	}
	
	@Test(groups= {"user","admin"})
	public void TC_03_Edit_User()
	{
		
	}
	
	@Test(groups= {"user","admin"})
	public void TC_04_Delete_User()
	{
		
	}
	@AfterClass(alwaysRun=true)
	public void cleanBrowser() {
		System.out.println("close browser");
		driver.quit();
	}
	
	
}
