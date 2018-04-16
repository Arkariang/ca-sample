package com.example.isabelmartin.astropicture.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import org.json.JSONObject;

public class Photo implements Parcelable {

    private String url = "";
    private String descrption = "";
    public final static String LOGTAG = "Photo";

    private final String PHOTO_EXPLANATION = "explanation";
    private final String PHOTO_URL = "url";

    public Photo() {
    }

    public void parseModel(JSONObject jsonObject) {

        try {
            url = jsonObject.optString(PHOTO_URL,"");
            descrption = jsonObject.optString(PHOTO_EXPLANATION,"");

        } catch (Exception e) {
            Log.e(LOGTAG, e.getLocalizedMessage());
        }
    }

    public String getDescription() {
        return descrption;
    }

    public String getUrl() {
        return url;
    }


    protected Photo(Parcel in) {
        url = in.readString();
        descrption = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(url);
        parcel.writeString(descrption);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Photo> CREATOR = new Parcelable.Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };
}