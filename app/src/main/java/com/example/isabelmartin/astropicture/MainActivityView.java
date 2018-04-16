package com.example.isabelmartin.astropicture;

import com.example.isabelmartin.astropicture.model.Photo;

interface MainActivityView {
    void setScrollEnabledAndSo();
    void receivedNewPhoto(Photo newPhoto);
}
