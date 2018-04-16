package com.example.isabelmartin.astropicture.application;

import android.app.Application;

import com.example.isabelmartin.astropicture.dagger.AppComponent;
import com.example.isabelmartin.astropicture.dagger.AppModule;
import com.example.isabelmartin.astropicture.dagger.DaggerAppComponent;
import com.example.isabelmartin.astropicture.dagger.NetworkModule;
import com.example.isabelmartin.astropicture.dagger.PresenterModule;

public class SampleApplication extends Application {

    static AppComponent component;
    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .presenterModule(new PresenterModule())
                .build();
    }

    public static AppComponent getComponent() {
        return component;
    }
}