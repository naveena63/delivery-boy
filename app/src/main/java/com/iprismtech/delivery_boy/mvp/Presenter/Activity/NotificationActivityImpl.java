package com.iprismtech.delivery_boy.mvp.Presenter.Activity;

import android.content.Context;

import com.iprismtech.delivery_boy.mvp.Contract.Activity.NotificationActivityContarct;
import com.iprismtech.delivery_boy.mvp.base.BasePresenter;
import com.iprismtech.delivery_boy.network.constants.NetworkConstants;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;
import com.iprismtech.delivery_boy.network.models.UserApiModel;

import java.util.Map;

public class NotificationActivityImpl extends BasePresenter implements NotificationActivityContarct.IPresenter {
    private NotificationActivityContarct.IView forgototp;
    private Context context;

    public NotificationActivityImpl(NotificationActivityContarct.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @Override
    public void notifications(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.notifications(NetworkConstants.RequestCode.NOTIFICATIONS, requestBody);
    }
}