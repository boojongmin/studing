package com.school.bank_java.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import com.school.bank_java.service.Service;

public class ServiceAop {
	
	public void beforeMonitor(JoinPoint thisJoinPoint){
		System.out.println("before : "+thisJoinPoint);
	}
	
	
	public void afterReturningMonitor(JoinPoint thisJoinPoint){
		System.out.println("target : " + thisJoinPoint.getTarget());
		System.out.println("after returning: ");	
	}
	
	public void afterThrowingMonitor(JoinPoint thisJoinPoint, Exception exception){
		System.out.println("exception : " + exception.getMessage());	
		System.out.println("after throwing: ");
	}
	
	public void afterMonitor(JoinPoint thisJoinPoint){
		System.out.println("after : ");
	}
	
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
