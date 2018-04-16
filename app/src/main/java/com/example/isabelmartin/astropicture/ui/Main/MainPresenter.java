package com.example.isabelmartin.astropicture.ui.Main;


public interface MainPresenter {
    void setView(MainActivityView view);
    void requestPhoto();
    Boolean getLoadingData();
}
