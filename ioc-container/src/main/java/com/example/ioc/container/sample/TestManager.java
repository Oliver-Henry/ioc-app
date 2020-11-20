package com.example.ioc.container.sample;

public class TestManager implements TestService {

    @Override
    public String sayHello() {
        return "Hello, World!";
    }

}
