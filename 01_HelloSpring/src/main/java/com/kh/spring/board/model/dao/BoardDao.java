package com.kh.spring.board.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;

public interface BoardDao {
	
	List<Board> selectBoardList(SqlSessionTemplate session, int cPage, int numPerPage);
	
	int selectBoardCount(SqlSessionTemplate session);
	
	int insertBoard(SqlSessionTemplate session, Board board);
	int insertAttachment(SqlSessionTemplate session, Attachment attach);
}
