package com.example.raghuveer.inclass06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetAppsList.IActivity{
    public static final String NEWS_KEYS = "KEYS";
    ArrayList<App> appArrayList,sortedEntries,lastEntries;
    EditText edit;
    ArrayAdapter<String> aa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appArrayList=new ArrayList<App>();
        setTitle("Apps Activity");
        edit=(EditText) findViewById(R.id.appNameTextView);
        new GetAppsList(this).execute("https://itunes.apple.com/us/rss/toppodcasts/limit=30/json");


    }

    @Override
    public void setUpList(ArrayList<App> app) {
        appArrayList=app;
    }

    public void onGoClick(View view)
    {
        ListView listView = (ListView) findViewById(R.id.listView);
        String searchName  = edit.getText().toString();
        if(searchName.equals(""))
        {
            Toast.makeText(this,"Enter search name", Toast.LENGTH_SHORT).show();
            return;
        }
        sortedEntries = new ArrayList<App>();
        lastEntries = new ArrayList<App>();
        for(App entry : appArrayList)
        {
            if(entry.getTitle().contains(searchName))
            {
                entry.setSearched(true);
                sortedEntries.add(entry);
            }
            else
            {
                entry.setSearched(false);
                lastEntries.add(entry);
            }
        }
        sortedEntries.addAll(lastEntries);
        appAdapter adapter = new appAdapter(this, R.layout.listviewlayout, sortedEntries);
        listView.setAdapter(adapter);
        adapter.setNotifyOnChange(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ItemDetailsActivity.class);
                intent.putExtra(NEWS_KEYS, appArrayList.get(position));
                startActivity(intent);

            }
        });
    }

    public void clear(View view)
    {
        for(App entry : appArrayList) {
            entry.setSearched(false);
        }
        String searchName  = edit.getText().toString();
       /* if(searchName.equals(""))
        {
            Toast.makeText(this,"Enter search name", Toast.LENGTH_SHORT).show();
            return;
        }*/
        ListView listView = (ListView) findViewById(R.id.listView);
        appAdapter adapter = new appAdapter(this, R.layout.listviewlayout, appArrayList);
        listView.setAdapter(adapter);
        adapter.setNotifyOnChange(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ItemDetailsActivity.class);
                intent.putExtra(NEWS_KEYS, appArrayList.get(position));
                startActivity(intent);

            }
        });
    }

}
