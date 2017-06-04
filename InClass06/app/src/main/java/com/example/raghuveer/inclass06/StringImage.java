package com.example.raghuveer.inclass06;

import android.widget.ImageView;

/**
 * Created by lenovo on 10/3/2016.
 */
public class StringImage {
    ImageView iv;
    String url;

    public StringImage(ImageView iv, String url)
    {
        this.iv = iv;
        this.url = url;
    }

    public ImageView getIv() {
        return iv;
    }

    public String getBitmap() {
        return url;
    }
}
