package com.iprismtech.delivery_boy.mvp.Presenter.Activity;

import android.content.Context;

import com.iprismtech.delivery_boy.mvp.Contract.Activity.OrderConfirmedActivityContract;
import com.iprismtech.delivery_boy.mvp.base.BasePresenter;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;

import java.util.Map;

public class OrderConfirmedActivityImpl extends BasePresenter implements OrderConfirmedActivityContract.IPresenter {
    private OrderConfirmedActivityContract.IView forgototp;
    private Context context;

    public OrderConfirmedActivityImpl(OrderConfirmedActivityContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @Override
    public void complaintAdmin(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {

    }
}