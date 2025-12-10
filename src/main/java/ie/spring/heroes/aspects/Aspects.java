package ie.spring.heroes.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class Aspects {

    // Define a pointcut that matches any method named `findAll()` in any subpackage of `ie.spring.heroes`
    @Pointcut("execution(* ie.spring.heroes.*.*.findAll())")
    public void findAll() {}

    // Advice that runs *before* any method matched by `findAll()`
    @Before("findAll()")
    public void before(JoinPoint joinPoint) {
        // Logs the method signature before it executes
        log.info("✅ Before " + joinPoint.getSignature());
    }

    // Advice that runs *after* any method matched by `findAll()` (regardless of outcome)
    @After("findAll()")
    public void after(JoinPoint joinPoint) {
        // Logs the method signature after it executes
        log.info("✅ After " + joinPoint.getSignature());
    }

    // Advice that runs *after successfully returning* from a method matched by `findAll()`
    @AfterReturning(value = "findAll()", returning = "returned")
    public void afterReturning(JoinPoint joinPoint, Object returned) {
        // Logs the method signature and what it returned
        log.info("✅ AfterReturning " + joinPoint.getSignature() + " returned " + returned);
    }

    // Advice that runs when *any method in the package* throws an exception
    @AfterThrowing(value = "execution(* ie.spring.heroes..*.*(..))", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        // Logs the method and the exception message if one is thrown
        log.info("❌ After throwing " + joinPoint.getSignature() + " exception " + ex.getMessage());
    }

    // Pointcut that matches any method annotated with @Transactional
    @Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void transactionalAnnotation() {}

    // Advice that runs *after* any method annotated with @Transactional
    @After("transactionalAnnotation()")
    public void afterTransactionAnnotation(JoinPoint joinPoint) {
        // Logs that the transactional method has completed
        log.info("✅ After Transactional annotation in " + joinPoint.getSignature());
    }
}

