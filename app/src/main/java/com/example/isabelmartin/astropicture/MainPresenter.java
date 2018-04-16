package com.example.isabelmartin.astropicture;

public interface MainPresenter {
    void setView(MainActivityView view);
    void requestPhoto();
    Boolean getLoadingData();
}
