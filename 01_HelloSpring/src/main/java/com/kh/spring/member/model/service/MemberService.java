package com.kh.spring.member.model.service;

import com.kh.spring.member.model.vo.Member;

public interface MemberService {
	int inserMember(Member m);
	Member selectMemberOne(Member m);
}
