package com.school.bank_java.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.school.bank_java.vo.UsersVo;


//@Component
@Repository
public class LoginDao {
	@Autowired private SqlSession sqlSession;
	 
//	 @Autowired
//	 public LoginDao(SqlSession sqlSession){
//		 this.sqlSession = sqlSession;
//	 }
	
	public UsersVo doLogin(String userid){
		return sqlSession.selectOne("bankMapper.selectUserByUserid", userid);
	}
}
