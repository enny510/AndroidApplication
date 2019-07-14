package com.example.androidapplication;

import android.app.Application;

import com.example.androidapplication.config.AppComponent;
import com.example.androidapplication.config.DaggerAppComponent;
import com.example.androidapplication.config.modules.AppModule;

public class AndroidApplication extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getComponent() {
        return component;
    }
}
