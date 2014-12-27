package com.school.bank_java.aop;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.JoinPoint;

import com.school.bank_java.service.Service;

public class TransactionAop {
	
	private SqlSessionFactory sqlSessionFactory;
	private SqlSession sqlSession;
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	
	
	public void beforeMonitor(JoinPoint thisJoinPoint){
		System.out.println("sqlSession.open()");
		Service service = (Service)thisJoinPoint.getTarget();
		this.sqlSession = this.sqlSessionFactory.openSession(false);
		service.sqlSession = this.sqlSession ;
	}
	
	public void afterReturningMonitor(JoinPoint thisJoinPoint){
		System.out.println("sqlSession.commit()");
		this.sqlSession.commit();
		this.sqlSession.close();
	}
	
	public void afterThrowingMonitor(JoinPoint thisJoinPoint, Exception exception){
		System.out.println("sqlSession.rollback()");
		this.sqlSession.rollback();
		this.sqlSession.close();
	}
}
