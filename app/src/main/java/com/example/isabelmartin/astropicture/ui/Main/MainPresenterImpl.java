package com.example.isabelmartin.astropicture.ui.Main;
import android.content.Context;
import android.net.Uri;

import com.example.isabelmartin.astropicture.R;
import com.example.isabelmartin.astropicture.application.SampleApplication;
import com.example.isabelmartin.astropicture.constants.Constants;
import com.example.isabelmartin.astropicture.dagger.AppComponent;
import com.example.isabelmartin.astropicture.model.Photo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import dagger.Component;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainPresenterImpl implements MainPresenter {

    private MainActivityView viewResponder;

    @Inject OkHttpClient client;
    @Inject Context context;

    Calendar calendar = Calendar.getInstance();

    private Boolean isLoadingData = false;

    @Inject
    public MainPresenterImpl(){
    }

    @Override
    public void setView(MainActivityView view) {
        if (view != null) {
            viewResponder = view;
            viewResponder.setScrollEnabledAndSo();
        }
    }

    @Override
    public void requestPhoto() {
        isLoadingData = true;

        client.newCall(getRequest())
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        isLoadingData = false;
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        JSONObject photoJSON = null;
                        try {

                            photoJSON = new JSONObject(response.body().string());

                            if (photoJSON.optString(Constants.MEDIA_TYPE_KEY,"") != Constants.MEDIA_TYPE_VIDEO_VALUE) {
                                Photo receivedPhoto = new Photo();
                                receivedPhoto.parseModel(photoJSON);
                                viewResponder.receivedNewPhoto(receivedPhoto);
                                isLoadingData = false;
                            } else {
                                requestPhoto();
                            }
                        } catch (JSONException e) {
                            isLoadingData = false;
                            e.printStackTrace();
                        }
                    }
                });
    }

    public Boolean getLoadingData() {
        return isLoadingData;
    }

    public Request getRequest() {
        Request request = new Request.Builder()
                .url(updateDate()).build();
        return request;
    }

    private String updateDate() {

        Uri.Builder urlRequest = new Uri.Builder()
                .scheme(Constants.URL_SCHEME)
                .authority(Constants.URL_AUTHORITY)
                .appendPath(Constants.URL_PATH_1)
                .appendPath(Constants.URL_PATH_2)
                .appendQueryParameter(Constants.URL_QUERY_PARAM_DATE_KEY, getDataDayBefore())
                .appendQueryParameter(Constants.URL_QUERY_PARAM_API_KEY, context.getString(R.string.api_key));

        return urlRequest.build().toString();
    }

    private String getDataDayBefore(){
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date date = calendar.getTime();
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date).toString();

        return currentDate;
    }
}
