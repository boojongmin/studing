package com.school.bank_java;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.school.bank_java.command.AccountCommand;
import com.school.bank_java.command.Command;
import com.school.bank_java.command.LoginCommand;
import com.school.bank_java.factory.BankFactory;
import com.school.bank_java.factory.Factory;
import com.school.bank_java.login.LoginManager;
import com.school.bank_java.service.LoginService;
import com.school.bank_java.vo.UsersVo;

public class Main {

	public static void main(String[] args) throws IOException {
		Command command;
		Factory bankFactory = new BankFactory();
		Scanner sc = new Scanner(System.in);
		while(true){
			if(LoginManager.loginInfo.get("UsersVo") == null){
				command = new LoginCommand();
				command.execute(bankFactory);
			}else{
				command = new AccountCommand();
				command.execute(bankFactory);
			}
			//sc.close();
		}
		
	}
}