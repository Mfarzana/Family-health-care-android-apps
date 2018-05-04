package com.example.asd.com.healthcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

public class MemberHomePageActivity extends AppCompatActivity {
    SessionManager sessionManager;
    AllProfileList allProfileList;
    SingleProfileInfo singleProfileInfo;
    ProfileGridAdapter profileGridAdapter;
    ImageButton imgPrifilePic;
    TextView tvProfileName;
    TextView tvProfileAge;
    TextView tvProfileHieght;
    TextView tvProfileWeight;
    GridView gridViewProfileList;
    int profileId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_home_page);
        Intent intent = getIntent();
        profileId=intent.getExtras().getInt("id");

        sessionManager = new SessionManager(MemberHomePageActivity.this);
        sessionManager.checkSessionValidity();
        String profilePinCode = sessionManager.getPinCode();
        allProfileList =new AllProfileList(this);
        singleProfileInfo =allProfileList.getProfileById(profileId);
        imgPrifilePic = (ImageButton)findViewById(R.id.imgProfilePic);
        tvProfileName = (TextView)findViewById(R.id.tvProfileName);
        tvProfileAge =(TextView)findViewById(R.id.tvProfileAge);
        tvProfileHieght =(TextView)findViewById(R.id.tvProfileHieght);
        tvProfileWeight =(TextView)findViewById(R.id.tvProfileWeight);
        gridViewProfileList = (GridView)findViewById(R.id.gridProfileList);
        if(singleProfileInfo.getProfileGender().equals("Male"))
            imgPrifilePic.setImageResource(R.drawable.profilemale);
        else
            imgPrifilePic.setImageResource(R.drawable.profilefemale);
        tvProfileName.setText(singleProfileInfo.getProfileName());
        tvProfileAge.setText(String.valueOf(singleProfileInfo.getProfileAge()));
        tvProfileHieght.setText(singleProfileInfo.getProfileHieght());
        tvProfileWeight.setText(singleProfileInfo.getProfileWeight());
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        ((TextView)findViewById(R.id.setTitile)).setText("Health Care");
        setSupportActionBar(toolbar);

    }
    public void prfileDetais(View view) {
        Intent intent = new Intent(this,ProfileDetailsActivity.class);
        intent.putExtra("id",singleProfileInfo.getProfileID());
        startActivity(intent);
    }

    public void memberDiet(View view) {
        Intent intent = new Intent(this,MemberDietActivity.class);
        intent.putExtra("id",singleProfileInfo.getProfileID());
        startActivity(intent);

    }

    public void memberVaccine(View view) {
        Intent intent = new Intent(this,MemberVaccineActivity.class);
        intent.putExtra("id",singleProfileInfo.getProfileID());
        startActivity(intent);

    }

    public void Doctor(View view) {
        Intent intent = new Intent(this,MemberDoctorActivity.class);
        intent.putExtra("id",singleProfileInfo.getProfileID());
        startActivity(intent);
    }

    public void familyHospital(View view) {
        Intent intent = new Intent(this,HospitalList.class);
        intent.putExtra("id",singleProfileInfo.getProfileID());
        startActivity(intent);
    }

    public void familyHealthTips(View view) {
        Intent intent = new Intent(this,HealthTips.class);
        intent.putExtra("id",singleProfileInfo.getProfileID());
        startActivity(intent);
    }

    public void ProfileHealthCare(View view) {
        Intent intent = new Intent(this,HealthCare.class);
        intent.putExtra("id",singleProfileInfo.getProfileID());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }



    public void signOut(MenuItem item) {
        sessionManager.sessionSignOut();
    }

    public void home(MenuItem item) {
        AllProfileList allProfileList = new AllProfileList(this);
        SingleProfileInfo singleProfileInfo= allProfileList.getProfileById(profileId);
        if(sessionManager.getAdminPinCode().equals(sessionManager.getLogedPin())){
            Intent intent= new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
        else {
            Intent intent=new Intent(getApplicationContext(),MemberHomePageActivity.class);
            intent.putExtra("id",profileId);
            startActivity(intent);
        }
    }
}
