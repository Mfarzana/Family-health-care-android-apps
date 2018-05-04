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

public class HospitalList extends AppCompatActivity {
    SessionManager sessionManager;
    HospitalAdapter hospitalAdapter;
    ListView listView;
    HospitalInfo hospitalInfo;
    ArrayList<Hospital> hospitalList;
    int memberId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_list);
        sessionManager = new SessionManager(this);
        sessionManager.checkSessionValidity();
        hospitalInfo = new HospitalInfo(this);
        hospitalList = new ArrayList<>();
        Intent intent = getIntent();
        memberId=intent.getExtras().getInt("id");
        hospitalList = hospitalInfo.getHospitalListByMemberId(memberId);
        hospitalAdapter = new HospitalAdapter(this,hospitalList);
        listView=(ListView)findViewById(R.id.hospitalListView);
        listView.setAdapter(hospitalAdapter);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        ((TextView)findViewById(R.id.setTitile)).setText("Hospital List");
        toolbar.setBackgroundColor(Color.parseColor("#8bc34a"));
        setSupportActionBar(toolbar);
    }

    public void addHospital(View view) {
        Intent intent = new Intent(this,AddHospital.class);
        intent.putExtra("id",memberId);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }



    public void signOut(MenuItem item) {
        sessionManager.sessionSignOut();
    }

    public void home(MenuItem item) {
        AllProfileList allProfileList = new AllProfileList(this);
        SingleProfileInfo singleProfileInfo= allProfileList.getProfileById(memberId);
        if(sessionManager.getAdminPinCode().equals(sessionManager.getLogedPin())){
            Intent intent= new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
        else {
            Intent intent=new Intent(getApplicationContext(),MemberHomePageActivity.class);
            intent.putExtra("id",memberId);
            startActivity(intent);
        }
    }


}
