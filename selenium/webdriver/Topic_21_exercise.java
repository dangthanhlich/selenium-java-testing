package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;

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
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}


//	public void TC_01_Button() {
//		driver.get("https://www.fahasa.com/customer/account/create");
//		
//		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
//		By loginButtonBy = By.cssSelector("button.fhs-btn-login");
//		
//		
//		//isEnabled new 1 element ma enabled ->true
//		//isEnabled new 1 element ma disabled-> false
//		
//		//verify  login button is disabled
//		Assert.assertFalse(driver.findElement(loginButtonBy).isEnabled());
//		
//		driver.findElement(By.cssSelector("input#login_username")).sendKeys("aaaa@gmail.com");
//		driver.findElement(By.cssSelector("input#login_password")).sendKeys("aaaaabb");
//		sleepInSecond(1);
//		
//		//verify login button is disabled
//		Assert.assertTrue(driver.findElement(loginButtonBy).isEnabled());
//		
//		
//		// Verify background color = red
//		String loginButtonBackgroundColorRgb = driver.findElement(loginButtonBy).getCssValue("background-color");
//		System.out.println("RBG color" + loginButtonBackgroundColorRgb);
//		
//		//verify = RBG
//		Assert.assertEquals(loginButtonBackgroundColorRgb, "rgb(201, 33, 39)");
//		
//		//Convert qa Hexa
//		String loginButtonBackgroundColorHexa = Color.fromString(loginButtonBackgroundColorRgb).asHex();
//		System.out.println("Hexa color = " + loginButtonBackgroundColorHexa);
//		
//		Assert.assertEquals(loginButtonBackgroundColorHexa.toUpperCase(),"#C92127");
//		
//		driver.navigate().refresh();
//		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
//		
//		//remove disabled attribute
//		jsExecutor.executeScript("arguments[0].removeAttribute('disabled');", driver.findElement(loginButtonBy));
//		sleepInSecond(2);
//		
//		driver.findElement(loginButtonBy).click();
//		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='S??? ??i???n tho???i/Email']//following-sibling::div[@class='fhs-input-alert']")).getText(), "Th??ng tin n??y kh??ng th??? ????? tr???ng");
//		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='M???t kh???u']//following-sibling::div[@class='fhs-input-alert']")).getText(), "Th??ng tin n??y kh??ng th??? ????? tr???ng");
//		
//		
//		
//	}
//
//	public void TC_02_Default_Radio() {
//		//Default - the input
//		//Action: click
//		//verify
//		
//		//custom - the input
//		//action : kh??ng click ???????c 
//		//verify ???????c
//		
//		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
//		
//		By oneDotEightPetroRadio = By.xpath("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::input");
//		By twoDotZeroPetroRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
//		By threeDotSixPetroRadio = By.xpath("//label[text()='3.6 Petrol, 191kW']/preceding-sibling::input");
//		
//		
//		Assert.assertFalse(driver.findElement(oneDotEightPetroRadio).isSelected());
//		driver.findElement(oneDotEightPetroRadio).click();
//		sleepInSecond(2);
//		
//		Assert.assertTrue(driver.findElement(oneDotEightPetroRadio).isSelected());
//		
//		driver.findElement(twoDotZeroPetroRadio).click();
//		sleepInSecond(2);
//		
//		//Deselectted: b??? ch???n
//		Assert.assertFalse(driver.findElement(oneDotEightPetroRadio).isSelected());
//		
//		//Selected:???? ch???n
//		Assert.assertTrue(driver.findElement(twoDotZeroPetroRadio).isSelected());
//		
//		//Enabled: ???? b???t
//		Assert.assertTrue(driver.findElement(oneDotEightPetroRadio).isEnabled());
//		Assert.assertTrue(driver.findElement(twoDotZeroPetroRadio).isEnabled());
//		
//		
//		//Disabled = read only(t???t ,ch??? ?????c)
//		Assert.assertFalse(driver.findElement(threeDotSixPetroRadio).isEnabled());
//		
//		
//	}
//	
//	
//
//	public void TC_03_Default_Checkbox() {
//		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
//		
//		By luggageCheckbox = By.xpath("//label[text()='Luggage compartment cover']//preceding-sibling::input");
//		By heatedCheckbox = By.xpath("//label[text()='Heated front and rear seats']//preceding-sibling::input");
//		By towbarCheckbox = By.xpath("//label[text()='Towbar preparation']//preceding-sibling::input");
//		By leatherCheckbox = By.xpath("//label[text()='Leather trim']//preceding-sibling::input");
//		
//		//select
//		checkToCheckbox(luggageCheckbox);
//		checkToCheckbox(heatedCheckbox);
//		
//		//Selected
//		Assert.assertTrue(isElementSelected(luggageCheckbox));
//		Assert.assertTrue(isElementSelected(heatedCheckbox));
//		Assert.assertTrue(isElementSelected(leatherCheckbox));
//		
//		//Disabled 
//		Assert.assertFalse(isElementEnabled(towbarCheckbox));
//		Assert.assertFalse(isElementEnabled(leatherCheckbox));
//		
//		//De-select
//		uncheckToCheckbox(luggageCheckbox);
//		uncheckToCheckbox(heatedCheckbox);
//		
//		//De-selected
//		Assert.assertFalse(isElementSelected(luggageCheckbox));
//		Assert.assertFalse(isElementSelected(heatedCheckbox));
//		Assert.assertFalse(isElementSelected(towbarCheckbox));
//		
//		
//	}
//	
//	
//	public void TC_04_Multiple_checkbox() {
//		driver.get("https://automationfc.github.io/multiple-fields/");
//		
//		List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
//		System.out.println("checkbox size" + checkboxes.size());
//		
//		// Action-select
//		for(WebElement checkbox: checkboxes) {
//			if(!checkbox.isSelected()) {
//				checkbox.click();
//				sleepMiliSecond(300);
//			}
//		}
//		//verify -selected
//		for(WebElement checkbox: checkboxes) {
//			Assert.assertTrue(checkbox.isSelected());
//		}
//		
//		// Action-select
//		for(WebElement checkbox: checkboxes) {
//			if(checkbox.isSelected()) {
//				checkbox.click();
//				sleepMiliSecond(300);
//			}
//		}
//		//verify -selected
//		for(WebElement checkbox: checkboxes) {
//			Assert.assertFalse(checkbox.isSelected());
//		}
//	}
//	
//	
//	public void TC_05_Custom_Radio()
//	{
//		driver.get("https://material.angular.io/components/radio/examples");
//		
//		//Default  Radia /Check box
//		//Action :selenium webElement click()
//		//Verify:isSelected()
//		
//		//1 element ph???i define t???i 2 locator
//		// d??? b??? nh???m l???n :team member h??? d??ng -> hi???u nh???m//b???i tr?? code nhi???u
//		//case 1 : d??ng th??? input
//	//	selenium click();-> Eroe elementnotInteractableException
//	//	isSelected()->work
//		
//		//Case 2 : use span
//		//selenium click();->work
//		//isSelected()-> nope work
//		//case 3: use span - click()
//		//use input - isSelected()
//		
//		//case 4: use input
//		//javascript - click () (care  element hide/show)
//		//isSelected- verify
//		
//		By winterCheckboxInput = By.cssSelector("input[value='Winter']");
//		clickByJavascript(winterCheckboxInput);
//		sleepMiliSecond(2);
//		
//		Assert.assertTrue(driver.findElement(winterCheckboxInput).isSelected());
//
//		
//	}
	
//	@Test
//	public void TC_06_Custom_Checckbox()
//	{
//		driver.get("https://material.angular.io/components/checkbox/examples");
//		
//		By checkedCheckbox = By.xpath("//span[text()='Checked']/preceding-sibling::span/input");
//		By indeterminateCheckbox = By.xpath("//span[text()='Indeterminate']/preceding-sibling::span/input");
//		
//		clickByJavascript(checkedCheckbox);
//		sleepInSecond(2);
//		
//		clickByJavascript(indeterminateCheckbox);
//		sleepInSecond(2);
//		
//		Assert.assertTrue(isElementSelected(checkedCheckbox));
//		Assert.assertTrue(isElementSelected(indeterminateCheckbox));
//		sleepInSecond(2);
//		
//		clickByJavascript(checkedCheckbox);
//		sleepInSecond(2);
//		
//		clickByJavascript(indeterminateCheckbox);
//		sleepInSecond(2);
//		
//		Assert.assertFalse(isElementSelected(checkedCheckbox));
//		Assert.assertFalse(isElementSelected(indeterminateCheckbox));
//		sleepInSecond(2);
//		
//		
//	}
	
//	@Test
//	public void TC_07_Custom_Radio()
//	{
//		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
//		
//		By mySelfRadio = By.xpath("//div[text()='????ng k?? b???n th??n']//preceding-sibling::div/input");
//		By myFamilyadio = By.xpath("//div[text()='????ng k?? cho ng?????i th??n']//preceding-sibling::div/input");
//		
//		clickByJavascript(myFamilyadio);
//		sleepInSecond(2);
//		
//		Assert.assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='registerFullname']")).isDisplayed());
//		Assert.assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='registerPhoneNumber']")).isDisplayed());
//		
//		clickByJavascript(mySelfRadio);
//		sleepInSecond(2);
//		
//		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
//		Assert.assertEquals(driver.findElements(By.xpath("//input[@formcontrolname='registerFullname']")).size(), 0);
//		Assert.assertEquals(driver.findElements(By.xpath("//input[@formcontrolname='registerPhoneNumber']")).size(), 0);
//	}
	
	@Test
	public void TC_08_Custom_Radio_Google_Doc()
	{
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		By haiPhongCityRadio = By.xpath("//div[@aria-label='H???i Ph??ng']");
		By quangNamCityCheckbox = By.xpath("//div[@aria-label='Qu???ng Nam']");
		By quangBinhCityCheckbox = By.xpath("//div[@aria-label='Qu???ng B??nh']");
		
//		Assert.assertEquals(driver.findElement(haiPhongCityRadio).getAttribute("aria-checked"),"false");
//		Assert.assertEquals(driver.findElement(quangNamCityCheckbox).getAttribute("aria-checked"),"false");
//		Assert.assertEquals(driver.findElement(quangBinhCityCheckbox).getAttribute("aria-checked"),"false");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='H???i Ph??ng' and @aria-checked='false']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Qu???ng Nam' and @aria-checked='false']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Qu???ng B??nh' and @aria-checked='false']")).isDisplayed());
		
		driver.findElement(haiPhongCityRadio).click();
		sleepInSecond(2);
		
		driver.findElement(quangNamCityCheckbox).click();
		sleepInSecond(2);
		
		driver.findElement(quangBinhCityCheckbox).click();
		sleepInSecond(2);
		
		
		
//		Assert.assertEquals(driver.findElement(haiPhongCityRadio).getAttribute("aria-checked"),"true");
//		Assert.assertEquals(driver.findElement(quangNamCityCheckbox).getAttribute("aria-checked"),"true");
//		Assert.assertEquals(driver.findElement(quangBinhCityCheckbox).getAttribute("aria-checked"),"true");
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='H???i Ph??ng' and @aria-checked='true']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Qu???ng Nam' and @aria-checked='true']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Qu???ng B??nh' and @aria-checked='true']")).isDisplayed());

		
	
	}
	
	@Test
	public void TC_08_B_c2_Custom_Radio_Google_Doc()
	{
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		By haiPhongCityRadio = By.xpath("//div[@aria-label='H???i Ph??ng']");
		By quangNamCityCheckbox = By.xpath("//div[@aria-label='Qu???ng Nam']");
		By quangBinhCityCheckbox = By.xpath("//div[@aria-label='Qu???ng B??nh']");
		
		Assert.assertEquals(driver.findElement(haiPhongCityRadio).getAttribute("aria-checked"),"false");
		Assert.assertEquals(driver.findElement(quangNamCityCheckbox).getAttribute("aria-checked"),"false");
		Assert.assertEquals(driver.findElement(quangBinhCityCheckbox).getAttribute("aria-checked"),"false");
		
		driver.findElement(haiPhongCityRadio).click();
		sleepInSecond(2);
		
		driver.findElement(quangNamCityCheckbox).click();
		sleepInSecond(2);
		
		driver.findElement(quangBinhCityCheckbox).click();
		sleepInSecond(2);
		
		
		
		Assert.assertEquals(driver.findElement(haiPhongCityRadio).getAttribute("aria-checked"),"true");
		Assert.assertEquals(driver.findElement(quangNamCityCheckbox).getAttribute("aria-checked"),"true");
		Assert.assertEquals(driver.findElement(quangBinhCityCheckbox).getAttribute("aria-checked"),"true");

		
	
	}
	
	public void clickByJavascript(By by) {
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(by));
		
	}
	
	
	public void checkToCheckbox(By by)
	{
		if(!driver.findElement(by).isSelected())
		{
			driver.findElement(by).click();
		}
	}
	
	public void uncheckToCheckbox(By by)
	{
		if(driver.findElement(by).isSelected())
		{
			driver.findElement(by).click();
		}
	}
	
	public boolean isElementSelected(By by)
	{
		if(driver.findElement(by).isSelected())
		{
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isElementEnabled(By by)
	{
		if(driver.findElement(by).isEnabled())
		{
			return true;
		}else {
			return false;
		}
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
	public void sleepMiliSecond(long milisecond) {
		try {
			Thread.sleep(milisecond);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}