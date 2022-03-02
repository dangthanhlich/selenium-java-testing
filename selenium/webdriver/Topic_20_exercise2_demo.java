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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_exercise2_demo {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		//wait cho các trạng thái của element : visible/presence/invisibe/staleness
		explicitWait = new WebDriverWait(driver, 30);
		
		//Wait cho việc tìm element (findElement)
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	//	- step1: Click vào các element cho nó sổ ra các item
	//	- step 2: chờ cho các item load hết ra thành công
	//	- step 3: tìm icon cần chọn
	//	 + b1: nếu item cần chọn nằm trong vùng nhìn thấy thì không cần scroll xuống tìm tiếp
	//	 + b2: Nếu item cần chọn nằm ở dưới thì scroll xuống đến ite đó
	//	 - step 4: Click vào item đó
	
	@Test
	public void TC_01() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		//		- step1: Click vào các element cho nó sổ ra các item
		driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-icon")).click();
		sleepInSecond(2);
		
		//	- step 2: chờ cho các item load hết ra thành công
		// wait  visible : 8 cái
		// wait presence : 19 cái
		//lưu ý 1: Locator chứa hết tất cả item
		//lưu ý 2: Locator phải đến node cuối cùng của text 
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu div")));
		
		//	- step 3: tìm icon cần chọn
		//	 + b1: nếu item cần chọn nằm trong vùng nhìn thấy thì không cần scroll xuống tìm tiếp
		//	 + b2: Nếu item cần chọn nằm ở dưới thì scroll xuống đến ite đó
		
		// lấy hết tất cả các element(item) ra sau đó duyệt qua từng item
		List<WebElement> allItems = driver.findElements(By.cssSelector("ul#number-menu div"));
		//Duyệt qua từng item getText của item ra
		for(WebElement item : allItems) {
			String actuaText = item.getText();
			System.out.println("Actual Text = " + actuaText);
			// Nếu text = text mình ong muốn(item cần click vào)
			if(actuaText.equals("5")) {
				// - step 4: Click vào item đó
				item.click();
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