package com.kh.spring.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

public class AspectTest {
	
	private Logger logger = LoggerFactory.getLogger(AspectTest.class);
	//전처리만
	public void beforeTest(JoinPoint jp) {
		logger.debug("=====before=====");
		Signature sig = jp.getSignature();
		//Signature : 각종 요청 주소나 정보(각 지점에 있는 메소드)를 가져올 수 있다.
		String methodName = sig.getName(); //실행된 메소드의 이름
		logger.debug("실행된 메소드 : "+methodName);
	}
	//후처리만
	public void afterTest(JoinPoint jp) {
		logger.debug("=====after=====");
		Signature sig = jp.getSignature();
		String methodName = sig.getName();
		String className = sig.getDeclaringTypeName();
		Object[] param = jp.getArgs();
		for(Object o : param) {
			logger.debug(""+o);
		}
		logger.debug("실행된 메소드 : "+className+methodName);
	}
	
	//around처리하기 전후처리
	public Object aroundTest(ProceedingJoinPoint pjp) throws Throwable{
		//전처리
		logger.debug("시작시간 : "+System.currentTimeMillis());
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		//후처리로 넘어가는 기준
		Object obj = pjp.proceed();
		// return pjp.proceed(); 전처리만 하는 방법!
		
		stopWatch.stop();
		logger.debug("종료 시간 : "+System.currentTimeMillis());
		logger.debug("걸린 시간 : "+stopWatch.getTotalTimeMillis());
		return obj;
	}
}
