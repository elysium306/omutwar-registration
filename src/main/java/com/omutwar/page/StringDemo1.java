package com.omutwar.page;

import org.apache.commons.text.RandomStringGenerator;

public class StringDemo1 {

	public static void main(String[] args) {

//		String phone = new Random().nextInt(201, 989) + RandomStringUtils.randomNumeric(7);
//		System.out.println(phone);

		RandomStringGenerator random = RandomStringGenerator.builder().setAccumulate(true).withinRange(11, 21)
				.withinRange('a', 'z').get();
		System.out.println(random.generate(25, 50));

	}
}
