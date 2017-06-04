package com.example.raghuveer.inclass06;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ItemDetailsActivity extends AppCompatActivity {
   // ArrayList<App> appArrayList;
    private App app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        Intent intent = getIntent();
        if(intent == null)
        {
            return;
        }

        Bundle bundle = intent.getExtras();
        app = (App)bundle.getSerializable(MainActivity.NEWS_KEYS);

        new LoadImage().execute(app.getImage());
        TextView text1 = (TextView) findViewById(R.id.textView_title);
        text1.setText(app.getTitle());
        TextView text2 = (TextView) findViewById(R.id.updated_date);
        text2.setText(app.getReleaseDate());
        TextView text3 = (TextView) findViewById(R.id.summary_text);
        text3.setText(app.getSummary());
    }

    public class LoadImage extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            if(bitmap != null)
                imageView.setImageBitmap(bitmap);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            try{
                URL url = new URL(params[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                Bitmap bitmap = BitmapFactory.decodeStream(con.getInputStream());
                return bitmap;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return  null;
        }
    }


}
