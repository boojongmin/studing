package com.school.bank_java.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.school.bank_java.vo.AccountsVo;
import com.school.bank_java.vo.UsersVo;

//@Component
@Repository
public class AccountDao {
	@Autowired private SqlSession sqlSession;
	
	public List<AccountsVo> selectUsersAccount(int users_uid){
		return sqlSession.selectList("bankMapper.selectUsersAccount", 
					users_uid);
	}

	public int existAccount(AccountsVo accountsVo) {
		return sqlSession.selectOne("bankMapper.existAccount", accountsVo);
	}

	public void insertAmount(AccountsVo accountsVo) {
		sqlSession.insert("bankMapper.insertAmount", accountsVo);
	}

	public String selectMaxAccount(int uid) {
		return sqlSession.selectOne("bankMapper.selectMaxAccount", uid);
	}

	public int insertAccount(AccountsVo accountsVo) {
		return sqlSession.insert("bankMapper.insertAccount", accountsVo);
	}

	public int selectTotalAmount(String account_number) {
		return 
		 sqlSession.selectOne("bankMapper.selectTotalAmount", account_number);
	}

}
