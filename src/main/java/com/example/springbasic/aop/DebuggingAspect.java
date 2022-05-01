package com.example.springbasic.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect //AOP class annotation : injection of extended feature
@Component //IoC container create and manage the object
@Slf4j
public class DebuggingAspect {

    //select target method : CommentService#cerate()
    @Pointcut("execution(* com.example.springbasic.api.*.*(..))") //with *, apply this to all method in api
    private void cut() {

    }
    //set execution timing : before cut()
    @Before("cut()")
    public void loggingArgs(JoinPoint joinPoint){ //point around the method of cut()
        //get the input
        Object[] args= joinPoint.getArgs();
        //className
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();
        //methodName
        String methodName = joinPoint.getSignature()
                .getName();
        //log the input
        //ex) the input of CommentService#create() => 5
        //ex) the input of CommentService#create() => CommentDto(id=null, ...)
        for (Object obj: args) {
            log.info("{}#{}'s input => {}", className, methodName, obj);
        }
    }
    //set execution timing : after cut()'s success
    @AfterReturning(value = "cut()", returning = "returnObj") //be careful of setting returning
    public void loggingReturnValue(JoinPoint joinPoint, Object returnObj){
        //className
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();
        //methodName
        String methodName = joinPoint.getSignature()
                .getName();
        //log the return value
        //CommentService#crate()'s return value => CommentDto(id = 10, ...)
        log.info("{}#{}'s return value => {}", className, methodName, returnObj);
    }
}
