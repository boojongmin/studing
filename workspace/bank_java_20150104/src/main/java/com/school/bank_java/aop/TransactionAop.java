package com.school.bank_java.aop;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.JoinPoint;

import com.school.bank_java.service.Service;

public class TransactionAop {
	SqlSessionFactory sqlSessionFactory;
	SqlSession sqlSession;
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public void beforeTransactionMethod(JoinPoint jp){
		//System.out.println("beforeTransactionMethod 실행");
		Service service = (Service)jp.getTarget();
		sqlSession = sqlSessionFactory.openSession(false);
		//sevirce.setSqlSession(sqlSession);
		service.sqlSession = sqlSession;
		
	}
	
	public void afterReturningTransactionMethod(JoinPoint jp,
												Object retVal){
		//System.out.println("afterReturningTransactionMethod 실행");
		sqlSession.commit();
		sqlSession.close();
	}
	
	public void afterThrowingTransactionMethod(JoinPoint jp,
												Exception ex){
		//System.out.println("afterThrowingTransactionMethod 실행");
		sqlSession.rollback();
		sqlSession.close();
		System.out.println(ex.getMessage());
	}
	

}
