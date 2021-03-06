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
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;

public class EditVaccine extends AppCompatActivity {
    private SessionManager sessionManager;
    SingleProfileInfo singleProfileInfo;
    AllProfileList allProfileList;
    VaccineInfo vaccineInfo;
    Vaccine vaccine;
    private Button setDate;
    private Button setTime;
    private int year,month,day;
    private int ry,rm,rd,rh,rmin,rmin_f,date_f;
    private int hour,min;
    private Calendar calendar;
    private EditText etDetails;
    private EditText etName;
    private String vaccineDate;
    private String vaccineTime;
    private String vaccineDetails;
    private String vaccineName;
    private String vaccineAlam;
    private String vaccineRemainder;
    private String tDate;
    private int profileId;
    private int vaccineId;
    private static int screenHieght;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vaccine);
        sessionManager = new SessionManager(this);
        sessionManager.checkSessionValidity();
        allProfileList = new AllProfileList(this);
        Intent intent = getIntent();
        profileId=intent.getExtras().getInt("pid");
        vaccineId=intent.getExtras().getInt("did");
        vaccineInfo = new VaccineInfo(this);
        vaccine = vaccineInfo.getVaccineById(vaccineId);
        singleProfileInfo =allProfileList.getProfileById(profileId);
        ((TextView)findViewById(R.id.setTitile)).setText("Edit Vaccine");
        etDetails = (EditText) findViewById(R.id.etDetails);
        etName = (EditText) findViewById(R.id.etName);
        setDate = (Button) findViewById(R.id.btDatePicker);
        setTime = (Button) findViewById(R.id.btTimePicker);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        toolbar.setBackgroundColor(Color.parseColor("#ff760d"));
        setSupportActionBar(toolbar);
        vaccineName=vaccine.getName();
        vaccineDate=vaccine.getDate();
        tDate=vaccineDate;
        vaccineTime=vaccine.getTime();
        vaccineDetails =vaccine.getDetails();
        vaccineAlam=vaccine.getAlarm();
        vaccineRemainder=vaccine.getRemainder();
        etName.setText(vaccineName);
        etDetails.setText(vaccineDetails);
        setDate.setText(vaccineDate);
        setTime.setText(vaccineTime);

        if(vaccine.getAlarm().equals("on")) {
            RadioButton radioButtonGender = (RadioButton) findViewById(R.id.vaccineAlam);
            radioButtonGender.setChecked(true);
        }
        if(vaccine.getRemainder().equals("on"))  {
            RadioButton radioButtonGender = (RadioButton) findViewById(R.id.vaccineRemainder);
            radioButtonGender.setChecked(true);
        }
        rmin=-1;
        date_f=-1;

    }




    public void alamRbutton(View view) {

        if(view.getId()==R.id.vaccineAlam)
        {
            vaccineAlam="on";
            vaccineRemainder="off";
        }
        else
        {
            vaccineRemainder="on";
            vaccineAlam="off";
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
                    hour = selectedHour;
                    min = selectedMinute;
                    rh=view.getCurrentHour();
                    rmin_f=view.getCurrentMinute()-30;
                    rmin=view.getCurrentMinute();

                    // set current time into textview
                    setTime.setText(new StringBuilder().append(pad(hour))
                            .append(":").append(pad(min)));
                    vaccineTime =(pad(hour))+(":")+(pad(min));
                    if(date_f==-1){
                        vaccineDate="";
                        setDate.setText("      ");

                    }

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
            date_f=ry;
            vaccineDate = arg3+"/"+arg2+"/"+arg1;
            setDate.setText(vaccineDate);
            if(rmin==-1) {
                vaccineTime= "";
                setTime.setText("      ");
            }

        }
    };


    public void saveVaccine(View view) {
        screenHieght =((ScrollView)findViewById(R.id.scrollViewIdAddprofile)).getHeight();
        /*int h=((ScrollView)findViewById(R.id.scrollViewIdAddprofile)).getHeight();
        String v=String.valueOf(h);
        Toast.makeText(this,v,Toast.LENGTH_LONG).show();*/
        vaccineName = etName.getText().toString();
        vaccineDetails = etDetails.getText().toString();
        if(!vaccineDetails.equals(""))
            vaccineDetails=vaccineDetails.substring(0,1).toUpperCase()+vaccineDetails.substring(1);
        if(!vaccineName.equals(""))
            vaccineName=vaccineName.substring(0,1).toUpperCase()+vaccineName.substring(1);
        VaccineInfo vaccineInfo = new VaccineInfo(this);
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


        if(vaccineName.equals("") ||  vaccineName.length()<3 ) {

            int nameFieldPosition =(screenHieght-230)+position;
            if(vaccineName.equals("")) {
                if (screenHieght < nameFieldPosition)
                    ((TextView) view1.findViewById(R.id.inputFieldTostTextMES)).setText("Please fill out name field.");
                custom_tost.setGravity(Gravity.BOTTOM, 30, nameFieldPosition);
                custom_tost.setDuration(Toast.LENGTH_LONG);
                custom_tost.setView(view1);
                custom_tost.show();
            }
            else if(vaccineName.length()<3){
                ((TextView) view1.findViewById(R.id.inputFieldTostTextMES)).setText("Details lengh must be 3.");
                custom_tost.setGravity(Gravity.BOTTOM, 30, nameFieldPosition);
                custom_tost.setDuration(Toast.LENGTH_LONG);
                custom_tost.setView(view1);
                custom_tost.show();
            }
        }
        else if(vaccineDate.equals("") || vaccineInfo.compareDateWithToday(vaccineDate)<0){
            int birthdayFieldPosition =(screenHieght-390 )+position;

            if(vaccineDate.equals("")) {
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
        else if(vaccineTime.equals("")){
            int genderFieldPosition =(screenHieght-550)+position;
            if(screenHieght<genderFieldPosition)
                ((TextView) view1.findViewById(R.id.inputFieldTostTextMES)).setText("Please fill out time field.");
            custom_tost.setGravity(Gravity.BOTTOM,30,genderFieldPosition);
            custom_tost.setDuration(Toast.LENGTH_LONG);
            custom_tost.setView(view1);
            custom_tost.show();
        }
        else
        {
            boolean updated = vaccineInfo.updateVaccine(vaccineId, String.valueOf(singleProfileInfo.getProfileID()), vaccineName, vaccineDate, vaccineTime, vaccineDetails,vaccineAlam, vaccineRemainder);
            if(updated){
                int alarmId= vaccineId+1000;

                if(vaccineRemainder.equals("on") && rmin_f!=-1 && date_f!=-1){
                    String msg=singleProfileInfo.getProfileName()+" have a vaccine ("+vaccineName+")";
                    RemainderManager remainderManager = new RemainderManager(this,getBaseContext(),msg);
                    remainderManager.updateRemainder(ry,rm,rd,rh,rmin_f,alarmId);
                }
                else  if(vaccineAlam.equals("on") && rmin_f!=-1 && date_f!=-1){
                    String msg=singleProfileInfo.getProfileName()+" have a vaccine ("+vaccineName+")";
                    RemainderManager remainderManager = new RemainderManager(this,getBaseContext(),msg);
                    remainderManager.updateRemainder(ry,rm,rd,rh,rmin,alarmId);
                }

                
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                dlgAlert.setMessage("Update vaccine Sucessfuly");
                dlgAlert.setIcon(R.drawable.ic_launcher);
                dlgAlert.setTitle("Save Vaccine");
                dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    VaccineInfo vaccineInfo = new VaccineInfo(getApplicationContext());

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (vaccineInfo.compareDateWithToday(tDate)<0) {
                            Intent intent = new Intent(getApplicationContext(), PreviousVaccine.class);
                            intent.putExtra("id", profileId);
                            startActivity(intent);

                        } else if (vaccineInfo.compareDateWithToday(tDate) > 0) {
                            Intent intent = new Intent(getApplicationContext(), UpComingVaccine.class);
                            intent.putExtra("id", profileId);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(getApplicationContext(), TodaysVaccine.class);
                            intent.putExtra("id", profileId);
                            startActivity(intent);
                        }
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
        if (vaccineInfo.compareDateWithToday(tDate)<0) {
            Intent intent = new Intent(getApplicationContext(), PreviousVaccine.class);
            intent.putExtra("id", profileId);
            startActivity(intent);

        } else if (vaccineInfo.compareDateWithToday(tDate) > 0) {
            Intent intent = new Intent(getApplicationContext(), UpComingVaccine.class);
            intent.putExtra("id", profileId);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getApplicationContext(), TodaysVaccine.class);
            intent.putExtra("id", profileId);
            startActivity(intent);
        }


    }

}
