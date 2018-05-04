package com.example.asd.com.healthcare;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Assaduzzaman Noor on 4/29/2016.
 */


class Hospital {
    private int hospitalID;
    private String memberID;

    public String getHospitalName() {
        return hospitalName;
    }

    public String getHospitalState() {
        return hospitalState;
    }

    public String getHospitalNumber() {
        return hospitalNumber;
    }

    private String hospitalName;
    private String hospitalState;
    private String hospitalNumber;
    private String hospitalEmail;
    private String hospitalAdress;


    public Hospital(int hospitalID,String memberID, String hospitalName, String hospitalState, String hospitalNumber, String hospitalEmail, String hospitalAdress ) {
        this.hospitalID = hospitalID;
        this.memberID=memberID;
        this.hospitalName = hospitalName;
        this.hospitalState = hospitalState;
        this.hospitalNumber = hospitalNumber;
        this.hospitalEmail = hospitalEmail;
        this.hospitalAdress = hospitalAdress;

    }


    public int getHospitalID() {
        return hospitalID;
    }

    public String getMemberID(){ return memberID;}

    public String getHospitalEmail() {
        return hospitalEmail;
    }

    public String getHospitalAdress() {
        return hospitalAdress;
    }
}

public class HospitalInfo {

    private Hospital hospital;
    private ArrayList<Hospital> hospitalArrayList;
    private DatabaseManager databaseManager;
    Context context;

    public HospitalInfo(Context context) {
        this.context=context;
        hospitalArrayList = new ArrayList<>();
        databaseManager = new DatabaseManager(context);
        hospitalArrayList=databaseManager.getHospitalList();
    }

    public boolean addHospital(int hospitalID,String memberId, String hospitalName, String hospitalState, String hospitalNumber,String hospitalEmail, String hospitalAdress){
        hospital = new Hospital(hospitalID,memberId,hospitalName,hospitalState,hospitalNumber,hospitalEmail,hospitalAdress);
        hospitalArrayList.add(hospital);
        boolean inserted = databaseManager.addHospital(hospital);
        if(inserted) {
            hospitalArrayList = new ArrayList<>();
            hospitalArrayList = databaseManager.getHospitalList();
            return inserted;
        }
        else
            return inserted;

    }

    public boolean updateHospital(int hospitalID, String memberId,String hospitalName, String hospitalState, String hospitalNumber,String hospitalEmail, String hospitalAdress){
        hospital = new Hospital(hospitalID,memberId,hospitalName,hospitalState,hospitalNumber,hospitalEmail,hospitalAdress);
        boolean updated = databaseManager.updateHospital(hospital);
        if(updated) {
            hospitalArrayList = new ArrayList<>();
            hospitalArrayList = databaseManager.getHospitalList();
            return updated;
        }
        else
            return updated;

    }

    public Hospital getHospitalById(int hospitalID){
        for(int i=0; i<hospitalArrayList.size(); i++){
            if(hospitalID==hospitalArrayList.get(i).getHospitalID()){
                return hospitalArrayList.get(i);
            }
        }
        return hospitalArrayList.get(0);
    }


    public boolean deleteHospital(int hospitalId){
        for(int i=0; i<hospitalArrayList.size(); i++){
            if(hospitalId==hospitalArrayList.get(i).getHospitalID()){
                hospitalArrayList.remove(i);
                break;
            }
        }
        return databaseManager.deleteHospital(hospitalId);
    }

    public ArrayList<Hospital> getHospitalListByMemberId(int memberID){

        return databaseManager.getHospitalList();
    }

}
