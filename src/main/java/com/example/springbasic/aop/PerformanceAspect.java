package com.example.springbasic.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class PerformanceAspect {
    //designate a target annotation
    @Pointcut("@annotation(com.example.springbasic.annotation.RunningTime)")
    private void enableRunningTime() {

    }
    //all method of basic package
    @Pointcut("execution(* com.example.springbasic..*.*(..))")
    private void cut() {

    }

    @Around("cut() && enableRunningTime()") //execution timing(before and after of the object that satisfies with both conditions)
    public void loggingRunningTime(ProceedingJoinPoint joinPoint) throws Throwable {
        //before method, start
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        //execute method
        Object returningObj = joinPoint.proceed();
        //after method, finish and log
        stopWatch.stop();
        String methodName = joinPoint.getSignature()
                .getName();
        log.info("{}'s total execution time => {} sec", methodName, stopWatch.getTotalTimeSeconds());

    }

}
