package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_webBrowser_command2 {
	//khai báo 1 biến đại diện cho selenium ưebdriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com/");
		driver.manage().window().maximize();
	}

	//biến local= biến cục bộ
	//biến global = biến toàn cục
	//biến global khi chưa khởi tạo nó sẽ có giá trị mặc định
	// biến local khi mà chưa khởi tạo nó sẽ k có giá trị mặc định
	
	@Test
	public void TC_01() {
		//nếu như element chỉ khai báo 1 lần thì khoog cần khai báo biến
		driver.findElement(By.name("login")).click();
		
		//nếu như element có dùng từ 2 lần trở lên thì nên khai báo biến để tái sử dụng
		
		WebElement emailTextbox = driver.findElement(By.cssSelector("#email"));
		emailTextbox.clear();
		emailTextbox.sendKeys("demo@gmail.com");
		emailTextbox.getCssValue("backgroud-color");
		
		//khai báo biến
		String homePageUrl = driver.getCurrentUrl();
	}
	
	@Test
	public void TC_02() {
		//get luôn trả về dữ liệu
		
		WebElement element = driver.findElement(By.xpath(""));
		//xóa dữ liệu trong textbox/textarea/editable dropdown (cho phép edit và nhập liệu)
		element.clear();
		
		//nhập dữ liệu vào trong textbox/textarea/edittable dropdown(cho phép edit và nhập liệu)
		element.sendKeys("");
		
		//Click vào button /radio butto/checkbox/link/image/....
		element.click();
		
		driver.getCurrentUrl();
		driver.getPageSource();
		driver.getTitle();
		driver.getWindowHandle();
		driver.getWindowHandles();
		driver.manage().window().getPosition();
		driver.manage().window().getSize();
		
		//lấy ra giá trị của attribute
		element.getAttribute("placeholder");
		driver.get("http://live.techpanda.org/index.php/");
		String searchPlaceholder = driver.findElement(By.id("search")).getAttribute("placeholder");
		System.out.println(searchPlaceholder);
		
		//lấy ra text của element
		element.getText();
		driver.get("http://live.techpanda.org/index.php/customer/account/create/");
		//String searchPlaceholder = driver.findElement(By.id("search")).getAttribute("placeholder");
		System.out.println(searchPlaceholder);
		String instructionText = driver.findElement(By.cssSelector("p.form-instructions")).getText();
		System.out.println(instructionText);
		
		//điều lấy ra các thuộc tín h CSS | thường dùng để test GUI: font/color/size/location/position ..
		element.getCssValue("");
		driver.get("https://www.facebook.com/");
		WebElement loginButton = driver.findElement(By.name("login"));
		System.out.println(loginButton.getCssValue("font-size"));
		System.out.println(loginButton.getCssValue("background-color"));
		System.out.println(loginButton.getCssValue("width"));
		System.out.println(loginButton.getCssValue("font-family"));
		
		
		
		element.getLocation();
		element.getRect();
		//chụp hình của element lại lưu bằng format nào đó
		String base64Image = element.getScreenshotAs(OutputType.BASE64);
		element.getScreenshotAs(OutputType.BYTES);
		element.getScreenshotAs(OutputType.FILE);
		
		//lấy ra tên thẻ html của element
		element = driver.findElement(By.xpath("//[@id='email']"));
		element = driver.findElement(By.cssSelector("input[id='email']"));
		String elementTagname = element.getTagName();
		
		element.getSize();
		
		//hàm action là boid
		//hàm get luôn kiểu dữ liệu là string
		//hàm vẻify kiểu dữ liệu boolean (đúng /sai)(true/false) 
		
		//kiểm tra 1 element  có hiển thị hay ko - tất cae element
		// người dùng có thể nhìn thấy được
		
		//nếu như mong đợ hiển thị (kiểu như k nhập yêu cầu được hiển thị vaidation)
		Assert.assertTrue(element.isDisplayed());
		
		//nếu như mong đợi không hiển thị
		Assert.assertFalse(element.isDisplayed());
		
		element.isDisplayed();
		
		
		//kiểm tra 1 element có thể thao tác được không
		//read-onlu hoặc disable (kiểu như disable)
		//mong đợi thao tác được enable
		Assert.assertTrue(element.isEnabled());
		//mong đợi nó bị disble/read-only/không thao tác
		Assert.assertTrue(element.isEnabled());
		element.isEnabled();
		
		//kiểm tra 1 element đã được chọn hay chưa
		//radio button,checkbox/drpdown
		element.isSelected();
		//mong đợi nó đã được chọn rồi
		Assert.assertTrue(element.isSelected());
		//mong đợi nó chưa chọn/bỏ chọn
		Assert.assertTrue(element.isSelected());
		
		
		//tương ứng với hành động là nhấn phím ENTER trên bàn phím
		// chỉ dùng với element là form hoặc nằm trong form
		element.submit();
		
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