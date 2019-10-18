package com.kh.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.kh.spring.LoggerTest;
import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

@SessionAttributes(value= {"loginMember", "msg"})
@Controller
public class MemberController {
	private static Logger logger = LoggerFactory.getLogger(LoggerTest.class);
	//@Autowired란? spring container가 알아서 해당하는 객체를 생성해서 활용해
	//spring bean내에서 찾아서!!
	@Autowired
	private MemberService service;
	@Autowired
	private BCryptPasswordEncoder pwEncoder;
	
	@RequestMapping("/member/memberEnroll.do")
	public String memberEnroll() {
		//페이지 전환용
		return "member/memberEnroll";
	}
	
	@RequestMapping("/member/memberEnrollEnd.do")
	public String memberEnrollEnd(Member m, Model model) {
		//1. 파라미터 받고
			//1) reqeust.getParameter
			//2) vo객체로 받는것
			//3) Map객체로 받는것
			//4) @RequestParam이용, 변수명, name값 매칭선언
		//2. 파라미터를 DB저장 요청
		
		//비밀번호를 암호화 해 보자!!
		//매번 암호화할때마다 다르지만 맨 앞의 알고리즘과 구분자를 통해서 알아서 비교도 가능하다!
		m.setPassword( pwEncoder.encode(m.getPassword()) );
		
		logger.debug(m.getPassword());
		
		int result = service.inserMember(m);
		
		//msg.jsp이용하여 처리해보자.
		String msg = "";
		String loc = "/";
		if(result > 0) msg="회원가입완료!";
		else msg="회원가입실패!";
		
		model.addAttribute("msg",msg);
		model.addAttribute("loc",loc);
		
		// return "redirect:/"; 
		// "redirect:/" : redirect방식으로 메인화면으로 이동
		return "common/msg";
	}
	
	@RequestMapping("/member/memberLogin.do")
	public String login(Member m , Model model) {
		Member result = service.selectMemberOne(m);
		
		String msg = "";
		String loc = "/";
		
		if(result!=null) {
			
			if(pwEncoder.matches(m.getPassword(), result.getPassword())) {
				msg = "로그인 성공!";
				logger.debug(result.toString());
//				session.setAttribute("loginMember", result);
				model.addAttribute("loginMember", result);
			}
			else {
				msg = "비밀번호가 틀렸습니다.";
			}
		}else {
			msg="로그인 실패! 다시 시도하세요.";
		}
		
		model.addAttribute("msg",msg);
		model.addAttribute("loc",loc);
		
		return "common/msg";
	}
	
	@RequestMapping("/member/memberLogout.do")
	public String logout(HttpSession session, SessionStatus s) {
		
		if(!s.isComplete()) {
			s.setComplete();//로그아웃 SessionAttributes에 있는 값을 지우는 것
			session.invalidate();
		}
		
		return "redirect:/";
	}
	
}
