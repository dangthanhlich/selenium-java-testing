package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_demo_all {
	//khai báo 1 biến đại diện cho selenium ưebdriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		//khởi tạo browser lên
		driver = new FirefoxDriver();
		//set thời gian chờ để tìm được element
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//mở trang facebook lên
//		driver.get("https://www.facebook.com/");
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01() {
		//ID 
		driver.findElement(By.id("email")).sendKeys("thanhlichit1999@gmail.com");
		sleepInSecond(3);
		driver.findElement(By.id("pass")).sendKeys("1111");
		sleepInSecond(3);
	}
	@Test
	public void TC_02() {
		
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