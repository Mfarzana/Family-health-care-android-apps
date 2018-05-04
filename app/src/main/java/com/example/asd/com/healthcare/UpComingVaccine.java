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

public class UpComingVaccine extends AppCompatActivity {
    SessionManager sessionManager;
    VaccineAdapter vaccineAdapter;
    VaccineInfo vaccineInfo;
    ArrayList<Vaccine> vaccineList;
    ListView listView;
    int memberId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_coming_vaccine);
        setContentView(R.layout.activity_up_coming_vaccine);
        setContentView(R.layout.activity_previous_vaccine);
        sessionManager = new SessionManager(this);
        sessionManager.checkSessionValidity();
        Intent intent = getIntent();
        memberId=intent.getExtras().getInt("id");
        vaccineInfo = new VaccineInfo(this);
        vaccineList = new ArrayList<>();
        vaccineList = vaccineInfo.getMemberUpcomingVaccineListByMemberId(memberId);
        vaccineAdapter = new VaccineAdapter(this,vaccineList);
        listView=(ListView)findViewById(R.id.profileListView);
        listView.setAdapter(vaccineAdapter);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        ((TextView)findViewById(R.id.setTitile)).setText("Up Coming Vaccine");
        toolbar.setBackgroundColor(Color.parseColor("#8bc34a"));
        setSupportActionBar(toolbar);
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
