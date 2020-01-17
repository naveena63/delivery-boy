package com.iprismtech.delivery_boy.mvp.Presenter.Fragment;

import android.content.Context;

import com.iprismtech.delivery_boy.mvp.Contract.Fragment.PickUpFragmentContract;
import com.iprismtech.delivery_boy.mvp.base.BasePresenter;
import com.iprismtech.delivery_boy.network.constants.NetworkConstants;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;
import com.iprismtech.delivery_boy.network.models.UserApiModel;

import java.util.Map;

public class PickUpFragmentImpl extends BasePresenter implements PickUpFragmentContract.IPresenter {
    public PickUpFragmentImpl(Object view, Context context) {
        super(view, context);
    }

    @Override
    public void agent_pickup_orders(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.agent_pickup_orders(NetworkConstants.RequestCode.AGENT_PICKUP_ORDERS, requestBody);
    }

    @Override
    public void start_warehouse_service(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.start_warehouse_service(NetworkConstants.RequestCode.AGENT_TOSTART_ORDER, requestBody);
    }

    @Override
    public void launchHomeFragment() {

    }
}