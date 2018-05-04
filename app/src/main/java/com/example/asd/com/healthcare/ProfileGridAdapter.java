package com.example.asd.com.healthcare;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Assaduzzaman Noor on 4/24/2016.
 */
public class ProfileGridAdapter extends BaseAdapter{
    ArrayList<SingleProfileInfo> singleProfileInfoArrayList;
    Context context;

    public ProfileGridAdapter(Context context, ArrayList<SingleProfileInfo> singleProfileInfoArrayList) {
        this.singleProfileInfoArrayList = singleProfileInfoArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return singleProfileInfoArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View grid;
        Button gridButton = null;
        final LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            grid = new View(context);
            grid = inflater.inflate(R.layout.grid_list_profile_item, null);
            TextView textViewName = (TextView) grid.findViewById(R.id.tvGridProfileName);
            TextView textViewRelation = (TextView) grid.findViewById(R.id.tvGridProfileRelation);
            ImageView imgViewProfilePic=(ImageView) grid.findViewById(R.id.gridProfilePicture);
            textViewName.setText(singleProfileInfoArrayList.get(position).getProfileName());
            textViewRelation.setText(singleProfileInfoArrayList.get(position).getProfileRelation());
            gridButton = (Button) grid.findViewById(R.id.gridItemButton);
            gridButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(context,MemberHomePageActivity.class);
                    intent.putExtra("id",singleProfileInfoArrayList.get(position).getProfileID());
                    context.startActivity(intent);

                }
            });
            if(singleProfileInfoArrayList.get(position).getProfileGender().equals("Male")){
                if(position%5==0)
                    imgViewProfilePic.setImageResource(R.drawable.gridprofilemale1);
                else if(position%5==1)
                    imgViewProfilePic.setImageResource(R.drawable.gridprofilemale2);
                else if(position%5==2)
                    imgViewProfilePic.setImageResource(R.drawable.gridprofilemale3);
                else if(position%5==3)
                    imgViewProfilePic.setImageResource(R.drawable.gridprofilemale4);
                else
                    imgViewProfilePic.setImageResource(R.drawable.gridprofilemale5);

            }
            else{
                if(position%5==0)
                    imgViewProfilePic.setImageResource(R.drawable.gridprofilefemale1);
                else if(position%5==1)
                    imgViewProfilePic.setImageResource(R.drawable.gridprofilefemale2);
                else if(position%5==2)
                    imgViewProfilePic.setImageResource(R.drawable.gridprofilefemale3);
                else if(position%5==3)
                    imgViewProfilePic.setImageResource(R.drawable.gridprofilefemale4);
                else
                    imgViewProfilePic.setImageResource(R.drawable.gridprofilefemale5);
            }
        } else {
            grid = (View) convertView;
        }



        return grid;

    }
}
