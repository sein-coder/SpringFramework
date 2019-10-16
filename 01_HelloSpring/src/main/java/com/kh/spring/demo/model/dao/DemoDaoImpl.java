package com.kh.spring.demo.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.demo.model.vo.Dev;

@Repository
public class DemoDaoImpl implements DemoDao {

	//별도의 멤버변수가 없고 바로 Mybatis를 통해서 db를 호출한다.
	@Override
	public int insertDemo(SqlSessionTemplate session, Dev dev) {
		// TODO Auto-generated method stub
		return session.insert("dev.insertDemo",dev);
	}
	
	
	
}
