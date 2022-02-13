package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_webBrowser_command {
	//khai báo 1 biến đại diện cho selenium ưebdriver
	WebDriver driver;
	WebElement element;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		//khởi tạo browser lên
		driver = new FirefoxDriver();
		//set thời gian chờ để tìm được element
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//mở trang facebook lên
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01() {
		// các hàm / method để thao tác với Browser là thông qua biến driver
		
		//mở ra 1 URL
		driver.get("https://www.sendo.vn/");
		driver.get("https://kynaforkids.vn/");
		
		//đóng browser nếu chỉ có 1 tab
		//đóng tab hiện tại nếu có nhiều tab
		//webDriver API - windows /Tabs
		driver.close();
		
		//đóng browser không quan tâm bao nhiêu tab
		driver.quit();
		
		//tìm 1 element trên page
		//trả về dât type là webElement
		//WebElement emailTextbox = driver.findElement(By.id("email"));
		
		
		//tìm nhiều hơn 1 element trên page
		//trả về data type là List<WebElement>
		driver.findElements(By.xpath("//a"));
		
		//trả về URL của page hiện tại
		driver.getCurrentUrl();
		
		//nếu các hàm để tương tác(Action) thì luôn luôn là kiểu void
		//nếu các hàm để lấy dữ liệu ra nó luôn trả về 1 kiểu dữ liệu nào đó cho mình sử dụng ở các step tiếp theo
		
		
		//không nên lưu thành 1 biến 
		
		
	}
	

	public void TC_02() {
		
	}

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