package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_class20_topic23_frame_iframe {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;
	//Frame: cùng Domain/ Iframe: khác Domain
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
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public void all_Demo()
	{
		driver.switchTo().alert();
		//Switch alert
		driver.switchTo().frame(0);
		driver.switchTo().frame("Videp-257654_youtube_iframe");
		driver.switchTo().frame(driver.findElement(By.xpath("")));
		driver.switchTo().defaultContent();//back lai
	}


	public void TC_01_Ỉame() {
		driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");
		
		//Switch to facebook iframe 
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='fb:page Facebook Social Plugin']")));
		
		//Element of facebook iframe
		String likeNumber = driver.findElement(By.xpath("//a[text()='Automation FC']/parent::div/following-sibling::div")).getText();
		 System.out.println(likeNumber);
		 
		 //Switch to parent page
//		 driver.switchTo().defaultContent();
//		 //Element of parent
//		 String postTitle = driver.findElement(By.xpath("//iframe[contains(@src,'docs.google.com')]"));
//		 driver.findElement(By.xpath("//div[contains@data-params,'HỌ TÊN']")).sendKeys("Automation FC");
	}
	@Test
	public void TC_02_Frame() {
		driver.get("https://v1.hdfcbank.com/assets/popupages/netbanking.html");
		
		driver.findElement(By.xpath("//div[@class='container']/div/a[text()='Continue to NetBanking'][1]")).click();
		
		//switch to login frame
		driver.switchTo().frame("login_page");
		driver.findElement(By.name("fldLoginUserId")).sendKeys("automation");
		driver.findElement(By.xpath("//a[contains(@onclick,'fLogin')]/img[@alt='continue']")).click();
		 Assert.assertTrue(driver.findElement(By.name("fldPassword")).isDisplayed());
		
		//Switch to parent page
		driver.switchTo().defaultContent();
		//switch to parent frame
		driver.switchTo().frame("footer");
		driver.findElement(By.xpath("//a[text()='Ters ad COnditions']")).click();
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