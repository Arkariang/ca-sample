package com.example.isabelmartin.astropicture.dagger;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final Context context;

    public AppModule(Context applicationContext) {
        context = applicationContext;
    }

    public AppModule(Application application) {
        context = application.getApplicationContext();
    }

    @Provides
    @Singleton
    Context provideContext() {
        return context;
    }
}
