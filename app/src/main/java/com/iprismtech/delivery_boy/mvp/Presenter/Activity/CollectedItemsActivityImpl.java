package com.iprismtech.delivery_boy.mvp.Presenter.Activity;

import android.annotation.SuppressLint;
import android.content.Context;

import com.iprismtech.delivery_boy.mvp.Contract.Activity.CollectedItemsActivityContract;
import com.iprismtech.delivery_boy.mvp.base.BasePresenter;
import com.iprismtech.delivery_boy.network.constants.NetworkConstants;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;
import com.iprismtech.delivery_boy.network.models.UserApiModel;

import java.util.Map;

public class CollectedItemsActivityImpl extends BasePresenter implements CollectedItemsActivityContract.IPresenter {
    private CollectedItemsActivityContract.IView forgototp;
    private Context context;

    public CollectedItemsActivityImpl(CollectedItemsActivityContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void agent_get_pickup_details(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.agent_get_pickup_details(NetworkConstants.RequestCode.AGENT_PICKUP_ITEMS, requestBody);
    }


    @Override
    public void check_single_items(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.check_single_items(NetworkConstants.RequestCode.CHECK_SINGLE_ITEM, requestBody);
    }

    @Override
    public void items_picked(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.items_picked(NetworkConstants.RequestCode.ITEMS_PICKED, requestBody);
    }
}