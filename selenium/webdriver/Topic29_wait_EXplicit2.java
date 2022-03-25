package webdriver;

import java.util.concurrent.TimeUnit;

import javax.xml.crypto.Data;

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
	
	String firstFileName = "a1.jpg";
	String SecondFileName = "a2.jpg";
	String firstFilePath = projectPath + "\\\\up_load_file\\" + firstFileName;
	String secondFilePath = projectPath + "\\\\up_load_file\\" + SecondFileName;
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		expliciWait = new WebDriverWait(driver, 60);
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
	
	@Test
	public void TC_04_Ajax_loading() {
		driver.get("https://gofile.io/uploadFiles");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(firstFilePath+"\n"+secondFilePath);
		//wait to serve icon invisible
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("div#rowUploadProgress-selectServer")));
		
		//wait progress bar icon invisible
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='progressbar']")));
		
		//wait sucess message displayed
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Your files have been successfully uploaded']")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='Your files have been successfully uploaded']")).isDisplayed());

		//wait  show file button clickable
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#rowUploadSuccess-showFiles")));
		driver.findElement(By.cssSelector("button#rowUploadSuccess-showFiles")).click();
		
		
		//verify image uploaded success
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='" + firstFileName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='" + SecondFileName +"']")).isDisplayed());
	}
	


	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}