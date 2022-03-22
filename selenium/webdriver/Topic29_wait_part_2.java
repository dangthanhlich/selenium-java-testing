package webdriver;

import java.util.concurrent.TimeUnit;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic29_wait_part_2 {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		expliciWait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_FindElement() {
		driver.get("https://www.facebook.com/");
		
		//1 có duy nhất 1 element
		//- không cần chờ hết timeout của implicit
		//tương tác lên element luôn
		driver.findElement(By.id("email")).sendKeys("automationfc@gmail.com");
		
		// 2 không có element nào hết
		//chờ hết timeout của implicit
		//trong thời gian cứ mỗi s sẽ tìm lại 1 lần
		//khi nào chờ hết timeout của implicit thì sẽ đánh fall testcase và throw exception:NoSuchEx 
//		driver.findElement(By.id("address")).sendKeys("viet nam");
//		driver.findElement(By.id("pass")).sendKeys("123456");
		
		//3 nhieeuf hown 1 element(>=2)
		//không cần hết chờ timeout của implicit
		//nó sẽ lấy cái element đầu tiên để tương tác
		//nó không quan tâ, bao nhiêu matching node
		System.out.print(driver.findElement(By.xpath("//input")).getAttribute("name"));
		System.out.print(driver.findElement(By.xpath("//input")).getAttribute("type"));
		System.out.print(driver.findElement(By.xpath("//input")).getAttribute("value"));
		
	}
	
	@Test
	public void TC_02_FindElements() {
		driver.navigate().refresh();
		
		//1 có duy nhất 1 element
		//không cần chwof hết timeout của implicit
		//tương tác lên element luôn
		driver.findElement(By.id("email")).sendKeys("automationfc@gmail.com");
		System.out.println(driver.findElements(By.id("address")).size());
		
		//2- không có element nào hế-> cần test 1 element không cuất hiện trên UI và khong có trong DOM
		//chờ hết timeout của implicit
		//trong thời gian chwof cứ mỗi s tìm lại 1 lần
		//khi nào chờ hết timeout của implicit thì không đánh fail testcase
		//trả về 1 list empty rỗng không có phần tử web Element nào hết
		//chuyển qua step tiếp theo
		//System.out.println(driver.findElements(By.id("address")).size());
		//3 - nhiều hơn 1 element>=2
		//chờ hết timeout của implicit
		//lưu hết tất cả element vào trong List
		List<WebElement> footerLinks = driver.findElements(By.cssSelector("ul.pageFooterLinkList a"));
		System.out.println(footerLinks.size());
		for (WebElement Link :footerLinks ) {
			System.out.println(Link.getText());
		}
		
				
	}
	
	public void TC_03_presence() {
		
	}

	public void TC_04_staleness() {
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