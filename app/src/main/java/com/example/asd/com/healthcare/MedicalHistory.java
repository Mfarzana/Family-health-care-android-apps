package com.example.asd.com.healthcare;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

public class MedicalHistory extends AppCompatActivity {
    private SessionManager sessionManager;
    AllProfileList allProfileList;
    private int profileId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_history);
        sessionManager = new SessionManager(this);
        sessionManager.checkSessionValidity();
        String profilePinCode = sessionManager.getPinCode();
        allProfileList =new AllProfileList(this);
        Intent intent = getIntent();
        profileId=intent.getExtras().getInt("id");
        ((TextView)findViewById(R.id.setTitile)).setText("Medical History");
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        toolbar.setBackgroundColor(Color.parseColor("#8bc34a"));
        setSupportActionBar(toolbar);

    }

    public void addPrescription(View view) {
        Intent intent = new Intent(this,AddPrescription.class);
        intent.putExtra("id",profileId);
        startActivity(intent);
    }
}
