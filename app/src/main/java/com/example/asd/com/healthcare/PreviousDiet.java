package com.example.asd.com.healthcare;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PreviousDiet extends AppCompatActivity {
    SessionManager sessionManager;
    DietAdapter dietAdapter;
    DietInfo dietInfo;
    ArrayList<Diet> dietList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_diet);
        sessionManager = new SessionManager(this);
        sessionManager.checkSessionValidity();
        Intent intent = getIntent();
        int memberId=intent.getExtras().getInt("id");
        dietInfo = new DietInfo(this);
        dietList = new ArrayList<>();
        dietList = dietInfo.getMemberPreviousDietListByMemberId(memberId);
        dietAdapter = new DietAdapter(this,dietList);
        listView=(ListView)findViewById(R.id.profileListView);
        listView.setAdapter(dietAdapter);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        ((TextView)findViewById(R.id.setTitile)).setText("Previous Diet");
        toolbar.setBackgroundColor(Color.parseColor("#8bc34a"));
        setSupportActionBar(toolbar);
    }
}
