package com.example.asd.com.healthcare;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sessionManager = new SessionManager(HomeActivity.this);
        sessionManager.checkSessionValidity();
        String profilePinCode = sessionManager.getPinCode();
        allProfileList =new AllProfileList(this);
        singleProfileInfo =allProfileList.getProfileByPinCaode(profilePinCode);
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

        profileGridAdapter = new ProfileGridAdapter(this,allProfileList.getSingleProfileInfoArrayList());

        gridViewProfileList.setAdapter(profileGridAdapter);


        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        ((TextView)findViewById(R.id.setTitile)).setText("Family Heath Care");
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public void signOut(MenuItem item) {
        sessionManager.sessionSignOut();
    }

    public void Profile(View view) {
        Intent intent = new Intent(this,ProfileActivity.class);
        intent.putExtra("do","");
        startActivity(intent);
    }


    public void prfileDetais(View view) {
        Intent intent = new Intent(this,ProfileDetailsActivity.class);
        intent.putExtra("id",singleProfileInfo.getProfileID());
        startActivity(intent);
    }

    public void familyDietChar(View view) {
        Intent intent = new Intent(this,DietChartList.class);
        intent.putExtra("id",singleProfileInfo.getProfileID());
        startActivity(intent);
    }

    public void seting(MenuItem item) {
        Intent intent = new Intent(this,ChangeAdminPinCode.class);
        startActivity(intent);
    }
}
