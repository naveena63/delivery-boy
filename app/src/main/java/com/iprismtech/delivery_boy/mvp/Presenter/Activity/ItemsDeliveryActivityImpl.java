package com.iprismtech.delivery_boy.mvp.Presenter.Activity;

import android.content.Context;

import com.iprismtech.delivery_boy.mvp.Contract.Activity.ItemsDeliveryActivityContract;
import com.iprismtech.delivery_boy.mvp.base.BasePresenter;
import com.iprismtech.delivery_boy.network.constants.NetworkConstants;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;
import com.iprismtech.delivery_boy.network.models.UserApiModel;

import java.util.Map;

public class ItemsDeliveryActivityImpl extends BasePresenter implements ItemsDeliveryActivityContract.IPresenter {
    private ItemsDeliveryActivityContract.IView forgototp;
    private Context context;

    public ItemsDeliveryActivityImpl(ItemsDeliveryActivityContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @Override
    public void warehouse_items_dropped(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.warehouse_items_dropped(NetworkConstants.RequestCode.AGENT_WHOUSE_ITEMS_DROPED, requestBody);
    }
}