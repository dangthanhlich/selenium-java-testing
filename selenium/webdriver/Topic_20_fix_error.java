package webdriver;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_fix_error {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		//wait cho các trạng thái của element : visible/presence/invisibe/staleness
		explicitWait = new WebDriverWait(driver, 15);
		
		//Ép kiểu tường minh
		jsExecutor = (JavascriptExecutor) driver;
		
		//Wait cho việc tìm element (findElement)
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	

	
	@Test
	public void TC_04_Angular() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		
		WebElement element = driver.findElement(By.cssSelector("ng-select[bindvalue='provinceCode'] span.ng-arrow-wrapper"));
		
		jsExecutor.executeScript(" arguments[0].scrollIntoView(true);",element);
		sleepInSecond(4);
		
		
//		driver.findElement(By.cssSelector("div.container-hotline a span")).click();
//		sleepInSecond(4);
		
		driver.findElement(By.cssSelector("ng-select[bindvalue='provinceCode'] span.ng-arrow-wrapper")).click();
		sleepInSecond(4);
		
		
	}
	
	public void selectItemInCustomDropdownList(String parentLocator, String childLocator, String expectedTextItem)
	{

		driver.findElement(By.cssSelector(parentLocator)).click();
		sleepInSecond(2);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		
		//	- step 3: tìm icon cần chọn
		
		// lấy hết tất cả các element(item) ra sau đó duyệt qua từng item
		List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));
		//Duyệt qua từng item getText của item ra
		for(WebElement item : allItems) {
			String actuaText = item.getText();
			System.out.println("Actual Text = " + actuaText);
			// Nếu text = text mình ong muốn(item cần click vào)
			if(actuaText.equals(expectedTextItem)) {
				//	+ b1: nếu item cần chọn nằm trong vùng nhìn thấy thì không cần scroll tới element tìm tiếp
				//	 + b2: Nếu item cần chọn nằm ở dưới thì scroll tới element đến item đó
				// - step 4: Click vào item đó
				jsExecutor.executeScript(" arguments[0].scrollIntoView();",item);
				sleepInSecond(2);
				item.click();
				
				sleepInSecond(2);
				//thoát khỏi vòng lặp không có kiểm tra element tiếp theo nữa
				break;
			}
		}
	}
	
	
	@Test
	public void TC_02() {
		
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