package com.example.asd.com.healthcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sessionManager = new SessionManager(this);
        String pin= sessionManager.getPinCode();
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        if(pin.equals("0"))
        {
            Intent intent = new Intent(this,SetPinActivity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(this,PinValidityCheckActivity.class);
            intent.putExtra(SessionManager.PREVIOUS_ACTIVITY_CONTEXT_KEY,"start");
            startActivity(intent);
        }
    }

}
