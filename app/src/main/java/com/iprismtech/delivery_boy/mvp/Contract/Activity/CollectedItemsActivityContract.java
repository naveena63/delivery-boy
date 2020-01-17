package com.iprismtech.delivery_boy.mvp.Contract.Activity;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.iprismtech.delivery_boy.mvp.base.IBaseContract;
import com.iprismtech.delivery_boy.mvp.base.IBaseDataManager;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;

import java.util.Map;

public interface CollectedItemsActivityContract {

    interface IPermissionIds extends IBaseContract

    {
        //todo add permissions
        int FINISH_FLASH_SCREEN = 13;
        int LAUNCH_LOCATION_SCREEN=14;
    }

    /**
     * Declared the methods here which can be used in Login Screen.
     */
    interface IView {
        void replaceRespectiveFragment(Fragment fragment, String[] data, String tag);

    }

    /**
     * Declared the methods here which can be used in Login Screen.
     */
    interface IPresenter {

        void agent_get_pickup_details(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody);

        void check_single_items(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody);

        void items_picked(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody);


        //This method is use to login.
        // void launchHomeFragment();
    }

    /**
     * Declared the methods here which can be used in Data manager of Login Screen.
     */
    interface IDataManager extends IBaseDataManager {


    }
}
