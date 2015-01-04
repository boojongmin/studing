package com.school.bank_java.dao;

import org.apache.ibatis.session.SqlSession;

import com.school.bank_java.vo.UsersVo;

public class LoginDao {
	private SqlSession sqlSession;
	public LoginDao(SqlSession sqlSession){
		this.sqlSession = sqlSession;
	}
	
	public UsersVo doLogin(String userid){
		return sqlSession
				.selectOne("bankMapper.selectUserByUserid", userid);
	}

}
