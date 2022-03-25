package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic01_Anoction {
 
	@Test
	public void TC_01() {
		System.out.println("Run testcase 01");
	}
	
	@Test
	public void TC_02() {
		System.out.println("Run testcase 02");
	}
	
	@Test
	public void TC_03() {
		System.out.println("Run testcase 03");
	}
	//before test chạy đầu tiên
	//before mwthod chạy cho tưng testcase
	
  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }

  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

  @BeforeSuite
  public void beforeSuite() {
  }

  @AfterSuite
  public void afterSuite() {
  }

}
