package com.example.isabelmartin.astropicture.dagger;

import com.example.isabelmartin.astropicture.ui.Main.MainPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainPresenterModuleImpModule {
    @Provides
    @Singleton
    public MainPresenterImpl provideMainPresenterImpl() {
        MainPresenterImpl impl = new MainPresenterImpl();
        return impl;
    }
}
