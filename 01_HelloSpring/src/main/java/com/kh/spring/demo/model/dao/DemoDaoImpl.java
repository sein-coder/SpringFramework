package com.kh.spring.demo.model.dao;

import java.util.List;

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

	@Override
	public List<Dev> selectDevList(SqlSessionTemplate session) {
		// TODO Auto-generated method stub
		return session.selectList("dev.selectDevList");
	}

	@Override
	public int deleteDemo(SqlSessionTemplate session, int devNo) {
		// TODO Auto-generated method stub
		return session.delete("dev.deleteDemo", devNo);
	}

	@Override
	public Dev selectDev(SqlSessionTemplate session, int devNo) {
		// TODO Auto-generated method stub
		return session.selectOne("dev.selectDev", devNo);
	}

	@Override
	public int updateDev(SqlSessionTemplate session, Dev dev) {
		// TODO Auto-generated method stub
		return session.update("dev.updateDev", dev);
	}
	
	
	
}
