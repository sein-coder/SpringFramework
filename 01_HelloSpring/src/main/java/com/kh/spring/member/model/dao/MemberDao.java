package com.kh.spring.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;

import com.kh.spring.member.model.vo.Member;

public interface MemberDao {
	int memberEnroll(SqlSessionTemplate session, Member m);
}
