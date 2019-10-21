package com.kh.spring.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggerInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("==========start==========");
		logger.debug("요청주소 : "+ request.getRequestURI());
		logger.debug("----------메소드 시작!----------");
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.debug("----------메소드 종료!----------");
		logger.debug(""+modelAndView.getViewName());
		if(modelAndView.getModel()!=null) {
			for(String key : modelAndView.getModel().keySet()) {
				logger.debug(key+" : "+modelAndView.getModel().get(key));			
			}
		}
		logger.debug("==========end==========");
		super.postHandle(request, response, handler, modelAndView);
	}
	
}
