package com.example.raghuveer.inclass06;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class appAdapter extends ArrayAdapter<App>{
    Context mContext;
    int mResource;
    ArrayList<App> mData;


    public appAdapter(Context context, int resource, ArrayList<App> data){
        super(context, resource, data);
        this.mContext = context;
        this.mResource = resource;
        this.mData = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent, false);
        }

        App app = mData.get(position);

        TextView appName = (TextView) convertView.findViewById(R.id.appNameTextView);
        appName.setText(app.getTitle());
        if(app.isSearched())
        {
            convertView.setBackgroundColor(Color.CYAN);
        }

        ImageView im= (ImageView)convertView.findViewById(R.id.imageView);
        new getImagee().execute(new StringImage(im,app.getImageThumbnail()));
        return convertView;
    }


    public class getImagee extends AsyncTask<StringImage, Void, Bitmap>{
        ImageView img;
        @Override
        protected Bitmap doInBackground(StringImage... params) {
            try{
                img=params[0].getIv();
                URL url = new URL(params[0].getBitmap());
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

        @Override
        protected void onPostExecute(Bitmap bitmap) {
           // imageView= (ImageView)convertView.findViewById(R.id.imageView);
            if(bitmap != null)
                img.setImageBitmap(bitmap);
        }
    }
}
