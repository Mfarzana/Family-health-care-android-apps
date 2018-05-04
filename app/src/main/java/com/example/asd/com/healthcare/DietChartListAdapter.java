package com.example.asd.com.healthcare;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

import java.util.ArrayList;

import static android.content.Intent.ACTION_CALL;

/**
 * Created by Assaduzzaman Noor on 4/29/2016.
 */
public class DietChartListAdapter extends ArrayAdapter<ChartList> {
    ArrayList<ChartList> chartLists;
    Context context;
    TextView tvName;
    TextView tvType;
    TextView tvMenu;


    public DietChartListAdapter(Context context, ArrayList<ChartList> chartLists) {
        super(context, R.layout.chart_diet_item, chartLists);
        this.chartLists = chartLists;
        this.context = context;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.chart_diet_item, null);

        tvName = (TextView)convertView.findViewById(R.id.tvName);
        tvType = (TextView)convertView.findViewById(R.id.tvType);
        tvMenu = (TextView)convertView.findViewById(R.id.tvMenu);

        if(position%5==0)
            tvName.setTextColor(Color.parseColor("#00d700"));
        else if(position%5==1)
            tvName.setTextColor(Color.parseColor("#fec901"));
        else if(position%5==2)
            tvName.setTextColor(Color.parseColor("#019dd8"));
        else if(position%5==3)
            tvName.setTextColor(Color.parseColor("#9551fe"));
        else
            tvMenu.setTextColor(Color.parseColor("#b50937"));


        if(position%5==0)
            tvMenu.setTextColor(Color.parseColor("#00d700"));
        else if(position%5==1)
            tvMenu.setTextColor(Color.parseColor("#fec901"));
        else if(position%5==2)
            tvMenu.setTextColor(Color.parseColor("#019dd8"));
        else if(position%5==3)
            tvMenu.setTextColor(Color.parseColor("#9551fe"));
        else
            tvMenu.setTextColor(Color.parseColor("#b50937"));
        if(position%5==0)
            tvType.setTextColor(Color.parseColor("#00d700"));
        else if(position%5==1)
            tvType.setTextColor(Color.parseColor("#fec901"));
        else if(position%5==2)
            tvType.setTextColor(Color.parseColor("#019dd8"));
        else if(position%5==3)
            tvType.setTextColor(Color.parseColor("#9551fe"));
        else
            tvType.setTextColor(Color.parseColor("#b50937"));

        tvName.setText(chartLists.get(position).getPname());
        tvMenu.setText(chartLists.get(position).getDtype());
        tvType.setText(chartLists.get(position).getDmenu());
        return convertView;

    }
}
