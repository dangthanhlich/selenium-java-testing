package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_13 {
	//khai báo 1 biến đại diện cho selenium ưebdriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	

	@Test
	public void TC_01_Firefox() {
		// Executable File ; chromedriver / geckodriver/ edgedriver / IEDriver/...
		System.setProperty("webdriver.gecko.driver",projectPath + "\\browserDrivers\\geckodriver.exe");
		
		//class: firefoxDriver/chromeDriver /EdgeDriver/SafariDriver/...
		driver = new FirefoxDriver();
		driver.get("https://www.facebook.com/");
		driver.quit();
	}
	@Test
	public void TC_02_chrome() {
		System.setProperty("webdriver.chrome.driver",projectPath + "\\browserDrivers\\chromedriver.exe");
		
		driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		driver.quit();
	}
	@Test
	public void TC_03_Edge() {
		
		System.setProperty("webdriver.edge.driver",projectPath + "\\browserDrivers\\msedgedriver.exe");
		driver = new EdgeDriver();
		driver.get("https://www.facebook.com/");
		driver.quit();
	}

	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}