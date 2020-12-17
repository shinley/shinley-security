package com.shinley.service;

import org.springframework.stereotype.Component;

@Component
public class HelloService {
    public String greeting(String name) {
        System.out.println("greeting");
        return "hello " + name ;
    }
}
