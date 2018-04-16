package com.example.isabelmartin.astropicture.dagger;

import com.example.isabelmartin.astropicture.ui.Main.MainPresenter;
import com.example.isabelmartin.astropicture.ui.Main.MainPresenterImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Singleton
@Module
public abstract class MainPresenterModule {

    @Binds
    public abstract MainPresenter bindMainPresenter(MainPresenterImpl mainPresenter);

}
