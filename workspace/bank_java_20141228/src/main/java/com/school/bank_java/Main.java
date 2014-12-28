package com.school.bank_java;

import java.io.IOException;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.school.bank_java.command.AccountCommand;
import com.school.bank_java.command.Command;
import com.school.bank_java.command.LoginCommand;
import com.school.bank_java.login.LoginManager;

public class Main {

	public static void main(String[] args) throws IOException {
		
		String[] path 
		= {
			"classpath:com/school/bank_java/resource/spring/appContext.xml",
			"classpath:com/school/bank_java/resource/spring/aop.xml"
		};
		
		
		ApplicationContext ctx 
			= new FileSystemXmlApplicationContext(path);
		
		Scanner sc = new Scanner(System.in);
		while(true){
			if(LoginManager.loginInfo.get("UsersVo") == null){
				Command command = new LoginCommand();
				command.execute(ctx);
			}else{
				Command command = new AccountCommand();
				command.execute(ctx); 
			}
		}
		
	}
}
