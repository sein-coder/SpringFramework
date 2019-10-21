package com.kh.spring.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AspectTest {
	
	private Logger logger = LoggerFactory.getLogger(AspectTest.class);
	
	public void beforeTest(JoinPoint jp) {
		logger.debug("=====before=====");
		Signature sig = jp.getSignature();
		//Signature : 각종 요청 주소나 정보(각 지점에 있는 메소드)를 가져올 수 있다.
		String methodName = sig.getName(); //실행된 메소드의 이름
		logger.debug("실행된 메소드 : "+methodName);
	}
	
}
