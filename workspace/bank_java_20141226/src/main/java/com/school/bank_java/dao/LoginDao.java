package com.school.bank_java.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.school.bank_java.exception.BankException;
import com.school.bank_java.vo.UsersVo;

public class LoginDao {
	 private SqlSession sqlSession;
	 public LoginDao(SqlSession sqlSession){
		 this.sqlSession = sqlSession;
	 }
	
	public UsersVo doLogin(String userid){
		return sqlSession.selectOne("bankMapper.selectUserByUserid", userid);
	}
}
