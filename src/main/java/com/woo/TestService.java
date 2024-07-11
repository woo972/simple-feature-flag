package com.woo;

import org.springframework.stereotype.Component;

@Component
public class TestService {
    @FeatureFlag(id = 1, fallbackMethod = "fallbackMethod")
    public void testB(){
        System.out.println("B");
    }

    public void fallbackMethod(){
        System.out.println("A");
    }
}
