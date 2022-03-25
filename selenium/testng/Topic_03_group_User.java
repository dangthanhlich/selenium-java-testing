package testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_group_User {
//	@BeforeClass(groups="user")
	
	@BeforeClass(alwaysRun=true)
	public void initBrowser() {
		System.out.println("Open browser");
	}
	@Test(groups="user")
	public void TC_01_Create_User()
	{
		
	}

	@Test(groups= {"user","admin"})
	public void TC_02_Create_User()
	{
		
	}
	
	@Test(groups= {"user","admin"})
	public void TC_03_Edit_User()
	{
		
	}
	
	@Test(groups= {"user","admin"})
	public void TC_04_Delete_User()
	{
		
	}
	@AfterClass(alwaysRun=true)
	public void cleanBrowser() {
		System.out.println("close browser");
	}
	
	
}
