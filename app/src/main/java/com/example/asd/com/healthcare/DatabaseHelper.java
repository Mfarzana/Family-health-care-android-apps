package com.example.asd.com.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Assaduzzaman Noor on 4/22/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    static  final int DATABASE_VERSION=1;
    static  final  String DATABASE_NAME="health_care";
    static  final String PROFILE_TABLE_NAME="profile";
    static final String COL_ID="id";
    static final String PROFILE_COL_ID=COL_ID;
    static final String PROFILE_COL_PINCODE="code";
    static final String PROFILE_COL_NAME="name";
    static final String PROFILE_COL_DATEOFBIRTH="dateofbirth";
    static final String PROFILE_COL_GENDER="gender";
    static final String PROFILE_COL_BLOODGROUP="bloodgroup";
    static final String PROFILE_COL_HIEGTH="higth";
    static final String PROFILE_COL_WEIGHT="weigth";
    static final String PROFILE_COL_RELATION="relation";

    static  final String DIET_TABLE_NAME="diet";
    static final String DIET_COL_ID=COL_ID;
    static final String DIET_COL_MEMBER_ID="mid";
    static final String DIET_COL_TYPE="type";
    static final String DIET_COL_MENU="menu";
    static final String DIET_COL_TIME="time";
    static final String DIET_COL_DATE="date";
    static final String DIET_COL_ALARM="alarm";
    static final String DIET_COL_REMAINDER="remaimder";

    static  final String VACCINE_TABLE_NAME="vaccine";
    static final String VACCINE_COL_ID=COL_ID;
    static final String VACCINE_COL_MEMBER_ID="memberid";
    static final String VACCINE_COL_NAME="name";
    static final String VACCINE_COL_TIME="time";
    static final String VACCINE_COL_DATE="date";
    static final String VACCINE_COL_DETAILS="details";
    static final String VACCINE_COL_ALARM="alarm";
    static final String VACCINE_COL_REMAINDER="remaimder";


    static  final String DOCTOR_TABLE_NAME="doctor";
    static final String DOCTOR_COL_ID=COL_ID;
    static final String DOCTOR_COL_MEMBER_ID="memberid";
    static final String DOCTOR_COL_NAME="name";
    static final String DOCTOR_COL_DEPARTMENT="datedepartment";
    static final String DOCTOR_COL_NUMBER="number";
    static final String DOCTOR_COL_EMAIL="email";
    static final String DOCTOR_COL_ADRESS="address";

    static  final String HOSPITAL_TABLE_NAME="hospital";
    static final String HOSPITAL_COL_ID=COL_ID;
    static final String HOSPITAL_COL_MEMBER_ID="memberid";
    static final String HOSPITAL_COL_NAME="name";
    static final String HOSPITAL_COL_STATE="state";
    static final String HOSPITAL_COL_NUMBER="number";
    static final String HOSPITAL_COL_EMAIL="email";
    static final String HOSPITAL_COL_ADRESS="address";






    String CREATE_TABLE_PROFILE=" CREATE TABLE " + PROFILE_TABLE_NAME +
            " ( " + PROFILE_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ PROFILE_COL_PINCODE + " TEXT," + PROFILE_COL_NAME + " TEXT," +
            PROFILE_COL_DATEOFBIRTH + " TEXT," +  PROFILE_COL_GENDER + " TEXT," + PROFILE_COL_BLOODGROUP + " TEXT," +  PROFILE_COL_RELATION + " TEXT," +  PROFILE_COL_HIEGTH + " TEXT," +  PROFILE_COL_WEIGHT +  " TEXT )";


    String CREATE_TABLE_DIET=" CREATE TABLE " + DIET_TABLE_NAME +
            " ( " + DIET_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ DIET_COL_MEMBER_ID + " TEXT," + DIET_COL_TYPE + " TEXT," +
            DIET_COL_MENU + " TEXT," +  DIET_COL_TIME + " TEXT," + DIET_COL_DATE + " TEXT," +  DIET_COL_ALARM + " TEXT," +  DIET_COL_REMAINDER +  " TEXT )";


    String CREATE_TABLE_VACCINE=" CREATE TABLE " + VACCINE_TABLE_NAME +
            " ( " + VACCINE_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ VACCINE_COL_MEMBER_ID + " TEXT," + VACCINE_COL_NAME + " TEXT," +
             VACCINE_COL_TIME + " TEXT," + VACCINE_COL_DATE + " TEXT," + VACCINE_COL_DETAILS + " TEXT," +  VACCINE_COL_ALARM + " TEXT," +  VACCINE_COL_REMAINDER +  " TEXT )";


    String CREATE_TABLE_DOCTOR=" CREATE TABLE " + DOCTOR_TABLE_NAME +
            " ( " + DOCTOR_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ DOCTOR_COL_MEMBER_ID + " TEXT," + DOCTOR_COL_NAME + " TEXT," +
            DOCTOR_COL_DEPARTMENT + " TEXT," +  DOCTOR_COL_NUMBER + " TEXT," + DOCTOR_COL_EMAIL + " TEXT," +  DOCTOR_COL_ADRESS+ " TEXT )";


    String CREATE_TABLE_HOSPITAL=" CREATE TABLE " + HOSPITAL_TABLE_NAME +
            " ( " + HOSPITAL_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ HOSPITAL_COL_MEMBER_ID + " TEXT," + HOSPITAL_COL_NAME + " TEXT," +
            HOSPITAL_COL_STATE + " TEXT," +  HOSPITAL_COL_NUMBER + " TEXT," + HOSPITAL_COL_EMAIL + " TEXT," +  HOSPITAL_COL_ADRESS+ " TEXT )";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PROFILE);
        db.execSQL(CREATE_TABLE_DIET);
        db.execSQL(CREATE_TABLE_VACCINE);
        db.execSQL(CREATE_TABLE_DOCTOR);
        db.execSQL(CREATE_TABLE_HOSPITAL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
