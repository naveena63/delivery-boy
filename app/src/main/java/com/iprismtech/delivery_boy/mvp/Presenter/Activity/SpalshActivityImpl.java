package com.iprismtech.delivery_boy.mvp.Presenter.Activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;

import com.iprismtech.delivery_boy.app.constants.AppConstants;
import com.iprismtech.delivery_boy.app.controller.ApplicationController;
import com.iprismtech.delivery_boy.mvp.Contract.Activity.SpalshActivityContract;
import com.iprismtech.delivery_boy.mvp.base.BasePresenter;
import com.iprismtech.delivery_boy.mvp.base.IBaseContract;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;
import com.iprismtech.delivery_boy.sharepref.UserSession;
import com.iprismtech.delivery_boy.ui.Activity.SplashActivity;

import java.util.Map;

public class SpalshActivityImpl extends BasePresenter implements SpalshActivityContract.IPresenter {

    private final int SPLASH_INTERVAL = 100;
    private SpalshActivityContract.IView splashScreenView;
    UserSession userSession;

    public SpalshActivityImpl(SpalshActivityContract.IView splashScreenView, Context context) {
        super(splashScreenView, context);
        this.context = context;
        userSession = new UserSession(context);
        this.splashScreenView = splashScreenView;

    }



    public void finishSplashScreen(final Object data) {
        try {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    launchRespectiveScreen(data);


                    //  ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_LOGIN_SCREEN);


                }
            }, SPLASH_INTERVAL);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Checking for user is login or not
    public void launchRespectiveScreen(Object data) {
        try {
            if (userSession.isUserLoggedIn()) {
                //For conflication we are using dummy splash
                Bundle b = new Bundle();
                b.putString("comingfrm","splash");
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_FRSTHOME_SCREEN,b);
                SplashActivity.splashActivity.finish();
            } else {
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_WELCOME_SCREEN);
                SplashActivity.splashActivity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void getAthenticationToken(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }

    @Override
    public void checkUserActiveOrNot(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }

    public void launchSplashScreen(@IBaseContract.PermissionIds int permissionId, @Nullable Object data) {
        switch (permissionId) {
            case SpalshActivityContract.IPermissionIds.FINISH_FLASH_SCREEN:
                try {
                    Log.d(AppConstants.LOG_CAT, ">> FINISH_SPLASH_SCREEN");
                    finishSplashScreen(data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
