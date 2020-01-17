package com.iprismtech.delivery_boy.mvp.Presenter.Activity;

import android.content.Context;

import com.iprismtech.delivery_boy.mvp.Contract.Activity.LoginActivityContract;
import com.iprismtech.delivery_boy.mvp.base.BasePresenter;
import com.iprismtech.delivery_boy.network.constants.NetworkConstants;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;
import com.iprismtech.delivery_boy.network.models.UserApiModel;

import java.util.Map;

public class LoginActivityImpl extends BasePresenter implements LoginActivityContract.IPresenter {
    private LoginActivityContract.IView forgototp;
    private Context context;

    public LoginActivityImpl(LoginActivityContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @Override
    public void agent_login(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.agent_login(NetworkConstants.RequestCode.AGENT_LOGIN, requestBody);
    }

    @Override
    public void update_device_token(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.update_device_token(NetworkConstants.RequestCode.UPDATE_TOKEN, requestBody);
    }
}