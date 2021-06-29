package com.code.spring.e.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author 愆凡
 * @date 2021/6/29 10:07
 */
@Slf4j
@Aspect
@Component
public class AspectDemo {

	@Pointcut("@annotation(com.code.spring.e.aop.AnnotationDemo)")
	public void doOperation() {

	}


	@Before("doOperation()")
	public void before(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();

	}


	@AfterReturning(returning = "result", pointcut = "doOperation()")
	public void doAfterReturning(Object result) {

	}

}
