package com.java_study.spring_boot.vo;

import javax.validation.constraints.Size;

public class UsersVo {
	private int uid;
	private String userid;
	
//	@NotBlank(message="필수 입력항목입니다.")
	@Size(min=5, max=20, message="5~20 이내로 입력해주세요")
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
