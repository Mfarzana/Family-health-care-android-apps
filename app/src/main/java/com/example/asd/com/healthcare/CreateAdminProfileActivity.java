package com.example.asd.com.healthcare;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateAdminProfileActivity extends AppCompatActivity {
    private SessionManager sessionManager;
    private Spinner bloodGroupSpinner;
    private Button btBirthDay;
    private int year,month,day;
    private Calendar calendar;
    private EditText etName;
    private EditText etHieghtFt;
    private EditText etHieghtIn;
    private EditText etWeight;
    private String profilePincode;
    private String profileName;
    private String profileBirthday;
    private String profileGender;
    private String profileBloodgroup;
    private String profileRelation;
    private String profileHieght;
    private String profileWeight;
    private static int screenHieght;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_admin_profile);
        sessionManager = new SessionManager(this);
        ((TextView)findViewById(R.id.setTitile)).setText("Add Profile");
        profilePincode=sessionManager.getAdminPinCode();
        btBirthDay = (Button) findViewById(R.id.btBirthday);
        etName = (EditText) findViewById(R.id.etName);
        etHieghtFt = (EditText)findViewById(R.id.etHieghtFt);
        etHieghtIn = (EditText)findViewById(R.id.etHieghtIn);
        etWeight = (EditText) findViewById(R.id.etweight);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        profileBirthday="";
        profileGender="";
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        setBloodGroupSpinner();
        profileBloodgroup="";
        profileRelation="";
    }



    public void ganderRbutton(View view) {
        if(view.getId()==R.id.maleRbutton)
        {
            profileGender="Male";
            profileRelation="";
        }
        else
        {
            profileGender="Female";
            profileRelation="";
        }


    }



    private void setBloodGroupSpinner(){
        bloodGroupSpinner = (Spinner) findViewById(R.id.spinnerBloodgroup);

        List<String> bloodGroup = new ArrayList<String>();
        bloodGroup.add("Select blood group");
        bloodGroup.add("A+");
        bloodGroup.add("A-");
        bloodGroup.add("B+");
        bloodGroup.add("B-");
        bloodGroup.add("O+");
        bloodGroup.add("O-");
        bloodGroup.add("AB+");
        bloodGroup.add("AB-");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bloodGroup);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodGroupSpinner.setAdapter(dataAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.only_back, menu);
        return true;
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


    public void createProfile(View view) {
        screenHieght =((ScrollView)findViewById(R.id.scrollViewIdAddprofile)).getHeight();
        /*int h=((ScrollView)findViewById(R.id.scrollViewIdAddprofile)).getHeight();
        String v=String.valueOf(h);
        Toast.makeText(this,v,Toast.LENGTH_LONG).show();*/
        profileName = etName.getText().toString();
        profileHieght = etHieghtFt.getText().toString()+'`'+etHieghtIn.getText().toString()+'"';
        profileWeight = etWeight.getText().toString();
        profileBloodgroup = bloodGroupSpinner.getSelectedItem().toString();
        profileName=profileName.substring(0,1).toUpperCase()+profileName.substring(1);
        profileRelation ="Myself";
        AllProfileList allProfileList = new AllProfileList(this);
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

        if(profileName.equals("") ||  profileName.length()<4 ) {

            int nameFieldPosition =(screenHieght-240)+position;
            if(profileName.equals("")) {
                if (screenHieght < nameFieldPosition)
                    ((TextView) view1.findViewById(R.id.inputFieldTostTextMES)).setText("Please fill out name field.");
                custom_tost.setGravity(Gravity.BOTTOM, 30, nameFieldPosition);
                custom_tost.setDuration(Toast.LENGTH_LONG);
                custom_tost.setView(view1);
                custom_tost.show();
            }
            else if(profileName.length()<4 ){
                ((TextView) view1.findViewById(R.id.inputFieldTostTextMES)).setText("Name lengh must be 4.");
                custom_tost.setGravity(Gravity.BOTTOM, 30, nameFieldPosition);
                custom_tost.setDuration(Toast.LENGTH_LONG);
                custom_tost.setView(view1);
                custom_tost.show();
            }
        }
        else if(profileBirthday.equals("")){
            int birthdayFieldPosition =(screenHieght-400 )+position;
            if(screenHieght<birthdayFieldPosition)
                ((TextView) view1.findViewById(R.id.inputFieldTostTextMES)).setText("Please fill out birth field.");
            custom_tost.setGravity(Gravity.BOTTOM,30,birthdayFieldPosition);
            custom_tost.setDuration(Toast.LENGTH_LONG);
            custom_tost.setView(view1);
            custom_tost.show();
        }
        else if(profileGender.equals("")){
            int genderFieldPosition =(screenHieght-560)+position;
            if(screenHieght<genderFieldPosition)
                ((TextView) view1.findViewById(R.id.inputFieldTostTextMES)).setText("Please fill out gender field.");
            custom_tost.setGravity(Gravity.BOTTOM,30,genderFieldPosition);
            custom_tost.setDuration(Toast.LENGTH_LONG);
            custom_tost.setView(view1);
            custom_tost.show();
        }
        else if(profileBloodgroup.equals("Select blood group")){
            int profileBloodgroupFieldPosition =(screenHieght-720)+position;
            if(screenHieght<profileBloodgroupFieldPosition)
                ((TextView) view1.findViewById(R.id.inputFieldTostTextMES)).setText("Please fill out blood field.");
            custom_tost.setGravity(Gravity.BOTTOM,30,profileBloodgroupFieldPosition);
            custom_tost.setDuration(Toast.LENGTH_LONG);
            custom_tost.setView(view1);
            custom_tost.show();
        }
        else if(profileHieght.equals("`"+'"')){
            int profileHieghtFieldPosition =(screenHieght-880)+position;
            custom_tost.setGravity(Gravity.BOTTOM,30,profileHieghtFieldPosition);
            custom_tost.setDuration(Toast.LENGTH_LONG);
            custom_tost.setView(view1);
            custom_tost.show();
        }
        else if(profileWeight.equals("")){
            int profileWeightFieldPosition =(screenHieght-1050)+position;
            custom_tost.setGravity(Gravity.BOTTOM,30,profileWeightFieldPosition);
            custom_tost.setDuration(Toast.LENGTH_LONG);
            custom_tost.setView(view1);
            custom_tost.show();
        }
        else
        {
            boolean inserted = allProfileList.addSingleProfile(0,profilePincode,profileName, profileBirthday, profileGender, profileBloodgroup, profileRelation, profileHieght, profileWeight);
            if(inserted){
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                dlgAlert.setMessage("Profile Create Sucessfuly Done");
                dlgAlert.setIcon(R.drawable.ic_launcher);
                dlgAlert.setTitle("Create Profile");
                dlgAlert.setCancelable(false);
                dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);
                    }
                });
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
            }

        }

    }

    public void backepage(MenuItem item) {
        Intent intent = new Intent(this,SetPinActivity.class);
        startActivity(intent);
    }
}
