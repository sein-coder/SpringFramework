package com.kh.spring.member.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dao.MemberDao;
import com.kh.spring.member.model.vo.Member;

//service를 spring에 등록하는 방법
//1.어노테이션방법
//2.servlet-context.xml에 bean으로 등록하는 방법
@Service
public class MemberServiceImpl implements MemberService {
	//session관리 (Connection이랑 같은 것)
	//connection생성, connection.close(), 트랜젝션 처리(commit,rollback)
	
	@Autowired
	private MemberDao dao;
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public int inserMember(Member m) {
		// TODO Auto-generated method stub
		return dao.inserMember(session, m);
	}

	@Override
	public Member selectMemberOne(Member m) {
		// TODO Auto-generated method stub
		return dao.selectMemberOne(session,m);
	}

	
}
