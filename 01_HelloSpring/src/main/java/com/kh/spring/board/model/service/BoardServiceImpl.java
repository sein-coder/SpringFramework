package com.kh.spring.board.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.board.model.dao.BoardDao;
import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private SqlSessionTemplate session;
	
	@Autowired
	private BoardDao dao;

	@Override
	public List<Board> selectBoardList(int cPage, int numPerPage) {
		// TODO Auto-generated method stub
		return dao.selectBoardList(session,cPage,numPerPage);
	}

	@Override
	public int selectBoardCount() {
		// TODO Auto-generated method stub
		return dao.selectBoardCount(session);
	}

	@Override
	public int insertBoard(Board board, List<Attachment> list) {
		int result = 0;
		result = dao.insertBoard(session, board);
		for(Attachment at : list) {
			at.setBoardNo(board.getBoardNo());
			result = dao.insertAttachment(session,at);
		}
		return result;
	}
	
	
	
}
