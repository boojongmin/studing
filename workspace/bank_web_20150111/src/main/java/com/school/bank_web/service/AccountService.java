package com.school.bank_web.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.bank_web.dao.AccountDao;
import com.school.bank_web.exception.BankException;
import com.school.bank_web.vo.AccountsVo;
import com.school.bank_web.vo.AjaxVo;
import com.school.bank_web.vo.UsersVo;

@Service
public class AccountService{
	@Autowired private AccountDao dao;
	
	@Transactional
	public void createAccount(UsersVo usersVo) {
		
		AccountsVo accountsVo = new AccountsVo();
		accountsVo.setAmount(0);
		accountsVo.setUsers_uid(usersVo.getUid());
		String account_number = dao.selectMaxAccount(usersVo.getUid());
		int account_int = 1;
		String users_uid = "" + usersVo.getUid();
		if(account_number != null){ account_int = Integer.parseInt(account_number.replaceFirst(users_uid, "")) +1; }
		
		account_number = String.format("%05d%05d", usersVo.getUid(), account_int);
		accountsVo.setAccount_number(account_number); 
		int result_count = dao.insertAccount(accountsVo);
		if(result_count != 1){
			throw new BankException("계좌정보 생성중 오류가 발생했습니다."); 
		}else{
			System.out.println("계좌가 생성되었습니다.");
		}
	}

	public List<AccountsVo> viewMyAccount(UsersVo usersVo) {
		List<AccountsVo> list = dao.selectUsersAccount(usersVo.getUid());
		return list;
	}

	@Transactional
	public void saveAmount(UsersVo usersVo) {
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
				int myAccountCount =
						dao.existAccount(accountsVo);
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
					
					accountsVo.setAmount(Integer.parseInt(command));
					if(accountsVo.getAmount() < 0){ 
						throw new BankException("[error] 금액을 잘못 입력하셨습니다. (양수만 입력 가능합니다.)\n[message] 입금처리를 취소합니다."); 
					}
					
					dao.insertAmount(accountsVo);
//					sqlSession.commit();
					//sqlSession.insert("bankMapper.insertAmount", accountsVo);
					System.out.println("[message] 입급이 완료되었습니다.");
					break;
				}
			}
		
		}
	}

	@Transactional
	public void sendAmount(UsersVo usersVo) {
		AccountsVo accountsVo = new AccountsVo();
		accountsVo.setUsers_uid(usersVo.getUid());
		
		Scanner sc = new Scanner(System.in);
		System.out.println("==========================================================================");
		System.out.println("본인의 계좌번호를 입력해주세요");
		System.out.println("==========================================================================");
		System.out.println("(취소 back):");
		if(sc.hasNextLine()){
			String command = sc.nextLine();
			if("back".equals(command)){
				return;
			}
			accountsVo.setAccount_number(command);
			int myAccountCount =  dao.existAccount(accountsVo);
			if(myAccountCount == 0 ){
				throw new BankException("[error] 계좌번호를 잘못 입력했습니다.(본인의 계좌만 입금 가능합니다.)");
			}					
			int totalAmount = 
					dao.selectTotalAmount(accountsVo.getAccount_number());
//;
			System.out.println("==========================================================================");
			System.out.println(String.format("이체 금액을 입력해주세요 [이제가능 금액 : %d]", totalAmount));
			System.out.println("==========================================================================");
			if(sc.hasNextLine()){
				System.out.println("(취소 0):");
				command = sc.nextLine();
				if("0".equals(command)){
					throw new BankException("[message] 취소되었습니다.");
				}
				
				try{
					accountsVo.setAmount(Integer.parseInt(command));
					if(accountsVo.getAmount() < 0){ throw new Exception(); }
				}catch(Exception e){
					throw new BankException("[error] 금액을 잘못 입력하셨습니다. (양수만 입력 가능합니다.)\n[message] 이체 처리를 취소합니다.");
				}
				
				if(totalAmount - accountsVo.getAmount() < 0){
					System.out.println("");
					System.out.println("");
					throw new BankException("[error] 계좌의 잔액 이상 이체를 할 수 없습니다.\n[message] [message] 이체 처리를 취소합니다.");
				}
				
				System.out.println("==========================================================================");
				System.out.println("이체할 계좌번호를 입력해주세요");
				System.out.println("==========================================================================");
				if(sc.hasNextLine()){
					AccountsVo sendAccountVo = new AccountsVo();
					command = sc.nextLine();
					sendAccountVo.setAccount_number(command);
					if(accountsVo.getAccount_number().equals(command)){
						System.out.println("");
						throw new BankException("[erorr] 이체대상과 이체할 계좌의 계좌번호가 일치합니다. 더이상 진행하지 않습니다.");
					}
					int sendAccountCount = dao.existAccount(sendAccountVo); 
					if(sendAccountCount == 0 ){
						throw new BankException("[error] 입금할 계좌번호가 존재하지 않습니다.");
					}
					sendAccountVo.setAmount(accountsVo.getAmount());
					
					dao.insertAmount(sendAccountVo);
					accountsVo.setAmount(accountsVo.getAmount() * -1);
					dao.insertAmount(accountsVo);
					
					System.out.println("[message] 이제가이 완료되었습니다.");
				}
			}
		}
	}

	public AjaxVo checkExistsAccount(AccountsVo accountsVo) {
		
		AjaxVo vo = new AjaxVo();
		int accountCount = dao.existAccount(accountsVo);
		if(accountCount > 0){
			vo.setResultCondition(true);
			vo.setMessage("계좌가 존재합니다.");
		}else{
			vo.setResultCondition(false);
			vo.setMessage("계좌가 없습니다..");
		}
		return vo;
	}
}
