package com.iprismtech.delivery_boy.network.constants;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.iprismtech.delivery_boy.network.constants.NetworkConstants.RequestCode.AGENT_GET_DROPED_DATA;
import static com.iprismtech.delivery_boy.network.constants.NetworkConstants.RequestCode.AGENT_LOGIN;
import static com.iprismtech.delivery_boy.network.constants.NetworkConstants.RequestCode.AGENT_PICKUP_ORDERS;
import static com.iprismtech.delivery_boy.network.constants.NetworkConstants.RequestCode.AGENT_PROFILE;
import static com.iprismtech.delivery_boy.network.constants.NetworkConstants.RequestCode.AGENT_TOSTARTDROP_ORDER;
import static com.iprismtech.delivery_boy.network.constants.NetworkConstants.RequestCode.AGENT_TOSTART_ORDER;
import static com.iprismtech.delivery_boy.network.constants.NetworkConstants.RequestCode.AGENT_WHOUSE_ITEMS_DROPED;
import static com.iprismtech.delivery_boy.network.constants.NetworkConstants.RequestCode.CHECK_SINGLE_DELIVERY_ITEM;
import static com.iprismtech.delivery_boy.network.constants.NetworkConstants.RequestCode.CHECK_SINGLE_ITEM;
import static com.iprismtech.delivery_boy.network.constants.NetworkConstants.RequestCode.CUSTOMER_SERVICE;
import static com.iprismtech.delivery_boy.network.constants.NetworkConstants.RequestCode.DELIVERY_ITEMS_PICKED;
import static com.iprismtech.delivery_boy.network.constants.NetworkConstants.RequestCode.ITEMS_DELIVERD;
import static com.iprismtech.delivery_boy.network.constants.NetworkConstants.RequestCode.ITEMS_DROPED_ORDERS;
import static com.iprismtech.delivery_boy.network.constants.NetworkConstants.RequestCode.ITEMS_PICKED;
import static com.iprismtech.delivery_boy.network.constants.NetworkConstants.RequestCode.NOTIFICATIONS;
import static com.iprismtech.delivery_boy.network.constants.NetworkConstants.RequestCode.UPDATE_PROFILE;
import static com.iprismtech.delivery_boy.network.constants.NetworkConstants.RequestCode.UPDATE_TOKEN;
import static com.iprismtech.delivery_boy.network.constants.NetworkConstants.RequestCode.agent_register;


public interface NetworkConstants {
    String PAGE = "?page=";
    String LIMIT = "&limit=";

    interface Headers {
        String X_AUTH_TOKEN = "X-AUTH-TOKEN";
        String BASIC_AUTH_TOKEN = "Authorization";
    }

    /**
     * This is the network request to all apis.
     */
    interface URL {

        String Imagepath_URL = "http://appilabz.com/top_shelf/api";


        String BASE_URL = "http://appilabz.com/top_shelf/api/";

        String TOKEN = BASE_URL + "get_token";
        String UPDATE_TOKEN = BASE_URL + "update_device_token";
        String agent_register = BASE_URL + "agent_register";
        String AGENT_LOGIN = BASE_URL + "agent_login";
        String AGENT_PICKUP_ORDERS = BASE_URL + "agent_pickup_orders";
        String AGENT_TOSTART_ORDER = BASE_URL + "start_warehouse_service";
        String AGENT_PICKUP_ITEMS = BASE_URL + "agent_get_pickup_details";
        String AGENT_WHOUSE_ITEMS_DROPED = BASE_URL + "warehouse_items_dropped";
        String CHECK_SINGLE_ITEM = BASE_URL + "check_single_items";
        String ITEMS_PICKED = BASE_URL + "items_picked";

        //dropeddata

        String ITEMS_DROPED_ORDERS = BASE_URL + "agent_delivery_orders";
        String AGENT_TOSTARTDROP_ORDER = BASE_URL + "start_home_delivery_service";
        String AGENT_GET_DROPED_DATA = BASE_URL + "agent_get_home_delivery_details";
        String CHECK_SINGLE_DELIVERY_ITEM = BASE_URL + "check_single_home_delivery_items";
        String DELIVERY_ITEMS_PICKED = BASE_URL + "delivery_items_picked";
        String ITEMS_DELIVERD = BASE_URL + "items_delivered";


        String AGENT_PROFILE = BASE_URL + "agent_profile";
        String CUSTOMER_SERVICE = BASE_URL + "customer_support";
        String UPDATE_PROFILE = BASE_URL + "agent_update_profile";
        String NOTIFICATIONS = BASE_URL + "agent_push_notifications";




    }

    /**
     * Application Controller events ids
     * Maintain all app level event ids and def of that event ids
     */
    @Retention(RetentionPolicy.CLASS)
    @IntDef({agent_register,AGENT_LOGIN,AGENT_PICKUP_ORDERS,AGENT_TOSTART_ORDER,AGENT_WHOUSE_ITEMS_DROPED,CHECK_SINGLE_ITEM,ITEMS_PICKED,
            ITEMS_DROPED_ORDERS,AGENT_TOSTARTDROP_ORDER,AGENT_GET_DROPED_DATA,CHECK_SINGLE_DELIVERY_ITEM,DELIVERY_ITEMS_PICKED,ITEMS_DELIVERD,
            AGENT_PROFILE,CUSTOMER_SERVICE,UPDATE_PROFILE,NOTIFICATIONS,UPDATE_TOKEN})
    @interface RequestCode {
        int SESSION_EXPIRE = 1017;
        int agent_register = 110;
        int AGENT_LOGIN = 111;
        int AGENT_PICKUP_ORDERS = 112;
        int AGENT_TOSTART_ORDER = 113;
        int AGENT_PICKUP_ITEMS = 114;
        int AGENT_WHOUSE_ITEMS_DROPED = 115;
        int CHECK_SINGLE_ITEM = 116;
        int ITEMS_PICKED = 117;
        int ITEMS_DROPED_ORDERS = 118;
        int AGENT_TOSTARTDROP_ORDER = 119;
        int AGENT_GET_DROPED_DATA = 120;
        int CHECK_SINGLE_DELIVERY_ITEM = 121;
        int DELIVERY_ITEMS_PICKED = 122;
        int ITEMS_DELIVERD = 123;
        int AGENT_PROFILE = 124;
        int CUSTOMER_SERVICE = 125;
        int UPDATE_PROFILE = 126;
        int NOTIFICATIONS = 127;
        int UPDATE_TOKEN = 128;



    }
}