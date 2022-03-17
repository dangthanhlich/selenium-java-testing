package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_26_upload_file1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String googleFiName = "a1.jpg";
	String amazonFiName = "a2.jpg";
	String ApleFiName = "a3.jpg";
	String googleFilePath = projectPath + "\\up_load_file\\"+ googleFiName;
	String amazonFilePath = projectPath + "\\up_load_file\\"+ amazonFiName;
	String ApleFilePath = projectPath + "\\up_load_file\\"+ ApleFiName;
	

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
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	public void TC_01_SendKey_only_file() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		//Load file không cần bật file dialog
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(googleFilePath);
		sleepInSecond(5);
		//verify file loaded sucess 
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ googleFiName +"']")).isDisplayed());
		
		//click start upload button
		driver.findElement(By.cssSelector("table .start")).click();
		sleepInSecond(10);
		//verify file upload sucess
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ googleFiName +"']")).isDisplayed());
	}
	@Test
	public void TC_02_SendKey_multipe_file() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		//Load file không cần bật file dialog
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(googleFilePath+ "\n" +amazonFilePath+ "\n" + ApleFilePath);
		sleepInSecond(5);
		//verify file loaded sucess 
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ googleFiName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ amazonFiName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ ApleFiName +"']")).isDisplayed());
		
		//click start upload button
		List<WebElement> startUploadButtons = driver.findElements(By.cssSelector("table .start"));
		
		for(WebElement startButton : startUploadButtons) {
			startButton.click();
			sleepInSecond(1);
		}
		
		sleepInSecond(10);
		//verify file upload sucess
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ googleFiName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ amazonFiName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ ApleFiName +"']")).isDisplayed());
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