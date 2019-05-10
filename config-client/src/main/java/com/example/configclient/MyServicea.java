package com.example.configclient;

import org.springframework.stereotype.Component;

@Component
public class MyServicea implements Servicea {
    @Override
    public String test() {
        return "hystrix";
    }
}
