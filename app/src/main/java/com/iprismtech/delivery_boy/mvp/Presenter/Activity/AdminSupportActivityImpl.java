package com.iprismtech.delivery_boy.mvp.Presenter.Activity;

import android.content.Context;

import com.iprismtech.delivery_boy.mvp.Contract.Activity.AdminSupportActivityContract;
import com.iprismtech.delivery_boy.mvp.base.BasePresenter;
import com.iprismtech.delivery_boy.network.constants.NetworkConstants;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;
import com.iprismtech.delivery_boy.network.models.UserApiModel;

import java.util.Map;

public class AdminSupportActivityImpl extends BasePresenter implements AdminSupportActivityContract.IPresenter {
    private AdminSupportActivityContract.IView forgototp;
    private Context context;

    public AdminSupportActivityImpl(AdminSupportActivityContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @Override
    public void customer_support(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.customer_support(NetworkConstants.RequestCode.CUSTOMER_SERVICE, requestBody);
    }
}