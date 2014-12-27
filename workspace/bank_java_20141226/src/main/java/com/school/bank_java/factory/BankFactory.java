package com.school.bank_java.factory;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.school.bank_java.exception.BankException;
import com.school.bank_java.service.AccountService;
import com.school.bank_java.service.LoginService;

public class BankFactory implements Factory {
	SqlSessionFactory sqlSessionFactory;
	
	public BankFactory(){
		String resource = "com/school/bank_java/resource/conf/mybatis-config.xml";
		InputStream inputStream = null;
		try{
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(inputStream != null)
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	public Object getBeanByType(Class classType){
		Object obj = null;
		try {
			switch(classType.getName()){
				case "com.school.bank_java.service.LoginService":
					obj = new LoginService(sqlSessionFactory);
					break;
				case "com.school.bank_java.service.AccountService":
					obj = new AccountService(sqlSessionFactory);
					break;
				default:
					throw new BankException("요청하신 클래스를 생성할 수 없습니다.");
			}
		} catch( BankException e ){
			System.out.println(e.getMessage());
		}
		return obj;
	}
}
