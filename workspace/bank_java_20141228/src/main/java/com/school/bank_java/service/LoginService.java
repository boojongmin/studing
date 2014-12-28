package com.school.bank_java.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.school.bank_java.dao.LoginDao;
import com.school.bank_java.exception.BankException;
import com.school.bank_java.login.LoginManager;
import com.school.bank_java.vo.UsersVo;

public class LoginService {
	private SqlSessionFactory sqlSessionFactory;
	
	public LoginService(SqlSessionFactory sqlSessionFactory){
		this.sqlSessionFactory = sqlSessionFactory;
	}
	public UsersVo doLogin(String userid){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		LoginDao dao = new LoginDao(sqlSession);
		
		UsersVo vo = dao.doLogin(userid);
		sqlSession.close();
		return vo;
	}
}
