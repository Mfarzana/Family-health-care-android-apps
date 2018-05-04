package com.example.asd.com.healthcare;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileDetailsActivity extends AppCompatActivity {
    AllProfileList allProfileList;
    SessionManager sessionManager;
    SingleProfileInfo singleProfileInfo;
    private TextView profileName;
    private TextView profileBirthday;
    private TextView profileAge;
    private TextView ProfileGender;
    private TextView ProfileBloodGroup;
    private TextView ProfileRelation;
    private TextView ProfileHieght;
    private TextView ProfileWeight;
    private Button setPinCode;
    int profileId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);
        sessionManager = new SessionManager(this);
        profileName =(TextView)findViewById(R.id.tvName);
        profileBirthday =(TextView)findViewById(R.id.tvBirthday);
        profileAge =(TextView)findViewById(R.id.tvAge);
        ProfileGender =(TextView)findViewById(R.id.tvGender);
        ProfileBloodGroup =(TextView)findViewById(R.id.tvBloodGroup);
        ProfileRelation =(TextView)findViewById(R.id.tvRelation);
        ProfileHieght =(TextView)findViewById(R.id.tvHieght);
        ProfileWeight=(TextView)findViewById(R.id.tvWeight);
        setPinCode = (Button)findViewById(R.id.btSetpincode);
        allProfileList = new AllProfileList(this);
        Intent intent = getIntent();
        profileId=intent.getExtras().getInt("id");
        singleProfileInfo=allProfileList.getProfileById(profileId);
        int ddd=0;

        if(singleProfileInfo.getProfileGender().equals("Male")) {
            ((ImageView) findViewById(R.id.imgPropic)).setImageResource(R.drawable.gridprofilemale4);
        }
        else {
            ((ImageView) findViewById(R.id.imgPropic)).setImageResource(R.drawable.gridprofilefemale4);
        }
        profileName.setText(singleProfileInfo.getProfileName());
        profileBirthday.setText(singleProfileInfo.getProfileBirthdy());
        profileAge.setText(String.valueOf(singleProfileInfo.getProfileAge()));
        ProfileGender.setText(singleProfileInfo.getProfileGender());
        ProfileBloodGroup.setText(singleProfileInfo.getProfileBloodgroup());
        ProfileRelation.setText(singleProfileInfo.getProfileRelation());
        ProfileHieght.setText(singleProfileInfo.getProfileHieght());
        ProfileWeight.setText(singleProfileInfo.getProfileWeight());

        if(sessionManager.isAdmin() && !singleProfileInfo.getProfilePincode().equals(sessionManager.getAdminPinCode()) && singleProfileInfo.getProfilePincode().length()<4){
            ((TextView)findViewById(R.id.tvAdminmess)).setText("Now you can proviede access to your family member for maintaine their own acount by pin code");
            setPinCode.setText("Set Pin Code");
        }


        ((TextView)findViewById(R.id.setTitile)).setText("Profile");
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        toolbar.setBackgroundColor(Color.parseColor("#9551fe"));
        setSupportActionBar(toolbar);
    }

    public void SetPinCode(View view) {
        if(sessionManager.isAdmin() &&  !singleProfileInfo.getProfilePincode().equals(sessionManager.getAdminPinCode()) && singleProfileInfo.getProfilePincode().length()<4){
            Intent intent = new Intent(this,MemberSetPin.class);
            intent.putExtra("id",profileId);
            startActivity(intent);
        }

    }
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
