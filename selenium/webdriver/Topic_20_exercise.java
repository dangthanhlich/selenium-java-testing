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

public class Topic_20_exercise {
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
	
	//	- step1: Click vào các element cho nó sổ ra các item
	//	- step 2: chờ cho các item load hết ra thành công
	//	- step 3: tìm icon cần chọn
	//	 + b1: nếu item cần chọn nằm trong vùng nhìn thấy thì không cần scroll xuống tìm tiếp
	//	 + b2: Nếu item cần chọn nằm ở dưới thì scroll xuống đến ite đó
	//	 - step 4: Click vào item đó
	
//	@Test
//	public void TC_01_JQuery() {
//		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
//		// parentLocator = span#number-button>span.ui-selectmenu-icon
//		// childLocator = ul#number-menu div
//		// expectedTextItem = 5
//		selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon","ul#number-menu div","5");
//		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text ")).getText(), "5");
//		
//		selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon","ul#number-menu div","15");
//		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text ")).getText(), "15");
//		
//		selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon","ul#number-menu div","19");
//		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text ")).getText(), "19");
//		
//		selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon","ul#number-menu div","3");
//		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text ")).getText(), "3");
//		
//	}
//	@Test
//	public void TC_02_React() {
//		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
//
//		selectItemInCustomDropdownList("i.dropdown","div.item>span.text","Jenny Hess");
//		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Jenny Hess");
//		
//		selectItemInCustomDropdownList("i.dropdown","div.item>span.text","Justen Kitsune");
//		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Justen Kitsune");
//		
//		selectItemInCustomDropdownList("i.dropdown","div.item>span.text","Stevie Feliciano");
//		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Stevie Feliciano");
//		
//	}
//	
//	@Test
//	public void TC_03_VueJs() {
//		driver.get("https://mikerodham.github.io/vue-dropdowns/");
//
//		selectItemInCustomDropdownList("li.dropdown-toggle>span.caret","ul.dropdown-menu>li>a","First Option");
//		Assert.assertEquals(driver.findElement(By.cssSelector("div.btn-group>li.dropdown-toggle")).getText(), "First Option");
//		
//		selectItemInCustomDropdownList("li.dropdown-toggle>span.caret","ul.dropdown-menu>li>a","Second Option");
//		Assert.assertEquals(driver.findElement(By.cssSelector("div.btn-group>li.dropdown-toggle")).getText(), "Second Option");
//		
//		selectItemInCustomDropdownList("li.dropdown-toggle>span.caret","ul.dropdown-menu>li>a","Third Option");
//		Assert.assertEquals(driver.findElement(By.cssSelector("div.btn-group>li.dropdown-toggle")).getText(), "Third Option");
//		
//	}
	
//	@Test
//	public void TC_04_Angular_selecte() {
//		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
//
//		selectItemInCustomDropdownList("ng-select[bindvalue='provinceCode'] span.ng-arrow-wrapper","span.ng-option-label","Thành phố Hồ Chí Minh");
//		
//		//1 text nó không có nằm trên html
//		String actualText = (String) jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue='provinceCode'] span.ng-value-label\").innerText");
//		Assert.assertEquals(actualText, "Thành phố Hồ Chí Minh");
//		
//		//2 
//		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='provinceCode'] span.ng-value-label")).getText(), "Thành phố Hồ Chí Minh");
//		
//		//3
//		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='provinceCode'] span.ng-value-label")).getAttribute("innerText"), "Thành phố Hồ Chí Minh");
//		
//		
//		selectItemInCustomDropdownList("ng-select[bindvalue='districtCode'] span.ng-arrow-wrapper","div[role='option'] span.ng-option-label","Huyện Ngọc Hiển");
//		Assert.assertEquals(driver.findElement(By.cssSelector("div.btn-group>li.dropdown-toggle")).getText(), "Huyện Ngọc Hiển");
//		
//    	selectItemInCustomDropdownList("ng-select[bindvalue='wardCode'] span.ng-arrow-wrapper","div[role='option'] span.ng-option-label","Xã Đất Mũi");
//		Assert.assertEquals(driver.findElement(By.cssSelector("div.btn-group>li.dropdown-toggle")).getText(), "Xã Đất Mũi");
//		
//	}
	
	@Test
	public void TC_04_Angular_Enter() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");

		enterTocustomDropdownList("ng-select[bindvalue='provinceCode'] input[role='combobox']","span.ng-option-label","Thành phố Hà Nội");
		sleepInSecond(1);
		enterTocustomDropdownList("ng-select[bindvalue='districtCode'] input[role='combobox']","span.ng-option-label","Quận Ba Đình");
		sleepInSecond(1);
		enterTocustomDropdownList("ng-select[bindvalue='wardCode'] input[role='combobox']","span.ng-option-label","Phường Phúc Xá");
		sleepInSecond(1);
	
	}
	
	
	public void selectItemInCustomDropdownList(String parentLocator, String childLocator, String expectedTextItem)
	{
		//		- step1: Click vào các element cho nó sổ ra các item
		driver.findElement(By.cssSelector(parentLocator)).click();
		sleepInSecond(2);
		
		//	- step 2: chờ cho các item load hết ra thành công
		// wait  visible : 8 cái
		// wait presence : 19 cái
		//lưu ý 1: Locator chứa hết tất cả item
		//lưu ý 2: Locator phải đến node cuối cùng của text 
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
	
	public void enterTocustomDropdownList(String parentLocator, String childLocator, String expectedTextItem)
	{
		//		- step1:phải lấy 1 thẻ input 9textbox) để senkey vào
		driver.findElement(By.cssSelector(parentLocator)).sendKeys(expectedTextItem);
		sleepInSecond(2);
		
		//	- step 2: chờ cho các item load hết ra thành công
		// wait  visible : 8 cái
		// wait presence : 19 cái
		//lưu ý 1: Locator chứa hết tất cả item
		//lưu ý 2: Locator phải đến node cuối cùng của text 
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