// package selenium_class25;
//
// import java.util.concurrent.TimeUnit;
//
// import org.openqa.selenium.By;
// import org.openqa.selenium.JavascriptExecutor;
// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.chrome.ChromeDriver;
// import org.testng.annotations.AfterClass;
// import org.testng.annotations.BeforeClass;
// import org.testng.annotations.Test;
//
// public class button_radio {
// WebDriver driver;
// String projectPath = System.getProperty("user.dir");
// String osName = System.getProperty("os.name");
// JavascriptExecutor jsExecutor;
//
// @BeforeClass
// public void beforeClass() {
// System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
// driver = new ChromeDriver();
// driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
// // ép kiểu driver->interface
// jsExecutor = (JavascriptExecutor) driver;
// driver.manage().window().maximize();
// }
//
// @Test
// public void TC_01() {
// // dùng thư viện javascriptexecuter : không quan tâm element bị che hay không, vẫn click được
//
// By checkedCheckbox = By.xpath("//span[text()='checked']/preceding-sibling::span/input");
// jsExecutor.executeScript("arguments[0].click()", driver.findElement(checkedCheckbox));
//
// }
//
// @Test
// public void TC_02() {
//
// driver.get("");
// // radio
//
// By hanoiRadio = By.xpath("//div[@ariia-label='Hà Nội']");
// driver.findElement(hanoiRadio).click();
// sleepInSecond(3);
// Assert.assertEquals();
// }
//
// @AfterClass
// public void afterClass() {
// driver.quit();
// }
//
// public void sleepInSecond(long second) {
// try {
// Thread.sleep(second * 1000);
// } catch (InterruptedException e) {
// e.printStackTrace();
// }
// }
// }
