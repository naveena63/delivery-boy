package com.iprismtech.delivery_boy.mvp.Presenter.Activity;

import android.content.Context;

import com.iprismtech.delivery_boy.mvp.Contract.Activity.PickedItemsActivityContract;
import com.iprismtech.delivery_boy.mvp.base.BasePresenter;
import com.iprismtech.delivery_boy.network.constants.NetworkConstants;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;
import com.iprismtech.delivery_boy.network.models.UserApiModel;

import java.util.Map;

public class PickedItemsActivityImpl extends BasePresenter implements PickedItemsActivityContract.IPresenter {
    private PickedItemsActivityContract.IView forgototp;
    private Context context;

    public PickedItemsActivityImpl(PickedItemsActivityContract.IView view, Context context) {
        super(view, context);
        this.context = context;
        this.forgototp = forgototp;
    }

    @Override
    public void agent_get_home_delivery_details(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.agent_get_home_delivery_details(NetworkConstants.RequestCode.AGENT_GET_DROPED_DATA, requestBody);
    }

    @Override
    public void check_single_home_delivery_items(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.check_single_home_delivery_items(NetworkConstants.RequestCode.CHECK_SINGLE_DELIVERY_ITEM, requestBody);
    }

    @Override
    public void divery_elitems_picked(Context context, APIResponseCallback apiResponseCallback, Map<String, String> requestBody) {
        UserApiModel userApiModel = new UserApiModel(context, apiResponseCallback);
        userApiModel.divery_elitems_picked(NetworkConstants.RequestCode.DELIVERY_ITEMS_PICKED, requestBody);
    }
}