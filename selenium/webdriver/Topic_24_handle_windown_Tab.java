package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_24_handle_windown_Tab {
	WebDriver driver;
	Actions action;
	String osName = System.getProperty("os.name");
	String projectPath = System.getProperty("user.dir");
	

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
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	public void TC_01_Hover() {
		driver.get("https://jqueryui.com/resources/demos/tooltip/default.html");
		action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
		sleepInSecond(4);
		Assert.assertEquals(driver.findElement(By.cssSelector(".ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
		
		
	}
	
	public void TC_02_Click_And_hold_I() {
		driver.get("https://www.myntra.com/");
		action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"))).perform();
		sleepInSecond(6);
		
		//Actions click
		action.click(driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Home & Bath']"))).perform();
		sleepInSecond(6);
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()='Kids Home Bath']")).isDisplayed());
	}
	
	public void TC_02_Click_And_hold_II() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List<WebElement> rectangles = driver.findElements(By.cssSelector(".ui-selectee"));
		
		//lấy ra được 12 cái element
		//index (0 -11)
		/* 
		 * index   | 0  | 1 | 2  | 3 |4 |....|
		 * element | e1 | e1| e2 | e3|e4|....|
		 */
		action.clickAndHold(rectangles.get(0))
		.moveToElement(rectangles.get(3))
		.release()
		.perform();
		sleepInSecond(6);
		
		Assert.assertEquals(driver.findElements(By.cssSelector(".ui-selectee.ui-selected")).size(),4);
	}
	
	@Test
	public void TC_02_Click_And_hold_III() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List<WebElement> rectangles = driver.findElements(By.cssSelector(".ui-selectee"));
		
		action.keyDown(rectangles.get(0),Keys.CONTROL).perform();
		action.click(rectangles.get(0))
		.click(rectangles.get(2))
		.click(rectangles.get(5))
		.click(rectangles.get(10)).perform();
		action.keyUp(rectangles.get(0),Keys.CONTROL).perform();
		
		sleepInSecond(6);
		
		Assert.assertEquals(driver.findElements(By.cssSelector(".ui-selectee.ui-selected")).size(),4);
	}
	
	public void TC_03_Double_Click()
	{
		
		
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