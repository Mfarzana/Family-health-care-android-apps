package com.example.asd.com.healthcare;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class PinValidityCheckActivity extends AppCompatActivity {
    SessionManager sessionManager;
    EditText etPass;
    TextView tvError;
    String previousActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_validity_check);
        sessionManager =new SessionManager(this);
        etPass =(EditText) findViewById(R.id.etPass);
        tvError = (TextView) findViewById(R.id.tvError);
        previousActivity=getIntent().getExtras().getString(SessionManager.PREVIOUS_ACTIVITY_CONTEXT_KEY);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void check(View view) {
        String Pass = etPass.getText().toString();
        String pinCode="-1";
        if(Pass.length()>0)
            pinCode= Pass;
        boolean isMach = sessionManager.checkPin(pinCode);
        if(isMach && !sessionManager.getAdminPinCode().equals(pinCode)){
            DatabaseManager databaseManager= new DatabaseManager(this);
            int profileId=databaseManager.getprofileId(pinCode);
            sessionManager.sessionIn(pinCode);
            Intent intent = new Intent(getApplicationContext(),MemberHomePageActivity.class);
            intent.putExtra("id", profileId);
            startActivity(intent);
            return;

        }
        else if(isMach && previousActivity.equals("start")){
            sessionManager.startSession(pinCode);
        }
        else if(isMach){
            sessionManager.sessionIn(pinCode);
            onBackPressed();
        }
        else if(Pass.length()>0) {

            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
            dlgAlert.setMessage("Current Pin Code is wrong");
            dlgAlert.setIcon(R.drawable.ic_launcher);
            dlgAlert.setTitle("Incorrect Pin!");
            dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    etPass.setText("");
                }
            });
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();


        }

    }
}
