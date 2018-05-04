package com.example.asd.com.healthcare;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddDoctor extends AppCompatActivity {
    private SessionManager sessionManager;
    DoctorInfo doctorInfo;
    private EditText etName;
    private EditText etDprt;
    private EditText etNumber;
    private EditText etEmail;
    private EditText etAddress;
    private String doctorName;
    private String doctorDprt;
    private String doctorNumber;
    private String doctorEmail;
    private String doctorAddress;
    private int profileId;
    private static int screenHieght;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);
        sessionManager = new SessionManager(this);
        sessionManager.checkSessionValidity();
        doctorInfo = new DoctorInfo(this);
        ((TextView)findViewById(R.id.setTitile)).setText("Add Doctor");
        Intent intent = getIntent();
        profileId=intent.getExtras().getInt("id");
        etName = (EditText) findViewById(R.id.etName);
        etDprt = (EditText) findViewById(R.id.etdprt);
        etNumber = (EditText) findViewById(R.id.etNumber);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etAddress = (EditText) findViewById(R.id.etAddress);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        toolbar.setBackgroundColor(Color.parseColor("#ff760d"));
        setSupportActionBar(toolbar);


    }

    public void saveDoctor(View view) {
        screenHieght =((ScrollView)findViewById(R.id.scrollViewIdAddprofile)).getHeight();
        /*int h=((ScrollView)findViewById(R.id.scrollViewIdAddprofile)).getHeight();
        String v=String.valueOf(h);
        Toast.makeText(this,v,Toast.LENGTH_LONG).show();*/

        doctorName=etName.getText().toString();
        doctorDprt=etDprt.getText().toString();
        doctorNumber=etNumber.getText().toString();
        doctorEmail=etEmail.getText().toString();
        doctorAddress=etAddress.getText().toString();

        if(!doctorName.equals(""))
            doctorName=doctorName.substring(0,1).toUpperCase()+doctorName.substring(1);
        DoctorInfo doctorInfo = new DoctorInfo(this);
        LayoutInflater inflater = getLayoutInflater();
        View view1 = inflater.inflate(R.layout.input_field_tost, (ViewGroup) findViewById(R.id.rootContent));
        final Toast custom_tost =new  Toast(getApplicationContext());
        View scrollViewView= (ScrollView)findViewById(R.id.scrollViewIdAddprofile);
        int position = scrollViewView.getScrollY();

        if(position!=0){
            if(600<position)
                position+=110;
            else if(300<position)
                position+=100;
            else if(200<position)
                position+=80;
            else if(100<position)
                position+=60;
            else if(50<position)
                position+=30;
            screenHieght+=position;
        }

        if(doctorName.equals("") ||  doctorName.length()<4 ) {

            int nameFieldPosition =(screenHieght-230)+position;
            if(doctorName.equals("")) {
                if (screenHieght < nameFieldPosition)
                    ((TextView) view1.findViewById(R.id.inputFieldTostTextMES)).setText("Please fill out name field.");
                custom_tost.setGravity(Gravity.BOTTOM, 30, nameFieldPosition);
                custom_tost.setDuration(Toast.LENGTH_LONG);
                custom_tost.setView(view1);
                custom_tost.show();
            }
            else if(doctorName.length()<4 ){
                ((TextView) view1.findViewById(R.id.inputFieldTostTextMES)).setText("Menu lengh must be 4.");
                custom_tost.setGravity(Gravity.BOTTOM, 30, nameFieldPosition);
                custom_tost.setDuration(Toast.LENGTH_LONG);
                custom_tost.setView(view1);
                custom_tost.show();
            }
        }
        else if(doctorDprt.equals("") ){
            int birthdayFieldPosition =(screenHieght-390 )+position;

                if (screenHieght < birthdayFieldPosition)
                    ((TextView) view1.findViewById(R.id.inputFieldTostTextMES)).setText("Please fill out dprt field.");
                custom_tost.setGravity(Gravity.BOTTOM, 30, birthdayFieldPosition);
                custom_tost.setDuration(Toast.LENGTH_LONG);
                custom_tost.setView(view1);
                custom_tost.show();

        }
        else if(doctorNumber.equals("")){
            int genderFieldPosition =(screenHieght-550)+position;
            if(screenHieght<genderFieldPosition)
                ((TextView) view1.findViewById(R.id.inputFieldTostTextMES)).setText("Please fill out number field");
            custom_tost.setGravity(Gravity.BOTTOM,30,genderFieldPosition);
            custom_tost.setDuration(Toast.LENGTH_LONG);
            custom_tost.setView(view1);
            custom_tost.show();
        }
        else
        {
            boolean inserted = doctorInfo.addDoctor(0,String.valueOf(profileId),doctorName, doctorDprt, doctorNumber, doctorEmail, doctorAddress);
            if(inserted){
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                dlgAlert.setMessage("Save doctor Sucessfuly");
                dlgAlert.setIcon(R.drawable.ic_launcher);
                dlgAlert.setTitle("Save Doctor");
                dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(),DoctorList.class);
                        intent.putExtra("id",profileId);
                        startActivity(intent);
                    }
                });
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();

            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.only_back, menu);
        return true;
    }



    public void backepage(MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), DoctorList.class);
        intent.putExtra("id", profileId);
        startActivity(intent);
    }



}
