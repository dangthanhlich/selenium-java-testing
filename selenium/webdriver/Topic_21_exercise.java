package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

//	@Test
//	public void TC_01_Button() {
//		driver.get("https://www.fahasa.com/customer/account/create");
//		
//		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
//		By loginButtonBy = By.cssSelector("button.fhs-btn-login");
//		
//		
//		//isEnabled new 1 element ma enabled ->true
//		//isEnabled new 1 element ma disabled-> false
//		
//		//verify  login button is disabled
//		Assert.assertFalse(driver.findElement(loginButtonBy).isEnabled());
//		
//		driver.findElement(By.cssSelector("input#login_username")).sendKeys("aaaa@gmail.com");
//		driver.findElement(By.cssSelector("input#login_password")).sendKeys("aaaaabb");
//		sleepInSecond(1);
//		
//		//verify login button is disabled
//		Assert.assertTrue(driver.findElement(loginButtonBy).isEnabled());
//		
//		
//		// Verify background color = red
//		String loginButtonBackgroundColorRgb = driver.findElement(loginButtonBy).getCssValue("background-color");
//		System.out.println("RBG color" + loginButtonBackgroundColorRgb);
//		
//		//verify = RBG
//		Assert.assertEquals(loginButtonBackgroundColorRgb, "rgb(201, 33, 39)");
//		
//		//Convert qa Hexa
//		String loginButtonBackgroundColorHexa = Color.fromString(loginButtonBackgroundColorRgb).asHex();
//		System.out.println("Hexa color = " + loginButtonBackgroundColorHexa);
//		
//		Assert.assertEquals(loginButtonBackgroundColorHexa.toUpperCase(),"#C92127");
//		
//		driver.navigate().refresh();
//		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
//		
//		//remove disabled attribute
//		jsExecutor.executeScript("arguments[0].removeAttribute('disabled');", driver.findElement(loginButtonBy));
//		sleepInSecond(2);
//		
//		driver.findElement(loginButtonBy).click();
//		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']//following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
//		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Mật khẩu']//following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
//		
//		
//		
//	}
	@Test
	public void TC_02_Default_Radio() {
		//Default - the input
		//Action: click
		//verify
		
		//custom - the input
		//action : không click được 
		//verify được
		
		driver.get("");
	}
	@Test
	public void TC_03_Default_Checkbox() {
		
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