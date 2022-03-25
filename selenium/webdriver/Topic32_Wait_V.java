package webdriver;


import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.time.Duration;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
//import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic32_Wait_V {
	WebDriver driver;
	WebDriverWait expliciWait;
	FluentWait<WebElement> fluentElement;
	String projectPath = System.getProperty("user.dir");
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

	}
	
	public void TC_01() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		WebElement countdownTimer = driver.findElement(By.id("javascript_countdown_time"));
		fluentElement = new FluentWait<WebElement>(countdownTimer);
		fluentElement.withTimeout(Duration.ofSeconds(15))
		.pollingEvery(Duration.ofSeconds(1))
		.ignoring(NoSuchElementException.class)
		.until(new Function<WebElement,Boolean>(){
			@Override
			public Boolean apply(WebElement countdown) {
				boolean status = countdown.getText().endsWith("00");
				System.out.println("Text = " + countdown.getText() +"-----"+status);
				return status;
			}
		});
	}
	@Test
	public void TC_02() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		ClickToElement(By.xpath("//div[@id='finish']/h4[text()='Hello Word!']"));
		 
	}
	
	
	
	public WebElement getForElement(By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(polling))
				.ignoring(NoSuchElementException.class);
		
		WebElement element = wait.until(new Function<WebDriver, WebElement>(){
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		return element;
	}
	
	public void ClickToElement(By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(polling))
				.ignoring(NoSuchElementException.class);
		
		WebElement element = wait.until(new Function<WebDriver, WebElement>(){
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		element.click();
	}
	public void ClicksToElement(By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(polling))
				.ignoring(NoSuchElementException.class);
		
		WebElement status = wait.until(new Function<WebDriver, WebElement>(){
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		status.click();
	}
	
//	public boolean isElementDisplayed(By locator) {
//		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
//				.withTimeout(Duration.ofSeconds(timeout))
//				.pollingEvery(Duration.ofSeconds(polling))
//				.ignoring(NoSuchElementException.class);
//		
//		WebElement status = wait.until(new Function<WebDriver, WebElement>(){
//			public Boolean apply(WebElement element) {
//				return driver.findElement(locator).isDisplayed();
//			}
//		});
//		return status;
//	}
	
	
	
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	long timeout = 15;
	long polling = 1;
}