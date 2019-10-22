package com.kh.spring.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.common.page.PageFactory;

@Controller
public class BoardController {
	
	private Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService service;
	
	@RequestMapping("/board/boardList")
	public ModelAndView boardList(@RequestParam(value = "cPage", required = false, defaultValue = "1")int cPage) {
		ModelAndView mv = new ModelAndView();
		int numPerPage = 5;
		int totalData = service.selectBoardCount();
		List<Board> list = service.selectBoardList(cPage, numPerPage);
	
		mv.addObject("list", list);
		mv.addObject("totalCount",totalData);
		mv.addObject("pageBar",PageFactory.getPageBar(totalData,cPage,numPerPage,"/spring/board/boardList"));
		
		mv.setViewName("board/boardList"); //modelandview객체도 리졸버를 거쳐서 가기 때문에 원래 쓰던데로 이름만 쓴다
		
		return mv;
	}
	
	@RequestMapping("/board/boardForm")
	public String boardForm(Board board) {
		return "board/boardForm";
	}
	
	// 스프링 파일 업로드 하기
	@RequestMapping("/board/boardFormEnd.do")
	public ModelAndView insertBoard(Board b, MultipartFile[] upFile, HttpServletRequest req) {
		//스프링에서 업로드된 파일을 받아오는 방법은 MultipartFile객체를 이용해서 이름만 맞춰주면 알아서 가져온다.
		ModelAndView mv = new ModelAndView();
		logger.debug("board객체 : "+b);
		logger.debug("업로드 된 파일 : "+upFile[0].getOriginalFilename());
		logger.debug("업로드 된 파일 : "+upFile[1].getOriginalFilename());
		//입력된 파일 서버에 저장하기
		//1.파일 저장 경로
		String saveDir = req.getSession().getServletContext().getRealPath("/resources/upload/board");
		List<Attachment> list = new ArrayList(); //다중파일을 저장하는 객체, 이름같은 것들을
		//저장경로가 없으면 생성하고 있으면 생성하지 않는 코드 작성
		File dir = new File(saveDir);
		if(!dir.exists()) logger.debug("폴더 생성"+dir.mkdirs()); //mkdirs() : 해당 경로에 속하는 모든 경로의 모든 폴더를 생성
		for(MultipartFile f : upFile) {
			if(!f.isEmpty()) {
				//파일명 설정(renamed)
				String oriFileName = f.getOriginalFilename();
				//파일명에서 확장자 빼기
				String ext = oriFileName.substring(oriFileName.lastIndexOf(".")); //ext는 확장자를 의미!
				//rename규칙 설정
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
				int rnd = (int)(Math.random()*1000);
				String reName =sdf.format(System.currentTimeMillis())+"_"+rnd+ext;
				//reName된 파일명으로 저장하기
				try {
					f.transferTo(new File(saveDir+"/"+reName));
				}catch(IOException e) {
					e.printStackTrace();
				}
				//서버에 실제 파일 저장 완료
				Attachment at = new Attachment();
				at.setOriginalFileName(oriFileName);
				at.setRenamedFileName(reName);
				list.add(at);
			}
		}
		service.insertBoard(b,list);
		
		mv.setViewName("board/boardList");
		return mv;
	}
	
}
