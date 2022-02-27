package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_exercise {
	//khai báo 1 biến đại diện cho selenium ưebdriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Add_Employee() {
		String firstname = "Luiss";
		String lastname = "Suarezs";
		String editFirstname = "Stevens";
		String editLastname = "Gerrard";
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		//textbox
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		//button
		driver.findElement(By.id("btnLogin")).click();
		
		sleepInSecond(5);
		
		//At Dashboard page:'Add employee' sub-menu  link is not displayed
		Assert.assertFalse(driver.findElement(By.cssSelector("a#menu_pim_addEmployee")).isDisplayed());
		
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/pim/addEmployee");
		
		
		//At Dashboard page:'Add employee' sub-menu  link is  displayed
		Assert.assertTrue(driver.findElement(By.cssSelector("a#menu_pim_addEmployee")).isDisplayed());
		
		
		driver.findElement(By.id("firstName")).sendKeys(firstname);
		driver.findElement(By.id("lastName")).sendKeys(lastname);
		String employeeID = driver.findElement(By.id("employeeId")).getAttribute("value");
		
		driver.findElement(By.id("btnSave")).click();
		
		sleepInSecond(5);
		
		By fisrtNameTextboxBy = By.id("personal_txtEmpFirstName");
		By LastNameTextboxBy = By.id("personal_txtEmpLastName");
		By employeeIDTextboxBy = By.id("personal_txtEmployeeId");
		
		//verify 'First name/last name / EmployeeID' textbox are disabled(is not enable)
		
		Assert.assertFalse(driver.findElement(fisrtNameTextboxBy).isEnabled());
		Assert.assertFalse(driver.findElement(LastNameTextboxBy).isEnabled());
		Assert.assertFalse(driver.findElement(employeeIDTextboxBy).isEnabled());

		//verify 'fristname/last name /emplyeeID value watching with input value
		Assert.assertEquals(driver.findElement(fisrtNameTextboxBy).getAttribute("value"), firstname);
		Assert.assertEquals(driver.findElement(LastNameTextboxBy).getAttribute("value"),lastname);
		Assert.assertEquals(driver.findElement(employeeIDTextboxBy).getAttribute("value"),employeeID);
		
		
		//Click Edit button
		driver.findElement(By.cssSelector("input#btnSave")).click();
		
		//verify "first name /last name/employeeID" textbox are enabled (is not enabled)
		Assert.assertTrue(driver.findElement(fisrtNameTextboxBy).isEnabled());
		Assert.assertTrue(driver.findElement(LastNameTextboxBy).isEnabled());
		Assert.assertTrue(driver.findElement(employeeIDTextboxBy).isEnabled());
		
		//edit firstname/lastname
		driver.findElement(fisrtNameTextboxBy).clear();
		driver.findElement(fisrtNameTextboxBy).sendKeys(editFirstname);
		
		driver.findElement(LastNameTextboxBy).clear();
		driver.findElement(LastNameTextboxBy).sendKeys(editLastname);
	
		//click save button
		driver.findElement(By.cssSelector("input#btnSave")).click();
		sleepInSecond(5);
		
		//verify 'First name/last name / EmployeeID' textbox are disabled(is not enable)
		
		Assert.assertFalse(driver.findElement(fisrtNameTextboxBy).isEnabled());
		Assert.assertFalse(driver.findElement(LastNameTextboxBy).isEnabled());
		Assert.assertFalse(driver.findElement(employeeIDTextboxBy).isEnabled());
		
		//verify 'fristname/last name /emplyeeID value watching with input value
		Assert.assertEquals(driver.findElement(fisrtNameTextboxBy).getAttribute("value"), editFirstname);
		Assert.assertEquals(driver.findElement(LastNameTextboxBy).getAttribute("value"), editLastname);
	
		
		//Click to 'Imigration' tab
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		
		//Click to 'Add' button
		driver.findElement(By.cssSelector("input#btnAdd")).click();
		
		//Enter to 'Immigration' number tẽtbox
		driver.findElement(By.id("immigration_number")).sendKeys("31195855");
		driver.findElement(By.cssSelector("textarea#immigration_comments")).sendKeys("Steven's\nPassport\nID");
		sleepInSecond(5);
		
		driver.findElement(By.id("btnSave")).click();
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//td[@class='document']/a[text()='Passport']")).click();
	
		//verify
		Assert.assertEquals(driver.findElement(By.id("immigration_number")).getAttribute("value"),"31195855");
		Assert.assertEquals(driver.findElement(By.cssSelector("textarea#immigration_comments")).getAttribute("value"),"Steven's\nPassport\nID");
		
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