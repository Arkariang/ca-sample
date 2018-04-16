package com.example.isabelmartin.astropicture.ui.Main;

import com.example.isabelmartin.astropicture.model.Photo;

interface MainActivityView {
    void setScrollEnabledAndSo();
    void receivedNewPhoto(Photo newPhoto);
}
