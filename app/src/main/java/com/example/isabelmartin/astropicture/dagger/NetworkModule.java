package com.example.isabelmartin.astropicture.dagger;

import android.net.Uri;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;


@Module
public class NetworkModule {

    @Provides
    @Singleton
    public OkHttpClient provideHttpClient() {
        OkHttpClient client = new OkHttpClient();
        return client;
    }
}
