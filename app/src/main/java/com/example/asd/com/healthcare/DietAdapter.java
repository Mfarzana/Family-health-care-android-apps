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
 * Created by Assaduzzaman Noor on 4/26/2016.
 */
public class DietAdapter extends ArrayAdapter<Diet> {
    ArrayList<Diet> dietList;
    Context context;
    ImageButton buttonEdit;
    ImageButton buttonDelete;
    ImageView imgProfile;
    TextView profileType;
    TextView profileMenu;
    TextView profileTime;
    TextView cnt;

    public DietAdapter(Context context, ArrayList<Diet> dietList) {
        super(context, R.layout.diet_item, dietList);
        this.dietList = dietList;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.diet_item, null);
        imgProfile =(ImageView)  convertView.findViewById(R.id.propic);
        buttonDelete = (ImageButton) convertView.findViewById(R.id.tDietDelete);
        buttonEdit = (ImageButton) convertView.findViewById(R.id.tdietEdit);
        profileType = (TextView) convertView.findViewById(R.id.tDietztype);
        profileMenu = (TextView) convertView.findViewById(R.id.tDirtMenu);
        profileTime = (TextView) convertView.findViewById(R.id.tDietTime);
        cnt =(TextView) convertView.findViewById(R.id.count);

        DietInfo dietInfo = new DietInfo(context);

        if(dietInfo.compareDateWithToday(dietList.get(position).getDate())>=0){

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(context);
                    dlgAlert.setMessage("Are you sure want to delete this Diet?");
                    dlgAlert.setIcon(R.drawable.ic_launcher);
                    dlgAlert.setTitle("Delete Diet");
                    dlgAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            DietInfo dietInfo = new DietInfo(context);
                            AllProfileList allProfileList = new AllProfileList(context);
                            SingleProfileInfo singleProfileInfo= allProfileList.getProfileById(Integer.parseInt(dietList.get(position).getMemberId()));
                            String msg=singleProfileInfo.getProfileName()+" have a diet ("+dietList.get(position).getMenu()+")";
                            RemainderManager remainderManager = new RemainderManager(context,context.getApplicationContext(),msg);
                            remainderManager.deleteRemainder(dietList.get(position).getDietId()+100);
                            if(dietInfo.compareDateWithToday(dietList.get(position).getDate())<0) {
                                DatabaseManager databaseManager = new DatabaseManager(context);
                                databaseManager.deleteDiet(dietList.get(position).getDietId());
                                Intent intent = new Intent(context, PreviousDiet.class);
                                intent.putExtra("id",Integer.parseInt(dietList.get(position).getMemberId()));
                                context.startActivity(intent);

                            }
                            else if(dietInfo.compareDateWithToday(dietList.get(position).getDate())>0) {
                                DatabaseManager databaseManager = new DatabaseManager(context);
                                databaseManager.deleteDiet(dietList.get(position).getDietId());
                                Intent intent = new Intent(context, UpComingDiet.class);
                                intent.putExtra("id",Integer.parseInt(dietList.get(position).getMemberId()));
                                context.startActivity(intent);
                            }
                            else {
                                DatabaseManager databaseManager = new DatabaseManager(context);
                                databaseManager.deleteDiet(dietList.get(position).getDietId());
                                Intent intent = new Intent(context, TodaysDiet.class);
                                intent.putExtra("id", Integer.parseInt(dietList.get(position).getMemberId()));
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
                    Intent intent = new Intent(context, EditDiet.class);
                    intent.putExtra("pid",Integer.parseInt(dietList.get(position).getMemberId()));
                    intent.putExtra("did", dietList.get(position).getDietId());
                    context.startActivity(intent);
                }

            });
        }
        else {
            buttonDelete.setImageResource(R.drawable.delete_disable);
            buttonEdit.setImageResource(R.drawable.editing_disable);
        }






            if(position%5==0)
                imgProfile.setImageResource(R.drawable.dietitem1);
            else if(position%5==1)
                imgProfile.setImageResource(R.drawable.dietitem2);
            else if(position%5==2)
                imgProfile.setImageResource(R.drawable.dietitem3);
            else if(position%5==3)
                imgProfile.setImageResource(R.drawable.dietitem4);
            else
                imgProfile.setImageResource(R.drawable.dietitem5);


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
        if(dietList.get(position).getRemainder().equals("on")||dietList.get(position).getAlarm().equals("on"))
            ((ImageView) convertView.findViewById(R.id.imgDietRemainder)).setImageResource(R.drawable.clock);
        profileType.setText(dietList.get(position).getType());
        profileMenu.setText(dietList.get(position).getMenu());
        String time=dietList.get(position).getTime();
        int hour=Integer.valueOf(time.substring(0,2));
        if(hour>12) {
            String h=String.valueOf(hour%12);
            if (h.length()<2)
                h+="0";
            time = h+ time.substring(2) + " PM";
        }
        else
            time=time +" AM";

        profileTime.setText(dietList.get(position).getDate()+"  "+time);
        return convertView;

    }



}
