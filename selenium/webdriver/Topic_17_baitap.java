package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_17_baitap {
	//khai báo 1 biến đại diện cho selenium ưebdriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName,fullname, lastName, emailAddress, password, nonExistedEmailAddress;
	
	//Global variable
	By emailTextboxBy = By.id("email");
	By passwordTextboxBy = By.id("pass");
	By loginButtonBy = By.id("send2");

	//chạy trước cho testcase đầu tiên
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		firstName ="Stevetest";
		lastName ="Jobb";
		fullname =firstName+" "+lastName;
		emailAddress="Stevetest" + getRandomNumber() + "@gmail.com";
		nonExistedEmailAddress ="Stevetest" + getRandomNumber() + "@gmail.net";
		password ="123456789";
		
		
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	//dự án ổn định mới làm auto
	//trước khi bắt tay vào implement 1 chức năng nào đó
	//+ phân tích xem yêu cầu của chức năng đó-> có phức tạp hay ko, estimate thời gian /có nên làm chức năng đó hay k
	//Manual test qua để xem chức năng này có lỗi hay không / đã stable chưa
	// nắm rõ được requirement / business của chức năng đó(không phải người làm automation mà không biết gì về business)
	
	//chạy trước cho tất cả testcase
	@BeforeMethod
	public void beforeMethod() {
		driver.get("http://live.techpanda.org/index.php/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	}

	@Test
	public void TC_01_Empty_Email_And_password() {
		driver.findElement(emailTextboxBy).clear();
		driver.findElement(passwordTextboxBy).clear();
		driver.findElement(loginButtonBy).click();
		
		//verify error message displayed
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),"This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),"This is a required field.");
		
	}
	
	@Test
	public void TC_02_Invalid_Email() {
		driver.findElement(emailTextboxBy).sendKeys("546@3453.4543");
		driver.findElement(passwordTextboxBy).sendKeys("123456");
		driver.findElement(loginButtonBy).click();
		
		//Verify Error mesage displayed
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");
	}
	@Test
	public void TC_03_Invalid_Password() {
		driver.findElement(emailTextboxBy).sendKeys("aaa@gmail.com");
		driver.findElement(passwordTextboxBy).sendKeys("12");
		driver.findElement(loginButtonBy).click();
		
		//Verify Error mesage displayed
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");
	}
	@Test
	public void TC_04_Create_New_Account_Success() {
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		//Existed Email
		
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		
		driver.findElement(By.xpath("//button[@title='Register']")).click();
	
		Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(), "MY DASHBOARD");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(),"Hello, "+ fullname+"!");
		
		String contacInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(contacInformation.contains(fullname));
		Assert.assertTrue(contacInformation.contains(emailAddress));
	
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
	
		Assert.assertTrue(driver.findElement(By.cssSelector("div.page-title img[src$='logo.png']")).isDisplayed());
	}
	@Test
	public void TC_05_Invalid_Email_Or_Password() {
		// Existed Email + incorrect password ->unsuccess
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(passwordTextboxBy).sendKeys("123456");
		driver.findElement(loginButtonBy).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(), "Invalid login or password.");
		
		// Non existed Email + correct /valid password ->unsucess
		driver.findElement(emailTextboxBy).clear();
		driver.findElement(emailTextboxBy).sendKeys(nonExistedEmailAddress);
		driver.findElement(passwordTextboxBy).clear();
		driver.findElement(passwordTextboxBy).sendKeys("password");
		driver.findElement(loginButtonBy).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(), "Invalid login or password.");
		
		
	}
	
	@Test
	public void TC_06_valid_Email_And_Password() {
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(loginButtonBy).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(), "MY DASHBOARD");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(),"Hello, "+ fullname+"!");
		String contacInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(contacInformation.contains(fullname));
		Assert.assertTrue(contacInformation.contains(emailAddress));
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
	public int getRandomNumber()
	{
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}