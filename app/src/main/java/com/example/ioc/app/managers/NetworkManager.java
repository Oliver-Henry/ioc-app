package com.example.ioc.app.managers;

import com.example.ioc.app.services.NetworkService;

public class NetworkManager implements NetworkService {

    @Override
    public String doNetworkCall() {
        return "{\n" +
                "  \"data\": {\n" +
                "    \"id\": 1001,\n" +
                "    \"name\": \"Api name value\"\n" +
                "  }\n" +
                "}";
    }

}
