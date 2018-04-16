package com.example.isabelmartin.astropicture.dagger;

import com.example.isabelmartin.astropicture.MainPresenter;
import com.example.isabelmartin.astropicture.MainPresenterImpl;

import javax.inject.Singleton;

import dagger.MembersInjector;
import dagger.Module;
import dagger.Provides;
import okhttp3.Request;

@Module
public class PresenterModule {

    @Provides
    @Singleton
    public MainPresenter provideMainPresenter() {
        MainPresenter mp = (MainPresenter) new MainPresenterImpl();
        return mp;
    }
}
