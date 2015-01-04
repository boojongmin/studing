package com.school.bank_java.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AnnotationServiceAop {
	
	@Before("execution(* com.school.bank_java.service.*.*(..))")
	public void beforeMonitor(JoinPoint jp){
		System.out.println("beforeMonitor : " + jp.getClass());
	}
	
	@After("execution(* com.school.bank_java.service.*.*(..))")
	public void afterMonitor(JoinPoint jp){
		System.out.println("afterMonitor : " + jp.getClass());
	}
	
	@Around("execution(* com.school.bank_java.service.*.*(..))")
	public Object aroundMonitor(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("-> around 시작");
		Object retVal = pjp.proceed();
		System.out.println("-> around 종료");
		return retVal;
	}
	
	@AfterReturning(pointcut="execution(* com.school.bank_java.service.*.*(..))",
					returning="retVal")
	public void afterReturningMonitor(JoinPoint jp, Object retVal){
		System.out.println("afterReturningMonitor");
	}
	
	@AfterThrowing(pointcut="execution(* com.school.bank_java.service.*.*(..))",
				throwing="exception")
	public void afterThrowingMonitor(JoinPoint jp, Exception exception){
		System.out.println("afterThrowingMonitor : " + exception.getMessage());
	}

}
