package com.school.bank_web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.bank_web.dao.LoginDao;
import com.school.bank_web.vo.UsersVo;

@Service
public class LoginService{
	@Autowired private LoginDao dao;

	public UsersVo doLogin(String userid){
		UsersVo vo = dao.doLogin(userid);
		return vo;
	}
	
}
