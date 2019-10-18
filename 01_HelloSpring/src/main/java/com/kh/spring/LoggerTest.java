package com.kh.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerTest {
	//logger를 찍기 위해서는 Logger객체를 생성해야함.
	
	private static Logger logger = LoggerFactory.getLogger(LoggerTest.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoggerTest.test();
	}
	
	public static void test() {
		logger.debug("DEBUG LOGGER");
		logger.info("INFO LOGGER");
		logger.warn("WARN LOGGER");
		logger.error("ERROR LOGGER");
	}
	

}
