package com.project.elibrary.aspects;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionsAspect {
    @Pointcut("within(com.project.elibrary.controllers.*)")
<<<<<<< HEAD
    void controllersPointCut() {
    };
=======
    void controllersPointCut(){};
>>>>>>> 8962e4db0c153c380270c20d4a8987f711f2ab4d

    @Around("controllersPointCut()")
    Object exceptionHandler(ProceedingJoinPoint joinPoint) {
        try {
            Object result = joinPoint.proceed();
            return result;
        } catch (Throwable e) {
            Map<String, Integer> res = new HashMap<>();
            res.put("website die", 404);
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
    }
}
