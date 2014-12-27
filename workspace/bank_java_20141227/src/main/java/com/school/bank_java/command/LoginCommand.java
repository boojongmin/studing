package com.school.bank_java.command;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;

import com.school.bank_java.exception.BankException;
import com.school.bank_java.login.LoginManager;
import com.school.bank_java.service.LoginService;
import com.school.bank_java.vo.UsersVo;

public class LoginCommand implements Command{

	@Override
	public void execute(ApplicationContext ctx) {
		LoginService loginService = (LoginService) ctx.getBean(LoginService.class);
		
		Scanner sc = new Scanner(System.in);
		System.out.println("==========================================================================");
		System.out.println("커맨드를 입력해주세요");
		System.out.println("login : 로그인");
		System.out.println("exit : 프로그램 종료");
		System.out.println("==========================================================================");
		System.out.println(":");
		if(sc.hasNextLine()){
			String command = sc.nextLine();
			if("login".equals(command)){
				while(true){
					System.out.println("==========================================================================");		
					System.out.println("로그인할 아이디를 입력해주세요 : ");
					System.out.println("back: 이전 메뉴로 이동");
					System.out.println("==========================================================================");
					System.out.println(":");
					if(sc.hasNextLine()){
						try{
							command = sc.nextLine();
							if("back".equals(command)){
								System.out.println("메인 메뉴로 이동합니다.");
								break;
							}					
							
							UsersVo vo = loginService.doLogin(command);
							if(vo == null){
								System.out.println("==========================================================================");
								System.out.println("아이디가 없습니다.");
								System.out.println("back: 이전 메뉴로 이동");
								System.out.println("==========================================================================");
							}else{
								LoginManager.loginInfo.put("UsersVo", vo);
								System.out.println("==========================================================================");
								System.out.println("로그인에 성공했습니다.");
								System.out.println(vo.getUsername() +   "님 환영합니다.");
								System.out.println("메인 메뉴로 이동합니다.");
								System.out.println("==========================================================================");
								break;
							}
						}catch(Exception e){
							e.printStackTrace();
							throw new BankException("로그인 오류");
						}
					}
					//sc.close();
				}
			}else if("exit".equals(command)){
				System.out.println("==========================================================================");
				System.out.println("프로그램을 종료합니다.");
				System.out.println("==========================================================================");
				System.exit(0);
			}else{
				System.out.println("==========================================================================");
				System.out.println("커맨드를 잘못 입력하셨습니다.");
				System.out.println("==========================================================================");
			}
		}
	}
}