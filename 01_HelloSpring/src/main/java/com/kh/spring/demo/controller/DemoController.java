package com.kh.spring.demo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring.LoggerTest;
import com.kh.spring.demo.model.service.DemoService;
import com.kh.spring.demo.model.vo.Dev;

//그냥 클래스여도 servlet에서 인식 가능하게 어노테이션 표시!!, 
//여기에서는 Controller이므로 어노테이션 Controller를 추가한다.
@Controller
public class DemoController {
	//service객체를 등록만하고 객체생성은 하지 않는다!
	//이 또한 어노테이션 Autowired 표시를 통해서 controller가 실행될때
	//Spring이 자동으로 DemoServiceImpl객체를 생성해서 넣어준다.
	@Autowired
	private DemoService service;
	//화면 전환용 HandlerMapping
	
	private static Logger logger = LoggerFactory.getLogger(LoggerTest.class);
	
	//@RequestMapping("주소값,맵핑값")
	//어노테이션으로 맵핑값을 지정해주고 메소드를 생성해서 붙여준다. 메소드의 기본 반환형은 String
	@RequestMapping("/demo/demo.do")
	public String demo() {
		logger.debug("/demo/demo.do가 호출됨.!");
		return "demo/demo";
		//==/WEB-INF/views/demo/demo.jsp
	}
	
	//HttpServletRequest로 파라미터 값 받기
	@RequestMapping("/demo/demo1.do")
	public String demo1(HttpServletRequest req) {
		logger.debug(req.getParameter("devName"));
		logger.debug(req.getParameter("devAge"));
		logger.debug(req.getParameter("devEmail"));
		logger.debug(req.getParameter("devGender"));
		logger.debug(req.getParameter("devLang"));
		
		Dev dev = new Dev();
		dev.setDevName(req.getParameter("devName"));
		dev.setDevAge(Integer.parseInt(req.getParameter("devAge")));
		dev.setDevEmail(req.getParameter("devEmail"));
		dev.setDevGender(req.getParameter("devGender"));
		dev.setDevLang(req.getParameterValues("devLang"));
		
		req.setAttribute("dev", dev);
		
		return "demo/demoView";
		//내부적으로 이것이 requestDispatcher . forward 역할을 한다.
	}
	
	// @RequestParam을 이용하여 파라미터 받기
	// @RequestParam을 이용해서 매개변수로 받을때는 무조건 required됐다고 생각한다. null값이 오면 안됨!!
	// 즉, required속성이 true라는 뜻!
	// @별도의 설정 value = 맵핑name, required = false 를 통해서 필수가 아니게 할 수 도 있다.
	// defaultValue를 통해서 default값을 지정해줄수도있다. 모든지 String으로 넘어오기때문에 int같은 경우는 defaultValue를 설정해야
	// 오류를 방지
	@RequestMapping("/demo/demo2.do")
//	public String demo2( @RequestParam(value="devName") String devName, @RequestParam(value="devAge", required=false, defaultValue="19") int devAge, @RequestParam String devEmail,
//			@RequestParam String devGender, @RequestParam(value="devLang", required=false) String[] devLang, HttpServletRequest req) {
	// 아래 방식은 @RequestParam을 생략하고 선언하는 방식인데, 무조건 input태그의 name값과 변수명이 같아야하고, 무조건 required=true이다!
	public String demo2(int devNo, String devName, int devAge, String devEmail, String devGender, String[] devLang, HttpServletRequest req) {
		System.out.println(devName);
		System.out.println(devAge);
		System.out.println(devEmail);
		System.out.println(devGender);
		System.out.println(devLang);
		
		req.setAttribute("dev", new Dev(devNo, devName,devAge,devEmail,devGender,devLang));
		
		return "demo/demoView";
		
	}
	
	@RequestMapping("/demo/demo3.do")
	public String demo3(@RequestParam Map map, HttpServletRequest req) {
		System.out.println(map);
		req.setAttribute("dev", map);
		return "demo/demoView";
	}
	
	//Command객체로 받기
	@RequestMapping("/demo/demo4.do")
	public String demo4(Dev dev, HttpServletRequest req) {
		req.setAttribute("dev", dev);
		return "demo/demoView";
	}
	
	@RequestMapping("/demo/insertDemo.do")
	public String insertDemo(Dev dev, Model model) {
		
		int result = service.insertDemo(dev);
		
		//redirect하기 : url창이 재요청됨, dispatcher는 servlet부터
		return "redirect:/index.jsp";
	}
	
	@RequestMapping("/demo/selectDevList.do")
	public String selectDevList(Model model) {
		List<Dev> list = service.selectDevList();
		//Model은 request객체처럼 데이터를 보관하는 객체
		model.addAttribute("list",list);
		return "demo/demoList";
	}
	
	@RequestMapping("/demo/demoUpdate.do")
	public String demoUpdate(int devNo, Model model) {
		Dev dev = service.selectDev(devNo);
		model.addAttribute(dev);
		return "demo/demoUpdate";
	}
	
	@RequestMapping("/demo/updateDemoEnd.do")
	public String demoUpdate(Dev dev) {
		int result = service.updateDemo(dev);
		return "redirect:/index.jsp";
	}
	
	@RequestMapping("/demo/demoDelete.do")
	public String demoDelote(int devNo) {
		int result = service.deleteDemo(devNo);
		return "redirect:/index.jsp";
	}
	
}
