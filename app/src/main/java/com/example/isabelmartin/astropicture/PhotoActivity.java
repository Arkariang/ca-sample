package com.example.isabelmartin.astropicture;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.isabelmartin.astropicture.application.SampleApplication;
import com.example.isabelmartin.astropicture.dagger.AppComponent;
import com.example.isabelmartin.astropicture.model.Photo;
import com.squareup.picasso.Picasso;

public class PhotoActivity extends AppCompatActivity {

    AppComponent component = SampleApplication.getComponent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        component.inject(this);

        Photo photo = getIntent().getExtras().getParcelable(Photo.LOGTAG);

        if (photo != null) {
            TextView txt = findViewById(R.id.photoDescription);
            txt.setText(photo.getDescription());

            ImageView img = findViewById(R.id.photoImageView);
            Picasso.with(this).load(photo.getUrl()).into(img);
        }
    }
}
