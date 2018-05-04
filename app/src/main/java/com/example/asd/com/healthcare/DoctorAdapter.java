package com.example.asd.com.healthcare;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.Intent.ACTION_CALL;

/**
 * Created by Assaduzzaman Noor on 4/28/2016.
 */

public class DoctorAdapter extends ArrayAdapter<Doctor> {
    ArrayList<Doctor> doctorList;
    Context context;
    ImageButton buttonEdit;
    ImageButton buttonDelete;
    ImageView imgDoctor;
    ImageButton imgCall;
    TextView doctorName;
    TextView doctorNumber;
    TextView cnt;

    public DoctorAdapter(Context context, ArrayList<Doctor> doctorList) {
        super(context, R.layout.doctor_item, doctorList);
        this.doctorList = doctorList;
        this.context = context;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.doctor_item, null);
        imgDoctor = (ImageView) convertView.findViewById(R.id.propic);
        buttonDelete = (ImageButton) convertView.findViewById(R.id.doctorDelete);
        buttonEdit = (ImageButton) convertView.findViewById(R.id.doctorEdit);
        doctorName = (TextView) convertView.findViewById(R.id.etDoctorName);
        doctorNumber = (TextView) convertView.findViewById(R.id.etDoctorNumber);
        imgCall = (ImageButton) convertView.findViewById(R.id.makeCall);


        cnt = (TextView) convertView.findViewById(R.id.count);

        imgDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DoctorDetailsActivity.class);
                intent.putExtra("id", doctorList.get(position).getDoctorID());
                context.startActivity(intent);
            }

        });


        imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ACTION_CALL, Uri.parse("tel:"+doctorList.get(position).getDoctorNumber()));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                context.startActivity(in);
            }

        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(context);
                dlgAlert.setMessage("Are you sure want to delete this doctor?");
                dlgAlert.setIcon(R.drawable.ic_launcher);
                dlgAlert.setTitle("Delete Doctor");
                dlgAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, DoctorList.class);
                        intent.putExtra("id", Integer.valueOf(doctorList.get(position).getMemberID()));
                        DoctorInfo doctorInfo = new DoctorInfo(context);
                        doctorInfo.deleteDoctor(doctorList.get(position).getDoctorID());
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
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditDoctor.class);
                intent.putExtra("id", doctorList.get(position).getDoctorID());
                context.startActivity(intent);
            }

        });

            if(position%5==0)
                imgDoctor.setImageResource(R.drawable.profilemale1);
            else if(position%5==1)
                imgDoctor.setImageResource(R.drawable.profilemale2);
            else if(position%5==2)
                imgDoctor.setImageResource(R.drawable.profilemale3);
            else if(position%5==3)
                imgDoctor.setImageResource(R.drawable.profilemale4);
            else
                imgDoctor.setImageResource(R.drawable.profilemale5);



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

        doctorName.setText(doctorList.get(position).getDoctorName());
        doctorNumber.setText(doctorList.get(position).getDoctorNumber());
        return convertView;

    }


}
