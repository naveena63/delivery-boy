package com.iprismtech.delivery_boy.mvp.Presenter.Activity;

import android.content.Context;

import com.iprismtech.delivery_boy.mvp.Contract.Activity.AccountSettingActivityContract;
import com.iprismtech.delivery_boy.mvp.base.BasePresenter;
import com.iprismtech.delivery_boy.network.constants.NetworkConstants;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;
import com.iprismtech.delivery_boy.network.models.UserApiModel;

import java.util.Map;

public class AccountSettingActivityImpl extends BasePresenter implements AccountSettingActivityContract.IPresenter {
    private AccountSettingActivityContract.IView forgototp;
    private Context context;

    public AccountSettingActivityImpl(AccountSettingActivityContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @Override
    public void agent_profile(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.agent_profile(NetworkConstants.RequestCode.AGENT_PROFILE, requestBody);
    }

    @Override
    public void agent_update_profile(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.agent_update_profile(NetworkConstants.RequestCode.UPDATE_PROFILE, requestBody);
    }
}