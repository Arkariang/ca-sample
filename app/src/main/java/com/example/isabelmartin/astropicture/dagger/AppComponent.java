package com.example.isabelmartin.astropicture.dagger;

import android.content.Context;
import android.net.Uri;

import com.example.isabelmartin.astropicture.ui.Main.MainActivity;
import com.example.isabelmartin.astropicture.ui.Main.MainPresenterImpl;
import com.example.isabelmartin.astropicture.ui.detail.PhotoActivity;
import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        MainPresenterModule.class,
})
public interface AppComponent {

    void inject(MainActivity mainActivity);
    void inject(PhotoActivity photoActivity);

    Context getContext();
    OkHttpClient getOkHttpClient();
    MainPresenterImpl getMainPresenterImpl();
}
