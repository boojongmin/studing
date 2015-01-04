package com.school.bank_java.command;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;

import com.school.bank_java.login.LoginManager;
import com.school.bank_java.service.AccountService;

public class AccountCommand implements Command { 

	@Override
	public void execute(ApplicationContext ctx) {
		AccountService service 
			= (AccountService)ctx.getBean(AccountService.class);
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("==========================================================================");
		System.out.println("커맨드를 선택하세요");
		System.out.println("view : 내 계좌보기");
		System.out.println("new : 신규계좌");
		System.out.println("save : 입금");
		System.out.println("withdrawal : 출금");
		System.out.println("send : 계좌 이체");
		System.out.println("logout : 로그아웃");
		System.out.println("exit : 시스템 종료");
		System.out.println("==========================================================================");
		System.out.println(":");
		if(sc.hasNextLine()){
			String command = sc.nextLine();
			if("view".equals(command)){
				service.viewMyAccount();
			}else if("new".equals(command)){
				service.createAccount();
			}else if("save".equals(command)){
				service.saveAmount();
			}else if("withdrawal".equals(command)){
				service.saveAmount();
			}else if("send".equals(command)){
				service.sendAmount();
			}else if("logout".equals(command)){
				LoginManager.loginInfo.remove("UsersVo"); 
			}else if("exit".equals(command)){
				System.out.println("==========================================================================");
				System.out.println("프로그램을 종료합니다.");
				System.out.println("==========================================================================");
				System.exit(0);
			}else{
				System.out.println("==========================================================================");
				System.out.println("커맨드를 잘못입력하셨습니다.");
				System.out.println("==========================================================================");
			}
		}
	}

}
