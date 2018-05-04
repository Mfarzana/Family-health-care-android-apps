package com.example.asd.com.healthcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by Assaduzzaman Noor on 4/19/2016.
 */

public class SessionManager {
    private DatabaseManager databaseManager;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private final static String SESSION_FILE_NAME="HealthCareSFile";
    private final static String PIN_CODE_KEY="PinCode";
    private final static String SESSION_STATUS_KEY="sessionStatus";
    public final static String PREVIOUS_ACTIVITY_CONTEXT_KEY="previousActivity";
    public final static String SESSION_PROFILE_ID="profileId";
    private final static String ADMIN_PIN_CODE_KEY="admin";
    private static Context context;
    private static String pinCode;
    private static String adminPinCode;

    public String getPinCode() {
        pinCode =sharedPreferences.getString(PIN_CODE_KEY, "0");
        return pinCode;
    }

    public String getAdminPinCode(){
        adminPinCode =sharedPreferences.getString(ADMIN_PIN_CODE_KEY,"0");
        return adminPinCode;
    }

    public boolean isAdmin(){
        return getPinCode().equals(getAdminPinCode());
    }

    public boolean isWelcomeSession(){
        return sharedPreferences.getBoolean(pinCode, false);
    }

    public void setIsWelcomeSession(boolean status){
        editor.putBoolean(pinCode,status);
    }

    public boolean getSessionStatus(){
        return sharedPreferences.getBoolean(SESSION_STATUS_KEY,false);
    }

    public void setAdminPinCode(String pinCode) {
        editor.putString(PIN_CODE_KEY, pinCode);
        editor.putString(ADMIN_PIN_CODE_KEY,pinCode);
        editor.commit();
    }

    public boolean setMemberPinCode(String pinCode , int profileId) {
       if(checkPin(pinCode))
       {
           return false;
       }

        databaseManager = new DatabaseManager(context);
        SingleProfileInfo singleProfileInfo = databaseManager.getSingleProfile(profileId);
        SingleProfileInfo updateProfile=new SingleProfileInfo(profileId,pinCode,singleProfileInfo.getProfileName(),singleProfileInfo.getProfileBirthdy(),singleProfileInfo.getProfileBloodgroup(),singleProfileInfo.getProfileGender(),singleProfileInfo.getProfileRelation(),singleProfileInfo.getProfileHieght(),singleProfileInfo.getProfileWeight());
        return databaseManager.updateSingleProfile(updateProfile);

    }


    public boolean checkPin(String pinCode){
        databaseManager = new DatabaseManager(context);
        ArrayList<SingleProfileInfo> singleProfileInfos;
        singleProfileInfos = databaseManager.getAllProfileList();

        for(int i=0; i<singleProfileInfos.size(); i++){
            SingleProfileInfo profileInfo= singleProfileInfos.get(i);
            if(profileInfo.getProfilePincode().equals(pinCode)){
                return true;
            }
        }
        return false;
    }

    public void sessionOut(){
        editor.putBoolean(SESSION_STATUS_KEY, false);
        editor.commit();
    }

    public void sessionSignOut(){
        if(isWelcomeSession())
            setIsWelcomeSession(false);
        sessionOut();
        Intent intent = new Intent(context, PinValidityCheckActivity.class);
        intent.putExtra(PREVIOUS_ACTIVITY_CONTEXT_KEY, "start");
        context.startActivity(intent);
    }

    public void sessionIn(String piCode){
        editor.putString("pin", piCode);
        editor.putBoolean(SESSION_STATUS_KEY, true);
        editor.commit();
    }
    public String getLogedPin(){

        return sharedPreferences.getString("pin", "0");
    }

    public void startSession(String pinCode){
        sessionIn(pinCode);
        Intent intent = new Intent(context,HomeActivity.class);
        context.startActivity(intent);
    }

    public void checkSessionValidity(){
        if(getSessionStatus())
            return ;
        else {
            Intent intent = new Intent(context, PinValidityCheckActivity.class);
            intent.putExtra(PREVIOUS_ACTIVITY_CONTEXT_KEY,"other");
            context.startActivity(intent);
            return;
        }
    }

    public SessionManager(Context context) {
        SessionManager.context =context;
        databaseManager =new DatabaseManager(context);
        sharedPreferences = context.getSharedPreferences(SESSION_FILE_NAME,context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        pinCode = sharedPreferences.getString(PIN_CODE_KEY,"0");
    }
}
