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
import android.widget.ImageView;
import android.widget.TextView;

import static android.content.Intent.ACTION_CALL;

public class HospitalDetailsActivity extends AppCompatActivity {
    HospitalInfo hospitalInfo;
    Hospital hospital;
    private TextView hospitalName;
    private TextView hospitalState;
    private TextView hospitalNumber;
    private TextView hospitalEmail;
    private TextView hospitalAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_details);
        hospitalName =(TextView)findViewById(R.id.tvName);
        hospitalNumber =(TextView)findViewById(R.id.tvNumber);
        hospitalState =(TextView)findViewById(R.id.tvState);
        hospitalEmail =(TextView)findViewById(R.id.tvEmail);
        hospitalAddress =(TextView)findViewById(R.id.tvAddress);
        hospitalInfo = new HospitalInfo(this);
        Intent intent = getIntent();
        int hospitalId=intent.getExtras().getInt("id");
        hospital=hospitalInfo.getHospitalById(hospitalId);
        hospitalName.setText(hospital.getHospitalName());
        hospitalNumber.setText(hospital.getHospitalNumber());
        hospitalState.setText(hospital.getHospitalState());
        hospitalEmail.setText(hospital.getHospitalEmail());
        hospitalAddress.setText(hospital.getHospitalAdress());
        ((TextView)findViewById(R.id.setTitile)).setText("Hospital");
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        toolbar.setBackgroundColor(Color.parseColor("#9551fe"));
        setSupportActionBar(toolbar);
    }

    public void makeCall(View view) {
        Intent in = new Intent(ACTION_CALL, Uri.parse("tel:" + hospital.getHospitalNumber()));
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
        Intent intent = new Intent(getApplicationContext(), HospitalList.class);
        intent.putExtra("id", Integer.valueOf(hospital.getMemberID()));
        startActivity(intent);
    }

}
