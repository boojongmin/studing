package com.school.bank_java;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.school.bank_java.exception.BankException;
import com.school.bank_java.vo.AccountsVo;
import com.school.bank_java.vo.UsersVo;

public class Account {
	
	public void createAccount(){
		String resource = "com/school/bank_java/resource/conf/mybatis-config.xml";
		InputStream inputStream = null;
		SqlSessionFactory sqlSessionFactory = null;
		SqlSession sqlSession = null;
		try{
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			sqlSession = sqlSessionFactory.openSession(true);
			
			//Map param = new HashMap();
			AccountsVo accountsVo = new AccountsVo();
			UsersVo usersVo = (UsersVo)Login.loginInfo.get("UsersVo");
			
			accountsVo.setAmount(0);
			accountsVo.setUsers_uid(usersVo.getUid());
			
			/* 
			System.out.println("계좌번호를 입력해주세요");
			while(true){
				Scanner sc = new Scanner(System.in);
				String command = sc.nextLine();
				
				if(command.length() > 11 && command.length() != 0){
					System.out.println("10자 이내로 입력해주세요 : ");
					continue;
				}else{
					accountsVo.setAccount_number(command);
					break;
				}
			}*/
			String account_number = sqlSession.selectOne("bankMapper.selectMaxAccount", usersVo.getUid());
			
			int account_int = 1;
			String users_uid = "" + usersVo.getUid();
			if(account_number != null){ account_int = Integer.parseInt(account_number.replaceFirst(users_uid, "")) +1; }
			
			
			
			account_number = String.format("%05d%05d", usersVo.getUid(), account_int);
			accountsVo.setAccount_number(account_number); 
			int result_count = sqlSession.insert("bankMapper.insertAccount", accountsVo);
			if(result_count != 1){
				throw new BankException("계좌정보 생성중 오류가 발생했습니다."); 
			}else{
				System.out.println("계좌가 생성되었습니다.");
			}
//			sqlSession.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(sqlSession != null) sqlSession.close();
			if(inputStream != null)
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	public void viewMyAccount() {
		UsersVo usersVo = (UsersVo)Login.loginInfo.get("UsersVo");
		String resource = "com/school/bank_java/resource/conf/mybatis-config.xml";
		InputStream inputStream = null;
		SqlSessionFactory sqlSessionFactory = null;
		SqlSession sqlSession = null;
		try{
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			sqlSession = sqlSessionFactory.openSession();

			
			List<AccountsVo> list = sqlSession.selectList("bankMapper.selectUsersAccount", usersVo.getUid());
			int i=0;
			System.out.println("==========================================================================");
			System.out.println("내 계좌 정보");
			for(AccountsVo vo : list){
				System.out.println(String.format("%d.\t%s\t%d ", ++i, vo.getAccount_number(), vo.getAmount()));
			}
			System.out.println("==========================================================================");
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(sqlSession != null) sqlSession.close();
			if(inputStream != null)
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
	}

	public void saveAmount() {
		UsersVo usersVo = (UsersVo)Login.loginInfo.get("UsersVo");
		String resource = "com/school/bank_java/resource/conf/mybatis-config.xml";
		InputStream inputStream = null;
		SqlSessionFactory sqlSessionFactory = null;
		SqlSession sqlSession = null;
		try{
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			sqlSession = sqlSessionFactory.openSession(true);
			
			AccountsVo accountsVo = new AccountsVo();
			accountsVo.setUsers_uid(usersVo.getUid());
			
			Scanner sc = new Scanner(System.in);
			while(true){
				System.out.println("==========================================================================");
				System.out.println("본인의 계좌번호를 입력해주세요");
				System.out.println("==========================================================================");
				System.out.println("(취소 back):");
				if(sc.hasNextLine()){
					String command = sc.nextLine();
					if("back".equals(command)){
						break;
					}
					accountsVo.setAccount_number(command);
					int myAccountCount = sqlSession.selectOne("bankMapper.existAccount", accountsVo);
					if(myAccountCount == 0 ){
						System.out.println("[error] 계좌번호를 잘못 입력했습니다.(본인의 계좌만 입금 가능합니다.)");
						continue;
					}					
					if(sc.hasNextLine()){
						System.out.println("==========================================================================");
						System.out.println("입금금액을 입력해주세요");
						System.out.println("==========================================================================");
						System.out.println("(취소 0):");
						command = sc.nextLine();
						if("0".equals(command)){
							System.out.println("[message] 취소되었습니다.");
							break;
						}
						
						try{
							accountsVo.setAmount(Integer.parseInt(command));
							if(accountsVo.getAmount() < 0){ throw new Exception(); }
						}catch(Exception e){
							System.out.println("[error] 금액을 잘못 입력하셨습니다. (양수만 입력 가능합니다.)");
							System.out.println("[message] 입금처리를 취소합니다..");
							continue;
						}
						
						sqlSession.insert("bankMapper.insertAmount", accountsVo);
						System.out.println("[message] 입급이 완료되었습니다.");
						break;
					}
				}
			
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(sqlSession != null) sqlSession.close();
			if(inputStream != null)
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	public void sendAmount() {
		UsersVo usersVo = (UsersVo)Login.loginInfo.get("UsersVo");
		String resource = "com/school/bank_java/resource/conf/mybatis-config.xml";
		InputStream inputStream = null;
		SqlSessionFactory sqlSessionFactory = null;
		SqlSession sqlSession = null;
		try{
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			sqlSession = sqlSessionFactory.openSession(true);
			
			AccountsVo accountsVo = new AccountsVo();
			accountsVo.setUsers_uid(usersVo.getUid());
			
			Scanner sc = new Scanner(System.in);
			while(true){
				System.out.println("==========================================================================");
				System.out.println("본인의 계좌번호를 입력해주세요");
				System.out.println("==========================================================================");
				System.out.println("(취소 back):");
				if(sc.hasNextLine()){
					String command = sc.nextLine();
					if("back".equals(command)){
						break;
					}
					accountsVo.setAccount_number(command);
					int myAccountCount = sqlSession.selectOne("bankMapper.existAccount", accountsVo);
					if(myAccountCount == 0 ){
						System.out.println("[error] 계좌번호를 잘못 입력했습니다.(본인의 계좌만 입금 가능합니다.)");
						continue;
					}					
					int totalAmount = sqlSession.selectOne("bankMapper.selectTotalAmount", accountsVo.getAccount_number());
					System.out.println("==========================================================================");
					System.out.println(String.format("이체 금액을 입력해주세요 [이제가능 금액 : %d]", totalAmount));
					System.out.println("==========================================================================");
					if(sc.hasNextLine()){
						System.out.println("(취소 0):");
						command = sc.nextLine();
						if("0".equals(command)){
							System.out.println("[message] 취소되었습니다.");
							break;
						}
						
						try{
							accountsVo.setAmount(Integer.parseInt(command));
							if(accountsVo.getAmount() < 0){ throw new Exception(); }
						}catch(Exception e){
							System.out.println("[error] 금액을 잘못 입력하셨습니다. (양수만 입력 가능합니다.)");
							System.out.println("[message] 이체 처리를 취소합니다.");
							continue;
						}
						
						if(totalAmount - accountsVo.getAmount() < 0){
							System.out.println("[error] 계좌의 잔액 이상 이체를 할 수 없습니다.");
							System.out.println("[message] 이체 처리를 취소합니다.");
							continue;
						}
						
						System.out.println("==========================================================================");
						System.out.println("이체할 계좌번호를 입력해주세요");
						System.out.println("==========================================================================");
						if(sc.hasNextLine()){
							AccountsVo sendAccountVo = new AccountsVo();
							command = sc.nextLine();
							sendAccountVo.setAccount_number(command);
							if(accountsVo.getAccount_number().equals(command)){
								System.out.println("[erorr] 이체대상과 이체할 계좌의 계좌번호가 일치합니다. 더이상 진행하지 않습니다.");
								continue;
							}
							int sendAccountCount = sqlSession.selectOne("bankMapper.existAccount", sendAccountVo);
							if(sendAccountCount == 0 ){
								System.out.println("[error] 입금할 계좌번호가 존재하지 않습니다.");
								continue;
							}
							sendAccountVo.setAmount(accountsVo.getAmount());
							sqlSession.insert("bankMapper.insertAmount", sendAccountVo);
							accountsVo.setAmount(accountsVo.getAmount() * -1);
							sqlSession.insert("bankMapper.insertAmount", accountsVo);
							System.out.println("[message] 이제가이 완료되었습니다.");
							break;
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(sqlSession != null) sqlSession.close();
			if(inputStream != null)
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
