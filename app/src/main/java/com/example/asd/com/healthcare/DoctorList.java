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

public class DoctorList extends AppCompatActivity {
    SessionManager sessionManager;
    DoctorAdapter doctorAdapter;
    ListView listView;
    DoctorInfo doctorInfo;
    ArrayList<Doctor> doctorList;
    int memberId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);
        sessionManager = new SessionManager(this);
        sessionManager.checkSessionValidity();
        doctorInfo = new DoctorInfo(this);
        doctorList = new ArrayList<>();
        Intent intent = getIntent();
        memberId=intent.getExtras().getInt("id");
        doctorList = doctorInfo.getDoctorListByMemberId(memberId);
        doctorAdapter = new DoctorAdapter(this,doctorList);
        listView=(ListView)findViewById(R.id.doctorListView);
        listView.setAdapter(doctorAdapter);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        ((TextView)findViewById(R.id.setTitile)).setText("Doctor List");
        toolbar.setBackgroundColor(Color.parseColor("#8bc34a"));
        setSupportActionBar(toolbar);
    }

    public void addDoctor(View view) {
        Intent intent = new Intent(this,AddDoctor.class);
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
