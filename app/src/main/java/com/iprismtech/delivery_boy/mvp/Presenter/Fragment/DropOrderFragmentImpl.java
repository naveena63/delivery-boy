package com.iprismtech.delivery_boy.mvp.Presenter.Fragment;

import android.content.Context;

import com.iprismtech.delivery_boy.mvp.Contract.Fragment.DropOrderFragmentContract;
import com.iprismtech.delivery_boy.mvp.base.BasePresenter;
import com.iprismtech.delivery_boy.network.constants.NetworkConstants;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;
import com.iprismtech.delivery_boy.network.models.UserApiModel;

import java.util.Map;

public class DropOrderFragmentImpl extends BasePresenter implements DropOrderFragmentContract.IPresenter {
    public DropOrderFragmentImpl(Object view, Context context) {
        super(view, context);
    }

    @Override
    public void agent_delivery_orders(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.agent_delivery_orders(NetworkConstants.RequestCode.ITEMS_DROPED_ORDERS, requestBody);


    }

    @Override
    public void start_home_delivery_service(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.start_home_delivery_service(NetworkConstants.RequestCode.AGENT_TOSTARTDROP_ORDER, requestBody);


    }

    @Override
    public void launchHomeFragment() {

    }
}

