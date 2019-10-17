package com.kh.spring.demo.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.kh.spring.demo.model.vo.Dev;

public interface DemoDao {
	int insertDemo(SqlSessionTemplate session, Dev dev);
	List<Dev> selectDevList(SqlSessionTemplate session);
	int deleteDemo(SqlSessionTemplate session, int devNo);
	Dev selectDev(SqlSessionTemplate session, int devNo);
	int updateDev(SqlSessionTemplate session, Dev dev);
}
