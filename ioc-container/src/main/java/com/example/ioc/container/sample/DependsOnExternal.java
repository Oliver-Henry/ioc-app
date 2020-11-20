package com.example.ioc.container.sample;

import com.example.ioc.container.annotations.Inject;

public class DependsOnExternal implements  ExternalDependencyService {

    @Inject TestService testService;


    @Override
    public String doSomethingWithExternalDependency() {
        return testService.sayHello();
    }

}
