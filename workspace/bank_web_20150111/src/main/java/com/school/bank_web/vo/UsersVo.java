package com.school.bank_web.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UsersVo {
	private int uid;
	
	@NotEmpty
	@Length(min=5, max=20, message="아이디는 5~20자 범위내에 있어야합니다.")	
	private String userid;
	
	private String username;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	

}
