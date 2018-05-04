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
 * Created by Assaduzzaman Noor on 4/29/2016.
 */
public class HospitalAdapter extends ArrayAdapter<Hospital> {
    ArrayList<Hospital> hospitalList;
    Context context;
    ImageButton buttonEdit;
    ImageButton buttonDelete;
    ImageView imgHospital;
    ImageButton imgCall;
    TextView hospitalName;
    TextView hospitalNumber;
    TextView cnt;



    public HospitalAdapter(Context context, ArrayList<Hospital> hospitalList) {
        super(context, R.layout.hospital_item, hospitalList);
        this.hospitalList = hospitalList;
        this.context = context;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.hospital_item, null);
        imgHospital = (ImageView) convertView.findViewById(R.id.propic);
        buttonDelete = (ImageButton) convertView.findViewById(R.id.hospitalDelete);
        buttonEdit = (ImageButton) convertView.findViewById(R.id.hospitalEdit);
        hospitalName = (TextView) convertView.findViewById(R.id.etHospitalName);
        hospitalNumber = (TextView) convertView.findViewById(R.id.etHospitalNumber);
        imgCall = (ImageButton) convertView.findViewById(R.id.makeCall);


        cnt = (TextView) convertView.findViewById(R.id.count);

        imgHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HospitalDetailsActivity.class);
                intent.putExtra("id", hospitalList.get(position).getHospitalID());
                context.startActivity(intent);
            }

        });


        imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ACTION_CALL, Uri.parse("tel:" + hospitalList.get(position).getHospitalNumber()));
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
                dlgAlert.setMessage("Are you sure want to delete this hospital?");
                dlgAlert.setIcon(R.drawable.ic_launcher);
                dlgAlert.setTitle("Delete Hospital");
                dlgAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, HospitalList.class);
                        intent.putExtra("id", Integer.valueOf(hospitalList.get(position).getMemberID()));
                        HospitalInfo hospitalInfo = new HospitalInfo(context);
                        hospitalInfo.deleteHospital(hospitalList.get(position).getHospitalID());
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
                Intent intent = new Intent(context, EditHospital.class);
                intent.putExtra("id", hospitalList.get(position).getHospitalID());
                context.startActivity(intent);
            }

        });




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

        hospitalName.setText(hospitalList.get(position).getHospitalName());
        hospitalNumber.setText(hospitalList.get(position).getHospitalNumber());
        return convertView;

    }



}
