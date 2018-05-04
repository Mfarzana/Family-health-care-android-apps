package com.example.asd.com.healthcare;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Assaduzzaman Noor on 4/27/2016.
 */


class Vaccine {
    private int vaccineId;
    private String memberId;
    private String name;
    private String date;
    private String time;
    private String details;
    private String alarm;
    private String remainder;

    public Vaccine(int vaccineId, String memberId, String name, String date, String time, String details, String alarm, String remainder) {
        this.vaccineId = vaccineId;
        this.memberId = memberId;
        this.name = name;
        this.date = date;
        this.time = time;
        this.details = details;
        this.alarm = alarm;
        this.remainder = remainder;
    }

    public int getVaccineId() {
        return vaccineId;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDetails() {
        return details;
    }

    public String getAlarm() {
        return alarm;
    }

    public String getRemainder() {
        return remainder;
    }
}


public class VaccineInfo {
    private Vaccine vaccine;
    private ArrayList<Vaccine> vaccineList;
    private DatabaseManager databaseManager;
    private String Today;
    private String ampm;
    private Context context;



    public VaccineInfo(Context context) {
        this.context = context;
        vaccineList = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhmma");
        String time = df.format(Calendar.getInstance().getTime());
        Today = String.valueOf(Integer.parseInt(time.substring(6, 8)))+"/"+ String.valueOf(Integer.parseInt(time.substring(4, 6))) + "/" +  String.valueOf(Integer.parseInt(time.substring(0, 4)));
        databaseManager = new DatabaseManager(context);
        vaccineList =databaseManager.getVaccineList();

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

    public boolean addVaccine(int vaccineId, String memberId, String name, String date, String time, String details, String alarm, String remainder){
        vaccine =new Vaccine(vaccineId,memberId,name,date,time,details,alarm,remainder);
        vaccineList.add(vaccine);
        boolean inserted = databaseManager.addVaccine(vaccine);
        vaccineList = databaseManager.getVaccineList();
        return inserted;
    }

    public boolean updateVaccine(int vaccineId, String memberId, String name, String date, String time, String details, String alarm, String remainder){
        vaccine =new Vaccine(vaccineId,memberId,name,date,time,details,alarm,remainder);
        boolean updated=databaseManager.updateVaccine(vaccine);
        vaccineList = databaseManager.getVaccineList();
        return updated;
    }



    public ArrayList<Vaccine> getMemberVaccineListByMemberId(int memberID){
        String mId=String.valueOf(memberID);
        ArrayList<Vaccine> memberVaccineList = new ArrayList<>();
        for(int i=0; i<vaccineList.size(); i++) {
            if (mId.equals(vaccineList.get(i).getMemberId())){
                memberVaccineList.add(vaccineList.get(i));
            }
        }
        return memberVaccineList;
    }

    public ArrayList<Vaccine> getMemberTodayVaccineListByMemberId(int memberID){
        String mId=String.valueOf(memberID);
        ArrayList<Vaccine> memberVaccineList = new ArrayList<>();
        for(int i=0; i<vaccineList.size(); i++){
            if(mId.equals(vaccineList.get(i).getMemberId()) && vaccineList.get(i).getDate().equals(Today)){
                memberVaccineList.add(vaccineList.get(i));
            }
        }
        return memberVaccineList;
    }
    public ArrayList<Vaccine> getMemberPreviousVaccineListByMemberId(int memberID){
        String mId=String.valueOf(memberID);
        ArrayList<Vaccine> memberVaccineList = new ArrayList<>();
        for(int i=0; i<vaccineList.size(); i++){
            if(mId.equals(vaccineList.get(i).getMemberId()) && compareDateWithToday(vaccineList.get(i).getDate())<0){
                memberVaccineList.add(vaccineList.get(i));
            }
        }
        return memberVaccineList;
    }

    public ArrayList<Vaccine> getMemberUpcomingVaccineListByMemberId(int memberID){
        String mId=String.valueOf(memberID);
        ArrayList<Vaccine> memberVaccineList = new ArrayList<>();
        for(int i=0; i<vaccineList.size(); i++){
            if(mId.equals(vaccineList.get(i).getMemberId()) && compareDateWithToday(vaccineList.get(i).getDate())>0){
                memberVaccineList.add(vaccineList.get(i));
            }
        }
        return memberVaccineList;
    }



    public Vaccine getVaccineById(int vaccineID){
        for(int i=0; i<vaccineList.size(); i++){
            if(vaccineID==vaccineList.get(i).getVaccineId()){
                return vaccineList.get(i);
            }
        }
        return vaccineList.get(0);
    }

}
