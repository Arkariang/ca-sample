package com.example.isabelmartin.astropicture.application;

import android.app.Application;

import com.example.isabelmartin.astropicture.dagger.AppComponent;
import com.example.isabelmartin.astropicture.dagger.AppModule;
import com.example.isabelmartin.astropicture.dagger.DaggerAppComponent;
import com.example.isabelmartin.astropicture.dagger.NetworkModule;

public class SampleApplication extends Application {

    public static String PACKAGE_NAME;
    static AppComponent component;
    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();

        PACKAGE_NAME = getPackageName();
    }

    public static AppComponent getComponent() {
        return component;
    }
}