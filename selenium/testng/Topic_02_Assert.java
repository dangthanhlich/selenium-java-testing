package testng;

import org.testng.Assert;

public class Topic_02_Assert {
	public void TC_01()
	{
		String employeeName = "Tran van Em";
		Object address = null;
		
		// dùng ddeer trả về 1 đk mong muốn trả về đúng (true)
		Assert.assertTrue(employeeName.equals("Tran van Em"));
		Assert.assertTrue(employeeName.equals("Tran van Em"),"employeess is not equals");
	
		//dùng để kiểm tra 1 điều kiện mong muốn trả về là sai
		Assert.assertFalse(employeeName.equals("Tran Van Anh"));
		
		//dùng để kiểm tra 2 điều kiện bằng nhau
		Assert.assertEquals(employeeName,"Tran Van Em");
		
		Assert.assertNull(address);
		address ="nguyen B";
		
		Assert.assertNotNull(address);
		
		//		TestNG Group
		
		
		
	}
	
}
