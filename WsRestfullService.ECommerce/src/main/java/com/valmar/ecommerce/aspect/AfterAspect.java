package com.valmar.ecommerce.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AfterAspect {

	@After("args(id)")
	public void logStringArguments(int id) {
		System.out.println("PRUEBA:  Running After Advice. String argument passed=" + id);
	}

	@AfterThrowing("within(com.valmar.ecommerce.security.model.AuthenticationRequest)")
	public void logExceptions(JoinPoint joinPoint) {
		System.out.println(" PRUEBA:  Exception thrown in AuthenticationRequest Method=" + joinPoint.toString());
	}

	@AfterReturning(pointcut = "execution(* createAuthenticationToken())", returning = "returnString")
	public void getNameReturningAdvice(String returnString) {
		System.out.println("PRUEBA:  getNameReturningAdvice executed. Returned String=" + returnString);
	}

}
