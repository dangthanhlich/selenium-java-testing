package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_class_20_topic22_Handlepopup_error {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		if(osName.startsWith("Windows")) {
			//Windows
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}else if(osName.startsWith("Mac")){
			//mac
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver_mac");
		}
		else{
			//linux
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver_linux");
		}
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(defaultTimeout, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	public void TC_01_ZingPoll() {
		driver.get("https://www.zingpoll.com/");
		By signInPopup = By.cssSelector(".modal_dialog_custom");
		
		driver.findElement(By.xpath("//a[@id='Loginform']")).click();
		sleepInSecond(2);
		//verify signin popup in displayed
		Assert.assertTrue(driver.findElement(signInPopup).isDisplayed());
		
		driver.findElement(By.id("loginEmail")).sendKeys("dam@gmail.com");
		driver.findElement(By.id("loginPassword")).sendKeys("123456");
		
		driver.findElement(By.cssSelector(".modal_dialog_custom .close")).click();
		
		sleepInSecond(2);
		
		//Verify signin popup is not displayed
		Assert.assertFalse(driver.findElement(signInPopup).isDisplayed());
	}
	
	public void TC_02_Shoppe() {
		driver.get("https://shopee.vn/");
		By homePopup = By.xpath("//img[@class='banner-image']");
		//verify signin popup in displayed
		
		Assert.assertTrue(isElementDisplayed(homePopup));
		
		driver.findElement(By.cssSelector(".shopee-popup__close-btn")).click();
		sleepInSecond(3);
		
		//Verify signin popup is not displayed
		Assert.assertFalse(isElementDisplayed(homePopup));
	}
	@Test
	public void TC_02_ShoppeII() {
		driver.get("https://shopee.vn/");
		By homePopup = By.xpath("//img[@class='banner-image']");
		//verify signin popup in displayed
	
		Assert.assertTrue(isElementDisplay(homePopup));
		
		driver.findElement(By.cssSelector(".shopee-popup__close-btn")).click();
		sleepInSecond(3);
		
		//Verify signin popup is not displayed
		Assert.assertFalse(isElementDisplay(homePopup));
	}
	
	  
	public boolean isElementDisplayed(By by)
	{
		try {
			//n?? t??m ra element trong v??ng 10s
			// khi n??o h???t 10s m???i throw exception 
			return driver.findElement(by).isDisplayed();
		}catch(Exception e){
			//catch bawts exception l???i
			return false;
		}
		
	}
	
	public boolean isElementDisplay(By by)
	{
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		List<WebElement> elements = driver.findElements(by);
		driver.manage().timeouts().implicitlyWait(defaultTimeout, TimeUnit.SECONDS);
		if(elements.size() == 0) {
			return false;
		}else if(elements.size()>0 && !elements.get(0).isDisplayed()) {
			return false;
		}else {
			return true;
		}
		
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
	long defaultTimeout = 30;
	
	public void sleepMiliSecond(long milisecond) {
		try {
			Thread.sleep(milisecond);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}