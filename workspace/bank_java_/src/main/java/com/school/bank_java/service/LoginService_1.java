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
import com.school.bank_java.vo.UsersVo;

public class LoginService_1 extends Service {
	private LoginDao loginDao;
	
	public LoginService_1(SqlSessionFactory sqlSessionFactory){
		super(sqlSessionFactory);
	}
	
	public UsersVo doLogin(String userid){
		//SqlSession sqlSession = sqlSessionFactory.openSession(true);
		loginDao = new LoginDao(sqlSession);
		UsersVo usersVo = loginDao.doLogin(userid);
		//sqlSession.close();
		return usersVo;
		
	}
}
