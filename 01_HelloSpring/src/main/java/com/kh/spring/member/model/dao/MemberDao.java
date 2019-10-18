package com.kh.spring.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;

import com.kh.spring.member.model.vo.Member;

public interface MemberDao {
	int inserMember(SqlSessionTemplate session, Member m);
	Member selectMemberOne(SqlSessionTemplate session, Member m);
}
