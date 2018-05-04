package com.example.asd.com.healthcare;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ProfileActivity extends AppCompatActivity {
    SessionManager sessionManager;
    ProfileAdapter profileAdapter;
    ListView listView;
    AllProfileList allProfileList;
    ArrayList<SingleProfileInfo> singleProfileInfoArrayList;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        sessionManager = new SessionManager(this);
        sessionManager.checkSessionValidity();
        allProfileList = new AllProfileList(this);
        singleProfileInfoArrayList = new ArrayList<>();
        singleProfileInfoArrayList = allProfileList.getSingleProfileInfoArrayList();
        profileAdapter = new ProfileAdapter(this,singleProfileInfoArrayList);
        listView=(ListView)findViewById(R.id.profileListView);
        listView.setAdapter(profileAdapter);
        Intent intent=getIntent();
        String cmd=intent.getExtras().getString("do");
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        ((TextView)findViewById(R.id.setTitile)).setText("Manage Member");
        toolbar.setBackgroundColor(Color.parseColor("#8bc34a"));
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    public void signOut(MenuItem item) {
        sessionManager.sessionSignOut();
    }

    public void addProfile(View view) {
        Intent intent = new Intent(this,AddProfileActivity.class);
        startActivity(intent);
    }

    public void home(MenuItem item) {
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }
}
