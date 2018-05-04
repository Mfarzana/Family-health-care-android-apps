package com.example.asd.com.healthcare;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import static android.content.Intent.ACTION_CALL;

public class DoctorDetailsActivity extends AppCompatActivity {
    DoctorInfo doctorInfo;
    Doctor doctor;
    private TextView doctorName;
    private TextView doctorDprt;
    private TextView doctorNumber;
    private TextView doctorEmail;
    private TextView doctorAddress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        doctorName =(TextView)findViewById(R.id.tvName);
        doctorNumber =(TextView)findViewById(R.id.tvNumber);
        doctorDprt =(TextView)findViewById(R.id.tvDprt);
        doctorEmail =(TextView)findViewById(R.id.tvEmail);
        doctorAddress =(TextView)findViewById(R.id.tvAddress);
        doctorInfo = new DoctorInfo(this);
        Intent intent = getIntent();
        int doctorId=intent.getExtras().getInt("id");
        doctor=doctorInfo.getDoctorById(doctorId);
        ((ImageView) findViewById(R.id.imgPropic)).setImageResource(R.drawable.profilemale);
        doctorName.setText(doctor.getDoctorName());
        doctorNumber.setText(doctor.getDoctorNumber());
        doctorDprt.setText(doctor.getDoctorDepartment());
        doctorEmail.setText(doctor.getDoctorEmail());
        doctorAddress.setText(doctor.getDoctorAdress());
        ((TextView)findViewById(R.id.setTitile)).setText("Doctor");
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        toolbar.setBackgroundColor(Color.parseColor("#9551fe"));
        setSupportActionBar(toolbar);
    }

    public void makeCall(View view) {
        Intent in = new Intent(ACTION_CALL, Uri.parse("tel:" + doctor.getDoctorNumber()));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(in);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.only_back, menu);
        return true;
    }



    public void backepage(MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), DoctorList.class);
        intent.putExtra("id", Integer.valueOf(doctor.getMemberID()));
        startActivity(intent);
    }






}
