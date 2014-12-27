package com.school.bank_java.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class Service {
	public SqlSessionFactory sqlSessionFactory;
	public SqlSession sqlSession;
	
	public Service(SqlSessionFactory sqlSessionFactory){
		this.sqlSessionFactory = sqlSessionFactory;
	}

}
