package com.school.bank_web.vo;

public class AccountsVo {
	private int uid;
	private int users_uid; 
	private String account_number; 
	private int amount;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getUsers_uid() {
		return users_uid;
	}
	public void setUsers_uid(int users_uid) {
		this.users_uid = users_uid;
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
