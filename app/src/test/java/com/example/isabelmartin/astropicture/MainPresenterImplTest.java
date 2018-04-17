package com.example.isabelmartin.astropicture;


import com.example.isabelmartin.astropicture.dagger.AppComponent;
import com.example.isabelmartin.astropicture.dagger.AppModule;
import com.example.isabelmartin.astropicture.dagger.DaggerAppComponent;
import com.example.isabelmartin.astropicture.dagger.MainPresenterModule;
import com.example.isabelmartin.astropicture.dagger.NetworkModule;
import com.example.isabelmartin.astropicture.ui.Main.MainPresenter;
import com.example.isabelmartin.astropicture.ui.Main.MainPresenterImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowApplication;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Provides;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class MainPresenterImplTest {
    private OkHttpClient mockedClient;
    private AppComponent componentTest;
    private MainPresenterImpl impl;
    private ShadowApplication app;

    Request request = new Request.Builder()
            .url("https://www.meneame.net/").build();

    private Callback callback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            assert (false);
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            assert (true);
        }
    };

    private Call c = new Call() {
        @Override
        public Request request() {
            return null;
        }

        @Override
        public Response execute() throws IOException {
            return null;
        }

        @Override
        public void enqueue(Callback responseCallback) {
            responseCallback = callback;

        }

        @Override
        public void cancel() {

        }

        @Override
        public boolean isExecuted() {
            return false;
        }

        @Override
        public boolean isCanceled() {
            return false;
        }

        @Override
        public Call clone() {
            return null;
        }
    };

    private Response r;

    @Before
    public void setUp(){
        app = ShadowApplication.getInstance();

        componentTest = DaggerAppComponent.builder()
                .appModule(new AppModule(app.getApplicationContext()))
                .networkModule(new MockNetworkingModule())
                .build();
    }

    @Test
    public void mainPresenterImplTest(){
        mockedClient = componentTest.getOkHttpClient();
        impl = componentTest.getMainPresenterImpl();

        impl.requestPhoto();
    }

    class MockNetworkingModule extends NetworkModule {

        @Provides
        @Singleton
        public OkHttpClient provideHttpClient() {
            mockedClient = mock(OkHttpClient.class);
            when(mockedClient.newCall(request)).thenReturn(c);
            return mockedClient;
        }
    }

    class MockMainPresenterImpModule extends MainPresenterModule {
        @Override
        public MainPresenter bindMainPresenter(MainPresenterImpl mainPresenter) {
            impl = mock(MainPresenterImpl.class);
            when(impl.getRequest()).thenReturn(request);
            return impl;
        }
    }
}
