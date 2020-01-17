package com.iprismtech.delivery_boy.service;

import android.annotation.SuppressLint;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.iprismtech.delivery_boy.app.controller.ApplicationController;
import com.iprismtech.delivery_boy.sharepref.UserSession;


/**
 * Created by USER on 9/23/2016.
 */

public class FCMInstanceIDService extends FirebaseInstanceIdService {

    private UserSession userSession;

    @SuppressLint("CommitPrefEdits")
    @Override
    public void onTokenRefresh() {
        try {
            String deviceToken = FirebaseInstanceId.getInstance().getToken();
            userSession = new UserSession(ApplicationController.getInstance().getContext());
            if (deviceToken != null && deviceToken.length() > 0) {
                userSession.setDeviceToken(deviceToken);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // UiAppConstant.isDevieTokenUpdate = "yes";
    }
}