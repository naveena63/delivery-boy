package com.iprismtech.delivery_boy.sharepref;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.iprismtech.delivery_boy.ui.Activity.LoginActivity;

import java.util.HashMap;

/**
 * Created by iprismTech on 12/07/2017.
 */

public class UserSession {
    // Shared Preferences reference
    SharedPreferences pref;

    SharedPreferences.Editor editor;

    Context _context;

    int PRIVATE_MODE = 0;

    public static final String PREFER_NAME = "Reg";

    public static final String IS_USER_LOGIN = "IsUserLoggedIn";



    public static final String KEY_EMAIL = "email";


    public static final String KEY_PASSWORD = "password";

    public static final String KEY_STATUS = "status";

    public static final String LOGIN_ID = "id";


    public static final String NATIONALITY_ID = "nationality";


    public static final String KEY_NATIONALITY_ID = "nationality_id";

    public static final String KEY_DEVICE_TOKEN = "devicetoken";





    public static final String KEY_USERID = "id";

    public static final String KEY_NAME = "name";

    public static final String KEY_UserName = "username";

    public static final String TYPE = "type";

    public static final String KEY_MOBILENO = "mobileno";

    public static final String KEY_CITYNAME = "cityname";






    // Constructor
    public UserSession(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    //Create login session
    public void StoreUserDetails(String userid, String name, String username, String mobileno, String cityname, String type) {
        // Storing login value as TRUE

        if (userid.isEmpty()) {
            editor.putBoolean(IS_USER_LOGIN, false);
        }else {
            editor.putBoolean(IS_USER_LOGIN, true);

        }

        //editor.putBoolean(IS_USER_LOGIN, true);

        //    editor.putString(KEY_STATUS, status1);

        //  editor.putString(NATIONALITY_ID, nationality);

        //editor.putString(LOGIN_ID, id);

        // editor.putString(KEY_UserName, name);

        editor.putString(KEY_USERID, userid);
        // Storing name in preferences
        editor.putString(KEY_NAME, name);

        editor.putString(KEY_UserName, username);
        // Storing mobile in preferences
        editor.putString(KEY_MOBILENO, mobileno);
        // Storing email in preferences
        editor.putString(KEY_CITYNAME, cityname);
        //Storing the password in preferences.
        editor.putString(TYPE, type);


        // editor.putString(TYPE, type);

        editor.commit();
    }


    public boolean checkLogin() {
        // Check login status
        if (!this.isUserLoggedIn()) {

            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);

            // Closing all the Activities from stack
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);

            return true;
        }
        return false;
    }





    /**
     * Get stored session data
     */
    public HashMap<String, String> getUserDetails() {

        //Use hashmap to store user credentials
        HashMap<String, String> user = new HashMap<String, String>();

        user.put(KEY_USERID, pref.getString(KEY_USERID, null));

        user.put(KEY_UserName, pref.getString(KEY_UserName, null));

        user.put(KEY_MOBILENO, pref.getString(KEY_MOBILENO, null));

        user.put(TYPE, pref.getString(TYPE, null));

        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        user.put(KEY_CITYNAME, pref.getString(KEY_CITYNAME, null));




        user.put(LOGIN_ID, pref.getString(LOGIN_ID, null));

        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));

        user.put(KEY_STATUS, pref.getString(KEY_STATUS, null));


        //   user.put(KEY_NATIONALITY_ID, pref.getString(KEY_NATIONALITY_ID, null));







        return user;
    }

    /**
     * Clear session details
     */
    public void logoutUser() {

        // Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();

    }

    /**
     * When user is logout then change the login screen details as user logout.
     */
    public void changeUserLoginScreenStatus() {
        editor.putBoolean(IS_USER_LOGIN, false);

        editor.commit();
    }

    // set the status of the user for read and unread here.
    public void setCheckTutorialScreenStatus(String status) {

        editor.putString(KEY_STATUS, status);

        editor.commit();
    }

    public void setDeviceToken(String deviceToken) {

        editor.putString(KEY_DEVICE_TOKEN, deviceToken);

        editor.commit();

    }

    // Check for login
    public boolean isUserLoggedIn() {
        return pref.getBoolean(IS_USER_LOGIN, false);
    }
}
