package com.woo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Aspect
@Component
public class FeatureFlagAspect {

    @Autowired
    private FeatureFlagService featureFlagService;

    @Autowired
    private FeatureFlagProvider featureFlagProvider;

    @Around("@annotation(com.woo.FeatureFlag)")
    public Object before(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        FeatureFlag featureFlagAnnotation = method.getAnnotation(FeatureFlag.class);

        if(!featureFlagProvider.isOn(featureFlagAnnotation.id())){
            return invokeFallbackMethod(proceedingJoinPoint, featureFlagAnnotation.fallbackMethod());
        }

//        AppVersion minimumTargetVersion = featureFlagProvider.getMinimumTargetVersion();
//        AppVersion appVersion = featureFlagService.getAppVersion();
//        if(appVersion == null || !appVersion.isTargetVersion(minimumTargetVersion)) {
//            return invokeFallbackMethod(proceedingJoinPoint, featureFlagAnnotation.fallbackMethod());
//        }

        return proceedingJoinPoint.proceed();
    }

    private Object invokeFallbackMethod(ProceedingJoinPoint proceedingJoinPoint, String fallbackMethodName)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if(fallbackMethodName == null || fallbackMethodName.isEmpty()) {
            throw new IllegalArgumentException("fallbackMethodName cannot be null or empty");
        }

        Method fallbackMethod = proceedingJoinPoint
                .getTarget()
                .getClass()
                .getMethod(fallbackMethodName);
        return fallbackMethod.invoke(proceedingJoinPoint.getTarget());
    }
}