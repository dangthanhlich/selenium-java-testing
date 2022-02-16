package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_baitap {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_URL() {
		driver.get("http://live.techpanda.org/index.php/");
		
		//click vào My Account ở dưới footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "http://live.techpanda.org/index.php/customer/account/login/");
	
		//Click vào create an Account button
		driver.findElement(By.xpath("//a[@title=\"Create an Account\"]")).click();
		
		String registerPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(registerPageUrl, "http://live.techpanda.org/index.php/customer/account/create/");
	}
	
	@Test
	public void TC_02_Title() {
		driver.get("http://live.techpanda.org/index.php/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		String loginPageTitle = driver.getTitle();
		Assert.assertEquals(loginPageTitle,"Customer Login");
	
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		String registerPageTitle = driver.getTitle();
		Assert.assertEquals(registerPageTitle, "Create New Customer Account");
	}
	
	@Test
	public void TC_03_Navigation() {
		driver.get("http://live.techpanda.org/index.php/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		//Back lại trang login
		driver.navigate().back();
		sleepInSecond(3);
		
		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "http://live.techpanda.org/index.php/customer/account/login/");
		
		//Forward qua trang  Register
		driver.navigate().forward();
		sleepInSecond(3);
		
		String registerPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(registerPageUrl,"http://live.techpanda.org/index.php/customer/account/create/");
	}
	
	@Test
	public void TC_04_Page_source() {
		driver.get("http://live.techpanda.org/index.php/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		String homePageSource = driver.getPageSource();
		Assert.assertTrue(homePageSource.contains("Login or Create an Account"));
		
		//Click vào Create an Account Button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		String registerPageSource = driver.getPageSource();
		Assert.assertTrue(registerPageSource.contains("Create an Account"));
		
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