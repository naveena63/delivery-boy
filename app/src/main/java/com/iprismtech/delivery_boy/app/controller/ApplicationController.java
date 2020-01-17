package com.iprismtech.delivery_boy.app.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.iprismtech.delivery_boy.app.application.MyApplication;
import com.iprismtech.delivery_boy.app.constants.AppConstants;
import com.iprismtech.delivery_boy.app.factories.ViewFactory;

import static com.iprismtech.delivery_boy.app.constants.AppConstants.EventIds.LAUNCH_ADMINSUPPORT_SCREEN;
import static com.iprismtech.delivery_boy.app.constants.AppConstants.EventIds.LAUNCH_APPINFO_SCREEN;
import static com.iprismtech.delivery_boy.app.constants.AppConstants.EventIds.LAUNCH_AUCNTSETTING_SCREEN;
import static com.iprismtech.delivery_boy.app.constants.AppConstants.EventIds.LAUNCH_COMPLETEDORDER_SCREEN;
import static com.iprismtech.delivery_boy.app.constants.AppConstants.EventIds.LAUNCH_EMPLOYID_SCREEN;
import static com.iprismtech.delivery_boy.app.constants.AppConstants.EventIds.LAUNCH_ITEMSDELIVERY_SCREEN;
import static com.iprismtech.delivery_boy.app.constants.AppConstants.EventIds.LAUNCH_ITEMSDROPED_SCREEN;
import static com.iprismtech.delivery_boy.app.constants.AppConstants.EventIds.LAUNCH_NOTIFICATIONS_SCREEN;
import static com.iprismtech.delivery_boy.app.constants.AppConstants.EventIds.LAUNCH_ORDERCONFORMED_SCREEN;
import static com.iprismtech.delivery_boy.app.constants.AppConstants.EventIds.LAUNCH_PICKEDITEMS_SCREEN;
import static com.iprismtech.delivery_boy.app.constants.AppConstants.EventIds.LAUNCH_SPLASH_SCREEN;


/**
 * Created by prasad on 05-07-17.
 * ApplicationController.java
 * <p/>
 * The ApplicationController Class, which helps to manage different Controllers,
 * Models, Views. This Class will be initialize as the platform requirement.
 */

public class ApplicationController {

    /**
     * private instance of ApplicationController for singleton Design Pattern
     */
    private static ApplicationController instance;

    /**
     * private instance of UIController for managing different AbstractViews
     */
    public UiController uiController;

    /**
     * private instance of ViewFactory for fast accessing.
     */
    public ViewFactory viewFactory;

    public Activity mActivity;
    public Context mContext;

    private MyApplication application;

    /**
     * Constructor of ApplicationController
     */
    private ApplicationController() {
        uiController = UiController.getInstance();
    }

    /**
     * Get Single instance of ApplicationController
     *
     * @return ApplicationController single instance
     */
    public static ApplicationController getInstance() {
        if (instance == null) {
            // creating new instance of application controller
            instance = new ApplicationController();
        }
        return instance;
    }
//
//    /**
//     * This Function will get called from LoginActivity or Any Activity which is
//     * going to display first screen after launching this application
//     */
//    public void initialize() {
//
//        // initialize the ModelFacade
//        modelFacade.initialize();
//
//        // set the reference for UI Controller
//        uiController = UIController.getInstance();
//
//        // initialize the UIController
//        uiController.initialize();
//
//        // set the viewFactory reference for further use in code.
//        viewFactory = ViewFactory.getInstance();
//
//    }

    /**
     * returns the current mActivity
     *
     * @return
     */
    public Activity getActivity() {
        return mActivity;
    }


    public void setActivity(@NonNull Activity mActivity) {
        this.mActivity = mActivity;
    }


    public MyApplication getApplication() {
        return application;
    }


    public void setApplication(MyApplication application) {
        this.application = application;
    }


    public Context getContext() {
        return mContext;
    }


    public void setContext(@NonNull Context mContext) {
        this.mContext = mContext;
    }


    public void handleEvent(@AppConstants.EventIds int eventId) {
        handleEvent(eventId, null);
    }


    /**
     * Handle Event Based on Event ID and Object
     *
     * @param eventId      Event Id based on user actions
     * @param eventObjects It stores the data for the given Event. so it can forward to
     *                     other events
     */
    @SuppressLint("WrongConstant")
    public void handleEvent(@AppConstants.EventIds int eventId, Object eventObjects) {
        Log.d(AppConstants.LOG_CAT, "handleEvent : " + eventId);

        switch (eventId) {

            case AppConstants.EventIds.LAUNCH_LOGIN_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.LOGIN_SCREEN);
                break;

            case AppConstants.EventIds.LAUNCH_FRSTHOME_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.LAUNCH_FRSTHOMESCREEN,(Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_WELCOME_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.WELCOME_SCREEN);
                break;

            case AppConstants.EventIds.LAUNCH_COLLECTED_ITEMS_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.COLLECTED_ITEMS_SCREEN, (Bundle) eventObjects);
                break;


            case AppConstants.EventIds.LAUNCH_AUCNTSETTING_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.ACUNTSETING_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_ADMINSUPPORT_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.ADMINSUPORT_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_APPINFO_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.APPINFO_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_COMPLETEDORDER_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.COMPLETEDORDERS_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_EMPLOYID_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.EMPLOID_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_ITEMSDELIVERY_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.ITEMSDELIVERY_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_ITEMSDROPED_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.ITEMS_DROPED_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_NOTIFICATIONS_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.NOTIFICATIONS_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_ORDERCONFORMED_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.ORDERCONFORMED_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_PICKEDITEMS_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.PICKEDITEMS_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_SPLASH_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.SPLASH_SCREEN);
                break;
            default:
                throw new IllegalStateException("Invalid Event id");
        }
    }
}
