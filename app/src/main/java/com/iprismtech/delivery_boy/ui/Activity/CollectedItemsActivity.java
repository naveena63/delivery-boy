package com.iprismtech.delivery_boy.ui.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismtech.delivery_boy.Adapter.CollectedItemAdapter;
import com.iprismtech.delivery_boy.Adapter.PickedItemsAdapter;
import com.iprismtech.delivery_boy.Adapter.PickedOrdersAdapter;
import com.iprismtech.delivery_boy.Pojos.PickedItemsPOJO;
import com.iprismtech.delivery_boy.Pojos.PickupOrdersPOJO;
import com.iprismtech.delivery_boy.R;
import com.iprismtech.delivery_boy.app.constants.AppConstants;
import com.iprismtech.delivery_boy.app.controller.ApplicationController;
import com.iprismtech.delivery_boy.base.BaseAbstractActivity;
import com.iprismtech.delivery_boy.dao.AdapterCallback;
import com.iprismtech.delivery_boy.dao.AdapterCallbackQuantity;
import com.iprismtech.delivery_boy.mvp.Contract.Activity.CollectedItemsActivityContract;
import com.iprismtech.delivery_boy.mvp.Presenter.Activity.CollectedItemsActivityImpl;
import com.iprismtech.delivery_boy.network.constants.NetworkConstants;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;
import com.iprismtech.delivery_boy.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CollectedItemsActivity extends BaseAbstractActivity<CollectedItemsActivityImpl> implements CollectedItemsActivityContract.IView, APIResponseCallback, AdapterCallbackQuantity {

    ImageView imageView;
    LinearLayout collected_lay_out;
    RecyclerView rv_collected_items;
    String order_id,invoice_id;
    APIResponseCallback apiResponseCallback;
    PickedItemsPOJO pickedItemsPOJO;
    PickedItemsAdapter pickedItemsAdapter;
    AdapterCallback adapterCallback;
    AdapterCallbackQuantity adapterCallbackQuantity;

    JSONArray jsonArray;
    private String all_click_status;
    TextView invoice_tv,no_datatv;
    CardView cv_data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_collected_items_screen);
        presenter.start();

    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_collected_items_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new CollectedItemsActivityImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        apiResponseCallback = this;
        adapterCallbackQuantity = this;

        final Bundle bundle = getIntent().getExtras();
        order_id = bundle.getString("order_id", "");
        invoice_id = bundle.getString("invoice_id", "");


        imageView = findViewById(R.id.collected_back);
        collected_lay_out = findViewById(R.id.collected_lay_out);
        rv_collected_items = findViewById(R.id.rv_collected_items);
        invoice_tv = findViewById(R.id.invoice_tv);
        no_datatv = findViewById(R.id.no_datatv);
        cv_data = findViewById(R.id.cv_data);
        collected_lay_out.setVisibility(View.GONE);

        if(!bundle.isEmpty()){
            invoice_tv.setText(invoice_id);
        }


        singleorderdetailsWBS();


        collected_lay_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemspickedWBS();


            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private void itemspickedWBS() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("order_id", order_id);
        requestBody.put("warehouse_items_picked", "yes");
        presenter.items_picked(CollectedItemsActivity.this, this, requestBody);

    }

    private void singleorderdetailsWBS() {

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("order_id", order_id);
        presenter.agent_get_pickup_details(CollectedItemsActivity.this, this, requestBody);


    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {

            } else if (NetworkConstants.RequestCode.AGENT_PICKUP_ITEMS == requestId) {
                if (jsonObject.optBoolean("status") == true) {




                    Util.getInstance().cusToast(CollectedItemsActivity.this, jsonObject.optString("message"));
                    pickedItemsPOJO = new Gson().fromJson(responseString, PickedItemsPOJO.class);

                if(pickedItemsPOJO.getResponse().size()>0) {
                    cv_data.setVisibility(View.VISIBLE);
                    no_datatv.setVisibility(View.GONE);


                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(CollectedItemsActivity.this);
                    //setting horizontal list
                    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    rv_collected_items.setLayoutManager(mLayoutManager);
                    rv_collected_items.setItemAnimator(new DefaultItemAnimator());

                    //Adding Adapter to recyclerView
                    pickedItemsAdapter = new PickedItemsAdapter(pickedItemsPOJO, CollectedItemsActivity.this, adapterCallbackQuantity);
                    rv_collected_items.setAdapter(pickedItemsAdapter);
                }else{
                    cv_data.setVisibility(View.GONE);
                    no_datatv.setVisibility(View.VISIBLE);

                }


                }

            } else if (NetworkConstants.RequestCode.CHECK_SINGLE_ITEM == requestId) {

                if (jsonObject.optBoolean("status") == true) {
                    Util.getInstance().cusToast(CollectedItemsActivity.this, jsonObject.optString("message"));

                   // singleorderdetailsWBS();

                } else {
                    Util.getInstance().cusToast(CollectedItemsActivity.this, jsonObject.optString("message"));

                }


            } else if (NetworkConstants.RequestCode.ITEMS_PICKED == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    Util.getInstance().cusToast(CollectedItemsActivity.this, jsonObject.optString("message"));


                    jsonArray = jsonObject.getJSONArray("response");
                    String shop_name = jsonArray.getJSONObject(0).optString("shop_name");
                    String lat = jsonArray.getJSONObject(0).optString("lat");
                    String lng = jsonArray.getJSONObject(0).optString("lng");
                    String shop_locality = jsonArray.getJSONObject(0).optString("shop_locality");
                    String shop_mobile = jsonArray.getJSONObject(0).optString("shop_mobile");
                    String shop_pincode = jsonArray.getJSONObject(0).optString("shop_pincode");


                    Bundle bundle = new Bundle();
                    bundle.putString("order_id", order_id);
                    bundle.putString("invoice_id", invoice_id);
                    bundle.putString("shop_name", shop_name);
                    bundle.putString("lat", lat);
                    bundle.putString("lng", lng);
                    bundle.putString("shop_locality", shop_locality);
                    bundle.putString("shop_mobile", shop_mobile);
                    bundle.putString("shop_pincode", shop_pincode);


                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_ITEMSDELIVERY_SCREEN, bundle);




                   /* Intent intent = new Intent(CollectedItemsActivity.this, ItemsDeliveryActivity.class);
                    startActivity(intent);*/
                } else {
                    Util.getInstance().cusToast(CollectedItemsActivity.this, jsonObject.optString("message"));

                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }


    @Override
    public void clickbox(CheckBox itemcheck, int i, String id, boolean isChecked) {
        if (itemcheck.getId() == R.id.itemcheck) {
            String product_id = id;

            if(isChecked){
                Map<String, String> requestBody = new HashMap<>();
                requestBody.put("order_id", order_id);
                requestBody.put("orders_details_id", product_id);
                requestBody.put("warehouse_items_collected", "yes");
                presenter.check_single_items(CollectedItemsActivity.this, this, requestBody);

            }else{
                Map<String, String> requestBody = new HashMap<>();
                requestBody.put("order_id", order_id);
                requestBody.put("orders_details_id", product_id);
                requestBody.put("warehouse_items_collected", "no");
                presenter.check_single_items(CollectedItemsActivity.this, this, requestBody);

            }


            for (int j = 0; j < pickedItemsPOJO.getResponse().size(); j++) {
                for (int k = 0; k < pickedItemsPOJO.getResponse().get(j).getShop_list().size(); k++) {
                    if (pickedItemsPOJO.getResponse().get(j).getShop_list().get(k).isClicked) {
                        all_click_status = "true";
                    } else {
                        all_click_status = "false";
                        break;
                    }

                }

            }
            if (all_click_status.equalsIgnoreCase("false")) {
                collected_lay_out.setVisibility(View.GONE);
            } else {
                collected_lay_out.setVisibility(View.VISIBLE);
            }


        } else {

        }
    }
}
