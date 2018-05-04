package com.example.asd.com.healthcare;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeAdminPinCode extends AppCompatActivity {
    SessionManager sessionManager;
    SingleProfileInfo singleProfileInfo;
    DatabaseManager databaseManager;
    AllProfileList allProfileList;
    private int memberId;
    EditText etPass;
    EditText etRepass;
    EditText etOldPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_admin_pin_code);
        etPass = (EditText) findViewById(R.id.etPass);
        etRepass = (EditText) findViewById(R.id.etRepass);
        etRepass = (EditText) findViewById(R.id.etRepass);
        etOldPass = (EditText) findViewById(R.id.etoldPass);
        sessionManager = new SessionManager(this);
        databaseManager = new DatabaseManager(this);
        memberId= databaseManager.getprofileId(sessionManager.getAdminPinCode());
        ((TextView)findViewById(R.id.setTitile)).setText("Setting");
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }
    public void register(View view) {
        String Pass=etPass.getText().toString();
        String Repass = etRepass.getText().toString();
        String oldPass = etOldPass.getText().toString();


        if(!oldPass.equals(sessionManager.getAdminPinCode())){
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
            dlgAlert.setMessage("Current Pin Code is wrong");
            dlgAlert.setIcon(R.drawable.ic_launcher);
            dlgAlert.setTitle("Incorrect Pin!");
            dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
            etOldPass.setText("");

        }
        else {


            int screenHieght = ((ScrollView) findViewById(R.id.scrollViewIdAddprofile)).getHeight();
            LayoutInflater inflater = getLayoutInflater();
            View view1 = inflater.inflate(R.layout.input_field_tost, (ViewGroup) findViewById(R.id.rootContent));
            final Toast custom_tost = new Toast(getApplicationContext());
            View scrollViewView = (ScrollView) findViewById(R.id.scrollViewIdAddprofile);
            int position = scrollViewView.getScrollY();
            if (position != 0) {
                if (600 < position)
                    position += 110;
                else if (300 < position)
                    position += 100;
                else if (200 < position)
                    position += 80;
                else if (100 < position)
                    position += 60;
                else if (50 < position)
                    position += 30;
                screenHieght += position;
            }


            if (Pass.length() < 4) {
                int profileRelationFieldPosition = (screenHieght - 800) + position;
                ((TextView) view1.findViewById(R.id.inputFieldTostTextMES)).setText("At least 4 pin");
                custom_tost.setGravity(Gravity.BOTTOM, 30, profileRelationFieldPosition);
                custom_tost.setDuration(Toast.LENGTH_LONG);
                custom_tost.setView(view1);
                custom_tost.show();
                etPass.setText("");
                etRepass.setText("");
            } else if (Pass.equals(Repass)) {
                String pinCade = Pass;

                databaseManager = new DatabaseManager(this);
                SingleProfileInfo singleProfileInfo = databaseManager.getSingleProfile(memberId);
                SingleProfileInfo updateProfile=new SingleProfileInfo(memberId,pinCade,singleProfileInfo.getProfileName(),singleProfileInfo.getProfileBirthdy(),singleProfileInfo.getProfileBloodgroup(),singleProfileInfo.getProfileGender(),singleProfileInfo.getProfileRelation(),singleProfileInfo.getProfileHieght(),singleProfileInfo.getProfileWeight());
               boolean isSet = databaseManager.updateSingleProfile(updateProfile);
                if (isSet) {
                    sessionManager.setAdminPinCode(pinCade);
                    sessionManager.sessionIn(pinCade);
                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
                    dlgAlert.setMessage("Change Pin Code Sucessfuly");
                    dlgAlert.setIcon(R.drawable.ic_launcher);
                    dlgAlert.setTitle("Change Pin Code");
                    dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                        }
                    });
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                } else {
                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
                    dlgAlert.setMessage("The Pin code already in use");
                    dlgAlert.setIcon(R.drawable.ic_launcher);
                    dlgAlert.setTitle("Faild !");
                    dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            etPass.setText("");
                            etRepass.setText("");
                        }
                    });
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();

                }

             /*Intent intent = new Intent(this,PinValidityCheckActivity.class);
            intent.putExtra(SessionManager.PREVIOUS_ACTIVITY_CONTEXT_KEY,"start");*/
            } else {
                int profileRelationFieldPosition = (screenHieght - 800) + position;
                ((TextView) view1.findViewById(R.id.inputFieldTostTextMES)).setText("Pin dosen't mach");
                custom_tost.setGravity(Gravity.BOTTOM, 30, profileRelationFieldPosition);
                custom_tost.setDuration(Toast.LENGTH_LONG);
                custom_tost.setView(view1);
                custom_tost.show();
                etPass.setText("");
                etRepass.setText("");
                etPass.setText("");
                etRepass.setText("");
            }
        }

    }

}
