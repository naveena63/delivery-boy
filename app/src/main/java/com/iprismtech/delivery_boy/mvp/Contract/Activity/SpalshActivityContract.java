package com.iprismtech.delivery_boy.mvp.Contract.Activity;

import android.content.Context;

import com.iprismtech.delivery_boy.mvp.base.IBaseContract;
import com.iprismtech.delivery_boy.mvp.base.IBaseDataManager;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;

import java.util.Map;

public interface SpalshActivityContract {


    /**
     * Declared the Event's Id which is be handle from Login Screen.
     */
    interface IPermissionIds extends IBaseContract {
        //todo add permissions

        int FINISH_FLASH_SCREEN = 13;
    }


    interface IView {


    }

    /**
     * Declared the methods here which can be used in Login Screen.
     */
    interface IPresenter {

        void finishSplashScreen(Object data);

        void launchRespectiveScreen(Object data);

        void getAthenticationToken(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody);

        void checkUserActiveOrNot(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody);
    }

    /**
     * Declared the methods here which can be used in Data manager of Login Screen.
     */
    interface IDataManager extends IBaseDataManager {


    }
}
