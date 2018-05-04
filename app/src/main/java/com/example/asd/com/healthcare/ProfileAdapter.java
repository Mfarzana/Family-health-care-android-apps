package com.example.asd.com.healthcare;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Assaduzzaman Noor on 4/23/2016.
 */
public class ProfileAdapter extends ArrayAdapter<SingleProfileInfo> {
    ArrayList<SingleProfileInfo> singleProfileInfoArrayList;
    Context context;
    ImageButton buttonEdit;
    ImageButton buttonDelete;
    ImageView imgProfile;
    TextView profileName;
    TextView profileRelation;
    TextView cnt;

    public ProfileAdapter(Context context, ArrayList<SingleProfileInfo> singleProfileInfoArrayList) {
        super(context, R.layout.single_profile_item, singleProfileInfoArrayList);
        this.singleProfileInfoArrayList = singleProfileInfoArrayList;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.single_profile_item, null);
        imgProfile =(ImageView)  convertView.findViewById(R.id.propic);
        buttonDelete = (ImageButton) convertView.findViewById(R.id.btProfileDelete);
        buttonEdit = (ImageButton) convertView.findViewById(R.id.btProfileEdit);
        profileName = (TextView) convertView.findViewById(R.id.btProfileName);
        profileRelation = (TextView) convertView.findViewById(R.id.btProfileRelation);
        cnt =(TextView) convertView.findViewById(R.id.count);

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProfileDetailsActivity.class);
                intent.putExtra("id", singleProfileInfoArrayList.get(position).getProfileID());
                context.startActivity(intent);
            }

        });
        SessionManager sessionManager = new SessionManager(context);
        if(!singleProfileInfoArrayList.get(position).getProfilePincode().equals(sessionManager.getAdminPinCode())) {
            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(context);
                    dlgAlert.setMessage("Are you sure want to delete this profile?");
                    dlgAlert.setIcon(R.drawable.ic_launcher);
                    dlgAlert.setTitle("Delete Profile");
                    dlgAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(context, ProfileActivity.class);
                            intent.putExtra("do", "delete");
                            AllProfileList allProfileList = new AllProfileList(context);
                            allProfileList.deleteSingleProfile(singleProfileInfoArrayList.get(position).getProfileID());
                            context.startActivity(intent);
                        }
                    });
                    dlgAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();


                }

            });

        }
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditProfile.class);
                intent.putExtra("id", singleProfileInfoArrayList.get(position).getProfileID());
                context.startActivity(intent);
            }

        });

        if(singleProfileInfoArrayList.get(position).getProfileGender().equals("Male")){
            if(position%5==0)
                imgProfile.setImageResource(R.drawable.profilemale1);
            else if(position%5==1)
                imgProfile.setImageResource(R.drawable.profilemale2);
            else if(position%5==2)
                imgProfile.setImageResource(R.drawable.profilemale3);
            else if(position%5==3)
                imgProfile.setImageResource(R.drawable.profilemale4);
            else
                imgProfile.setImageResource(R.drawable.profilemale5);

        }
        else{
            if(position%5==0)
                imgProfile.setImageResource(R.drawable.profilefemale1);
            else if(position%5==1)
                imgProfile.setImageResource(R.drawable.profilefemale2);
            else if(position%5==2)
                imgProfile.setImageResource(R.drawable.profilefemale3);
            else if(position%5==3)
                imgProfile.setImageResource(R.drawable.profilefemale4);
            else
                imgProfile.setImageResource(R.drawable.profilefemale5);
        }


        cnt.setText(String.valueOf(position + 1));
        if(position%5==0)
            cnt.setTextColor(Color.parseColor("#00d700"));
        else if(position%5==1)
            cnt.setTextColor(Color.parseColor("#fec901"));
        else if(position%5==2)
            cnt.setTextColor(Color.parseColor("#019dd8"));
        else if(position%5==3)
            cnt.setTextColor(Color.parseColor("#9551fe"));
        else
            cnt.setTextColor(Color.parseColor("#b50937"));

        profileName.setText(singleProfileInfoArrayList.get(position).getProfileName());
        profileRelation.setText(singleProfileInfoArrayList.get(position).getProfileRelation());
        return convertView;

    }

}