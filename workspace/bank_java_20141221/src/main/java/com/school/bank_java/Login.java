package com.school.bank_java;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.school.bank_java.exception.BankException;
import com.school.bank_java.vo.UsersVo;

public class Login {
	static Map loginInfo = new HashMap(); 
	
	public void doLogin(){
		String resource = "com/school/bank_java/resource/conf/mybatis-config.xml";
		SqlSessionFactory sqlSessionFactory = null;
		try(InputStream inputStream = Resources.getResourceAsStream(resource)){
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try(SqlSession sqlSession = sqlSessionFactory.openSession()){
			Scanner sc = new Scanner(System.in);
			while(true){
				System.out.println("==========================================================================");		
				System.out.println("로그인할 아이디를 입력해주세요 : ");
				System.out.println("back: 이전 메뉴로 이동");
				System.out.println("==========================================================================");
				System.out.println(":");
				if(sc.hasNextLine()){
					try{
						String command = sc.nextLine();
						if("back".equals(command)){
							System.out.println("메인 메뉴로 이동합니다.");
							break;
						}					
						
						UsersVo vo = (UsersVo)sqlSession.selectOne("bankMapper.selectUserByUserid", command);
						if(vo == null){
							System.out.println("==========================================================================");
							System.out.println("아이디가 없습니다.");
							System.out.println("back: 이전 메뉴로 이동");
							System.out.println("==========================================================================");
						}else{
							Login.loginInfo.put("UsersVo", vo);
							System.out.println("==========================================================================");
							System.out.println("로그인에 성공했습니다.");
							System.out.println(vo.getUsername() +   "님 환영합니다.");
							System.out.println("메인 메뉴로 이동합니다.");
							System.out.println("==========================================================================");
							break;
						}
					}catch(Exception e){
						throw new BankException("로그인 오류");
					}
				}
				//sc.close();
			}
		}
	}
}
