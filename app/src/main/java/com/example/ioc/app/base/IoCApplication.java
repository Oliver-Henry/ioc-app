package com.example.ioc.app.base;

import android.app.Application;

import com.example.ioc.app.managers.ApiManager;
import com.example.ioc.app.managers.NetworkManager;
import com.example.ioc.container.lib.DIGraph;

public class IoCApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DIGraph injector = DIGraph.getContainer();
        setUpDependencies(injector);
        injector.generateGraph();
    }
    private void setUpDependencies(DIGraph injector) {
        injector.installDependency(ApiManager.class);
        injector.installDependency(NetworkManager.class);
    }
}
