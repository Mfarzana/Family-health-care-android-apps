package com.example.asd.com.healthcare;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Assaduzzaman Noor on 4/21/2016.
 */
public class SingleProfileInfo {
    private int profileID;
    private int profileAge;
    private String profilePincode;
    private String profileName;
    private String profileBirthdy;
    private String profileBloodgroup;
    private String profileGender;
    private String profileRelation;
    private String profileHieght;
    private String profileWeight;


    public SingleProfileInfo(int profileID,String profilePinCode, String profileName, String profileBirthdy, String profileBloodgroup, String profileGender, String profileRelation, String profileHieght, String profileWeight) {
        this.profilePincode=profilePinCode;
        this.profileID = profileID;
        this.profileName = profileName;
        this.profileBirthdy = profileBirthdy;
        this.profileBloodgroup = profileBloodgroup;
        this.profileGender = profileGender;
        this.profileRelation = profileRelation;
        this.profileHieght = profileHieght;
        this.profileWeight = profileWeight;
        this.profileAge = calculateAge(profileBirthdy);
    }

    public String getProfilePincode() {
        return profilePincode;
    }

    public int getProfileAge() {
        return profileAge;
    }

    public int getProfileID() {
        return profileID;
    }

    public String getProfileName() {
        return profileName;
    }

    public String getProfileBirthdy() {
        return profileBirthdy;
    }

    public String getProfileBloodgroup(){
        return profileBloodgroup;
    }

    public String getProfileGender() {
        return profileGender;
    }

    public String getProfileHieght() {
        return profileHieght;
    }

    public String getProfileRelation() {
        return profileRelation;
    }

    public String getProfileWeight() {
        return profileWeight;
    }

    private int calculateAge(String birthday){
        int i;
        for( i=0;birthday.charAt(i)!='/';i++){
        }
        int bDay = Integer.parseInt(birthday.substring(0,i));
        int j=i+1;
        for(i=i+1; birthday.charAt(i)!='/';i++){
        }
        int bMonth = Integer.parseInt(birthday.substring(j,i));
        j=i+1;
        int bYear = Integer.parseInt(birthday.substring(j));

        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(bYear, bMonth, bDay);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        return age;

    }
}
