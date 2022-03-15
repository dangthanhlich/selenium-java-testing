package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_class20_topic24_Handle_windows_tab {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	public void TC_01() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//get ra window /tab id tại tab đang được active
		
		String parentTabID = driver.getWindowHandle();
		System.out.println("Parent ID =  "+ parentTabID);
		
		//Click to google link ->tab mới
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		//Switch qua google link ->tab mới
		switchToWindowById(parentTabID);
		sleepInSecond(3);
		
		String googleTabID = driver.getWindowHandle();
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Selenium");
		//Switch về parent tab
		switchToWindowById(googleTabID);
		
		
		sleepInSecond(10);
		
		//click to facebook link ->tab mới
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		//switch to parent tab
		switchToWindowByTitle("Facebook – log in or sign up");
		sleepInSecond(3);
		//Switch to parent tab
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		
		sleepInSecond(10);
		//click to tiki link ->tab mới
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		//switcch to facebook tab
		switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		sleepInSecond(10);
		Assert.assertTrue(driver.findElement(By.xpath("//input[@data-view-id='main_search_form_input']")).isDisplayed());
		sleepInSecond(10);
		//switch to parent tab
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		sleepInSecond(10);
		//Click to lazada link ->tab mới
		driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
		
		//switch to lazada tab
		switchToWindowByTitle("Shopping online - Buy online on Lazada.vn");
		
	}
	
	
	public void TC_02_Kyna() {
		driver.get("https://kyna.vn/");
		String parentTabID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='facebook']")).click();
		switchToWindowById(parentTabID);
		//switch qua parent (kyna.vn)
		sleepInSecond(5);
		
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		
		//Click to danh sach khoa hoc-tab moi
		sleepInSecond(10);
		driver.findElement(By.xpath("//div[@id='k-footer']//a[text()='Danh sách khóa học']")).click();
		switchToWindowByTitle("Tổng hợp Tất Cả Khóa Học Online mới nhất tại Kyna");
		
		driver.findElement(By.id("live-search-bar")).sendKeys("Automation test FC");
		sleepInSecond(5);
		
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		
		sleepInSecond(10);
		driver.findElement(By.xpath("//div[@id='k-footer']//a[text()='Câu hỏi thường gặp']")).click();
		switchToWindowByTitle("Câu hỏi thường gặp - Kyna.vn");
		sleepInSecond(10);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='title-faq']")).isDisplayed());
		
		//closeAllTabwithoutParent(parentID);
		
	}
	
	@Test
	public void TC_03_LiveGuru()
	{
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		
		String parentID= driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product IPhone has been added to comparison list.']")).isDisplayed());
		
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());
	
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		switchToWindowById(parentID);
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/catalog/product_compare/index/");
		
	}
	
	public void switchToWindowById(String windowID)
	{
		//get hết ra ID đang có
		Set<String> allWindowIDs = driver.getWindowHandles();
		//Duyệt qua các giá trị trong all windowns
		for(String id : allWindowIDs)
		{
			//kiểm tra đk nếu như khác với windowID truyền vào thì switch 
			if(!id.equals(windowID)) {
				driver.switchTo().window(id);
				//thoát khỏi vòng lặp
				break;
			}
		}
	}
	
	
	public void switchToWindowByTitle(String tabTitle)
	{
		// Get hết ra các id đang có
		Set<String> allWindowIDs =  driver.getWindowHandles();
		//Duyệt qua các giá trị trong all windowns 
		for (String id : allWindowIDs)
		{
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if(actualTitle.equals(tabTitle)) {
				break;
			}
			
		}
	}
	
	public void closeAllTabwithoutParent(String parentID)
	{
		//get hết tất cả ID đang có
		Set<String> allWindownIDs = driver.getWindowHandles();
		//duyệt qua các giá trị trong windows
		for(String id:allWindownIDs) {
			if(!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
			driver.switchTo().window(parentID);
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
}