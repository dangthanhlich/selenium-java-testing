package webdriver;

import java.util.concurrent.TimeUnit;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic29_wait_implicit {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	
	By startButton = By.cssSelector("#start>button");
	By loadingIcon = By.cssSelector("#div#loading");
	By helloWordText = By.xpath("//h4[text()='Hello World!']");
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
//		expliciWait = new WebDriverWait(driver, 15);
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Dont_Set() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(startButton).click();
		Assert.assertTrue(driver.findElement(helloWordText).isDisplayed());
	}
	
	@Test
	public void TC_02_FindElements() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);		
		driver.findElement(startButton).click();
		Assert.assertTrue(driver.findElement(helloWordText).isDisplayed());
	}
	@Test
	public void TC_03_presence() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		driver.findElement(startButton).click();
		Assert.assertTrue(driver.findElement(helloWordText).isDisplayed());
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