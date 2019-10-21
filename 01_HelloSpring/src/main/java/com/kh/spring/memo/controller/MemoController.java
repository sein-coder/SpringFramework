package com.kh.spring.memo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring.memo.model.service.MemoServiceImpl;
import com.kh.spring.memo.model.vo.Memo;

@Controller
public class MemoController {
	
	@Autowired
	private MemoServiceImpl service;
	
	@RequestMapping("/memo/memo.do")
	public String memoList(Model model) {
		model.addAttribute("list",service.selectMemoList());
		return "memo/memo";
	}
	
	@RequestMapping("/memo/insertMemo.do")
	public String insertMemo(Memo memo, Model model) {
		int result = service.insertMemo(memo);
		String msg = "";
		String loc = "/memo/memo.do";
		
		msg = result>0?"메모 작성 성공":"메모 작성 실패";
		
		model.addAttribute("msg",msg);
		model.addAttribute("loc",loc);
		return "common/msg";
	}
	
	@RequestMapping("/memo/deleteMemo.do")
	public String deleteMemo(Memo memo) {
		int result = service.deleteMemo(memo);
		String msg = "";
		String loc = "/memo/memo.do";
		if(result > 0) msg = "메모 삭제 성공!";
		else msg = "메모 삭제 실패";
		
		return "common/msg";
	}
	
}
