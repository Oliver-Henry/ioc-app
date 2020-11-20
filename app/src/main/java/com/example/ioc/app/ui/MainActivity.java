package com.example.ioc.app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.ioc.app.R;
import com.example.ioc.app.services.ApiService;
import com.example.ioc.container.lib.DIGraph;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName() + "_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiService apiService = DIGraph.getContainer().inject(ApiService.class);
        Log.d(TAG, "onCreate: " + apiService.getDataFromApi());
    }

}