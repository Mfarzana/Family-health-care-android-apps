package com.example.asd.com.healthcare;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class Adddiet extends AppCompatActivity {
    private SessionManager sessionManager;
    SingleProfileInfo singleProfileInfo;
    AllProfileList allProfileList;
    private Button setDate;
    private Button setTime;
    private int year,month,day;
    private int ry,rm,rd,rh,rmin,rmin_f;
    private int hour,min;
    private Calendar calendar;
    private EditText etMenu;
    private String dietDate;
    private String dietTime;
    private String dietMenu;
    private String dietType;
    private String dietAlam;
    private String dietRemainder;
    private int profileId;
    private static int screenHieght;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddiet);
        sessionManager = new SessionManager(this);
        sessionManager.checkSessionValidity();
        allProfileList = new AllProfileList(this);
        Intent intent = getIntent();
        profileId=intent.getExtras().getInt("id");
        singleProfileInfo =allProfileList.getProfileById(profileId);
        ((TextView)findViewById(R.id.setTitile)).setText("Add Diet");
        etMenu = (EditText) findViewById(R.id.etMenu);
        setDate = (Button) findViewById(R.id.btDatePicker);
        setTime = (Button) findViewById(R.id.btTimePicker);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        toolbar.setBackgroundColor(Color.parseColor("#ff760d"));
        setSupportActionBar(toolbar);
        dietType="";
        dietDate="";
        dietTime="";
        dietAlam="off";
        dietRemainder="off";

    }



    public void alamRbutton(View view) {

        if(view.getId()==R.id.deitAlam)
        {
            dietAlam="on";
            dietRemainder="off";
        }
        else
        {
            dietRemainder="on";
            dietAlam="off";
        }

    }



    public void dietRbutton(View view) {
        if(view.getId()==R.id.dinerRbutton)
        {
            dietType="Dinner";
        }
        else if(view.getId()==R.id.breckfastRbutton)
        {
            dietType="Breakfast";
        }
        else
        {
            dietType="Lunch";
        }
    }


    public void setTate(View view) {
        showDialog(9999);
    }

    public void setDate(View view) {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        else if(id==9999){
            return new TimePickerDialog(this,
                    timePickerListener, hour, min,false);
        }
        return null;
    }
    private TimePickerDialog.OnTimeSetListener timePickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int selectedHour,
                                      int selectedMinute) {
                    hour = selectedHour;
                    min = selectedMinute;
                    rh=view.getCurrentHour();
                    rmin_f=view.getCurrentMinute()-30;
                    rmin=view.getCurrentMinute();

                    // set current time into textview
                    setTime.setText(new StringBuilder().append(pad(hour))
                            .append(":").append(pad(min)));
                    dietTime =(pad(hour))+(":")+(pad(min));

                }
            };

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }



    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            arg2++;
            ry=arg0.getYear();
            rm=arg0.getMonth();
            rd=arg0.getDayOfMonth();
            dietDate = arg3+"/"+arg2+"/"+arg1;
            setDate.setText(dietDate);
        }
    };


    public void saveDiet(View view) {
        screenHieght =((ScrollView)findViewById(R.id.scrollViewIdAddprofile)).getHeight();
        /*int h=((ScrollView)findViewById(R.id.scrollViewIdAddprofile)).getHeight();
        String v=String.valueOf(h);
        Toast.makeText(this,v,Toast.LENGTH_LONG).show();*/

        dietMenu = etMenu.getText().toString();
        if(!dietMenu.equals(""))
            dietMenu=dietMenu.substring(0,1).toUpperCase()+dietMenu.substring(1);
        DietInfo dietInfo = new DietInfo(this);
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

        if(dietType.equals("")){
            int typeFieldPosition =(screenHieght-230 )+position;
            if(screenHieght<typeFieldPosition)
                ((TextView) view1.findViewById(R.id.inputFieldTostTextMES)).setText("Please fill out Diet Type.");
            custom_tost.setGravity(Gravity.BOTTOM,30,typeFieldPosition);
            custom_tost.setDuration(Toast.LENGTH_LONG);
            custom_tost.setView(view1);
            custom_tost.show();
        }
        else if(dietMenu.equals("") ||  dietMenu.length()<2 ) {

            int nameFieldPosition =(screenHieght-390)+position;
            if(dietMenu.equals("")) {
                if (screenHieght < nameFieldPosition)
                    ((TextView) view1.findViewById(R.id.inputFieldTostTextMES)).setText("Please fill out name field.");
                custom_tost.setGravity(Gravity.BOTTOM, 30, nameFieldPosition);
                custom_tost.setDuration(Toast.LENGTH_LONG);
                custom_tost.setView(view1);
                custom_tost.show();
            }
            else if(dietMenu.length()<2 ){
                ((TextView) view1.findViewById(R.id.inputFieldTostTextMES)).setText("Menu lengh must be 2.");
                custom_tost.setGravity(Gravity.BOTTOM, 30, nameFieldPosition);
                custom_tost.setDuration(Toast.LENGTH_LONG);
                custom_tost.setView(view1);
                custom_tost.show();
            }
        }
        else if(dietDate.equals("") || dietInfo.compareDateWithToday(dietDate)<0){
            int birthdayFieldPosition =(screenHieght-550 )+position;
            if(dietDate.equals("")) {
                if (screenHieght < birthdayFieldPosition)
                    ((TextView) view1.findViewById(R.id.inputFieldTostTextMES)).setText("Please fill out date field.");
                custom_tost.setGravity(Gravity.BOTTOM, 30, birthdayFieldPosition);
                custom_tost.setDuration(Toast.LENGTH_LONG);
                custom_tost.setView(view1);
                custom_tost.show();
            }
            else{
                ((TextView) view1.findViewById(R.id.inputFieldTostTextMES)).setText("Date can't be previous.");
                custom_tost.setGravity(Gravity.BOTTOM, 30, birthdayFieldPosition);
                custom_tost.setDuration(Toast.LENGTH_LONG);
                custom_tost.setView(view1);
                custom_tost.show();
            }
        }
        else if(dietTime.equals("")){
            int genderFieldPosition =(screenHieght-700)+position;
            if(screenHieght<genderFieldPosition)
                ((TextView) view1.findViewById(R.id.inputFieldTostTextMES)).setText("Please fill out time field.");
            custom_tost.setGravity(Gravity.BOTTOM,30,genderFieldPosition);
            custom_tost.setDuration(Toast.LENGTH_LONG);
            custom_tost.setView(view1);
            custom_tost.show();
        }
        else
        {

            boolean inserted = dietInfo.addDiet(0,String.valueOf(singleProfileInfo.getProfileID()),dietType, dietMenu, dietDate, dietTime, dietAlam, dietRemainder);
            if(inserted){
                DatabaseManager databaseManager;
                databaseManager = new DatabaseManager(this);
                ArrayList<Diet> dietArrayList;

                dietArrayList =   databaseManager.getDietList();
                int alarmId= dietArrayList.get(dietArrayList.size()-1).getDietId()+100;
                if(dietRemainder.equals("on")){

                    String msg= singleProfileInfo.getProfileName()+" have a diet ("+dietMenu+")" ;
                    RemainderManager remainderManager = new RemainderManager(this,getBaseContext(),msg);
                    remainderManager.setRemainder(ry,rm,rd,rh,rmin_f,alarmId);
                }
                else  if(dietAlam.equals("on")){
                    String msg=singleProfileInfo.getProfileName()+" have a diet ("+dietMenu+")";
                    RemainderManager remainderManager = new RemainderManager(this,getBaseContext(),msg);
                    remainderManager.setRemainder(ry,rm,rd,rh,rmin,alarmId);
                }
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                dlgAlert.setMessage("Save diet Sucessfuly");
                dlgAlert.setIcon(R.drawable.ic_launcher);
                dlgAlert.setTitle("Save Diet");
                dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(),MemberDietActivity.class);
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
        Intent intent = new Intent(getApplicationContext(), MemberDietActivity.class);
        intent.putExtra("id", profileId);
        startActivity(intent);
    }
}
