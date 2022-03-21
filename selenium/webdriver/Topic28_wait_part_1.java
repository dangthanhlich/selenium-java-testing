package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic28_wait_part_1 {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		expliciWait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	public void TC_01_Visible_Displayed() {
		driver.get("https://www.facebook.com/");
		
		//Wait cho 1 element hiển thị trong khoảng thời gian 15s
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
		
		//verify cho  1 element hiển thị
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='email']")).isDisplayed());
	}
	
	
	public void TC_02_Invisible_undisplayed() {
		driver.get("https://www.facebook.com/");
		//Wait cho button tạo tài khoản có thể click
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Create New Account']")));
		
		//Action
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();
		
		//không có trên UI nhưng vấn có trong DOM
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")));
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		
		//không có trong UI và không có trong DOm
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@id='reg']")));
	}
	
	public void TC_03_presence() {
		driver.get("https://www.facebook.com/");
		//hiển thị trên UI và vẫn có trong DOM->pass
		expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='email']")));
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Create New Account']")));
		
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();
		
		
		//không hiển thị trên UI và vẫn có trong DOM->pas
		expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")));
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		//không hiển thị treen UI và không có trong DOM ->Fail
		//expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//form[@id='reg']")));
		
	}
	@Test
	public void TC_04_staleness() {
		driver.get("https://www.facebook.com/");
		//hiển thị trên UI và vẫn có trong DOM->pass

		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Create New Account']")));
		
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='reg']")));
		expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		
		WebElement registerForm = driver.findElement(By.xpath("//form[@id='reg']"));
		WebElement confirmEmail = driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']"));
		

		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")));
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
	
		expliciWait.until(ExpectedConditions.stalenessOf(registerForm));
		expliciWait.until(ExpectedConditions.stalenessOf(confirmEmail));
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