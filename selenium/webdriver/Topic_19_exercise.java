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

public class Topic_19_exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	JavascriptExecutor  jsExecutor;
	WebDriverWait explicitWait;
	Actions action;
	Select select;
	
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		// tạo mới new firefoxdriver
		driver = new FirefoxDriver();
		
		//khởi tạo sau khi driver này được sinh ra
		//javascriptExecutor/ WebDriverWait/Actions/...
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver,30);
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Rode() {
		
		driver.get("https://rode.com/wheretobuy");
		//khởi tạo khi sử dụng (element xuất hiện)
		//khởi rạo select để thao tác element
		select = new Select(driver.findElement(By.xpath("//select[@id='where_country']")));
		
		//không support multiple select
		Assert.assertFalse(select.isMultiple());
		
		//Select giá trị Vietname
		select.selectByVisibleText("Vietnam");
		sleepInSecond(5);
		
		//vefify Vietname selected sucess
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");
		
		//32 result
		driver.findElement(By.cssSelector("input#search_loc_submit")).click();
		sleepInSecond(5);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result_count>span")).getText(), "33");
		List<WebElement> storeName = driver.findElements(By.cssSelector("div.result_item div .store_name"));
		
		//verify tổng số lượng store name = 33
		Assert.assertEquals(storeName.size(),33);
		
		for (WebElement store : storeName) {
			System.out.println(store.getText());
		}
	}
	@Test
	public void TC_02_NopComerce() {
		String  firstName= "john";
		String  lastName= "wich";
		String  date= "10";
		String  month= "October";
		String  year= "2000";
		String  emailAddress= "john"+getRandomNumber()+"@gmail.com";
		String  password= "123456789";
		
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		
		//Date
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		select.selectByVisibleText(date);
		
		//month
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(month);
		
		//year
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(year);
		
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		driver.findElement(By.id("register-button")).click();
		
		//Thành công
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
	
		driver.findElement(By.cssSelector("a.ico-account")).click();
		
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