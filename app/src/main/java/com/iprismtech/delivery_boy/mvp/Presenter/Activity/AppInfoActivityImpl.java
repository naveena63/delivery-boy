package com.iprismtech.delivery_boy.mvp.Presenter.Activity;

import android.content.Context;

import com.iprismtech.delivery_boy.mvp.Contract.Activity.AppInfoActivityContract;
import com.iprismtech.delivery_boy.mvp.base.BasePresenter;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;

import java.util.Map;

public class AppInfoActivityImpl extends BasePresenter implements AppInfoActivityContract.IPresenter {
    private AppInfoActivityContract.IView forgototp;
    private Context context;

    public AppInfoActivityImpl(AppInfoActivityContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @Override
    public void complaintAdmin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }
}