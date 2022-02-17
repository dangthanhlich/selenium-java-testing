package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_15_baitap {
	//khai báo 1 biến đại diện cho selenium ưebdriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	By fullNameTextboxBy = By.id("txtFirstname");
	By emailTextboxBy = By.id("txtCEmail");
	By confirmEmailTextboxBy = By.id("txtEmail");
	By passwordTextboxBy = By.id("txtPassword");
	By confirmPasswordTextboxBy = By.id("txtCPassword");
	By phoneTextboxBy = By.id("txtPhone");
	By registerButtonBy = By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']");
	
	By firstNameErorMessage = By.id("txtFirstname-error");
	By emailErorMessage = By.id("txtEmail-error");
	By confirmEmailErorMessage = By.id("txtCEmail-error");
	By passwordErorMessage = By.id("txtPassword-error");
	By confirmPasswordErorMessage = By.id("txtCPassword-error");
	By phoneErorMessage = By.id("txtPhone-error");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html/");
	}
	@Test
	public void TC_01_Register_Empty_Data() {
		driver.findElement(fullNameTextboxBy).sendKeys("");
		driver.findElement(emailTextboxBy).sendKeys("");
		driver.findElement(confirmEmailTextboxBy).sendKeys("");
		driver.findElement(passwordTextboxBy).sendKeys("");
		driver.findElement(confirmPasswordTextboxBy).sendKeys("");
		driver.findElement(phoneTextboxBy).sendKeys("");
		driver.findElement(registerButtonBy).click();


		Assert.assertEquals(driver.findElement(firstNameErorMessage).getText(),"Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(emailErorMessage).getText(),"Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(confirmEmailErorMessage).getText(),"Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(passwordErorMessage).getText(),"Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(confirmPasswordErorMessage).getText(),"Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(phoneErorMessage).getText(),"Vui lòng nhập số điện thoại.");
	}
	@Test
	public void TC_02_Register_Invalid_Email() {
		driver.findElement(fullNameTextboxBy).sendKeys("Steve Job");
		driver.findElement(emailTextboxBy).sendKeys("123456@1@23@");
		driver.findElement(confirmEmailTextboxBy).sendKeys("123456@1@23@");
		driver.findElement(passwordTextboxBy).sendKeys("12345678");
		driver.findElement(confirmPasswordTextboxBy).sendKeys("12345678");
		driver.findElement(phoneTextboxBy).sendKeys("0989111121");
		driver.findElement(registerButtonBy).click();
		Assert.assertEquals(driver.findElement(emailErorMessage).getText(),"Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(confirmEmailErorMessage).getText(),"Vui lòng nhập email hợp lệ");
	}
	@Test
	public void TC_03_Register_Incorrect_confirm_Email() {
		driver.findElement(fullNameTextboxBy).sendKeys("Steve Job");
		driver.findElement(emailTextboxBy).sendKeys("123456@123");
		driver.findElement(confirmEmailTextboxBy).sendKeys("123456@1@23@");
		driver.findElement(passwordTextboxBy).sendKeys("12345678");
		driver.findElement(confirmPasswordTextboxBy).sendKeys("12345678");
		driver.findElement(phoneTextboxBy).sendKeys("0989111121");
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(confirmEmailErorMessage).getText(),"Email nhập lại không đúng");
	}
	@Test
	public void TC_04_Register_Invalid_password() {
		driver.findElement(fullNameTextboxBy).sendKeys("Steve Job");
		driver.findElement(emailTextboxBy).sendKeys("1234@123");
		driver.findElement(confirmEmailTextboxBy).sendKeys("1234@123");
		driver.findElement(passwordTextboxBy).sendKeys("1234");
		driver.findElement(confirmPasswordTextboxBy).sendKeys("1234");
		driver.findElement(phoneTextboxBy).sendKeys("0989111121");
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(passwordErorMessage).getText(),"Mật khẩu ít nhất có 6 ký tự");
		Assert.assertEquals(driver.findElement(confirmPasswordErorMessage).getText(),"Mật khẩu ít nhất có 6 ký tự");
	}
	@Test
	public void TC_05_Invalid_Phone() {
		driver.findElement(fullNameTextboxBy).sendKeys("Steve Job");
		driver.findElement(emailTextboxBy).sendKeys("1234@123");
		driver.findElement(confirmEmailTextboxBy).sendKeys("1234@123");
		driver.findElement(passwordTextboxBy).sendKeys("12345678");
		driver.findElement(confirmPasswordTextboxBy).sendKeys("12345678");
		driver.findElement(phoneTextboxBy).sendKeys("0989111ss121");
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(phoneErorMessage).getText(),"Vui lòng nhập phone hợp lệ");
	}
	@Test
	public void TC_06_Register_Empty_Data() {
		driver.findElement(fullNameTextboxBy).sendKeys("Steve Job");
		driver.findElement(emailTextboxBy).sendKeys("123456@123");
		driver.findElement(confirmEmailTextboxBy).sendKeys("123456@1@23@");
		driver.findElement(passwordTextboxBy).sendKeys("12345678");
		driver.findElement(confirmPasswordTextboxBy).sendKeys("12345678");
		driver.findElement(phoneTextboxBy).sendKeys("0989111121");
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(confirmEmailErorMessage).getText(),"số điện thoại phải từ 10-11 số.");
	
		driver.findElement(phoneTextboxBy).clear();
		driver.findElement(phoneTextboxBy).sendKeys("09891");
		driver.findElement(registerButtonBy).click();
		Assert.assertEquals(driver.findElement(confirmEmailErorMessage).getText(),"số điện thoại phải từ 09 , 03, 012.");
		
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