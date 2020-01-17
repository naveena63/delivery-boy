package com.iprismtech.delivery_boy.app.factories;


import android.support.annotation.IntDef;


import com.iprismtech.delivery_boy.app.controller.ApplicationController;
import com.iprismtech.delivery_boy.ui.Activity.AccountSettingActivity;
import com.iprismtech.delivery_boy.ui.Activity.AdminSupportActivity;
import com.iprismtech.delivery_boy.ui.Activity.AppInfoActivity;
import com.iprismtech.delivery_boy.ui.Activity.CollectedItemsActivity;
import com.iprismtech.delivery_boy.ui.Activity.CompletedOrderActivity;
import com.iprismtech.delivery_boy.ui.Activity.EmployeeIdActivity;
import com.iprismtech.delivery_boy.ui.Activity.FirstMainActivity;
import com.iprismtech.delivery_boy.ui.Activity.ItemsDeliveryActivity;
import com.iprismtech.delivery_boy.ui.Activity.ItemsDroppedActivity;
import com.iprismtech.delivery_boy.ui.Activity.LoginActivity;
import com.iprismtech.delivery_boy.ui.Activity.NotificationActivity;
import com.iprismtech.delivery_boy.ui.Activity.OrderConfirmedActivity;
import com.iprismtech.delivery_boy.ui.Activity.PickedItemsActivity;
import com.iprismtech.delivery_boy.ui.Activity.SplashActivity;
import com.iprismtech.delivery_boy.ui.Activity.WelComeActivity;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//import static com.iprismtech.proffers.app.constants.AppConstants.EventIds.LAUNCH_SIGNUP_SCREEN;


/**
 * Created by prasad on 05/07/2017.
 * ViewFactory.java The Class which returns the Class (Screen) to the
 * application frame. Developer should use this class to get the reference of
 * any screen in the application. He should not create the screen by him/her
 * self
 */
public class ViewFactory {

    @Retention(RetentionPolicy.CLASS)
    @IntDef({ScreenIds.LOGIN_SCREEN,  ScreenIds.LAUNCH_FRSTHOMESCREEN, ScreenIds.WELCOME_SCREEN,ScreenIds.COLLECTED_ITEMS_SCREEN,ScreenIds.ACUNTSETING_SCREEN,
    ScreenIds.ADMINSUPORT_SCREEN,ScreenIds.APPINFO_SCREEN,ScreenIds.COMPLETEDORDERS_SCREEN,ScreenIds.EMPLOID_SCREEN,ScreenIds.ITEMSDELIVERY_SCREEN,
    ScreenIds.ITEMS_DROPED_SCREEN,ScreenIds.NOTIFICATIONS_SCREEN,ScreenIds.ORDERCONFORMED_SCREEN,ScreenIds.PICKEDITEMS_SCREEN,ScreenIds.SPLASH_SCREEN})
    public @interface ScreenIds {
        int LOGIN_SCREEN = 1001;

        int LAUNCH_FRSTHOMESCREEN=1002;

        int WELCOME_SCREEN = 1003;

        int COLLECTED_ITEMS_SCREEN = 1005;

        int ACUNTSETING_SCREEN = 1006;

        int ADMINSUPORT_SCREEN = 1007;
        int APPINFO_SCREEN = 1008;
        int COMPLETEDORDERS_SCREEN = 1009;
        int EMPLOID_SCREEN = 1010;
        int ITEMSDELIVERY_SCREEN = 1011;

        int ITEMS_DROPED_SCREEN = 1012;
        int NOTIFICATIONS_SCREEN = 1013;
        int ORDERCONFORMED_SCREEN = 1014;

        int PICKEDITEMS_SCREEN = 1015;
        int SPLASH_SCREEN = 1016;

    }


    private ApplicationController mApplicationController = null;


    private ViewFactory() {
        mApplicationController = ApplicationController.getInstance();
    }


    public static Class getActivityClass(@ScreenIds int id) {

        switch (id) {

            case ScreenIds.LOGIN_SCREEN: {
                return LoginActivity.class;

            }


            case ScreenIds.LAUNCH_FRSTHOMESCREEN: {
                return FirstMainActivity.class;

            }


            case ScreenIds.WELCOME_SCREEN: {
                return WelComeActivity.class;

            }
            case ScreenIds.COLLECTED_ITEMS_SCREEN: {
                return CollectedItemsActivity.class;

            }

            case ScreenIds.ACUNTSETING_SCREEN: {
                return AccountSettingActivity.class;

            } case ScreenIds.ADMINSUPORT_SCREEN: {
                return AdminSupportActivity.class;

            } case ScreenIds.APPINFO_SCREEN: {
                return AppInfoActivity.class;

            } case ScreenIds.COMPLETEDORDERS_SCREEN: {
                return CompletedOrderActivity.class;

            } case ScreenIds.EMPLOID_SCREEN: {
                return EmployeeIdActivity.class;

            } case ScreenIds.ITEMSDELIVERY_SCREEN: {
                return ItemsDeliveryActivity.class;

            } case ScreenIds.ITEMS_DROPED_SCREEN: {
                return ItemsDroppedActivity.class;

            } case ScreenIds.NOTIFICATIONS_SCREEN: {
                return NotificationActivity.class;

            } case ScreenIds.ORDERCONFORMED_SCREEN: {
                return OrderConfirmedActivity.class;

            } case ScreenIds.PICKEDITEMS_SCREEN: {
                return PickedItemsActivity.class;

            } case ScreenIds.SPLASH_SCREEN: {
                return SplashActivity.class;


            }
            default:
                throw new IllegalStateException("Invalid screen id");
        }
    }


    /**
     * This function should only be used when whole application is made by
     * multiple Fragment.
     *
     * @param id
     * @return Fragment class
     */
//    public static Class getFragmentClass(@ScreenIds int id) {
//
//        switch (id) {
//            //todo logic for fragments are same
//            case LOGIN_SCREEN: {
//                return LoginActivity.class;
//            }
//
//            case SIGNUP_SCREEN: {
//                return SignupActivity.class;
//            }
//
////            case FAVOURITES_SCREEN: {
////
////                return FavoritesActivity.class;
////
////            }
//
//            default:
//                throw new IllegalStateException("Invalid Event id");
//
//        }
//
//    }
}