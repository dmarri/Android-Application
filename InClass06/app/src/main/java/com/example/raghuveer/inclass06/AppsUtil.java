package com.example.raghuveer.inclass06;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class AppsUtil {
    static  public class AppsJsonParser{
        static ArrayList<App> parseApps(String in) throws JSONException{
            ArrayList<App> appsList = new ArrayList<>();

            JSONObject root = new JSONObject(in);
            JSONObject feed = root.getJSONObject("feed");

            JSONArray appJSONArray = feed.getJSONArray("entry");

            for(int i = 0; i<appJSONArray.length(); i++){
                JSONObject tune=appJSONArray.getJSONObject(i);
                App app=new App();

                JSONObject names = tune.getJSONObject("im:name");
                app.setTitle(names.getString("label"));

                JSONArray tunesArrayIMG=tune.getJSONArray("im:image");

                JSONObject imgthumb=tunesArrayIMG.getJSONObject(0);
                app.setImageThumbnail(imgthumb.getString("label"));
                JSONObject img=tunesArrayIMG.getJSONObject(2);
                app.setImage(img.getString("label"));

                JSONObject sum = tune.getJSONObject("summary");
                app.setSummary(sum.getString("label"));

                //  JSONArray tunesArraydate=tune.getJSONArray("im:releaseDate");
                JSONObject tunesObjdate = tune.getJSONObject("im:releaseDate");

                app.setReleaseDate(tunesObjdate.getString("label"));

                appsList.add(app);
            }
            Log.d("demo",appsList.toString());

            return appsList;
        }
    }
}
