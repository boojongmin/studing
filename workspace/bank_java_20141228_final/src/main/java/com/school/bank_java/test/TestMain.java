package com.school.bank_java.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestMain {

	public static void main(String[] args) {
		String path ="classpath:com/school/bank_java/resource/spring/appContext.xml";
		ApplicationContext ctx 
			= new FileSystemXmlApplicationContext(path);
		
		C c = ctx.getBean(C.class);
		C cc = ctx.getBean(C.class);
		System.out.println("c == cc : " + (c == cc));
		
		
		C c1 = new C(new B(new A()));
		C c2 = new C(new B(new A()));
		System.out.println("c1 == c2 : " + (c1 == c2));
		
	}

}
