package com.kh.spring.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@RequestMapping("/member/memberEnroll.do")
	public String memberEnroll() {
		return "member/memberEnroll";
	}
	
	@RequestMapping("/member/memberEnrollEnd.do")
	public String memberEnrollEnd(Member m, Model model) {
		
		System.out.println(m);
		
		int result = service.memberEnroll(m);
		
		return "redirect:/index.jsp";
	}
	
}
