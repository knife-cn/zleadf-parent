package com.xhs.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Log {
	
	@Before("execution(* com.xhs.service.impl.*.*(..))")
	public void before(){
		System.out.println("-----方法执行前-----");
	}
	
	@After("execution(* com.xhs.service.impl.*.*(..))")
	public void after(){
		System.out.println("-----方法执行后-----");
	}
	
}
