package com.iprismtech.delivery_boy.network.models;

import android.content.Context;

import com.android.volley.Request;
import com.iprismtech.delivery_boy.R;
import com.iprismtech.delivery_boy.network.constants.NetworkConstants;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;
import com.iprismtech.delivery_boy.network.volley.APIHandler;

import java.util.Map;

/**
 * Created by PRASAD on 09-Dec-17.
 */

public class UserApiModel extends BaseApiModel {

    public UserApiModel(Context context, APIResponseCallback apiResponseCallback) {
        super(context);
        this.context = context;
        this.apiResponseCallback = apiResponseCallback;
    }

    public void agent_register(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.agent_register, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void agent_login(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.AGENT_LOGIN, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void agent_pickup_orders(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.AGENT_PICKUP_ORDERS, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void start_warehouse_service(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.AGENT_TOSTART_ORDER, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void agent_get_pickup_details(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.AGENT_PICKUP_ITEMS, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void warehouse_items_dropped(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.AGENT_WHOUSE_ITEMS_DROPED, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void items_picked(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.ITEMS_PICKED, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void check_single_items(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.CHECK_SINGLE_ITEM, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void agent_delivery_orders(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.ITEMS_DROPED_ORDERS, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void start_home_delivery_service(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.AGENT_TOSTARTDROP_ORDER, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void agent_get_home_delivery_details(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.AGENT_GET_DROPED_DATA, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void check_single_home_delivery_items(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.CHECK_SINGLE_DELIVERY_ITEM, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void divery_elitems_picked(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.DELIVERY_ITEMS_PICKED, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void items_delivered(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.ITEMS_DELIVERD, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }


    public void agent_profile(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.AGENT_PROFILE, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }


    public void customer_support(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.CUSTOMER_SERVICE, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }
    public void agent_update_profile(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.UPDATE_PROFILE, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void notifications(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.GET,
                NetworkConstants.URL.NOTIFICATIONS, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }

    public void update_device_token(@NetworkConstants.RequestCode int requestId, Map<String, String> params) {
        System.out.println("Check here weather user login or not>>>>>>>>>>>>>>>>>>>");
        apiHandler = new APIHandler(context, this, requestId, Request.Method.POST,
                NetworkConstants.URL.UPDATE_TOKEN, true, false,
                context.getString(R.string.pleasewait), params, getHeader());
        apiHandler.requestAPI();
    }
    @Override
    public void onAPISuccessResponse(int requestId, String responseString) {
        super.onAPISuccessResponse(requestId, responseString);
        apiResponseCallback.onSuccessResponse(requestId, responseString, "");
    }

    @Override
    public void onAPIFailureResponse(int requestId, String errorString) {
        super.onAPIFailureResponse(requestId, errorString);
        apiResponseCallback.onSuccessResponse(requestId, errorString, "");
    }

}
