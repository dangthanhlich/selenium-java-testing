package webdriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_23_Alert {
	WebDriver driver;
	WebDriverWait explicitWait;
	Alert alert;
	String projectPath = System.getProperty("user.dir");
	String authenChrome =  projectPath + "\\AutoScripts\\authen_chrome.exe";
	String authenFirefox =  projectPath + "\\AutoScripts\\authen_firefox.exe";

	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver,15);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//Alert // poppup & Dialong //ưindows

	public void TC_00_login_Empty_Data() {
		driver.get("https://demo.guru99.com/V4/index.php");
		driver.findElement(By.name("btnLogin")).click();
		sleepInSecond(2);
		//chwof cho alert xuaast hieenj + switch qua luôn
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "User or Password is not valid");
		
		alert.accept();
		sleepInSecond(2);
	}
	
	
	public void TC_01_Accept_Alert() {
		// dùng 4 hàm
		//1 : void dismiss():=> cancel 1 alert
		//2 void accept()=>Accept 1 alert
		//3 String getText()=> trả về text của 1 alert
		//void sendKeys(String KeysTosend);=> nhập dữ liệu vào prompt alert
		
		driver.get("https://automationfc.github.io/basic-form/");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(2);
		//Switch qua Alert
		alert = driver.switchTo().alert();
		
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		alert.accept();
		
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");	
	}
	
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleepInSecond(3);
		// switch qua Alert
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		//Cancel an alert
		alert.dismiss();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
	}
	
	
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/");
		String textToSendKey = "AutomationTest";
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSecond(2);
		// switch qua Alert
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		//nhập text và Alert
		alert.sendKeys(textToSendKey);
		sleepInSecond(2);
		
		//Accept an alert
		alert.accept();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: "+textToSendKey);
	}
	
	public void TC_04_I_Authentication_Alert() {
		String username="admin";
		String password="admin";
		//C1: chạy winow /mac/linux (có lúc  chạy)
		driver.get("http://"+username+":"+password+"@"+"the-internet.herokuapp.com/basic_auth");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).getText(), "Congratulations! You must have the proper credentials.");
	}
	
	
	public void TC_04_II_Authentication_Alert() {
		//k chạy được trên mac
		String username="admin";
		String password="admin";
		driver.get("http://the-internet.herokuapp.com/");
		String basicAuthenLink= driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		System.out.println(basicAuthenLink);
		
		String [] basicAuthen = basicAuthenLink.split("//");
		
		basicAuthenLink = basicAuthen[0] + "//"+username+":"+password+"@"+basicAuthen[1];
		System.out.println(basicAuthenLink);
		
		driver.get(basicAuthenLink);
		
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).getText(), "Congratulations! You must have the proper credentials.");
		
		
	}
	
	
	public void TC_04_III_Authentication_Alert() {
		//k chạy được trên mac:// mac chạy được dùng user.pass trực tiếp vào link
		String username="admin";
		String password="admin";
		driver.get("http://the-internet.herokuapp.com/");
		String basicAuthenLink= driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		driver.get(getAuthenticateLink(basicAuthenLink,username,password));
		driver.get(basicAuthenLink);
		
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).getText(), "Congratulations! You must have the proper credentials.");
		
		
	}
	
	@Test
	public void TC_04_IIII_Authentication_Alert() throws IOException {
		String username="admin";
		String password="admin";
		
		driver.get("http://the-internet.herokuapp.com/");
		//Script chạy trước để chờ alert bật lên sau
		if(driver.toString().contains("Firefox")){
			Runtime.getRuntime().exec(new String[] {authenFirefox, username, password});
		}else{
			Runtime.getRuntime().exec(new String[] {authenChrome, username, password});
		}
		
		
		driver.findElement(By.xpath("//a[text()='Basic Auth']")).click();
		
		sleepInSecond(8);
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).getText(), "Congratulations! You must have the proper credentials.");
		
	}
	
	public String getAuthenticateLink(String url,String username,String password) {
		String[] links = url.split("//");
		url = links[0] + "//"+username+":"+password+"@"+links[1];
		return url;
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