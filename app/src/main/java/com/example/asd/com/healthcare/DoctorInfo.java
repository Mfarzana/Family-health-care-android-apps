package com.example.asd.com.healthcare;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Assaduzzaman Noor on 4/28/2016.
 */

class Doctor {
    private int doctorID;
    private String memberID;
    private String doctorName;
    private String doctorDepartment;
    private String doctorNumber;
    private String doctorEmail;
    private String doctorAdress;


    public Doctor(int doctorID,String memberID, String doctorName, String doctorDepartment, String doctorNumber, String doctorEmail, String doctorAdress ) {

        this.doctorID = doctorID;
        this.memberID=memberID;
        this.doctorName = doctorName;
        this.doctorDepartment = doctorDepartment;
        this.doctorNumber = doctorNumber;
        this.doctorEmail = doctorEmail;
        this.doctorAdress = doctorAdress;

    }


    public int getDoctorID() {
        return doctorID;
    }

    public String getMemberID(){ return memberID;}

    public String getDoctorName() {
        return doctorName;
    }

    public String getDoctorDepartment() {
        return doctorDepartment;
    }

    public String getDoctorNumber(){
        return doctorNumber;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public String getDoctorAdress() {
        return doctorAdress;
    }

}




public class DoctorInfo {
    private Doctor doctor;
    private ArrayList<Doctor> doctorArrayList;
    private DatabaseManager databaseManager;
    Context context;

    public DoctorInfo(Context context) {
        this.context=context;
        doctorArrayList = new ArrayList<>();
        databaseManager = new DatabaseManager(context);
        doctorArrayList=databaseManager.getDoctorList();
    }

    public boolean addDoctor(int doctorID,String memberId, String doctorName, String doctorDepartment, String doctorNumber,String doctorEmail, String doctorAdress){
        doctor = new Doctor(doctorID,memberId,doctorName,doctorDepartment,doctorNumber,doctorEmail,doctorAdress);
        doctorArrayList.add(doctor);
        boolean inserted = databaseManager.addDoctor(doctor);
        if(inserted) {
            doctorArrayList = new ArrayList<>();
            doctorArrayList = databaseManager.getDoctorList();
            return inserted;
        }
        else
            return inserted;

    }

    public boolean updateDoctor(int doctorID, String memberId,String doctorName, String doctorDepartment, String doctorNumber,String doctorEmail, String doctorAdress){
        doctor = new Doctor(doctorID,memberId,doctorName,doctorDepartment,doctorNumber,doctorEmail,doctorAdress);
        boolean updated = databaseManager.updateDoctor(doctor);
        if(updated) {
            doctorArrayList = new ArrayList<>();
            doctorArrayList = databaseManager.getDoctorList();
            return updated;
        }
        else
            return updated;

    }

    public Doctor getDoctorById(int doctorID){
        for(int i=0; i<doctorArrayList.size(); i++){
            if(doctorID==doctorArrayList.get(i).getDoctorID()){
                return doctorArrayList.get(i);
            }
        }
        return doctorArrayList.get(0);
    }


    public boolean deleteDoctor(int doctorId){
        for(int i=0; i<doctorArrayList.size(); i++){
            if(doctorId==doctorArrayList.get(i).getDoctorID()){
                doctorArrayList.remove(i);
                break;
            }
        }
        return databaseManager.deleteDoctor(doctorId);
    }

    public ArrayList<Doctor> getDoctorListByMemberId(int memberID){
        String mId=String.valueOf(memberID);
        ArrayList<Doctor> memberDoctorList = new ArrayList<>();
        for(int i=0; i<doctorArrayList.size(); i++){
            if(mId.equals(doctorArrayList.get(i).getMemberID()) ){

                memberDoctorList.add(doctorArrayList.get(i));
            }
        }
        return memberDoctorList;
    }



    public ArrayList<Doctor> getDoctorArrayList() {
        return databaseManager.getDoctorList();
    }
}
