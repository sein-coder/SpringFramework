package com.kh.spring.demo.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.demo.model.dao.DemoDao;
import com.kh.spring.demo.model.vo.Dev;
//어노테이션 방식으로 Service객체임을 표시
//이를 통해 DemoService인터페이스 생성시 아래 객체를 생성해서 넣어준다.
@Service
public class DemoServiceImpl implements DemoService {

	@Autowired
	private DemoDao dao;
	//SqlSessionTemplate는 이미 Spring에 만들어져있다.
	@Autowired
	private SqlSessionTemplate session;
	@Override
	public int insertDemo(Dev dev) {
		return dao.insertDemo(session, dev);
	}
	
	
	
}
