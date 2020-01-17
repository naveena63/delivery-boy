package com.iprismtech.delivery_boy.mvp.Presenter.Activity;

import android.content.Context;

import com.iprismtech.delivery_boy.mvp.Contract.Activity.MainActivityContract;
import com.iprismtech.delivery_boy.mvp.base.BasePresenter;
import com.iprismtech.delivery_boy.network.constants.NetworkConstants;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;
import com.iprismtech.delivery_boy.network.models.UserApiModel;

import java.util.Map;

public class MainActivityImpl extends BasePresenter implements MainActivityContract.IPresenter {
    private MainActivityContract.IView forgototp;
    private Context context;

    public MainActivityImpl(MainActivityContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @Override
    public void agent_pickup_orders(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.agent_pickup_orders(NetworkConstants.RequestCode.AGENT_PICKUP_ORDERS, requestBody);
    }
}