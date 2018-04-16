package com.example.isabelmartin.astropicture.dagger;

import com.example.isabelmartin.astropicture.MainActivity;
import com.example.isabelmartin.astropicture.MainPresenterImpl;
import com.example.isabelmartin.astropicture.PhotoActivity;
import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        PresenterModule.class,
})
public interface AppComponent {

    void inject(MainActivity mainActivity);
    void inject(PhotoActivity photoActivity);
    void inject(MainPresenterImpl imp);
}
