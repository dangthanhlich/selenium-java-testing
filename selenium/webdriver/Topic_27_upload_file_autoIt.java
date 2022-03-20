package webdriver;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_27_upload_file_autoIt {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String googleFiName = "a1.jpg";
	String amazonFiName = "a2.jpg";
	String ApleFiName = "a3.jpg";
	String googleFilePath = projectPath + "\\up_load_file\\" + googleFiName;
	String amazonFilePath = projectPath + "\\up_load_file\\" + amazonFiName;
	String ApleFilePath = projectPath + "\\up_load_file\\" + ApleFiName;

	String firefoxOneAutoIT = projectPath + "\\AutoIT\\firefoxUploadOneTime.exe";
	String ChormeOneAutoIT = projectPath + "\\AutoIT\\chromeUploadOneTime.exe";
	String firefoxMultipAutoIT = projectPath + "\\AutoIT\\firefoxUploadMultiple.exe";
	String ChormeMultipAutoIT = projectPath + "\\AutoIT\\chromeUploadMultiple.exe";

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
//		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	public void TC_01_Auto_only_file() throws IOException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		// Bật được file dialog ->click vào button

		driver.findElement(By.cssSelector(".btn-success")).click();
		Runtime.getRuntime().exec(new String[] { firefoxOneAutoIT, googleFilePath});
		//Runtime.getRuntime().exec(new String[] { ChormeOneAutoIT, googleFilePath });
//		sleepInSecond(5);
		// verify file loaded sucess
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + googleFiName + "']")).isDisplayed());

		// click start upload button
		driver.findElement(By.cssSelector("table .start")).click();
		sleepInSecond(5);
		// verify file upload sucess
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + googleFiName + "']")).isDisplayed());
	
		
	}
	public void TC_02_Auto_multipe_file() throws IOException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		

		driver.findElement(By.cssSelector(".btn-success")).click();
		
		if(driver.toString().contains("Chrome") || driver.toString().contains("edge")) {
			Runtime.getRuntime().exec(new String[] { ChormeMultipAutoIT, googleFilePath,amazonFilePath,ApleFilePath});
		}else if(driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String[] { firefoxMultipAutoIT, googleFilePath,amazonFilePath,ApleFilePath});
		}
		
		// verify file loaded sucess
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + googleFiName + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + amazonFiName + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + ApleFiName + "']")).isDisplayed());

		// click start upload button
		List<WebElement> startUploadButtons = driver.findElements(By.cssSelector("table .start"));

		for (WebElement startButton : startUploadButtons) {
			startButton.click();
			sleepInSecond(1);
		}

		sleepInSecond(10);
		// verify file upload sucess
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + googleFiName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + amazonFiName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + ApleFiName + "']")).isDisplayed());
	}
	
	public void TC_03_robot() {
		
	}
	@Test
	public void TC_04_Upload_Flow() {
		driver.get("https://gofile.io/uploadFiles");
		
		String parentID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(googleFilePath+ "\n" +amazonFilePath+ "\n" + ApleFilePath);
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.tagName("h5")).getText().trim(),"Your files have been successfully uploaded");
		sleepInSecond(5);
		Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='Your files have been successfully uploaded']")).isDisplayed());
		
		driver.findElement(By.id("rowUploadSuccess-downloadPage")).click();
		switchToWindowById(parentID);
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='"+ googleFiName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='"+ amazonFiName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='"+ ApleFiName +"']")).isDisplayed());
		
	
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
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}