package Javabasic;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

public class Topic_13_data_Type {
	Integer[] phone = {6955, 569};

	public static void main(String[] args) {
		// 2 loaị kiểu  dữ liệu
		// kiểu nguyên thủy (primitive type)
		// 8 loại
		//chả - kí tự  - 16
		char c = 'A';
		System.out.println(c);
		//byte - số nguyên - 8
		byte bNumber = 15;
		System.out.println(bNumber);
		
		//short - số nguyên - 16
		short sNumber = -32000;
		System.out.println(sNumber);
		
		//int - số nguyên - 32
		int iNumber = 200000000;
		System.out.println(iNumber);
		
		//long - số nguyên - 64
		long lNumber = 2000000000;
		System.out.println(lNumber);
		
		// float - số thực - 32
		float fNumber = 9.5f;
		System.out.println(fNumber);
		//double - số thực - 64
		double dNumber = 9.5d;
		System.out.println(dNumber);
		
		//boolean - logic -1 (true / false)
		boolean mariedStatus = true;
		System.out.println(mariedStatus);
		
		
		// kiểu tham chiếu (Reference Type)
		//object
		Object o = new Object();
		
		//Aray
		String[]  addresses = { "Ha Noi", "sai gon", "Da Nang"};
		Integer[] phone = {6955, 569};
		long a[] = {10000L, 3000L,40000L,23456};
		//class
		Topic_13_data_Type topic = new Topic_13_data_Type();
		
		//Interface
		WebDriver driver;
		
		//collection : List/set/queue
		List<String> addresses = new ArrayList<String>();
		//string - chuỗi kí tự
		String name = "Automation test";
		String cityName = new String("Ho chi minh");

	}

}
