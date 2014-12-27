package com.school.bank_java.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import com.school.bank_java.service.Service;

@Aspect
public class AnnotationServiceAop {
	
	@Before("execution(* com.school.bank_java.service.*.*(..))")
	public void beforeMonitor(JoinPoint thisJoinPoint){
		System.out.println("before : "+thisJoinPoint);
	}
	
	@AfterReturning("execution(* com.school.bank_java.service.*.*(..))")
	public void afterReturningMonitor(JoinPoint thisJoinPoint){
		System.out.println("target : " + thisJoinPoint.getTarget());
		System.out.println("after returning: ");	
	}
	
	@AfterThrowing(pointcut = "execution(* com.school.bank_java.service.*.*(..))" , throwing = "exception" )
	public void afterThrowingMonitor(JoinPoint thisJoinPoint, Exception exception){
		System.out.println("exception : " + exception.getMessage());	
		System.out.println("after throwing: ");
	}
	@After("execution(* com.school.bank_java.service.*.*(..))")
	public void afterMonitor(JoinPoint thisJoinPoint){
		System.out.println("after : ");
	}
	
	
	@Around("execution(* com.school.bank_java.service.*.*(..))")
	public Object aroundMonitor(ProceedingJoinPoint thisJoinPoint) throws Throwable{
		System.out.println("aroundMonitor start.");
        long time1 = System.currentTimeMillis();
        Object retVal = thisJoinPoint.proceed();
 
        if(retVal != null ){
        	System.out.println(retVal.getClass().getSimpleName());
        }
        
        long time2 = System.currentTimeMillis();
        System.out.println("aroundMonitor end. Time("
            + (time2 - time1) + ")");
        return retVal;
	}

}
