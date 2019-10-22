package com.kh.spring.board.model.service;

import java.util.List;

import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;

public interface BoardService {
	
	List<Board> selectBoardList(int cPage, int numPerPage);
	
	int selectBoardCount();
	
	int insertBoard(Board board, List<Attachment> list);
}
