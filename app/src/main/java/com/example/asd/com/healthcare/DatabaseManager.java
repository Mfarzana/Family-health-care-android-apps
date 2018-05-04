package com.example.asd.com.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Assaduzzaman Noor on 4/22/2016.
 */
public class DatabaseManager {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    private Context context;

    public DatabaseManager(Context context) {
        this.context =context;
        databaseHelper = new DatabaseHelper(context);
    }

    private void open() {
        database = databaseHelper.getWritableDatabase();

    }

    private void close() {
        databaseHelper.close();
    }

    private boolean INSERT(int NUMBER_OF_DATA_TO_BE_INSERTED, String TABLE_NAME, String COL1, String COL2, String COL3, String COL4, String COL5, String COL6, String COL7, String COL8, String COL9, String COL10, String COL1_VALUE, String COL2_VALUE, String COL3_VALUE, String COL4_VALUE, String COL5_VALUE, String COL6_VALUE, String COL7_VALUE, String COL8_VALUE, String COL9_VALUE, String COL10_VALUE){
            this.open();
            ContentValues contentValues = new ContentValues();
            if(NUMBER_OF_DATA_TO_BE_INSERTED==1) {
                contentValues.put(COL1,COL1_VALUE);
            }
            else if(NUMBER_OF_DATA_TO_BE_INSERTED==2) {
                contentValues.put(COL1,COL1_VALUE);
                contentValues.put(COL2,COL2_VALUE);

            }
            else if(NUMBER_OF_DATA_TO_BE_INSERTED==3) {
                contentValues.put(COL1,COL1_VALUE);
                contentValues.put(COL2,COL2_VALUE);
                contentValues.put(COL3,COL3_VALUE);
            }
            else if(NUMBER_OF_DATA_TO_BE_INSERTED==4) {
                contentValues.put(COL1,COL1_VALUE);
                contentValues.put(COL2,COL2_VALUE);
                contentValues.put(COL3,COL3_VALUE);
                contentValues.put(COL4,COL4_VALUE);
            }
            else if(NUMBER_OF_DATA_TO_BE_INSERTED==5) {
                contentValues.put(COL1,COL1_VALUE);
                contentValues.put(COL2,COL2_VALUE);
                contentValues.put(COL3,COL3_VALUE);
                contentValues.put(COL4,COL4_VALUE);
                contentValues.put(COL5,COL5_VALUE);
            }
            else if(NUMBER_OF_DATA_TO_BE_INSERTED==6) {
                contentValues.put(COL1,COL1_VALUE);
                contentValues.put(COL2,COL2_VALUE);
                contentValues.put(COL3,COL3_VALUE);
                contentValues.put(COL4,COL4_VALUE);
                contentValues.put(COL5,COL5_VALUE);
                contentValues.put(COL6,COL6_VALUE);
            }
            else if(NUMBER_OF_DATA_TO_BE_INSERTED==7) {
                contentValues.put(COL1,COL1_VALUE);
                contentValues.put(COL2,COL2_VALUE);
                contentValues.put(COL3,COL3_VALUE);
                contentValues.put(COL4,COL4_VALUE);
                contentValues.put(COL5,COL5_VALUE);
                contentValues.put(COL6,COL6_VALUE);
                contentValues.put(COL7,COL7_VALUE);
            }
            else if(NUMBER_OF_DATA_TO_BE_INSERTED==8) {
                contentValues.put(COL1,COL1_VALUE);
                contentValues.put(COL2,COL2_VALUE);
                contentValues.put(COL3,COL3_VALUE);
                contentValues.put(COL4,COL4_VALUE);
                contentValues.put(COL5,COL5_VALUE);
                contentValues.put(COL6,COL6_VALUE);
                contentValues.put(COL7,COL7_VALUE);
                contentValues.put(COL8,COL8_VALUE);
            }
            else if(NUMBER_OF_DATA_TO_BE_INSERTED==9) {
                contentValues.put(COL1,COL1_VALUE);
                contentValues.put(COL2,COL2_VALUE);
                contentValues.put(COL3,COL3_VALUE);
                contentValues.put(COL4,COL4_VALUE);
                contentValues.put(COL5,COL5_VALUE);
                contentValues.put(COL6,COL6_VALUE);
                contentValues.put(COL7,COL7_VALUE);
                contentValues.put(COL8,COL8_VALUE);
                contentValues.put(COL9,COL9_VALUE);
            }
            else if(NUMBER_OF_DATA_TO_BE_INSERTED==10) {
                contentValues.put(COL1,COL1_VALUE);
                contentValues.put(COL2,COL2_VALUE);
                contentValues.put(COL3,COL3_VALUE);
                contentValues.put(COL4,COL4_VALUE);
                contentValues.put(COL5,COL5_VALUE);
                contentValues.put(COL6,COL6_VALUE);
                contentValues.put(COL7,COL7_VALUE);
                contentValues.put(COL8,COL8_VALUE);
                contentValues.put(COL9,COL9_VALUE);
                contentValues.put(COL10,COL10_VALUE);
            }

            long inserted = database.insert(TABLE_NAME, null, contentValues);
            this.close();

            if(inserted > 0)
                return true;
             else
                return false;

        }

    private boolean DELETE(String TABLE_NAME, String COL_ID_TO_BE_DELETE,int COL_ID_VALUE_TO_BE_DELETE) {
        this.open();
        int deleted = database.delete(TABLE_NAME, COL_ID_TO_BE_DELETE + "= " + COL_ID_VALUE_TO_BE_DELETE, null);
        this.close();
        if (deleted > 0)
            return true;
        else return false;
    }

    private ArrayList<Integer> SEARCH(int numberOfItemToBeSearch,String TABLE_NAME, String COL_NAME_TO_BE_SEARCH_ITEM1, String COL_VALUE_TO_BE_SEARCH_ITEM1,String COL_NAME_TO_BE_SEARCH_ITEM2, String COL_VALUE_TO_BE_SEARCH_ITEM2,String COL_NAME_TO_BE_SEARCH_ITEM3, String COL_VALUE_TO_BE_SEARCH_ITEM3,String COL_NAME_TO_BE_SEARCH_ITEM4, String COL_VALUE_TO_BE_SEARCH_ITEM4) {
        this.open();
        ArrayList<Integer> searchResultArrayList=new ArrayList<>();
        Cursor cursor;
        if(numberOfItemToBeSearch==1)
            cursor = database.query(TABLE_NAME + " where " + COL_NAME_TO_BE_SEARCH_ITEM1 + " = '" + COL_VALUE_TO_BE_SEARCH_ITEM1 + "';", null, null, null, null, null, null);
        else if(numberOfItemToBeSearch==2)
            cursor = database.query(TABLE_NAME + " where " + COL_NAME_TO_BE_SEARCH_ITEM1 + " = '" + COL_VALUE_TO_BE_SEARCH_ITEM1 +"' and " + COL_NAME_TO_BE_SEARCH_ITEM2 + " = '" + COL_VALUE_TO_BE_SEARCH_ITEM2 + "';", null, null, null, null, null, null);
        else if(numberOfItemToBeSearch==3)
            cursor = database.query(TABLE_NAME + " where " + COL_NAME_TO_BE_SEARCH_ITEM1 + " = '" + COL_VALUE_TO_BE_SEARCH_ITEM1 +"' and " + COL_NAME_TO_BE_SEARCH_ITEM2 + " = '" + COL_VALUE_TO_BE_SEARCH_ITEM2 + "' and " + COL_NAME_TO_BE_SEARCH_ITEM3 + " = '" + COL_VALUE_TO_BE_SEARCH_ITEM3 + "';", null, null, null, null, null, null);
        else
            cursor = database.query(TABLE_NAME + " where " + COL_NAME_TO_BE_SEARCH_ITEM1 + " = '" + COL_VALUE_TO_BE_SEARCH_ITEM1 +"' and " + COL_NAME_TO_BE_SEARCH_ITEM2 + " = '" + COL_VALUE_TO_BE_SEARCH_ITEM2 + "' and " + COL_NAME_TO_BE_SEARCH_ITEM3 + " = '" + COL_VALUE_TO_BE_SEARCH_ITEM3 + "' and " + COL_NAME_TO_BE_SEARCH_ITEM4 + " = '" + COL_VALUE_TO_BE_SEARCH_ITEM4+ "';", null, null, null, null, null, null);


        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                int resultId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID));
                searchResultArrayList.add(resultId);
                cursor.moveToNext();
            }
        }
        this.close();
        return searchResultArrayList;
    }

    private boolean UPDATE(int NUMBER_OF_DATA_TO_BE_UPDATED, String TABLE_NAME, String COL_ID_TO_BE_UPDATED, String COL_ID_VALUE_TO_BE_UPDATED, String COL1, String COL2, String COL3, String COL4, String COL5, String COL6, String COL7, String COL8, String COL9, String COL10, String COL1_VALUE, String COL2_VALUE, String COL3_VALUE, String COL4_VALUE, String COL5_VALUE, String COL6_VALUE, String COL7_VALUE, String COL8_VALUE, String COL9_VALUE, String COL10_VALUE ) {
        this.open();
        ContentValues contentValues = new ContentValues();
        if(NUMBER_OF_DATA_TO_BE_UPDATED==1) {
            contentValues.put(COL1, COL1_VALUE);
        }
        else if(NUMBER_OF_DATA_TO_BE_UPDATED==2) {
            contentValues.put(COL1,COL1_VALUE);
            contentValues.put(COL2,COL2_VALUE);

        }
        else if(NUMBER_OF_DATA_TO_BE_UPDATED==3) {
            contentValues.put(COL1,COL1_VALUE);
            contentValues.put(COL2,COL2_VALUE);
            contentValues.put(COL3,COL3_VALUE);
        }
        else if(NUMBER_OF_DATA_TO_BE_UPDATED==4) {
            contentValues.put(COL1,COL1_VALUE);
            contentValues.put(COL2,COL2_VALUE);
            contentValues.put(COL3,COL3_VALUE);
            contentValues.put(COL4,COL4_VALUE);
        }
        else if(NUMBER_OF_DATA_TO_BE_UPDATED==5) {
            contentValues.put(COL1,COL1_VALUE);
            contentValues.put(COL2,COL2_VALUE);
            contentValues.put(COL3,COL3_VALUE);
            contentValues.put(COL4,COL4_VALUE);
            contentValues.put(COL5,COL5_VALUE);
        }
        else if(NUMBER_OF_DATA_TO_BE_UPDATED==6) {
            contentValues.put(COL1,COL1_VALUE);
            contentValues.put(COL2,COL2_VALUE);
            contentValues.put(COL3,COL3_VALUE);
            contentValues.put(COL4,COL4_VALUE);
            contentValues.put(COL5,COL5_VALUE);
            contentValues.put(COL6,COL6_VALUE);
        }
        else if(NUMBER_OF_DATA_TO_BE_UPDATED==7) {
            contentValues.put(COL1,COL1_VALUE);
            contentValues.put(COL2,COL2_VALUE);
            contentValues.put(COL3,COL3_VALUE);
            contentValues.put(COL4,COL4_VALUE);
            contentValues.put(COL5,COL5_VALUE);
            contentValues.put(COL6,COL6_VALUE);
            contentValues.put(COL7,COL7_VALUE);
        }
        else if(NUMBER_OF_DATA_TO_BE_UPDATED==8) {
            contentValues.put(COL1,COL1_VALUE);
            contentValues.put(COL2,COL2_VALUE);
            contentValues.put(COL3,COL3_VALUE);
            contentValues.put(COL4,COL4_VALUE);
            contentValues.put(COL5,COL5_VALUE);
            contentValues.put(COL6,COL6_VALUE);
            contentValues.put(COL7,COL7_VALUE);
            contentValues.put(COL8,COL8_VALUE);
        }
        else if(NUMBER_OF_DATA_TO_BE_UPDATED==9) {
            contentValues.put(COL1,COL1_VALUE);
            contentValues.put(COL2,COL2_VALUE);
            contentValues.put(COL3,COL3_VALUE);
            contentValues.put(COL4,COL4_VALUE);
            contentValues.put(COL5,COL5_VALUE);
            contentValues.put(COL6,COL6_VALUE);
            contentValues.put(COL7,COL7_VALUE);
            contentValues.put(COL8,COL8_VALUE);
            contentValues.put(COL9,COL9_VALUE);
        }
        else if(NUMBER_OF_DATA_TO_BE_UPDATED==10) {
            contentValues.put(COL1,COL1_VALUE);
            contentValues.put(COL2,COL2_VALUE);
            contentValues.put(COL3,COL3_VALUE);
            contentValues.put(COL4,COL4_VALUE);
            contentValues.put(COL5,COL5_VALUE);
            contentValues.put(COL6,COL6_VALUE);
            contentValues.put(COL7,COL7_VALUE);
            contentValues.put(COL8,COL8_VALUE);
            contentValues.put(COL9,COL9_VALUE);
            contentValues.put(COL10,COL10_VALUE);
        }


        int updated = database.update(TABLE_NAME, contentValues, COL_ID_TO_BE_UPDATED + " = " + COL_ID_VALUE_TO_BE_UPDATED, null);
        this.close();
        if (updated > 0) {
            return true;
        } else return false;
    }

    public boolean addSingleProfile(SingleProfileInfo singleProfileInfo){

        boolean inserted=INSERT(8, DatabaseHelper.PROFILE_TABLE_NAME, DatabaseHelper.PROFILE_COL_PINCODE, DatabaseHelper.PROFILE_COL_NAME, DatabaseHelper.PROFILE_COL_DATEOFBIRTH, DatabaseHelper.PROFILE_COL_GENDER, DatabaseHelper.PROFILE_COL_BLOODGROUP, DatabaseHelper.PROFILE_COL_RELATION, DatabaseHelper.PROFILE_COL_HIEGTH, DatabaseHelper.PROFILE_COL_WEIGHT, null, null,
                singleProfileInfo.getProfilePincode(), singleProfileInfo.getProfileName(), singleProfileInfo.getProfileBirthdy(), singleProfileInfo.getProfileGender(), singleProfileInfo.getProfileBloodgroup(), singleProfileInfo.getProfileRelation(), singleProfileInfo.getProfileHieght(), singleProfileInfo.getProfileWeight(), null, null);
        return inserted;
    }

    public boolean updateSingleProfile(SingleProfileInfo singleProfileInfo){

        boolean inserted=UPDATE(8, DatabaseHelper.PROFILE_TABLE_NAME, DatabaseHelper.PROFILE_COL_ID, String.valueOf(singleProfileInfo.getProfileID()), DatabaseHelper.PROFILE_COL_PINCODE, DatabaseHelper.PROFILE_COL_NAME, DatabaseHelper.PROFILE_COL_DATEOFBIRTH, DatabaseHelper.PROFILE_COL_GENDER, DatabaseHelper.PROFILE_COL_BLOODGROUP, DatabaseHelper.PROFILE_COL_RELATION, DatabaseHelper.PROFILE_COL_HIEGTH, DatabaseHelper.PROFILE_COL_WEIGHT, null, null,
                singleProfileInfo.getProfilePincode(), singleProfileInfo.getProfileName(), singleProfileInfo.getProfileBirthdy(), singleProfileInfo.getProfileGender(), singleProfileInfo.getProfileBloodgroup(), singleProfileInfo.getProfileRelation(), singleProfileInfo.getProfileHieght(), singleProfileInfo.getProfileWeight(), null, null);
        return inserted;
    }

    public SingleProfileInfo getSingleProfile(int profileID){
        this.open();
        Cursor cursor = database.query(DatabaseHelper.PROFILE_TABLE_NAME + " where " + DatabaseHelper.PROFILE_COL_ID + " = '" + profileID + "';", null, null, null, null, null, null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            String profilePincode = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PROFILE_COL_PINCODE));
            String profileName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PROFILE_COL_NAME));
            String profileBirthdy = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PROFILE_COL_DATEOFBIRTH));
            String profileBloodgroup = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PROFILE_COL_BLOODGROUP));
            String profileGender = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PROFILE_COL_GENDER));
            String profileRelation = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PROFILE_COL_RELATION));
            String profileHieght = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PROFILE_COL_HIEGTH));
            String profileWeight = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PROFILE_COL_WEIGHT));
            SingleProfileInfo singleProfileInfo = new SingleProfileInfo(profileID,profilePincode,profileName,profileBirthdy,profileGender,profileBloodgroup,profileRelation,profileHieght,profileWeight);
            this.close();
            return singleProfileInfo;
        }
        else
        {
            SingleProfileInfo singleProfileInfo = new SingleProfileInfo(-1,null,null,null,null,null,null,null,null);
            this.close();
            return singleProfileInfo;
        }

    }

    public boolean deleteSingleProfile(int profileId){
        return DELETE(DatabaseHelper.PROFILE_TABLE_NAME,DatabaseHelper.PROFILE_COL_ID,profileId);
    }

    public ArrayList<SingleProfileInfo> getAllProfileList(){
        this.open();
        ArrayList<SingleProfileInfo> singleProfileInfoArrayList = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.PROFILE_TABLE_NAME  , null, null, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {
                int profileID =cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PROFILE_COL_ID));
                String profilePincode = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PROFILE_COL_PINCODE));
                String profileName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PROFILE_COL_NAME));
                String profileBirthdy = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PROFILE_COL_DATEOFBIRTH));
                String profileBloodgroup = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PROFILE_COL_BLOODGROUP));
                String profileGender = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PROFILE_COL_GENDER));
                String profileRelation = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PROFILE_COL_RELATION));
                String profileHieght = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PROFILE_COL_HIEGTH));
                String profileWeight = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PROFILE_COL_WEIGHT));
                SingleProfileInfo singleProfileInfo = new SingleProfileInfo(profileID,profilePincode,profileName,profileBirthdy,profileGender,profileBloodgroup,profileRelation,profileHieght,profileWeight);
                singleProfileInfoArrayList.add(singleProfileInfo);
                cursor.moveToNext();
                }

        }
        this.close();
        return singleProfileInfoArrayList;
    }

    public int getprofileId(String picCode){
        ArrayList<Integer> profileIdList=SEARCH(1,DatabaseHelper.PROFILE_TABLE_NAME,DatabaseHelper.PROFILE_COL_PINCODE,picCode,null,null,null,null,null,null);
        if(profileIdList.size()>0)
            return profileIdList.get(0);
        else
            return -1;
    }

    public boolean addDiet(Diet diet){
        boolean inserted=INSERT(7, DatabaseHelper.DIET_TABLE_NAME, DatabaseHelper.DIET_COL_MEMBER_ID, DatabaseHelper.DIET_COL_TYPE, DatabaseHelper.DIET_COL_MENU, DatabaseHelper.DIET_COL_DATE, DatabaseHelper.DIET_COL_TIME, DatabaseHelper.DIET_COL_ALARM, DatabaseHelper.DIET_COL_REMAINDER, null, null, null,
                diet.getMemberId(), diet.getType(), diet.getMenu(), diet.getDate(), diet.getTime(), diet.getAlarm(), diet.getRemainder(), null, null, null);
        return inserted;
    }

    public boolean updateDiet(Diet diet){

        boolean updated=UPDATE(7, DatabaseHelper.DIET_TABLE_NAME, DatabaseHelper.COL_ID, String.valueOf(diet.getDietId()), DatabaseHelper.DIET_COL_MEMBER_ID, DatabaseHelper.DIET_COL_TYPE, DatabaseHelper.DIET_COL_MENU, DatabaseHelper.DIET_COL_DATE, DatabaseHelper.DIET_COL_TIME, DatabaseHelper.DIET_COL_ALARM, DatabaseHelper.DIET_COL_REMAINDER, null, null, null,
                diet.getMemberId(), diet.getType(), diet.getMenu(), diet.getDate(), diet.getTime(), diet.getAlarm(), diet.getRemainder(), null, null, null);
        return updated;
    }

    public boolean deleteDiet(int dietId){
        return DELETE(DatabaseHelper.DIET_TABLE_NAME,DatabaseHelper.DIET_COL_ID,dietId);
    }

    public ArrayList<Diet> getDietList(){
        this.open();
        ArrayList<Diet> dietList = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.DIET_TABLE_NAME  , null, null, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {
                int dietID =cursor.getInt(cursor.getColumnIndex(DatabaseHelper.DIET_COL_ID));
                String memberId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DIET_COL_MEMBER_ID));
                String type = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DIET_COL_TYPE));
                String menu = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DIET_COL_MENU));
                String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DIET_COL_DATE));
                String time = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DIET_COL_TIME));
                String alarm = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DIET_COL_ALARM));
                String remainder = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DIET_COL_REMAINDER));

                Diet diet = new Diet(dietID,memberId,type,menu,date,time,alarm,remainder);
                dietList.add(diet);
                cursor.moveToNext();
            }

        }
        this.close();
        return dietList;
    }

    public ArrayList<Vaccine> getVaccineList() {
        this.open();
        ArrayList<Vaccine> vaccineList = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.VACCINE_TABLE_NAME  , null, null, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {
                int vaccineID =cursor.getInt(cursor.getColumnIndex(DatabaseHelper.VACCINE_COL_ID));
                String memberId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.VACCINE_COL_MEMBER_ID));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.VACCINE_COL_NAME));
                String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.VACCINE_COL_DATE));
                String time = cursor.getString(cursor.getColumnIndex(DatabaseHelper.VACCINE_COL_TIME));
                String details = cursor.getString(cursor.getColumnIndex(DatabaseHelper.VACCINE_COL_DETAILS));
                String alarm = cursor.getString(cursor.getColumnIndex(DatabaseHelper.VACCINE_COL_ALARM));
                String remainder = cursor.getString(cursor.getColumnIndex(DatabaseHelper.VACCINE_COL_REMAINDER));

                Vaccine vaccine = new Vaccine(vaccineID,memberId,name,date,time,details,alarm,remainder);
                vaccineList.add(vaccine);
                cursor.moveToNext();
            }

        }
        this.close();
        return vaccineList;
    }

    public boolean addVaccine(Vaccine vaccine) {
        boolean inserted=INSERT(7, DatabaseHelper.VACCINE_TABLE_NAME, DatabaseHelper.VACCINE_COL_MEMBER_ID, DatabaseHelper.VACCINE_COL_NAME, DatabaseHelper.VACCINE_COL_DATE, DatabaseHelper.VACCINE_COL_TIME, DatabaseHelper.VACCINE_COL_DETAILS, DatabaseHelper.VACCINE_COL_ALARM, DatabaseHelper.VACCINE_COL_REMAINDER, null, null, null,
                vaccine.getMemberId(), vaccine.getName(),  vaccine.getDate(), vaccine.getTime(),vaccine.getDetails(), vaccine.getAlarm(), vaccine.getRemainder(), null, null, null);
        return inserted;

    }

    public boolean updateVaccine(Vaccine vaccine) {
        boolean updated=UPDATE(7, DatabaseHelper.VACCINE_TABLE_NAME, DatabaseHelper.COL_ID, String.valueOf(vaccine.getVaccineId()), DatabaseHelper.VACCINE_COL_MEMBER_ID, DatabaseHelper.VACCINE_COL_NAME, DatabaseHelper.VACCINE_COL_DATE, DatabaseHelper.VACCINE_COL_TIME, DatabaseHelper.VACCINE_COL_DETAILS, DatabaseHelper.VACCINE_COL_ALARM, DatabaseHelper.VACCINE_COL_REMAINDER, null, null, null,
                vaccine.getMemberId(), vaccine.getName(), vaccine.getDate(), vaccine.getTime(), vaccine.getDetails(), vaccine.getAlarm(), vaccine.getRemainder(), null, null, null);
        return updated;

    }

    public boolean deleteVaccine(int vaccineId){
        return DELETE(DatabaseHelper.VACCINE_TABLE_NAME,DatabaseHelper.VACCINE_COL_ID,vaccineId);
    }

    public boolean addDoctor(Doctor doctor){

        boolean inserted=INSERT(6, DatabaseHelper.DOCTOR_TABLE_NAME,DatabaseHelper.DOCTOR_COL_MEMBER_ID, DatabaseHelper.DOCTOR_COL_NAME, DatabaseHelper.DOCTOR_COL_DEPARTMENT, DatabaseHelper.DOCTOR_COL_NUMBER, DatabaseHelper.DOCTOR_COL_EMAIL, DatabaseHelper.DOCTOR_COL_ADRESS,null, null, null,null,
                 doctor.getMemberID(), doctor.getDoctorName(), doctor.getDoctorDepartment(), doctor.getDoctorNumber(), doctor.getDoctorEmail(), doctor.getDoctorAdress(), null,null, null, null );
        return inserted;
    }

    public boolean updateDoctor(Doctor doctor){

        boolean inserted=UPDATE(6, DatabaseHelper.DOCTOR_TABLE_NAME, DatabaseHelper.DOCTOR_COL_ID, String.valueOf(doctor.getDoctorID()), DatabaseHelper.DOCTOR_COL_MEMBER_ID, DatabaseHelper.DOCTOR_COL_NAME, DatabaseHelper.DOCTOR_COL_DEPARTMENT, DatabaseHelper.DOCTOR_COL_NUMBER, DatabaseHelper.DOCTOR_COL_EMAIL, DatabaseHelper.DOCTOR_COL_ADRESS, null, null, null, null,
                doctor.getMemberID(), doctor.getDoctorName(), doctor.getDoctorDepartment(), doctor.getDoctorNumber(), doctor.getDoctorEmail(), doctor.getDoctorAdress(), null, null, null, null);
        return inserted;
    }

    public boolean deleteDoctor(int doctorId){
        return DELETE(DatabaseHelper.DOCTOR_TABLE_NAME,DatabaseHelper.DIET_COL_ID,doctorId);
    }

    public ArrayList<Doctor> getDoctorList(){
        this.open();
        ArrayList<Doctor> doctorList = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.DOCTOR_TABLE_NAME  , null, null, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {
                int doctorID =cursor.getInt(cursor.getColumnIndex(DatabaseHelper.DOCTOR_COL_ID));
                String memberId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DOCTOR_COL_MEMBER_ID));
                String doctorName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DOCTOR_COL_NAME));
                String doctorDepartment = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DOCTOR_COL_DEPARTMENT));
                String doctorNumber = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DOCTOR_COL_NUMBER));
                String doctorEmail = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DOCTOR_COL_EMAIL));
                String doctorAdress = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DOCTOR_COL_ADRESS));
                Doctor doctor = new Doctor(doctorID,memberId,doctorName,doctorDepartment,doctorNumber,doctorEmail,doctorAdress);
                doctorList.add(doctor);
                cursor.moveToNext();
            }
        }
        this.close();
        return doctorList;
    }



    public boolean addHospital(Hospital doctor){

        boolean inserted=INSERT(6, DatabaseHelper.HOSPITAL_TABLE_NAME,DatabaseHelper.HOSPITAL_COL_MEMBER_ID, DatabaseHelper.HOSPITAL_COL_NAME, DatabaseHelper.HOSPITAL_COL_STATE, DatabaseHelper.HOSPITAL_COL_NUMBER, DatabaseHelper.HOSPITAL_COL_EMAIL, DatabaseHelper.HOSPITAL_COL_ADRESS,null, null, null,null,
                doctor.getMemberID(), doctor.getHospitalName(), doctor.getHospitalState(), doctor.getHospitalNumber(), doctor.getHospitalEmail(), doctor.getHospitalAdress(), null,null, null, null );
        return inserted;
    }

    public boolean updateHospital(Hospital doctor){

        boolean inserted=UPDATE(6, DatabaseHelper.HOSPITAL_TABLE_NAME, DatabaseHelper.HOSPITAL_COL_ID, String.valueOf(doctor.getHospitalID()), DatabaseHelper.HOSPITAL_COL_MEMBER_ID, DatabaseHelper.HOSPITAL_COL_NAME, DatabaseHelper.HOSPITAL_COL_STATE, DatabaseHelper.HOSPITAL_COL_NUMBER, DatabaseHelper.HOSPITAL_COL_EMAIL, DatabaseHelper.HOSPITAL_COL_ADRESS, null, null, null, null,
                doctor.getMemberID(), doctor.getHospitalName(), doctor.getHospitalState(), doctor.getHospitalNumber(), doctor.getHospitalEmail(), doctor.getHospitalAdress(), null, null, null, null);
        return inserted;
    }

    public boolean deleteHospital(int hospitalId){
        return DELETE(DatabaseHelper.HOSPITAL_TABLE_NAME,DatabaseHelper.HOSPITAL_COL_ID,hospitalId);
    }


    public ArrayList<Hospital> getHospitalList(){
        this.open();
        ArrayList<Hospital> hospitalList = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.HOSPITAL_TABLE_NAME  , null, null, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {
                int hospitalID =cursor.getInt(cursor.getColumnIndex(DatabaseHelper.HOSPITAL_COL_ID));
                String memberId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.HOSPITAL_COL_MEMBER_ID));
                String hospitalName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.HOSPITAL_COL_NAME));
                String hospitalHospital = cursor.getString(cursor.getColumnIndex(DatabaseHelper.HOSPITAL_COL_STATE));
                String hospitalNumber = cursor.getString(cursor.getColumnIndex(DatabaseHelper.HOSPITAL_COL_NUMBER));
                String hospitalEmail = cursor.getString(cursor.getColumnIndex(DatabaseHelper.HOSPITAL_COL_EMAIL));
                String hospitalAdress = cursor.getString(cursor.getColumnIndex(DatabaseHelper.HOSPITAL_COL_ADRESS));
                Hospital hospital = new Hospital(hospitalID,memberId,hospitalName,hospitalHospital,hospitalNumber,hospitalEmail,hospitalAdress);
                hospitalList.add(hospital);
                cursor.moveToNext();
            }
        }
        this.close();
        return hospitalList;
    }



}
