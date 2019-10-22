package com.kh.spring.common.aspect;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.omg.CORBA.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

//여기서 만든 클래스는 그냥 spring bean(Pojo객체)이 아니라 aspect인 bean이라는 뜻
@Component
@Aspect
public class AnnoAspect {
	
	Logger logger = LoggerFactory.getLogger(AnnoAspect.class);
	
	//aspect는 Pointcut과 Advise로 이루어진다.
//	@Pointcut("execution(* com.kh.spring.memo.model.dao..insert*(..))") //리터럴로 다 적어줘도 된다!
//	public void tttt() { }
	
	@Around("execution(* com.kh.spring.memo.model.dao..insert*(..))")
	public Object AnnoAround(ProceedingJoinPoint pjp) throws Throwable {
		HttpSession session = (HttpSession)RequestContextHolder.currentRequestAttributes().resolveReference(RequestAttributes.REFERENCE_SESSION);
		if(session!=null && session.getAttribute("loginMember")==null) {
			throw new RuntimeException("로그인해 임마!"); //이후 로직을 수행되지 않게끔 강제 에러 발생 
		}
		return pjp.proceed();
	}
}
