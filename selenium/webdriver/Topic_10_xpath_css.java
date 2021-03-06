package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//http://live.techpanda.org/index.php/

public class Topic_10_xpath_css {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Browser() {
		//Action lên browser
		//open browser
		//open URL 
		//refresh /Back/Forward
		//Maximize/Minimize/ Fullscreen
		//....
		//lấy dữ liệu ra từ browser: trả về 1 kiểu dữ liệu nào đó để lưu trữ lại, nắm giữ dữ liệu đó
		//Get URL /Get title /get source page/ get Position/ get location/...
		
	}

	@Test
	public void TC_02_Element() {
		
	}
	@AfterClass
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