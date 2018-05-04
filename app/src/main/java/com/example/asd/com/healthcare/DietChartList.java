package com.example.asd.com.healthcare;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DietChartList extends AppCompatActivity {
    SessionManager sessionManager;
    DietChartListAdapter dietChartListAdapter;
    ListView listView;
    DietInfo dietInfo;
    ArrayList<ChartList> chartLists;
    int memberId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_chart_list);
        Intent intent = getIntent();
        memberId=intent.getExtras().getInt("id");
        dietInfo = new DietInfo(this);
        chartLists = dietInfo.getDietChartList();
        dietChartListAdapter = new DietChartListAdapter(this,chartLists);
        listView=(ListView)findViewById(R.id.DietListChart);
        listView.setAdapter(dietChartListAdapter);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        ((TextView)findViewById(R.id.setTitile)).setText("Family Diet Chart");
        toolbar.setBackgroundColor(Color.parseColor("#9551fe"));
        setSupportActionBar(toolbar);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.only_back, menu);
        return true;
    }



    public void backepage(MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }

}
