package com.example.isabelmartin.astropicture;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.isabelmartin.astropicture.application.SampleApplication;
import com.example.isabelmartin.astropicture.dagger.AppComponent;
import com.example.isabelmartin.astropicture.model.Photo;

import java.util.ArrayList;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity implements MainActivityView{

    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private ArrayList<Photo> photosList = new ArrayList<Photo>();
    private int lastVisibleItemPosition = 0;
    AppComponent component = SampleApplication.getComponent();
    @Inject MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        component.inject(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerAdapter(photosList);
        recyclerView.setAdapter(adapter);

        presenter.setView(this);

    }

    @Override
    protected void onStart() {

        if (photosList.size() == 0) {
            try {
                presenter.requestPhoto();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        super.onStart();
    }

    @Override
    public void setScrollEnabledAndSo() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                int totalItemCount = layoutManager.getItemCount();
                if (!presenter.getLoadingData() && totalItemCount == lastVisibleItemPosition + 1) {
                    presenter.requestPhoto();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    public void receivedNewPhoto(final Photo newPhoto) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                photosList.add(newPhoto);
                adapter.notifyItemInserted(photosList.size());
            }
        });
    }
}
