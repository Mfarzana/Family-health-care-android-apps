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

public class MemberVaccineActivity extends AppCompatActivity {
    private SessionManager sessionManager;
    private AllProfileList allProfileList;
    private SingleProfileInfo singleProfileInfo;
    private ImageButton imgPrifilePic;
    private TextView tvProfileName;
    private TextView tvProfileAge;
    private TextView tvProfileHieght;
    private TextView tvProfileWeight;
    private int profileId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_vaccine);
        sessionManager = new SessionManager(this);
        sessionManager.checkSessionValidity();
        String profilePinCode = sessionManager.getPinCode();
        allProfileList =new AllProfileList(this);
        Intent intent = getIntent();
        profileId=intent.getExtras().getInt("id");
        singleProfileInfo =allProfileList.getProfileById(profileId);
        imgPrifilePic = (ImageButton)findViewById(R.id.imgProfilePic);
        tvProfileName = (TextView)findViewById(R.id.tvProfileName);
        tvProfileAge =(TextView)findViewById(R.id.tvProfileAge);
        tvProfileHieght =(TextView)findViewById(R.id.tvProfileHieght);
        tvProfileWeight =(TextView)findViewById(R.id.tvProfileWeight);
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


    public void addVaccine(View view) {
        Intent intent = new Intent(this,AddVaccine.class);
        intent.putExtra("id",singleProfileInfo.getProfileID());
        startActivity(intent);
    }

    public void todyVaccine(View view) {
        Intent intent = new Intent(this,TodaysVaccine.class);
        intent.putExtra("id",profileId);
        startActivity(intent);
    }

    public void PreviousVaccine(View view) {
        Intent intent = new Intent(this,PreviousVaccine.class);
        intent.putExtra("id",profileId);
        startActivity(intent);
    }

    public void upComingVaccine(View view) {
        Intent intent = new Intent(this,UpComingVaccine.class);
        intent.putExtra("id",profileId);
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
