package com.kh.spring.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.member.model.vo.Member;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Override
	public int memberEnroll(SqlSessionTemplate session, Member m) {
		// TODO Auto-generated method stub
		return session.insert("member.memberEnroll",m);
	}
	
}
