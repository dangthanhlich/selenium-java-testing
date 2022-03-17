package webdriver;

import java.util.Random;
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

public class Topic_25_Javascript_Executor {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;

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
		explicitWait =  new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_live_Guru() {
		navigateToUrlByJS("http://live.techpanda.org/");
		//url
		sleepInSecond(5);
		String liveGuruDomain =(String) executeForBrowser("return document.domain;");
		
		Assert.assertEquals(liveGuruDomain, "live.techpanda.org");
		
		String liveGuruURL =(String) executeForBrowser("return document.URL;");
		Assert.assertEquals(liveGuruURL, "http://live.techpanda.org/");
		
		clickToElementByJS("//a[text()='Mobile']");
		sleepInSecond(5);
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		sleepInSecond(5);
		Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
		
		clickToElementByJS("//a[text()='Customer Service']");
		sleepInSecond(5);
		scrollToElementOnDown("//input[@id='newsletter']");
		
		sendkeyToElementByJS("//input[@id='newsletter']",getRandomEmail());
		sleepInSecond(5);
		clickToElementByJS("//button[@title='Subscribe']");
		sleepInSecond(5);
		Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."));
		
		//Chuyển sang domain mới
		navigateToUrlByJS("https://demo.guru99.com/v4/");
		sleepInSecond(5);
		//chờ 
		explicitWait.until(ExpectedConditions.urlToBe("https://demo.guru99.com/v4/"));
		Assert.assertEquals(executeForBrowser("return document.domain;"),"demo.guru99.com");
	}

	public void TC_02_Validation_Mesage() {
		navigateToUrlByJS("https://automationfc.github.io/html5/index.html");
		
		clickToElementByJS("//input[@name='submit-btn']");
		sleepInSecond(10);
		// hướng dẫn lấy validate .vào console 
		//var element = $x("//input[@id='fname']")[0];
		//element.validationMessage;-> lấy ra được : Please fill out this field.
		Assert.assertEquals(getElementValidationMessage("//input[@id='fname']"),"Please fill out this field.");
		
		sendkeyToElementByJS("//input[@id='fname']","Automation FC");
		sleepInSecond(3);
		
		clickToElementByJS("//input[@name='submit-btn']");
		sleepInSecond(3);
		Assert.assertEquals(getElementValidationMessage("//input[@id='pass']"),"Please fill out this field.");
		
		sendkeyToElementByJS("//input[@id='pass']","123456");
		sleepInSecond(3);
		
		clickToElementByJS("//input[@name='submit-btn']");
		sleepInSecond(3);
		Assert.assertEquals(getElementValidationMessage("//input[@id='em']"),"Please fill out this field.");
		
		sendkeyToElementByJS("//input[@id='em']","123");
		sleepInSecond(3);
		clickToElementByJS("//input[@name='submit-btn']");
		Assert.assertEquals(getElementValidationMessage("//input[@id='em']"),"Please enter an email address.");
		
		sendkeyToElementByJS("//input[@id='em']","123@123");
		sleepInSecond(3);
		clickToElementByJS("//input[@name='submit-btn']");
		Assert.assertEquals(getElementValidationMessage("//input[@id='em']"),"Please match the requested format.");
		
		
		sendkeyToElementByJS("//input[@id='em']","123!@#$%");
		sleepInSecond(3);
		
		clickToElementByJS("//input[@name='submit-btn']");
		Assert.assertEquals(getElementValidationMessage("//input[@id='em']"),"Please enter an email address.");
		
		
		sendkeyToElementByJS("//input[@id='em']","123@234.567");
		sleepInSecond(3);
		
		clickToElementByJS("//input[@name='submit-btn']");
		Assert.assertEquals(getElementValidationMessage("//select"),"Please select an item in the list.");
		
	}
	
	
	public void TC_03_New_Customer(){
		navigateToUrlByJS("https://demo.guru99.com/v4/");
		
		String emailAddress, loginPageUrl, userID, password, gender;
		String name, dateOfBirth, address, city,state,pin ,phone;
		
		By nameTextbox = By.name("name");
		By genderRadio = By.xpath("//input[@value='m']");
		By genderTextbox = By.name("gender");
		By dobTextbox = By.name("dob");
		By addressTextArea = By.name("addr");
		By cityTextbox= By.name("city");
		By stateTextbox= By.name("state");
		By pinTextbox = By.name("pinno");
		By phoneTextbox= By.name("telephoneno");
		By emailTextbox= By.name("emailid");
		By passwordTextbox= By.name("password");
		
		emailAddress = getRandomEmail();
		name = "john Deep";
		gender = "male";
		dateOfBirth = "1956-01-01";
		address = "77 PO Califirnia";
		state="Hawail";
		city = "califonia";
		pin = "2344";
		phone = "0897444555";
		
		loginPageUrl = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		
		driver.findElement(By.name("emailid")).sendKeys(emailAddress);
		
		driver.findElement(By.name("btnLogin")).click();
		
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
//		driver.get(loginPageUrl);
		navigateToUrlByJS("https://demo.guru99.com/v4/");
		
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(), "Welcome To Manager's Page of Guru99 Bank");
		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		driver.findElement(nameTextbox).sendKeys(name);
		driver.findElement(genderRadio).click();
		
		removeAttributeInDOM("//input[@name='dob']","type");
		
		sleepInSecond(3);
		
		driver.findElement(dobTextbox).sendKeys(dateOfBirth);
		
		driver.findElement(addressTextArea).sendKeys(address);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(phoneTextbox).sendKeys(phone);
		driver.findElement(emailTextbox).sendKeys(emailAddress);
		driver.findElement(passwordTextbox).sendKeys(password);
		
		driver.findElement(By.name("sub")).click();
		
		
	}
	
	@Test
	public void TC_04_Regester() {
		navigateToUrlByJS("http://live.techpanda.org/");
		clickToElementByJS("//div[@id='header-account']//a[text()='My Account']");
		
		
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
	// lấy domain ,URL ,Title -> trả về Object
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}
	
	// verify, có dữ liệu thì true , không có dữ liệu false
	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}
	
	// xuống dưới cùng trang
	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	//
	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}
	//giống kiểu style css 
	public void hightlightElement(String locator) {
		//lấy element ra
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}
	// senkey textbox, và textarea
	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}
	//hay  dùng
	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}
	// chiều rộng ,chiều cao hình
	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		if (status) {
			return true;
		}
		return false;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
	
	public String getRandomEmail()
	{
		Random rand = new Random();
		return "john" + rand.nextInt(9999) + "@gmail.com";
	}
	
	
}