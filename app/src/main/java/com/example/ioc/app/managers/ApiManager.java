package com.example.ioc.app.managers;

import com.example.ioc.container.annotations.Inject;
import com.example.ioc.app.services.ApiService;
import com.example.ioc.app.services.NetworkService;

public class ApiManager implements ApiService {

    @Inject
    private NetworkService networkService;

    @Override
    public String getDataFromApi() {
        return "API response is " + networkService.doNetworkCall();
    }

}
