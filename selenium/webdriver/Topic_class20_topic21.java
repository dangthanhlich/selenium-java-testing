package webdriver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_class20_topic21 {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	String jsDragDropPath = projectPath +"/drag_And_Drop/drag_and_drop_helper.js";
	String jqueryDragDropPath = projectPath +"/drag_And_Drop/jquery_load_helper.js";
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	public void TC_01_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		jsExecutor.executeScript(" arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//button[text()='Double click me']")));
		sleepInSecond(3);
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.id("demo")).getText(),"Hello Automation Guys!");
		
	}
	
	public void TC_02_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		sleepInSecond(3);
		
		//Before hover mouse
		Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-paste:not(.context-menu-hover):not(.context-menu-visible)")).isDisplayed());
		
		action.moveToElement(driver.findElement(By.cssSelector(".context-menu-icon-paste"))).perform();
		sleepInSecond(3);
		//After hover mouse
		Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-paste.context-menu-hover.context-menu-visible")).isDisplayed());
		//click to paste
		action.click(driver.findElement(By.cssSelector(".context-menu-icon-paste"))).perform();
		sleepInSecond(3);
		
		driver.switchTo().alert().accept();
	}
	
	
	
	public void TC_03_Drag_Drop() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		WebElement snallCircle = driver.findElement(By.id("draggable"));
		WebElement bigCircle = driver.findElement(By.id("droptarget"));
		
		action.dragAndDrop(snallCircle, bigCircle).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(bigCircle.getText(),"You did great!");
		//CSS
		Assert.assertEquals(covertRgbaToHex(bigCircle.getCssValue("background-color")), "#03a9f4");
	}
	
	
	
	@Test
	public void TC_04_Drag_Drop_HTML_5() throws IOException{
		
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
		String columnA = "#column-a";
		String columnB = "#column-b";
		
		String jsValue = readFile(jsDragDropPath);
		
		jsValue = jsValue + "$(\"" + columnA + "\").simulateDragDrop({ dropTarget: \"" + columnB + "\"});";
		jsExecutor.executeScript(jsValue);
		sleepInSecond(3);
	}
	


	public String covertRgbaToHex(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	public String getContentFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
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
}