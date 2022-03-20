package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
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
		driver.get("https://www.sendo.vn/");//**
		driver.get("https://kynaforkids.vn/");
		
		//đóng browser nếu chỉ có 1 tab
		//đóng tab hiện tại nếu có nhiều tab
		//webDriver API - windows /Tabs
		driver.close();//*
		
		//đóng browser không quan tâm bao nhiêu tab
		driver.quit();//**
		
		//tìm 1 element trên page
		//trả về dât type là webElement
		//WebElement emailTextbox = driver.findElement(By.id("email"));//**
		
		
		//tìm nhiều hơn 1 element trên page
		//trả về data type là List<WebElement>
		//List<webElement> links = driver.findElements(By.xpath("//a"));//**
		
		//trả về URL của page hiện tại
		String homePageUrl = driver.getCurrentUrl();//**
		System.out.println(homePageUrl);
		
		//nếu các hàm để tương tác(Action) thì luôn luôn là kiểu void
		//nếu các hàm để lấy dữ liệu ra nó luôn trả về 1 kiểu dữ liệu nào đó cho mình sử dụng ở các step tiếp theo
		
		
		//không nên lưu thành 1 biến 
		
		
		//verify dữ liệu này đúng như mong đợi
		//verify tuyệt đói
		Assert.assertEquals(homePageUrl,"https://demo.nopcomerce.com/" );
		Assert.assertEquals(driver.getCurrentUrl(), "https://demo.nopcomerce.com");
		
		
		//lấy ra source code của trang hiện tại (HTML/CSS/JS/Jquery);
		//Verify tương đối 1 giá trin nào đó có trong trang
		String homePageSource = driver.getPageSource();
		Assert.assertTrue(homePageSource.contains("Welcome  to our store"));
		
		
		//lấy ra trả về title ở page hiện tại
		String homePageTitle = driver.getTitle();//*
		Assert.assertEquals(homePageTitle,"nopComerce demo store");
		//99% các hàm bắt đầu bằng get trả về dữ liệu
		
		//webDriver API - windows /Tabs
		// trả về 1 ID của tab hiện tại
		String signUpTabID = driver.getWindowHandle();//*
		
		//trả về ID của tất cả các tab đang có (n)
		Set<String> allTabID = driver.getWindowHandles();//*
		
		//xử lý cookie (framwork)
		driver.manage().getCookies();
		
		//lấy log của browser ra (Framwork)
		//driver.manage().logs();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//**
		
		//Time của việc findElement/findElements
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Time page được load xong
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		
		//Time để 1 đoạn async script được thực thi thành công (JavascriptExecutor)
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
		
		//Fullscreen browser
		driver.manage().window().fullscreen();
		//End User hay maximize
		driver.manage().window().maximize();//*
		
		//lấy ra vị trí của browser so với độ phân giải màn hình
		Point browserPosition = driver.manage().window().getPosition();
		
		//set vị trí của browser tại điểm )*250
		driver.manage().window().setPosition(new Point(0, 250));
		
		//lấy ra chiều rộng/ chiều cao của browser
		Dimension browserSize = driver.manage().window().getSize();
		
		// set browser mơ với kích thước nào
		driver.manage().window().setSize(new Dimension(1366,4768));
		driver.manage().window().setSize(new Dimension(1920,1080));
		
		//quay lại trang trước đó
		driver.navigate().back();
		
		//chuyển tới trang trước đó
		driver.navigate().refresh();
		
		//tải lại trang
		driver.navigate().to("https://www.sendo.vn/");
		
		//webdriver - API - Alert/Authentication Alert
		driver.switchTo().alert();//**
		//webdriver - API -frame/Iframe
		driver.switchTo().frame(1);//**
		//webdriver - API - windows/Tabs
		driver.switchTo().window("");//**
		
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