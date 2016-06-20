package com.chen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Wombat {

	final Logger logger = LoggerFactory.getLogger(Wombat.class);
	
	public void test () {
		logger.info("hello world");
	}
	
	public static void main(String[] args) {
		Wombat wm = new Wombat();
		wm.test();
	}
}
