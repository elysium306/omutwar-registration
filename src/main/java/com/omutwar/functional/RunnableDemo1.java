package com.omutwar.functional;

import org.apache.commons.text.RandomStringGenerator;

public class RunnableDemo1 {
	public static void main(String[] args) {

		Runnable email = new Runnable() {

			@Override
			public void run() {
				RandomStringGenerator rsg = RandomStringGenerator.builder().withinRange('a', 'j').get();
				System.out.println("user" + rsg.generate(10) + "@msn.com");
			}
		};

		email.run();

	}
}
