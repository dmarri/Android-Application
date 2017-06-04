package com.example.raghuveer.inclass06;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InterfaceAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by raghuveer on 10/5/2015.
 */
public class GetAppsList extends AsyncTask<String, Void, ArrayList<App>> {
    IActivity activity;

    public GetAppsList(IActivity activity) {
        this.activity = activity;
    }

    @Override
    protected ArrayList<App> doInBackground(String... params) {
        try{
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statusCode = con.getResponseCode();
            if(statusCode == HttpURLConnection.HTTP_OK){
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = reader.readLine();
                while(line != null){
                    sb.append(line);
                    line = reader.readLine();
                }
                Log.d("Alright","Ok");
                return AppsUtil.AppsJsonParser.parseApps(sb.toString());

            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("NO", "NOT OK");
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<App> apps) {
        activity.setUpList(apps);
    }

    static public interface IActivity{
        public void setUpList(ArrayList<App> appArrayList);
    }
}
