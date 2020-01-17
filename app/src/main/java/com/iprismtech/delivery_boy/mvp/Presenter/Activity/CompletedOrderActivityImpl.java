package com.iprismtech.delivery_boy.mvp.Presenter.Activity;

import android.content.Context;

import com.iprismtech.delivery_boy.mvp.Contract.Activity.CompletedOrderActivityContract;
import com.iprismtech.delivery_boy.mvp.base.BasePresenter;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;

import java.util.Map;

public class CompletedOrderActivityImpl extends BasePresenter implements CompletedOrderActivityContract.IPresenter {
    private CompletedOrderActivityContract.IView forgototp;
    private Context context;

    public CompletedOrderActivityImpl(CompletedOrderActivityContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @Override
    public void complaintAdmin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }
}