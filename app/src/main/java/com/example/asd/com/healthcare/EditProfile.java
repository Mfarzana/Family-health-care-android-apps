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
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EditProfile extends AppCompatActivity {
    private SessionManager sessionManager;
    private AllProfileList allProfileList;
    private SingleProfileInfo singleProfileInfo;
    private Spinner bloodGroupSpinner;
    private Spinner relationSpinner;
    private Button btBirthDay;
    private int year,month,day;
    private Calendar calendar;
    private EditText etName;
    private EditText etHieghtFt;
    private EditText etHieghtIn;
    private EditText etWeight;
    private int ProfileID;
    private String profileName;
    private String profileBirthday;
    private String profileGender;
    private String profileBloodgroup;
    private String profileRelation;
    private String profileHieght;
    private String profileWeight;
    private static int screenHieght;
    private int profileId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        sessionManager = new SessionManager(this);
        sessionManager.checkSessionValidity();
        ((TextView)findViewById(R.id.setTitile)).setText("Edit Profile");
        btBirthDay = (Button) findViewById(R.id.btBirthday);
        etName = (EditText) findViewById(R.id.etName);
        etHieghtFt = (EditText)findViewById(R.id.etHieghtFt);
        etHieghtIn = (EditText)findViewById(R.id.etHieghtIn);
        etWeight = (EditText) findViewById(R.id.etweight);

        Intent intent=getIntent();
        profileId=intent.getExtras().getInt("id");
        allProfileList = new AllProfileList(this);
        singleProfileInfo = allProfileList.getProfileById(profileId);
        etName.setText(singleProfileInfo.getProfileName());
        btBirthDay.setText(singleProfileInfo.getProfileBirthdy());
        ProfileID=singleProfileInfo.getProfileID();
        profileBirthday=singleProfileInfo.getProfileBirthdy();
        profileGender=singleProfileInfo.getProfileGender();
        profileBloodgroup=singleProfileInfo.getProfileBloodgroup();
        profileRelation=singleProfileInfo.getProfileRelation();
        String hieght=singleProfileInfo.getProfileHieght();
        String ftHieght;
        String inHieght;

        int i;
        for( i=0;hieght.charAt(i)!='`';i++){
        }
        ftHieght=hieght.substring(0,i);
        int j=i+1;
        for(i=i+1; hieght.charAt(i)!='"';i++){

        }
        inHieght=hieght.substring(j,i);

        etHieghtFt.setText(ftHieght.toString());
        etHieghtIn.setText(inHieght.toString());
        etWeight.setText(singleProfileInfo.getProfileWeight());
        if(singleProfileInfo.getProfileGender().equals("Female")) {
            RadioButton radioButtonGender = (RadioButton) findViewById(R.id.femaleRbutton);
            radioButtonGender.setChecked(true);
        }
        else {
            RadioButton radioButtonGender = (RadioButton) findViewById(R.id.maleRbutton);
            radioButtonGender.setChecked(true);
        }

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        setBloodGroupSpinner();
        setRelationSpinner();

    }

    public void ganderRbutton(View view) {
        if(view.getId()==R.id.maleRbutton)
        {
            setMaleRelationSpinner();
            profileGender="Male";

        }
        else
        {
            setFemaleRelationSpinner();
            profileGender="Female";

        }

    }

    private void setFemaleRelationSpinner(){
        relationSpinner = (Spinner) findViewById(R.id.spinnerRelation);
        List<String> relation = new ArrayList<String>();
        relation.add("Select relation");
        relation.add("Mother");
        relation.add("Aunty");
        relation.add("Partner");
        relation.add("Sister");
        relation.add("Cousin");
        relation.add("Daughter");
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, relation);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        relationSpinner.setAdapter(dataAdapter1);
    }

    private void setMaleRelationSpinner(){
        relationSpinner = (Spinner) findViewById(R.id.spinnerRelation);
        List<String> relation = new ArrayList<String>();
        relation.add("Select relation");
        relation.add("Father");
        relation.add("Uncle");
        relation.add("Partner");
        relation.add("Brother");
        relation.add("Cousin");
        relation.add("Son");
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, relation);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        relationSpinner.setAdapter(dataAdapter1);
    }

    private void setRelationSpinner(){
        relationSpinner = (Spinner) findViewById(R.id.spinnerRelation);
        List<String> relation = new ArrayList<String>();
        relation.add("Select relation");
        relation.add("Father");
        relation.add("Mother");
        relation.add("Uncle");
        relation.add("Aunty");
        relation.add("Brother");
        relation.add("Sister");
        relation.add("Son");
        relation.add("Daughter");
        relation.add("Cousin");
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, relation);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        relationSpinner.setAdapter(dataAdapter1);
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
        profileWeight = etWeight.getText().toString();

        if(!bloodGroupSpinner.getSelectedItem().toString().equals("Select blood group"))
            profileBloodgroup = bloodGroupSpinner.getSelectedItem().toString();
        if(!relationSpinner.getSelectedItem().toString().equals("Select relation"))
            profileRelation = relationSpinner.getSelectedItem().toString();
        profileHieght = etHieghtFt.getText().toString()+'`'+etHieghtIn.getText().toString()+'"';
        profileName=profileName.substring(0,1).toUpperCase()+profileName.substring(1);
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
        else if(profileRelation.equals("Select relation")){
            int profileRelationFieldPosition =(screenHieght-880 )+position;
            if(screenHieght<profileRelationFieldPosition)
                ((TextView) view1.findViewById(R.id.inputFieldTostTextMES)).setText("Please fill out relation field.");
            custom_tost.setGravity(Gravity.BOTTOM,30,profileRelationFieldPosition);
            custom_tost.setDuration(Toast.LENGTH_LONG);
            custom_tost.setView(view1);
            custom_tost.show();
        }
        else if(profileHieght.equals("")){
            int profileHieghtFieldPosition =(screenHieght-1050 )+position;
            custom_tost.setGravity(Gravity.BOTTOM,30,profileHieghtFieldPosition);
            custom_tost.setDuration(Toast.LENGTH_LONG);
            custom_tost.setView(view1);
            custom_tost.show();
        }
        else if(profileWeight.equals("")){
            int profileWeightFieldPosition =(screenHieght-1210 )+position;
            custom_tost.setGravity(Gravity.BOTTOM,30,profileWeightFieldPosition);
            custom_tost.setDuration(Toast.LENGTH_LONG);
            custom_tost.setView(view1);
            custom_tost.show();
        }
        else
        {
            boolean updated = allProfileList.updateSingleProfile(ProfileID, sessionManager.getPinCode(), profileName, profileBirthday, profileGender, profileBloodgroup, profileRelation, profileHieght, profileWeight);
            if(updated){
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                dlgAlert.setMessage("Profile Update Sucessfuly Done");
                dlgAlert.setIcon(R.drawable.ic_launcher);
                dlgAlert.setTitle("Update Profile");
                dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                        intent.putExtra("do","");
                        startActivity(intent);
                    }
                });

                dlgAlert.setCancelable(false);
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
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        intent.putExtra("id", profileId);
        startActivity(intent);
    }
}
