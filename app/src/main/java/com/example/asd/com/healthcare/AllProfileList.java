package com.example.asd.com.healthcare;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Assaduzzaman Noor on 4/21/2016.
 */

public class AllProfileList {
    private SingleProfileInfo singleProfileInfo;
    private ArrayList<SingleProfileInfo> singleProfileInfoArrayList;
    private DatabaseManager databaseManager;
    Context context;

    public AllProfileList(Context context) {
        this.context=context;
        singleProfileInfoArrayList = new ArrayList<>();
        databaseManager = new DatabaseManager(context);
        singleProfileInfoArrayList=databaseManager.getAllProfileList();
    }

    public boolean addSingleProfile(int profileID,String profilePinCode,String profileName, String profileBirthdy, String profileGender,String profileBloodgroup, String profileRelation, String profileHieght, String profileWeight){
        singleProfileInfo = new SingleProfileInfo(profileID,profilePinCode,profileName,profileBirthdy,profileGender,profileBloodgroup,profileRelation,profileHieght,profileWeight);
        singleProfileInfoArrayList.add(singleProfileInfo);
        boolean inserted = databaseManager.addSingleProfile(singleProfileInfo);
        if(inserted) {
            singleProfileInfoArrayList = new ArrayList<>();
            singleProfileInfoArrayList = databaseManager.getAllProfileList();
            return inserted;
        }
        else
            return inserted;

    }

    public boolean updateSingleProfile(int profileID,String profilePinCode, String profileName, String profileBirthdy, String profileGender,String profileBloodgroup, String profileRelation, String profileHieght, String profileWeight){
        singleProfileInfo = new SingleProfileInfo(profileID,profilePinCode,profileName,profileBirthdy,profileGender,profileBloodgroup,profileRelation,profileHieght,profileWeight);
        boolean updated = databaseManager.updateSingleProfile(singleProfileInfo);
        if(updated) {
            singleProfileInfoArrayList = new ArrayList<>();
            singleProfileInfoArrayList = databaseManager.getAllProfileList();
            return updated;
        }
        else
            return updated;

    }

    public SingleProfileInfo getProfileById(int profileID){
        for(int i=0; i<singleProfileInfoArrayList.size(); i++){
            if(profileID==singleProfileInfoArrayList.get(i).getProfileID()){
                return singleProfileInfoArrayList.get(i);
            }
        }
        return singleProfileInfoArrayList.get(0);
    }

    public SingleProfileInfo getProfileByPinCaode(String profilepinCode){
        return getProfileById(databaseManager.getprofileId(profilepinCode));
    }

    public boolean deleteSingleProfile(int profileId){
        for(int i=0; i<singleProfileInfoArrayList.size(); i++){
            if(profileId==singleProfileInfoArrayList.get(i).getProfileID()){
                singleProfileInfoArrayList.remove(i);
                break;
            }
        }
        return databaseManager.deleteSingleProfile(profileId);
    }


    public ArrayList<SingleProfileInfo> getSingleProfileInfoArrayList() {
        return singleProfileInfoArrayList;
    }

}
