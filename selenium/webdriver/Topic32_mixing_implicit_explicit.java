package webdriver;

import java.util.concurrent.TimeUnit;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic32_mixing_implicit_explicit {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

	}

	public void TC_01_Element_Found_Implicit_Explicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		expliciWait = new WebDriverWait(driver, 15);

		driver.get("https://www.facebook.com/");
		// explicit
		expliciWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@id='email']")));

		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("aaaa@gmail.com");

	}

	// public void TC_02_Element_not_Found_Implicit() {
	// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	// expliciWait = new WebDriverWait(driver ,10);
	//
	// //chờ hết timeout của implicit
	// //trong thời gian chwof cứ mỗi s sẽ tìm lại 1 lần
	// //khi nào chờ hết timout của implicit thì sẽ đánh fail testcase và throw exception :No SuchElementException
	//
	// driver.get("https://www.facebook.com/");
	//
	// try {
	// driver.findElement(By.xpath("//input[@id='email']")).sendKeys("aaaa@gmail.com");
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// }
	//
	//
	// public void TC_03_Element_not_Found_Implicit_Explicit() {
	// driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
	// expliciWait = new WebDriverWait(driver ,8);
	//
	// driver.get("https://www.facebook.com/");
	//// findElement trước
	//// apply điều kiện
	//// implicit sẽ ảnh hưởng vào các step có dùng explicit
	//
	// try {
	// expliciWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@id='ko_thay']")));
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }
	//
	// @Test
	// public void TC_04_Element_NOt_Found_Explicit_Param_By() {
	// expliciWait = new WebDriverWait(driver,5);
	//
	// driver.get("https://www.facebook.com/");
	// showDateTimeNow("start explicit (By):");
	// try {
	// expliciWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@id='ko_thay']")));
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// showDateTimeNow("End explicit (By):");
	// }
	// public void TC_05_Element_Not_Found_Explicit_Param_WebElement() {
	// expliciWait = new WebDriverWait(driver,5);
	//
	// driver.get("https://www.facebook.com/");
	// showDateTimeNow("start explicit (WebElement):");
	// try {
	// expliciWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@id='ko_thay']")));
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// showDateTimeNow("End explicit (WebElement):");
	// }
	//
	//// public String getDateTimeNow() {
	//// Date date = new Date();
	//// return date.toString();
	//// }
	//
	// public void showDateTimeNow(String status) {
	// Date date = new Date();
	// System.out.println("---------"+status +":"+ date.toString()+"----------");
	// }
	//
	// @AfterClass
	// public void afterClass() {
	// driver.quit();
	// }

}