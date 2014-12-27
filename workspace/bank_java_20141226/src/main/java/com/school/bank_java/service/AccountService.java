package com.school.bank_java.service;

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

import com.school.bank_java.dao.AccountDao;
import com.school.bank_java.dao.LoginDao;
import com.school.bank_java.exception.BankException;
import com.school.bank_java.login.LoginManager;
import com.school.bank_java.vo.AccountsVo;
import com.school.bank_java.vo.UsersVo;

public class AccountService {
	
	private SqlSessionFactory sqlSessionFactory;
	public AccountService(SqlSessionFactory sqlSessionFactory){
		this.sqlSessionFactory = sqlSessionFactory;
	}
	private AccountDao accountDao;
	
	
	public void createAccount(){
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		accountDao = new AccountDao(sqlSession);
		
		AccountsVo accountsVo = new AccountsVo();
		UsersVo usersVo = (UsersVo)LoginManager.loginInfo.get("UsersVo");
		
		accountsVo.setAmount(0);
		accountsVo.setUsers_uid(usersVo.getUid());
		
		String account_number = accountDao.selectMaxAccount(usersVo.getUid());
		
		int account_int = 1;
		String users_uid = "" + usersVo.getUid();
		if(account_number != null){ account_int = Integer.parseInt(account_number.replaceFirst(users_uid, "")) +1; }
		
		account_number = String.format("%05d%05d", usersVo.getUid(), account_int);
		accountsVo.setAccount_number(account_number); 
		int result_count = accountDao.insertAccount(accountsVo); 
		
		sqlSession.close();
		
		
		if(result_count != 1){
			throw new BankException("계좌정보 생성중 오류가 발생했습니다."); 
		}else{
			System.out.println("계좌가 생성되었습니다.");
		}
	}

	public void viewMyAccount() {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		accountDao = new AccountDao(sqlSession);
		
		UsersVo usersVo = (UsersVo)LoginManager.loginInfo.get("UsersVo");
		List<AccountsVo> list = accountDao.selectUsersAccount(usersVo.getUid());
		int i=0;
		System.out.println("==========================================================================");
		System.out.println("내 계좌 정보");
		for(AccountsVo vo : list){
			System.out.println(String.format("%d.\t%s\t%d ", ++i, vo.getAccount_number(), vo.getAmount()));
		}
		System.out.println("==========================================================================");
		
		sqlSession.close();
	}

	public void saveAmount() {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		accountDao = new AccountDao(sqlSession);
		
		UsersVo usersVo = (UsersVo)LoginManager.loginInfo.get("UsersVo");
		AccountsVo accountsVo = new AccountsVo();
		accountsVo.setUsers_uid(usersVo.getUid());
		
		Scanner sc = new Scanner(System.in);
		System.out.println("==========================================================================");
		System.out.println("본인의 계좌번호를 입력해주세요");
		System.out.println("==========================================================================");
		System.out.println(":");
		if(sc.hasNextLine()){
			String command = sc.nextLine();
			accountsVo.setAccount_number(command);
			int myAccountCount = accountDao.existAccount(accountsVo); 
			if(myAccountCount == 0 ){
				System.out.println("[error] 계좌번호를 잘못 입력했습니다.(본인의 계좌만 입금 가능합니다.)");
				return;
			}					
			System.out.println("==========================================================================");
			System.out.println("입금금액을 입력해주세요");
			System.out.println("==========================================================================");
			System.out.println(":");
			if(sc.hasNextLine()){
				command = sc.nextLine();
				try{
					accountsVo.setAmount(Integer.parseInt(command));
					if(accountsVo.getAmount() < 0){ throw new Exception(); }
				}catch(Exception e){
					System.out.println("[error] 금액을 잘못 입력하셨습니다. (양수만 입력 가능합니다.)");
					System.out.println("[message] 입금처리를 취소합니다.");
					return;
				}
				
				accountDao.insertAmount(accountsVo);
				System.out.println("[message] 입급이 완료되었습니다.");
				
				sqlSession.commit();
			}
		}
	}

	public void sendAmount() {
		UsersVo usersVo = (UsersVo)LoginManager.loginInfo.get("UsersVo");
		SqlSession sqlSession = sqlSessionFactory.openSession(false);
		accountDao = new AccountDao(sqlSession);
			
		AccountsVo accountsVo = new AccountsVo();
		accountsVo.setUsers_uid(usersVo.getUid());
		
		Scanner sc = new Scanner(System.in);
		System.out.println("==========================================================================");
		System.out.println("본인의 계좌번호를 입력해주세요");
		System.out.println("==========================================================================");
		System.out.println("(취소 back):");
		if(sc.hasNextLine()){
			String command = sc.nextLine();
			accountsVo.setAccount_number(command);
			int myAccountCount = sqlSession.selectOne("bankMapper.existAccount", accountsVo);
			if(myAccountCount == 0 ){
				System.out.println("[error] 계좌번호를 잘못 입력했습니다.(본인의 계좌만 입금 가능합니다.)");
				return;
			}					
			int totalAmount = accountDao.selectTotalAmount(accountsVo.getAccount_number());
			
			System.out.println("==========================================================================");
			System.out.println(String.format("이체 금액을 입력해주세요 [이제가능 금액 : %d]", totalAmount));
			System.out.println("==========================================================================");
			if(sc.hasNextLine()){
				command = sc.nextLine();
				
				try{
					accountsVo.setAmount(Integer.parseInt(command));
					if(accountsVo.getAmount() < 0){ throw new Exception(); }
				}catch(Exception e){
					System.out.println("[error] 금액을 잘못 입력하셨습니다. (양수만 입력 가능합니다.)");
					System.out.println("[message] 이체 처리를 취소합니다.");
					return;
				}
				
				if(totalAmount - accountsVo.getAmount() < 0){
					System.out.println("[error] 계좌의 잔액 이상 이체를 할 수 없습니다.");
					System.out.println("[message] 이체 처리를 취소합니다.");
					return;
				}
				
				System.out.println("==========================================================================");
				System.out.println("이체할 계좌번호를 입력해주세요");
				System.out.println("==========================================================================");
				if(sc.hasNextLine()){
					AccountsVo sendAccountVo = new AccountsVo();
					command = sc.nextLine();
					sendAccountVo.setAccount_number(command);
					
					int sendAccountCount = accountDao.existAccount(sendAccountVo);

					if(sendAccountCount == 0 ){
						System.out.println("[error] 입금할 계좌번호가 존재하지 않습니다.");
						return;
					}
					
					try{
						sendAccountVo.setAmount(accountsVo.getAmount());
						sqlSession.insert("bankMapper.insertAmount", sendAccountVo);
						accountsVo.setAmount(accountsVo.getAmount() * -1);
						sqlSession.insert("bankMapper.insertAmount", accountsVo);
						System.out.println("[message] 이제가이 완료되었습니다.");
					}catch(Exception e){
						e.printStackTrace();
						sqlSession.rollback();
					}
				}
				sqlSession.commit();
			}
		}
	}
}
