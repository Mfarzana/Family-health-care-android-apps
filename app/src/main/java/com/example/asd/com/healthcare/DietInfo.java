package com.example.asd.com.healthcare;

import android.content.Context;
import android.text.format.DateFormat;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Assaduzzaman Noor on 4/26/2016.
 */


class Diet {
    private int dietId;
    private String memberId;
    private String type;
    private String menu;
    private String date;
    private String time;
    private String alarm;
    private String remainder;


    public Diet(int dietId, String memberId, String type, String menu, String date, String time, String alarm, String remainder) {
        this.dietId = dietId;
        this.memberId = memberId;
        this.type = type;
        this.menu = menu;
        this.date = date;
        this.time = time;
        this.alarm = alarm;
        this.remainder = remainder;
    }

    public int getDietId() {
        return dietId;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getType() {
        return type;
    }

    public String getMenu() {
        return menu;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getAlarm() {
        return alarm;
    }

    public String getRemainder() {
        return remainder;
    }
}

public class DietInfo {
    private Diet diet;

    public ArrayList<Diet> getDietList() {
        return dietList;
    }

    private ArrayList<Diet> dietList;
    private DatabaseManager databaseManager;
    private String Today;
    private String ampm;
    private Context context;


    public DietInfo(Context context) {
        this.context = context;
        dietList = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhmma");
        String date = df.format(Calendar.getInstance().getTime());
        Today = String.valueOf(Integer.parseInt(date.substring(6, 8)))+"/"+ String.valueOf(Integer.parseInt(date.substring(4, 6))) + "/" +  String.valueOf(Integer.parseInt(date.substring(0, 4)));
        databaseManager = new DatabaseManager(context);
        dietList =databaseManager.getDietList();

    }

    public int compareDateWithToday(String cmpDate){
        int i;
        for( i=0;cmpDate.charAt(i)!='/';i++){
        }
        int bDay = Integer.parseInt(cmpDate.substring(0,i));
        int j=i+1;
        for(i=i+1; cmpDate.charAt(i)!='/';i++){
        }
        int bMonth = Integer.parseInt(cmpDate.substring(j, i));
        j=i+1;
        int bYear = Integer.parseInt(cmpDate.substring(j));
        Calendar today = Calendar.getInstance();
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH)+1;
        int day = today.get(Calendar.DAY_OF_MONTH);
        if(year>bYear)
            return -1;
        else if(year<bYear)
            return 1;
        else if (month>bMonth)
            return -1;
        else if (month<bMonth)
            return 1;
        else if (day>bDay)
            return -1;
        else if (day<bDay)
            return 1;
        else
            return 0;

    }

    public boolean addDiet(int dietId, String memberId, String type, String menu, String date, String time, String alarm, String remainder){
        diet =new Diet(dietId,memberId,type,menu,date,time,alarm,remainder);
        dietList.add(diet);
        boolean inserted = databaseManager.addDiet(diet);
        dietList = databaseManager.getDietList();
        return inserted;
    }

    public boolean updateDiet(int dietId, String memberId, String type, String menu, String date, String time, String alarm, String remainder){
        diet =new Diet(dietId,memberId,type,menu,date,time,alarm,remainder);
        boolean updated=databaseManager.updateDiet(diet);
        dietList = databaseManager.getDietList();
        return updated;
    }


    public ArrayList<Diet> getMemberDietListByMemberId(int memberID){
        String mId=String.valueOf(memberID);
        ArrayList<Diet> memberDietList = new ArrayList<>();
        for(int i=0; i<dietList.size(); i++){
            if(mId.equals(dietList.get(i).getMemberId())){
                memberDietList.add(dietList.get(i));
            }
        }
        return memberDietList;
    }

    public ArrayList<Diet> getMemberTodayDietListByMemberId(int memberID){
        String mId=String.valueOf(memberID);
        ArrayList<Diet> memberDietList = new ArrayList<>();
        for(int i=0; i<dietList.size(); i++){
            if(mId.equals(dietList.get(i).getMemberId()) && dietList.get(i).getDate().equals(Today)){
                memberDietList.add(dietList.get(i));
            }
        }
        return memberDietList;
    }
    public ArrayList<Diet> getMemberPreviousDietListByMemberId(int memberID){
        String mId=String.valueOf(memberID);
        ArrayList<Diet> memberDietList = new ArrayList<>();
        for(int i=0; i<dietList.size(); i++){
            if(mId.equals(dietList.get(i).getMemberId()) && compareDateWithToday(dietList.get(i).getDate())<0){
                memberDietList.add(dietList.get(i));
            }
        }
        return memberDietList;
    }

    public ArrayList<Diet> getMemberUpcomingDietListByMemberId(int memberID){
        String mId=String.valueOf(memberID);
        ArrayList<Diet> memberDietList = new ArrayList<>();
        for(int i=0; i<dietList.size(); i++){
            if(mId.equals(dietList.get(i).getMemberId()) && compareDateWithToday(dietList.get(i).getDate())>0){

                memberDietList.add(dietList.get(i));
            }
        }
        return memberDietList;
    }

    public Diet getDietById(int dietID){
        for(int i=0; i<dietList.size(); i++){
            if(dietID==dietList.get(i).getDietId()){
                return dietList.get(i);
            }
        }
        return dietList.get(0);
    }
    public ArrayList<ChartList> getDietChartList(){
        ArrayList<ChartList> chartListArrayList =new ArrayList<>();
        SingleProfileInfo singleProfileInfo;
        AllProfileList allProfileList = new AllProfileList(context);
        ArrayList<SingleProfileInfo> profileList=allProfileList.getSingleProfileInfoArrayList();
        for(int i=0;i<profileList.size();i++){
            singleProfileInfo=profileList.get(i);
            ArrayList<Diet> dietArrayList1=new ArrayList<>();
            dietArrayList1=getMemberTodayDietListByMemberId(singleProfileInfo.getProfileID());
            if(dietArrayList1.size()>0){
                Diet diet=dietArrayList1.get(0);
                ChartList chartList =new ChartList(singleProfileInfo.getProfileName(),diet.getType(),diet.getMenu());
                chartListArrayList.add(chartList);
            }
        }
        return chartListArrayList;
    }

}
