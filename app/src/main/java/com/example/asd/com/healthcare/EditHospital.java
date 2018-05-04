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
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class EditHospital extends AppCompatActivity {
    private SessionManager sessionManager;
    Hospital hospital;
    HospitalInfo hospitalInfo;
    private EditText etName;
    private EditText etState;
    private EditText etNumber;
    private EditText etEmail;
    private EditText etAddress;
    private String hospitalName;
    private String hospitalState;
    private String hospitalNumber;
    private String hospitalEmail;
    private String hospitalAddress;
    private int hospitalId;
    private static int screenHieght;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hospital);
        sessionManager = new SessionManager(this);
        sessionManager.checkSessionValidity();
        hospitalInfo = new HospitalInfo(this);
        ((TextView)findViewById(R.id.setTitile)).setText("Add Hospital");
        Intent intent = getIntent();
        hospitalId=intent.getExtras().getInt("id");
        etName = (EditText) findViewById(R.id.etName);
        etState = (EditText) findViewById(R.id.etstate);
        etNumber = (EditText) findViewById(R.id.etNumber);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etAddress = (EditText) findViewById(R.id.etAddress);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);

        hospital = hospitalInfo.getHospitalById(hospitalId);

        etName.setText(hospital.getHospitalName());
        etState.setText(hospital.getHospitalState());
        etNumber.setText(hospital.getHospitalNumber());
        etEmail.setText(hospital.getHospitalEmail());
        etAddress.setText(hospital.getHospitalAdress());

        toolbar.setBackgroundColor(Color.parseColor("#ff760d"));
        setSupportActionBar(toolbar);
    }

    public void saveHospital(View view) {
        screenHieght =((ScrollView)findViewById(R.id.scrollViewIdAddprofile)).getHeight();
        /*int h=((ScrollView)findViewById(R.id.scrollViewIdAddprofile)).getHeight();
        String v=String.valueOf(h);
        Toast.makeText(this,v,Toast.LENGTH_LONG).show();*/

        hospitalName=etName.getText().toString();
        hospitalState=etState.getText().toString();
        hospitalNumber=etNumber.getText().toString();
        hospitalEmail=etEmail.getText().toString();
        hospitalAddress=etAddress.getText().toString();

        if(!hospitalName.equals(""))
            hospitalName=hospitalName.substring(0,1).toUpperCase()+hospitalName.substring(1);
        HospitalInfo hospitalInfo = new HospitalInfo(this);
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

        if(hospitalName.equals("") ||  hospitalName.length()<4 ) {

            int nameFieldPosition =(screenHieght-230)+position;
            if(hospitalName.equals("")) {
                if (screenHieght < nameFieldPosition)
                    ((TextView) view1.findViewById(R.id.inputFieldTostTextMES)).setText("Please fill out name field.");
                custom_tost.setGravity(Gravity.BOTTOM, 30, nameFieldPosition);
                custom_tost.setDuration(Toast.LENGTH_LONG);
                custom_tost.setView(view1);
                custom_tost.show();
            }
            else if(hospitalName.length()<4 ){
                ((TextView) view1.findViewById(R.id.inputFieldTostTextMES)).setText("Menu lengh must be 4.");
                custom_tost.setGravity(Gravity.BOTTOM, 30, nameFieldPosition);
                custom_tost.setDuration(Toast.LENGTH_LONG);
                custom_tost.setView(view1);
                custom_tost.show();
            }
        }
        else if(hospitalState.equals("") ){
            int birthdayFieldPosition =(screenHieght-390 )+position;

            if (screenHieght < birthdayFieldPosition)
                ((TextView) view1.findViewById(R.id.inputFieldTostTextMES)).setText("Please fill out dprt field.");
            custom_tost.setGravity(Gravity.BOTTOM, 30, birthdayFieldPosition);
            custom_tost.setDuration(Toast.LENGTH_LONG);
            custom_tost.setView(view1);
            custom_tost.show();

        }
        else if(hospitalNumber.equals("")){
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

            boolean updated = hospitalInfo.updateHospital(hospital.getHospitalID(),hospital.getMemberID(),hospitalName, hospitalState, hospitalNumber, hospitalEmail, hospitalAddress);
            if(updated){
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                dlgAlert.setMessage("Update hospital Sucessfuly");
                dlgAlert.setIcon(R.drawable.ic_launcher);
                dlgAlert.setTitle("Update Hospital");
                dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(),HospitalList.class);
                        intent.putExtra("id",Integer.valueOf(hospital.getMemberID()));
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
        Intent intent = new Intent(getApplicationContext(), HospitalList.class);
        intent.putExtra("id", Integer.valueOf(hospital.getMemberID()));
        startActivity(intent);
    }
}
