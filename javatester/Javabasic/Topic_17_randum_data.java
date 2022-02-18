package Javabasic;

import java.util.Random;

public class Topic_17_randum_data {

	public static void main(String[] args) {
		Random rand = new Random();
		System.out.println(rand.nextInt());
		System.out.println(rand.nextInt(9999));
		System.out.println(rand.nextDouble());
		System.out.println(rand.nextLong());
		System.out.println(rand.nextFloat());

		System.out.println("Stevetest" + rand.nextInt(9999)+ "@gmail.com");
		System.out.println("Stevetest" + rand.nextInt(9999)+ "@gmail.com");
		System.out.println("Stevetest" + rand.nextInt(9999)+ "@gmail.com");
		System.out.println("Stevetest" + rand.nextInt(9999)+ "@gmail.com");
	}

}
