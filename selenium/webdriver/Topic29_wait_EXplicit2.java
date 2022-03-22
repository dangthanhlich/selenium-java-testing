package webdriver;

import java.util.concurrent.TimeUnit;
import java.util.List;
import org.openqa.selenium.WebElement;
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

public class Topic29_wait_EXplicit2 {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	
	By startButton = By.cssSelector("#start>button");
	By loadingIcon = By.cssSelector("div#loading");
	By helloWordText = By.xpath("//h4[text()='Hello World!']");
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		expliciWait = new WebDriverWait(driver, 30);
		driver.manage().window().maximize();

	}

	public void TC_01_Visible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(startButton).click();
		//chờ cho hello world text được hiển thị (visible)
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(helloWordText));
		
		Assert.assertTrue(driver.findElement(helloWordText).isDisplayed());
	}
	

	public void TC_02_InVisible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");		
		driver.findElement(startButton).click();
		
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		
		Assert.assertTrue(driver.findElement(helloWordText).isDisplayed());
	}
	
	public void TC_03_Ajax_loading() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");		
		
		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_RadCalendar1_Top")));
		
		Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(),"No Selected Dates to display.");
		
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[text()='27']")));
		driver.findElement(By.xpath("//td/a[text()='27']")).click();
		//Wait cho ajax loading biến mất
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='style=\"display:none;')]//div[@class='raDiv']")));
		
		//verify today được selected
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']//a[text()='27']")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']//a[text()='27']")).isDisplayed());
		
		//verify today update lên 'Selected Dates'
		Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(), "Sunday, March 27, 2022");
	}
	



	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}