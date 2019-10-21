package com.kh.spring.memo.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.memo.model.dao.MemoDao;
import com.kh.spring.memo.model.vo.Memo;

@Service
public class MemoServiceImpl implements MemoService {
	
	@Autowired
	private SqlSessionTemplate session;
	
	@Autowired
	private MemoDao dao;

	@Override
	public List<Memo> selectMemoList() {
		// TODO Auto-generated method stub
		return dao.selectMemoList(session);
	}

	@Override
	public int insertMemo(Memo memo) {
		// TODO Auto-generated method stub
		return dao.insertMemo(session, memo);
	}

	@Override
	public int deleteMemo(Memo memo) {
		// TODO Auto-generated method stub
		return dao.deleteMemo(session,memo);
	}
	
	
	
	
}
