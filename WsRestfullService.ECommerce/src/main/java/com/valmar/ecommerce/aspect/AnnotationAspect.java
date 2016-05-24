package com.valmar.ecommerce.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AnnotationAspect {

	@Before("@annotation(com.valmar.ecommerce.aspect.Loggable)")
    public void myAdvice(){
        System.out.println("PRUEBA: Executing myAdvice!!");
    }
	
	@Around("@annotation(com.valmar.ecommerce.aspect.Loggable)")
    public Object userAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("PRUEBA: Before invoking getName() method with annotation");
        Object value = null;
        try {
            value = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("PRUEBA: After invoking getName() method with annotation. Return value="+value);
        return value;
    }
}
