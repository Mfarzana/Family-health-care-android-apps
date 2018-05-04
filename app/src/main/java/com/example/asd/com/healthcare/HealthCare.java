package com.example.asd.com.healthcare;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class HealthCare extends AppCompatActivity {
    AllProfileList allProfileList;
    SingleProfileInfo singleProfileInfo;
    private TextView profileName;
    private TextView profileBirthday;
    private TextView profileAge;
    private TextView ProfileGender;
    private TextView ProfileBloodGroup;
    private TextView ProfileRelation;
    private TextView ProfileHieght;
    private TextView ProfileWeight;
    private TextView profileEXW;
    private TextView profileEXH;
    private TextView profileEXB;
    private int profileId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_care);
        profileName =(TextView)findViewById(R.id.tvName);
        profileAge =(TextView)findViewById(R.id.tvAge);

        ProfileBloodGroup =(TextView)findViewById(R.id.tvBloodGroup);
        ProfileRelation =(TextView)findViewById(R.id.tvRelation);
        ProfileHieght =(TextView)findViewById(R.id.tvHieght);
        ProfileWeight=(TextView)findViewById(R.id.tvWeight);
        profileEXH=(TextView)findViewById(R.id.tvExHieght);
        profileEXW=(TextView)findViewById(R.id.tvExWieght);
        profileEXB=(TextView)findViewById(R.id.tvBloodPressure);
        allProfileList = new AllProfileList(this);
        Intent intent = getIntent();
        profileId=intent.getExtras().getInt("id");
        singleProfileInfo=allProfileList.getProfileById(profileId);

        if(singleProfileInfo.getProfileGender().equals("Male")) {
            ((ImageView) findViewById(R.id.imgPropic)).setImageResource(R.drawable.gridprofilemale4);
        }
        else {
            ((ImageView) findViewById(R.id.imgPropic)).setImageResource(R.drawable.gridprofilefemale4);
        }
        String B;
        int a=singleProfileInfo.getProfileAge();

        if(a<6)
            B="116/75";
        else if(a<10)
            B="121/77";
        else if(a<13)
            B="125/82";
        else if(a<16)
            B="136/87";
        else if(a<19)
            B="120/85";
        else if(a<29)
            B="120/80";
        else if(a<34)
            B="122/81";
        else if(a<39)
            B="123/81";
        else if(a<44)
            B="125/83";
        else if(a<49)
            B="125/84";
        else if(a<54)
            B="128/85";
        else if(a<59)
            B="131/86";
        else
            B="134/87";

        String HH=singleProfileInfo.getProfileHieght();
        HH=HH.substring(0,2)+String.valueOf(Integer.parseInt(HH.substring(2,3))+2)+HH.substring(3);
        profileEXW.setText(String.valueOf(3*(singleProfileInfo.getProfileAge())+7));
        profileEXH.setText(HH);
        profileEXB.setText(B);
        profileName.setText(singleProfileInfo.getProfileName());
        profileAge.setText(String.valueOf(singleProfileInfo.getProfileAge()));
        ProfileBloodGroup.setText(singleProfileInfo.getProfileBloodgroup());
        ProfileRelation.setText(singleProfileInfo.getProfileRelation());
        ProfileHieght.setText(singleProfileInfo.getProfileHieght());
        ProfileWeight.setText(singleProfileInfo.getProfileWeight());
        ((TextView)findViewById(R.id.setTitile)).setText("Health Care");
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        toolbar.setBackgroundColor(Color.parseColor("#9551fe"));
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.only_back, menu);
        return true;
    }



    public void backepage(MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), MemberHomePageActivity.class);
        intent.putExtra("id", profileId);
        startActivity(intent);
    }


}
