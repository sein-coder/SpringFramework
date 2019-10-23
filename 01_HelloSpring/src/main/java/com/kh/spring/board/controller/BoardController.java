package com.kh.spring.board.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public ModelAndView insertBoard(Board b, @RequestParam(value = "upFile", required=false) MultipartFile[] upFile, HttpServletRequest req) {
		//@RequestParam을 지정해서 파일이 아예 안올라와서 null일 경우도 처리하기 위해 value속성과 required속성을 등록한다!
		//value속성은 view에서 데이터를 받아올 input태그의 name을 써주면 되고, required속성은 null값을 허용하고 싶을 경우 false를 null을 허용하지 않고 필수일 경우 true(default값이 true)를 준다 
		//스프링에서 업로드된 파일을 받아오는 방법은 MultipartFile객체를 이용해서 이름만 맞춰주면 알아서 가져온다.
		ModelAndView mv = new ModelAndView();
		logger.debug("board객체 : "+b);
		logger.debug("업로드 된 파일 : "+upFile[0].getOriginalFilename());
		logger.debug("업로드 된 파일 : "+upFile[1].getOriginalFilename());
		//입력된 파일 서버에 저장하기
		//1.파일 저장 경로
		
		//spring파일저장템플릿 만들기 : saveDir, upFile 두개를 매개변수로 받으면 될듯? 반환형은 list 
		
		String saveDir = req.getSession().getServletContext().getRealPath("/resources/upload/board"); //루트 디렉토리부터 시작해야한다.
		List<Attachment> list = new ArrayList(); //다중파일을 저장하는 객체, 파일이름같은 것들을 저장 (list형 추천!)
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
				String reName ="SEIN_"+sdf.format(System.currentTimeMillis())+"_"+rnd+ext;
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
		
		try {
			service.insertBoard(b,list);
		}catch(Exception e) {
			//파일삭제로직.
			//에러메세지출력로직!
			logger.debug("에러에러에러에러 삽입안됨!!!!");
			//파일 삭제
//			if(!list.isEmpty()) {
//				for(Attachment a : list) {
//					File deleteFile = new File(saveDir+"/"+a.getRenamedFileName());
//					deleteFile.delete();
//				}
//			}
		}
		
		mv.setViewName("redirect:/board/boardList"); //redirect를 통해 servlet으로 보내서 처리 (바로 view로 가는게 아님)
		return mv;
	}
	
	
	@RequestMapping("/board/boardView")
	public ModelAndView boardView(int boardNo) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("board", service.selectBoard(boardNo));
		mv.addObject("attach", service.selectAttach(boardNo));
		mv.setViewName("board/boardView");
		
		return mv;
	}
	
	@RequestMapping("/board/filedownLoad.do")
	public void fileDownLoad(String oName, String rName, HttpServletRequest req, HttpServletResponse res) {
		//파일 입출력을 위한 Stream을 선언
		BufferedInputStream bis = null;
		ServletOutputStream sos = null;
		
		//파일을 가져올 경로 == 파일을 저장한 경로와 동일!!
		String saveDir = req.getSession().getServletContext().getRealPath("/resources/upload/board");
		
		File downFile = new File(saveDir+"/"+rName);
		try {
			FileInputStream fis = new FileInputStream(downFile);
			bis = new BufferedInputStream(fis);
			sos = res.getOutputStream();
			String resFileName = "";
			
			//브라우저에 맞춰서 전송할 파일명 인코딩
			
			boolean isMSIE = req.getHeader("user-agent").indexOf("MSIE")!=-1 | req.getHeader("user-agent").indexOf("Trident")!=-1;
			if(isMSIE) {
				resFileName = URLEncoder.encode(oName,"UTF-8");
				resFileName = resFileName.replaceAll("\\+", "%20");  //%20은 띄어쓰기를 의미
				//ms exploer에서는 띄어쓰기를 \\+로 표시하기때문에 표준에 맞게 %20으로 replaceAll메소드를 통해 전부 변경한다.
			}else {
				resFileName = new String(oName.getBytes("UTF-8"),"ISO-8859-1");
			}
			res.setContentType("application/octet-stream;charset=utf-8");
			res.addHeader("Content-Disposition", "attachment;filename=\""+resFileName+"\"");
			res.setContentLength((int)downFile.length());
			int read = 0;
			while((read=bis.read())!=-1) {
				sos.write(read);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				sos.close();
				bis.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
