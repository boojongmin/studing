package com.school.bank_java.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BankSqlSessionFactory {
	private SqlSessionFactory sqlSessionFactory ;
	
	public BankSqlSessionFactory(){
		String resource = "com/school/bank_java/resource/conf/mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	public SqlSession openSession(boolean autoCommit){
		return sqlSessionFactory.openSession(autoCommit);
	}
	
	public void close(SqlSession sqlSession){
		if(sqlSession != null){
			sqlSession.close();
		}
	}
}
