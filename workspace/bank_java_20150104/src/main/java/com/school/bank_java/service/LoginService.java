package com.school.bank_java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.school.bank_java.dao.LoginDao;
import com.school.bank_java.vo.UsersVo;

@Service
public class LoginService{
	@Autowired private LoginDao dao;

	public UsersVo doLogin(String userid){
		UsersVo vo = dao.doLogin(userid);
		return vo;
	}
	
}
