package com.school.bank_java;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.school.bank_java.vo.UsersVo;

public class Main {

	public static void main(String[] args) throws IOException {
		
		/*List<UsersVo> userList = sqlSession.selectList("bankMapper.selectAllUsers");

		System.out.println("UID \t| USERID\t| USERNAME ");
		for(int i=0; i < userList.size(); i++){
			UsersVo vo = userList.get(i);
			String print_str = String.format("%d\t| %s\t| %s", 
							vo.getUid(), 
							vo.getUserid(), 
							vo.getUsername());
			System.out.println(print_str);
		}*/
		
		Scanner sc = new Scanner(System.in);
		while(true){
			if(Login.loginInfo.get("UsersVo") == null){
				System.out.println("==========================================================================");
				System.out.println("커맨드를 입력해주세요");
				System.out.println("login : 로그인");
				System.out.println("exit : 프로그램 종료");
				System.out.println("==========================================================================");
				System.out.println(":");
				if(sc.hasNextLine()){
					String command = sc.nextLine();
					if("login".equals(command)){
						Login login = new Login();
						login.doLogin();
						//break;
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
			}else{
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
						Account account = new Account();
						account.viewMyAccount();
					}else if("new".equals(command)){
						Account account = new Account();
						account.createAccount();
					}else if("save".equals(command)){
						Account account = new Account();
						account.saveAmount();
					}else if("withdrawal".equals(command)){
						Account account = new Account();
						account.saveAmount();
					}else if("send".equals(command)){
						Account account = new Account();
						account.sendAmount();
					}else if("logout".equals(command)){
						Login.loginInfo.remove("UsersVo");
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
			//sc.close();
		}
		
	}
}
