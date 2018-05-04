package com.example.asd.com.healthcare;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MemberSetPin extends AppCompatActivity {
    SessionManager sessionManager;
    private int memberId;
    EditText etPass;
    EditText etRepass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_set_pin);
        sessionManager = new SessionManager(this);
        Intent intent = getIntent();
        memberId=intent.getExtras().getInt("id");
        etPass = (EditText) findViewById(R.id.etPass);
        etRepass = (EditText) findViewById(R.id.etRepass);

    }
    public void register(View view) {
        String Pass=etPass.getText().toString();
        String Repass = etRepass.getText().toString();
        int screenHieght =((ScrollView)findViewById(R.id.scrollViewIdAddprofile)).getHeight();
        final LayoutInflater inflater = getLayoutInflater();
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



        if(Pass.length()<4)
        {
            int profileRelationFieldPosition =(screenHieght-800 )+position;
            ((TextView) view1.findViewById(R.id.inputFieldTostTextMES)).setText("At least 4 pin");
            custom_tost.setGravity(Gravity.BOTTOM, 30, profileRelationFieldPosition);
            custom_tost.setDuration(Toast.LENGTH_LONG);
            custom_tost.setView(view1);
            custom_tost.show();
            etPass.setText("");
            etRepass.setText("");
        }
        else if(Pass.equals(Repass)){
            String pinCade=Pass;
            boolean isSet=  sessionManager.setMemberPinCode(pinCade,memberId);
            if(isSet){
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                dlgAlert.setMessage("Set Pin Code Sucessfuly");
                dlgAlert.setIcon(R.drawable.ic_launcher);
                dlgAlert.setTitle("Set Pin Code");
                dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(),ProfileDetailsActivity.class);
                        intent.putExtra("id",memberId);
                        startActivity(intent);
                    }
                });
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
            }
            else{
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
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
        }
        else{
            int profileRelationFieldPosition =(screenHieght-800 )+position;
            ((TextView) view1.findViewById(R.id.inputFieldTostTextMES)).setText("Pin dosen't mach");
            custom_tost.setGravity(Gravity.BOTTOM,30,profileRelationFieldPosition);
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
