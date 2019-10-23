package com.kh.spring.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	
	
	@Override
	public List<Attachment> selectAttach(SqlSessionTemplate session, int boardNo) {
		// TODO Auto-generated method stub
		return session.selectList("board.selectAttach",boardNo);
	}

	@Override
	public Board selectBoard(SqlSessionTemplate session, int boardNo) {
		// TODO Auto-generated method stub
		return session.selectOne("board.selectBoard",boardNo);
	}

	@Override
	public List<Board> selectBoardList(SqlSessionTemplate session, int cPage, int numPerPage) {
		RowBounds r = new RowBounds((cPage-1)*numPerPage, numPerPage);
		// TODO Auto-generated method stub
		return session.selectList("board.selectBoardList", null, r);
	}

	@Override
	public int selectBoardCount(SqlSessionTemplate session) {
		// TODO Auto-generated method stub
		return session.selectOne("board.selectCount");
	}

	@Override
	public int insertBoard(SqlSessionTemplate session, Board board) {
		// TODO Auto-generated method stub
		return session.insert("board.insertBoard", board);
	}

	@Override
	public int insertAttachment(SqlSessionTemplate session, Attachment attach) {
		// TODO Auto-generated method stub
		return session.insert("board.insertAttachment",attach);
	}
	
	
	
}
