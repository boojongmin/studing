package com.school.bank_java;

import java.io.IOException;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.school.bank_java.command.AccountCommand;
import com.school.bank_java.command.Command;
import com.school.bank_java.command.LoginCommand;
import com.school.bank_java.config.AppConfig;
import com.school.bank_java.exception.BankException;
import com.school.bank_java.login.LoginManager;

public class Main {

	public static void main(String[] args) throws IOException {
		Command command;
		//ApplicationContext ctx = new FileSystemXmlApplicationContext(new String[]{"classpath:com/school/bank_java/resource/spring/appContext.xml", "classpath:com/school/bank_java/resource/spring/aop.xml"});
		//ApplicationContext ctx = new FileSystemXmlApplicationContext(new String[]{"classpath:com/school/bank_java/resource/spring/appContext.xml", /*"classpath:com/school/bank_java/resource/spring/aop.xml",*/ "classpath:com/school/bank_java/resource/spring/databaseContext.xml"});
		//ApplicationContext ctx = new FileSystemXmlApplicationContext(new String[]{"classpath:com/school/bank_java/resource/spring/annotation/appContext.xml"});
//		ApplicationContext ctx = new AnnotationConfigApplicationContext();
//		ctx.register(AppConfig.class);
//		ctx.refresh();
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		Scanner sc = new Scanner(System.in);
		while(true){
			try{
				if(LoginManager.loginInfo.get("UsersVo") == null){
					command = new LoginCommand();
					command.execute(ctx);
				}else{
					command = new AccountCommand();
					command.execute(ctx);
				}
			}catch(BankException e){
				System.out.println("[error]" + e.getMessage());
			}
			//sc.close();
		}
		
	}
}