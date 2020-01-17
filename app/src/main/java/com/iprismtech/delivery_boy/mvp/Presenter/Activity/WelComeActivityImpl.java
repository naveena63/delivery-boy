package com.iprismtech.delivery_boy.mvp.Presenter.Activity;

import android.content.Context;

import com.iprismtech.delivery_boy.mvp.Contract.Activity.WelComeActivityContract;
import com.iprismtech.delivery_boy.mvp.base.BasePresenter;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;

import java.util.Map;

public class WelComeActivityImpl extends BasePresenter implements WelComeActivityContract.IPresenter {
    private WelComeActivityContract.IView forgototp;
    private Context context;

    public WelComeActivityImpl(WelComeActivityContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @Override
    public void complaintAdmin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }
}