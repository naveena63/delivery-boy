package com.iprismtech.delivery_boy.mvp.Presenter.Activity;

import android.content.Context;

import com.iprismtech.delivery_boy.mvp.Contract.Activity.ItemsDeliveryActivityContract;
import com.iprismtech.delivery_boy.mvp.Contract.Activity.ItemsDroppedActivityContract;
import com.iprismtech.delivery_boy.mvp.base.BasePresenter;
import com.iprismtech.delivery_boy.network.constants.NetworkConstants;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;
import com.iprismtech.delivery_boy.network.models.UserApiModel;

import java.util.Map;

public class ItemsDroppedActivityImpl extends BasePresenter implements ItemsDroppedActivityContract.IPresenter {
    private ItemsDroppedActivityContract.IView forgototp;
    private Context context;

    public ItemsDroppedActivityImpl(ItemsDroppedActivityContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @Override
    public void items_delivered(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.items_delivered(NetworkConstants.RequestCode.ITEMS_DELIVERD, requestBody);
    }
}