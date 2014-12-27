package com.school.bank_java.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.school.bank_java.exception.BankException;
import com.school.bank_java.login.LoginManager;
import com.school.bank_java.vo.AccountsVo;
import com.school.bank_java.vo.UsersVo;

public class AccountDao {
	SqlSession sqlSession;
	
	public AccountDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public String selectMaxAccount(int uid) {
		return sqlSession.selectOne("bankMapper.selectMaxAccount", uid);
	}

	public int insertAccount(AccountsVo accountsVo) {
		return sqlSession.insert("bankMapper.insertAccount", accountsVo);
	}

	public List<AccountsVo> selectUsersAccount(int uid) {
		return sqlSession.selectList("bankMapper.selectUsersAccount", uid);
	}

	public int existAccount(AccountsVo accountsVo) {
		return sqlSession.selectOne("bankMapper.existAccount", accountsVo);
	}

	public void insertAmount(AccountsVo accountsVo) {
		sqlSession.insert("bankMapper.insertAmount", accountsVo);
	}

	public int selectTotalAmount(String account_number) {
		return sqlSession.selectOne("bankMapper.selectTotalAmount", account_number);
	}
}
