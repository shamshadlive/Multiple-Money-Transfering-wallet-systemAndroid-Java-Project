package com.shamshadlive.parentapplicationv4.utils;

import android.content.Context;
import android.content.SharedPreferences;



public class SharedPrefHelper {

    private static SharedPrefHelper instance;
    private static String SHARED_PREF_NAME = "parentapp#456";
    private static final String SP_LOGIN_USERNAME = "username";
    private static final String SP_LOGIN_PASSWORD = "password";
    private static final String SP_LOGIN_UPI = "upikey";
    private static final String SP_FIRSTNAME = "firstname";
    private static final String SP_LOG_ID = "log_id";
    private static final String SP_CHILDECEIVER_UPI = "childupiId";
    private static final String SP_PARENTTABLE_ID = "parenttableid";


    private static final String SP_BAL= "bal";
    private static Context mContext;
    private static final String SP_USER_ID = "userID";

    private SharedPrefHelper(Context context) {
        this.mContext = context;
    }

    public static SharedPrefHelper getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefHelper(context);
        }
        return instance;
    }

    public void saveLogin(String username, String password) {
        try {

            SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(SP_LOGIN_USERNAME, username);
            editor.putString(SP_LOGIN_PASSWORD, password);
            editor.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void saveFirstname(String firstname) {
        try {

            SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(SP_FIRSTNAME, firstname);
            editor.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    public void saveupiId(String upi) {
        try {

            SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(SP_LOGIN_UPI, upi);
            editor.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getSavedupiId() {
        try {

            SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

            String upi = sharedPreferences.getString(SP_LOGIN_UPI, "");

            return upi;

        } catch (Exception e) {
            e.printStackTrace();
            return "null";
        }
    }









    public void savechildupiId(String childupi) {
        try {

            SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(SP_CHILDECEIVER_UPI, childupi);
            editor.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getsavechildupiId() {
        try {

            SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

            String childupi = sharedPreferences.getString(SP_CHILDECEIVER_UPI, "");

            return childupi;

        } catch (Exception e) {
            e.printStackTrace();
            return "null";
        }
    }








    public void saveLogId(String logid) {
        try {

            SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(SP_LOG_ID, logid);
            editor.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getsaveLogId() {
        try {

            SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

            String logid = sharedPreferences.getString(SP_LOG_ID, "");

            return logid;

        } catch (Exception e) {
            e.printStackTrace();
            return "null";
        }
    }



    public void saveparenttableId(String parenttableid) {
        try {

            SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(SP_PARENTTABLE_ID, parenttableid);
            editor.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getsaveparenttableId() {
        try {

            SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

            String parenttableid = sharedPreferences.getString(SP_PARENTTABLE_ID, "");

            return parenttableid;

        } catch (Exception e) {
            e.printStackTrace();
            return "null";
        }
    }









    public String getFirstname() {
        try {

            SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

            String firstname = sharedPreferences.getString(SP_FIRSTNAME, "");

            return firstname;

        } catch (Exception e) {
            e.printStackTrace();
            return "null";
        }
    }




    public void clearsharedPreference() {
        try {

            saveLogin("","");
            saveupiId("");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getSavedUsername() {
        try {

            SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

            String username = sharedPreferences.getString(SP_LOGIN_USERNAME, "");

            return username;

        } catch (Exception e) {
            e.printStackTrace();
            return "null";
        }
    }
    public String getSavedPassword() {
        try {

            SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

            String password = sharedPreferences.getString(SP_LOGIN_PASSWORD, "");

            return password;

        } catch (Exception e) {
            e.printStackTrace();
            return "null";
        }
    }

    public void saveala(String bal) {
        try {

            SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(SP_BAL, bal);
            editor.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getBal() {
        try {

            SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

            String bal = sharedPreferences.getString(SP_BAL, "0");


            return bal;

        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

}
