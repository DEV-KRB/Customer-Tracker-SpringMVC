package com.eculant.crud.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	@Pointcut("execution(* com.eculant.crud.controller.*.*(..))")
	private void forControllerPackage() {}
	
	@Pointcut("execution(* com.eculant.crud.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* com.eculant.crud.dao.*.*(..))")
	private void forDaoPackage() {}
	
	@Pointcut("forControllerPackage()||forServicePackage()||forDaoPackage()")
	private void forAppFlow() {}
	
	//add @Before advice
	@Before("forAppFlow")
	public void before(JoinPoint theJoinPoint) {
		
		//display method we are calling
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("=====>> in @Before: calling method: " +theMethod);
		
		//display arguements to the method
		//get the arguements
		Object[] args=theJoinPoint.getArgs();
		
		//loop through and display args
		for(Object tempArg : args) {
			myLogger.info("=====>>arguement: "+tempArg);
		}
	}
		//add @AfterReturning advice
		@AfterReturning(
				pointcut="forAppFlow()",
				returning="theResult"
				)
		
		public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
		
			//display method we are returning from
			String theMethod = theJoinPoint.getSignature().toShortString();
			myLogger.info("=====>> in @AfterReturning: form method: " +theMethod);
			
			//display data method
			myLogger.info("=====>>result: "+theResult);
	}
}
