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

public class Topic_21_exercise_example {
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
//	@Test
//	public void TC_02_Default_Radio() {
//		//Default - the input
//		//Action: click
//		//verify
//		
//		//custom - the input
//		//action : không click được 
//		//verify được
//		
//		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
//		
//		By oneDotEightPetroRadio = By.xpath("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::input");
//		By twoDotZeroPetroRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
//		By threeDotSixPetroRadio = By.xpath("//label[text()='3.6 Petrol, 191kW']/preceding-sibling::input");
//		
//		
//		Assert.assertFalse(driver.findElement(oneDotEightPetroRadio).isSelected());
//		driver.findElement(oneDotEightPetroRadio).click();
//		sleepInSecond(2);
//		
//		Assert.assertTrue(driver.findElement(oneDotEightPetroRadio).isSelected());
//		
//		driver.findElement(twoDotZeroPetroRadio).click();
//		sleepInSecond(2);
//		
//		//Deselectted: bỏ chọn
//		Assert.assertFalse(driver.findElement(oneDotEightPetroRadio).isSelected());
//		
//		//Selected:đã chọn
//		Assert.assertTrue(driver.findElement(twoDotZeroPetroRadio).isSelected());
//		
//		//Enabled: đã bật
//		Assert.assertTrue(driver.findElement(oneDotEightPetroRadio).isEnabled());
//		Assert.assertTrue(driver.findElement(twoDotZeroPetroRadio).isEnabled());
//		
//		
//		//Disabled = read only(tắt ,chỉ đọc)
//		Assert.assertFalse(driver.findElement(threeDotSixPetroRadio).isEnabled());
//		
//		
//	}
	
	
	@Test
	public void TC_03_Default_Checkbox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		By luggageCheckbox = By.xpath("//label[text()='Luggage compartment cover']//preceding-sibling::input");
		By heatedCheckbox = By.xpath("//label[text()='Heated front and rear seats']//preceding-sibling::input");
		By towbarCheckbox = By.xpath("//label[text()='Towbar preparation']//preceding-sibling::input");
		By leatherCheckbox = By.xpath("//label[text()='Leather trim']//preceding-sibling::input");
		
		//select
		if(!driver.findElement(luggageCheckbox).isSelected()) {
			driver.findElement(luggageCheckbox).click();
		}
		if(!driver.findElement(heatedCheckbox).isSelected()) {
			driver.findElement(heatedCheckbox).click();
		}
		
		//Selected
		Assert.assertTrue(driver.findElement(luggageCheckbox).isSelected());
		Assert.assertTrue(driver.findElement(heatedCheckbox).isSelected());
		Assert.assertTrue(driver.findElement(leatherCheckbox).isSelected());
		
		//Disabled 
		Assert.assertFalse(driver.findElement(towbarCheckbox).isEnabled());
		Assert.assertFalse(driver.findElement(leatherCheckbox).isEnabled());
		
		//De-select
		if(driver.findElement(luggageCheckbox).isSelected())
		{
			driver.findElement(luggageCheckbox).click();
		}
		
		if(driver.findElement(heatedCheckbox).isSelected()) {
			driver.findElement(heatedCheckbox).click();
		}
		
		//De-selected
		Assert.assertFalse(driver.findElement(luggageCheckbox).isSelected());
		Assert.assertFalse(driver.findElement(heatedCheckbox).isSelected());
		Assert.assertFalse(driver.findElement(towbarCheckbox).isSelected());
		
		
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