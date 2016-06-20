package com.chen;

import org.apache.logging.log4j.*;;

public class logTest {
	private static final Logger LOGGER = LogManager.getLogger(logTest.class.getName());
	
	public void tryMethod () {
		LOGGER.info("HELLO WORLD");
	}
	
	public static void main(String[] args) {
		System.out.println("start");
		logTest lt = new logTest();
		lt.tryMethod();
		System.out.println("end");
	}
}
