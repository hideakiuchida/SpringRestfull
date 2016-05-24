package com.valmar.ecommerce.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AroundAspect {
	
	@Around("execution(* com.valmar.ecommerce.controller.AuthenticationRestController.createAuthenticationToken(..))")
    public Object userAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("PRUEBA: Before invoking getName() method");
        Object value = null;
        try {
            value = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("PRUEBA: After invoking getName() method. Return value="+value);
        return value;
    }

}
