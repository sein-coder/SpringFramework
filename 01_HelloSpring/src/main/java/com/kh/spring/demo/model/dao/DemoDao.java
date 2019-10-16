package com.kh.spring.demo.model.dao;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import com.kh.spring.demo.model.vo.Dev;

public interface DemoDao {
	int insertDemo(SqlSessionTemplate session, Dev dev);
}
