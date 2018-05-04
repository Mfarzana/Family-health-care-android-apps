package com.example.asd.com.healthcare;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddPrescription extends AppCompatActivity {
    private SessionManager sessionManager;
    private Spinner bloodGroupSpinner;
    private Button btBirthDay;
    private int year,month,day;
    private Calendar calendar;
    private String profileBirthday;
    private int profileId;

    private static int screenHieght;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prescription);
        sessionManager = new SessionManager(this);
        sessionManager.checkSessionValidity();
        ((TextView)findViewById(R.id.setTitile)).setText("Add Prescription");
        btBirthDay = (Button) findViewById(R.id.btBirthday);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        Intent intent = getIntent();
        profileId=intent.getExtras().getInt("id");

        setBloodGroupSpinner();
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        toolbar.setBackgroundColor(Color.parseColor("#ff760d"));
        setSupportActionBar(toolbar);

    }

    private void setBloodGroupSpinner(){
        bloodGroupSpinner = (Spinner) findViewById(R.id.spinnerBloodgroup);

        List<String> bloodGroup = new ArrayList<String>();
        bloodGroup.add("Select Doctor Name");
        DoctorInfo doctorInfo= new DoctorInfo( this);
        ArrayList<Doctor> doctorArrayList =  doctorInfo.getDoctorArrayList();
        for(int i=0; i<doctorArrayList.size(); i++){
            bloodGroup.add(doctorArrayList.get(i).getDoctorName());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bloodGroup);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodGroupSpinner.setAdapter(dataAdapter);
    }
    public void setDate(View view) {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            arg2++;
            profileBirthday = arg3+"/"+arg2+"/"+arg1;
            btBirthDay.setText(profileBirthday);

        }
    };


    public void AddPresscription(View view) {
        Intent intent = new Intent(this,MemberDoctorActivity.class);
        intent.putExtra("id",profileId);
        startActivity(intent);
    }
}
