package com.kh.spring.memo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring.memo.model.service.MemoService;
import com.kh.spring.memo.model.vo.Memo;

@Controller
public class MemoController {
	
	@Autowired
	private MemoService service; //자동완성할 때 조심 Impl아닌거로 해야함!
	
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
	
	//삭제는 관리자만이 가능하게 AOP를 이용해서 구현하시오!
	//AOP를 이용해서 around나 before를 통해 세션값의 아이디를 받아와서 해당 아이디가 admin일 경우에만 삭제가 되고
	//아닐 경우 에러페이지로 throw한다.
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
