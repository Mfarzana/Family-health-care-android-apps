package com.example.asd.com.healthcare;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Assaduzzaman Noor on 4/27/2016.
 */
public class VaccineAdapter extends ArrayAdapter<Vaccine> {
    ArrayList<Vaccine> vaccineList;
    Context context;
    ImageButton buttonEdit;
    ImageButton buttonDelete;
    ImageView imgProfile;
    TextView profileName;
    TextView profileDetails;
    TextView profileTime;
    TextView cnt;
    public VaccineAdapter(Context context, ArrayList<Vaccine> vaccineList) {
        super(context, R.layout.vaccine_item, vaccineList);
        this.vaccineList = vaccineList;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.vaccine_item, null);
        imgProfile =(ImageView)  convertView.findViewById(R.id.propic);
        buttonDelete = (ImageButton) convertView.findViewById(R.id.tVaccineDelete);
        buttonEdit = (ImageButton) convertView.findViewById(R.id.tvaccineEdit);
        profileName = (TextView) convertView.findViewById(R.id.tVaccineztype);
        profileDetails = (TextView) convertView.findViewById(R.id.tDetails);
        profileTime = (TextView) convertView.findViewById(R.id.tVaccineTime);
        cnt =(TextView) convertView.findViewById(R.id.count);

        VaccineInfo vaccineInfo = new VaccineInfo(context);

        if(vaccineInfo.compareDateWithToday(vaccineList.get(position).getDate())>=0){

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(context);
                    dlgAlert.setMessage("Are you sure want to delete this Vaccine?");
                    dlgAlert.setIcon(R.drawable.ic_launcher);
                    dlgAlert.setTitle("Delete Vaccine");
                    dlgAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            VaccineInfo vaccineInfo = new VaccineInfo(context);
                            AllProfileList allProfileList = new AllProfileList(context);
                            SingleProfileInfo singleProfileInfo= allProfileList.getProfileById(Integer.parseInt(vaccineList.get(position).getMemberId()));
                            String msg=singleProfileInfo.getProfileName()+" have a vaccine ("+vaccineList.get(position).getName()+")";
                            RemainderManager remainderManager = new RemainderManager(context,context.getApplicationContext(),msg);
                            remainderManager.deleteRemainder(vaccineList.get(position).getVaccineId()+1000);


                            if(vaccineInfo.compareDateWithToday(vaccineList.get(position).getDate())<0) {
                                DatabaseManager databaseManager = new DatabaseManager(context);
                                databaseManager.deleteVaccine(vaccineList.get(position).getVaccineId());
                                Intent intent = new Intent(context, PreviousVaccine.class);
                                intent.putExtra("id",Integer.parseInt(vaccineList.get(position).getMemberId()));
                                context.startActivity(intent);

                            }
                            else if(vaccineInfo.compareDateWithToday(vaccineList.get(position).getDate())>0) {
                                DatabaseManager databaseManager = new DatabaseManager(context);
                                databaseManager.deleteVaccine(vaccineList.get(position).getVaccineId());
                                Intent intent = new Intent(context, UpComingVaccine.class);
                                intent.putExtra("id",Integer.parseInt(vaccineList.get(position).getMemberId()));
                                context.startActivity(intent);
                            }
                            else {
                                DatabaseManager databaseManager = new DatabaseManager(context);
                                databaseManager.deleteVaccine(vaccineList.get(position).getVaccineId());
                                Intent intent = new Intent(context, TodaysVaccine.class);
                                intent.putExtra("id", Integer.parseInt(vaccineList.get(position).getMemberId()));
                                context.startActivity(intent);
                            }
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
                    Intent intent = new Intent(context, EditVaccine.class);
                    intent.putExtra("pid",Integer.parseInt(vaccineList.get(position).getMemberId()));
                    intent.putExtra("did", vaccineList.get(position).getVaccineId());
                    context.startActivity(intent);
                }

            });
        }
        else {
            buttonDelete.setImageResource(R.drawable.delete_disable);
            buttonEdit.setImageResource(R.drawable.editing_disable);
        }






        if(position%5==0)
            imgProfile.setImageResource(R.drawable.vaccineitem1);
        else if(position%5==1)
            imgProfile.setImageResource(R.drawable.vaccineitem2);
        else if(position%5==2)
            imgProfile.setImageResource(R.drawable.vaccineitem3);
        else if(position%5==3)
            imgProfile.setImageResource(R.drawable.vaccineitem4);
        else
            imgProfile.setImageResource(R.drawable.vaccineitem5);


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
        if(vaccineList.get(position).getRemainder().equals("on")||vaccineList.get(position).getAlarm().equals("on"))
            ((ImageView) convertView.findViewById(R.id.imgVaccineRemainder)).setImageResource(R.drawable.clock);
        profileName.setText(vaccineList.get(position).getName());
        profileDetails.setText(vaccineList.get(position).getDetails());
        String time=vaccineList.get(position).getTime();
        int hour=Integer.valueOf(time.substring(0,2));
        if(hour>12) {
            String h=String.valueOf(hour%12);
            if (h.length()<2)
                h+="0";
            time = h+ time.substring(2) + " PM";
        }
        else
            time=time +" AM";

        profileTime.setText(vaccineList.get(position).getDate()+"  "+time);
        return convertView;

    }



}
