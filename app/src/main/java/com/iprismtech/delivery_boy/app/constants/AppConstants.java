package com.iprismtech.delivery_boy.app.constants;

import android.support.annotation.IntDef;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

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


public interface AppConstants {

    String LOG_CAT = ">>logs : ";


    @Retention(RetentionPolicy.CLASS)
    @IntDef({EventIds.LAUNCH_LOGIN_SCREEN,EventIds.LAUNCH_FRSTHOME_SCREEN,EventIds.LAUNCH_WELCOME_SCREEN,EventIds.LAUNCH_COLLECTED_ITEMS_SCREEN,
            LAUNCH_AUCNTSETTING_SCREEN ,LAUNCH_ADMINSUPPORT_SCREEN ,LAUNCH_APPINFO_SCREEN ,LAUNCH_COMPLETEDORDER_SCREEN ,LAUNCH_EMPLOYID_SCREEN ,LAUNCH_ITEMSDELIVERY_SCREEN,
            LAUNCH_ITEMSDROPED_SCREEN ,LAUNCH_NOTIFICATIONS_SCREEN ,LAUNCH_ORDERCONFORMED_SCREEN ,LAUNCH_PICKEDITEMS_SCREEN ,LAUNCH_SPLASH_SCREEN
    })
    @interface EventIds {
        int LAUNCH_LOGIN_SCREEN = 100;
        int LAUNCH_FRSTHOME_SCREEN = 101;
        int LAUNCH_WELCOME_SCREEN = 102;
        int LAUNCH_COLLECTED_ITEMS_SCREEN = 103;

        int LAUNCH_AUCNTSETTING_SCREEN = 104;
        int LAUNCH_ADMINSUPPORT_SCREEN = 105;
        int LAUNCH_APPINFO_SCREEN = 106;
        int LAUNCH_COMPLETEDORDER_SCREEN = 107;
        int LAUNCH_EMPLOYID_SCREEN = 108;
        int LAUNCH_ITEMSDELIVERY_SCREEN = 109;
        int LAUNCH_ITEMSDROPED_SCREEN = 110;
        int LAUNCH_NOTIFICATIONS_SCREEN = 111;
        int LAUNCH_ORDERCONFORMED_SCREEN = 112;
        int LAUNCH_PICKEDITEMS_SCREEN = 113;
        int LAUNCH_SPLASH_SCREEN = 114;








    }


    interface PREFERENCES {
        String IS_LAUNCH_HOME_SCREEN = "isLaunchHomeScreen";
        String KEY_PREF_X_AUTH_TOKEN = "X-AUTH-TOKEN";
        String KEY_HOST_URL = "hostUrl";
    }
}
