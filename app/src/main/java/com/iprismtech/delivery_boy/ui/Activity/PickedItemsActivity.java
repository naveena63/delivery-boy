package com.iprismtech.delivery_boy.ui.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismtech.delivery_boy.Adapter.CollectedItemAdapter;
import com.iprismtech.delivery_boy.Adapter.PickedItemsAdapter;
import com.iprismtech.delivery_boy.Pojos.DropedsingleitemPOJO;
import com.iprismtech.delivery_boy.Pojos.PickedItemsPOJO;
import com.iprismtech.delivery_boy.R;
import com.iprismtech.delivery_boy.app.constants.AppConstants;
import com.iprismtech.delivery_boy.app.controller.ApplicationController;
import com.iprismtech.delivery_boy.base.BaseAbstractActivity;
import com.iprismtech.delivery_boy.dao.AdapterCallback;
import com.iprismtech.delivery_boy.dao.AdapterCallbackQuantity;
import com.iprismtech.delivery_boy.mvp.Contract.Activity.PickedItemsActivityContract;
import com.iprismtech.delivery_boy.mvp.Presenter.Activity.PickedItemsActivityImpl;
import com.iprismtech.delivery_boy.network.constants.NetworkConstants;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;
import com.iprismtech.delivery_boy.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PickedItemsActivity extends BaseAbstractActivity<PickedItemsActivityImpl> implements PickedItemsActivityContract.IView, APIResponseCallback, AdapterCallbackQuantity {
    ImageView imageView;
    LinearLayout linearLayout;
    RecyclerView rv_picked_item;
    CollectedItemAdapter collectedItemAdapter;
    TextView invoice_tv,no_datatv;
    String order_id,invoice_id;
    APIResponseCallback apiResponseCallback;
    AdapterCallback adapterCallback;
    AdapterCallbackQuantity adapterCallbackQuantity;
    private String all_click_status;
    CardView cv_data;
    DropedsingleitemPOJO dropedsingleitemPOJO;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_picked_items_screen);
        presenter.start();

    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_picked_items_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new PickedItemsActivityImpl(this, this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        apiResponseCallback = this;
        adapterCallbackQuantity = this;

        final Bundle bundle = getIntent().getExtras();
        order_id = bundle.getString("order_id", "");
        invoice_id = bundle.getString("invoice_id", "");




        invoice_tv = findViewById(R.id.invoice_tv);
        imageView = findViewById(R.id.items_picked_back);
        linearLayout = findViewById(R.id.picked_lay_out);
        linearLayout.setVisibility(View.GONE);
        rv_picked_item = findViewById(R.id.rv_picked_item);

        invoice_tv.setText(invoice_id);

        getdropeddata();

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemsdropedWBS();



               /* Intent intent = new Intent(PickedItemsActivity.this, ItemsDroppedActivity.class);
                startActivity(intent);*/
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

       /* LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        //setting horizontal list
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_picked_item.setLayoutManager(mLayoutManager);
        rv_picked_item.setItemAnimator(new DefaultItemAnimator());

        //Adding Adapter to recyclerView
        //pickedItemsAdapter = new PickedItemsAdapter(this);
        rv_picked_item.setAdapter(pickedItemsAdapter);*/
    }

    private void itemsdropedWBS() {

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("order_id", order_id);
        requestBody.put("home_items_picked", "yes");
        presenter.divery_elitems_picked(PickedItemsActivity.this, this, requestBody);





    }

    private void getdropeddata() {

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("order_id", order_id);
        presenter.agent_get_home_delivery_details(PickedItemsActivity.this, this, requestBody);

    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {

            } else if (NetworkConstants.RequestCode.AGENT_GET_DROPED_DATA == requestId) {
                if (jsonObject.optBoolean("status") == true) {

                    //  jsonArray = jsonObject.getJSONArray("response");


                    Util.getInstance().cusToast(PickedItemsActivity.this, jsonObject.optString("message"));
                    dropedsingleitemPOJO = new Gson().fromJson(responseString, DropedsingleitemPOJO.class);

                    if (dropedsingleitemPOJO.getResponse().size() > 0) {
                        //  cv_data.setVisibility(View.VISIBLE);
                        //   no_datatv.setVisibility(View.GONE);


                        LinearLayoutManager mLayoutManager = new LinearLayoutManager(PickedItemsActivity.this);
                        //setting horizontal list
                        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        rv_picked_item.setLayoutManager(mLayoutManager);
                        rv_picked_item.setItemAnimator(new DefaultItemAnimator());

                        //Adding Adapter to recyclerView
                        collectedItemAdapter = new CollectedItemAdapter(dropedsingleitemPOJO, PickedItemsActivity.this, adapterCallbackQuantity);
                        rv_picked_item.setAdapter(collectedItemAdapter);
                    } else {
                        //   cv_data.setVisibility(View.GONE);
                        //   no_datatv.setVisibility(View.VISIBLE);

                    }


                }

            } else if (NetworkConstants.RequestCode.CHECK_SINGLE_DELIVERY_ITEM == requestId) {

                    if (jsonObject.optBoolean("status") == true) {
                        Util.getInstance().cusToast(PickedItemsActivity.this, jsonObject.optString("message"));

                    //    getdropeddata();
                    } else {
                        Util.getInstance().cusToast(PickedItemsActivity.this, jsonObject.optString("message"));

                    }


            } else if (NetworkConstants.RequestCode.DELIVERY_ITEMS_PICKED == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    Util.getInstance().cusToast(PickedItemsActivity.this, jsonObject.optString("message"));


                    JSONArray jsonArray = jsonObject.getJSONArray("response");
                    String shop_name = jsonArray.getJSONObject(0).optString("d_store_name");
                    String lat = jsonArray.getJSONObject(0).optString("d_lat");
                    String lng = jsonArray.getJSONObject(0).optString("d_lng");
                    String contact_no = jsonArray.getJSONObject(0).optString("contact_no");
                    String d_address = jsonArray.getJSONObject(0).optString("d_address");
                    String d_pincode = jsonArray.getJSONObject(0).optString("d_pincode");


                    Bundle bundle = new Bundle();
                    bundle.putString("order_id", order_id);
                    bundle.putString("invoice_id", invoice_id);
                    bundle.putString("shop_name", shop_name);
                    bundle.putString("lat", lat);
                    bundle.putString("lng", lng);
                    bundle.putString("contact_no", contact_no);
                    bundle.putString("d_address", d_address);
                    bundle.putString("d_pincode", d_pincode);


                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_ITEMSDROPED_SCREEN, bundle);

                    /*Intent intent = new Intent(PickedItemsActivity.this, ItemsDeliveryActivity.class);
                    startActivity(intent);*/
                } else {
                    Util.getInstance().cusToast(PickedItemsActivity.this, jsonObject.optString("message"));

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

            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("order_id", order_id);
            requestBody.put("orders_details_id", product_id);
            requestBody.put("home_items_collected", "yes");
            presenter.check_single_home_delivery_items(PickedItemsActivity.this, this, requestBody);


            for (int j = 0; j < dropedsingleitemPOJO.getResponse().size(); j++) {
                for (int k = 0; k < dropedsingleitemPOJO.getResponse().get(j).getShop_list().size(); k++) {
                    if (dropedsingleitemPOJO.getResponse().get(j).getShop_list().get(k).isClicked) {
                        all_click_status = "true";
                    } else {
                        all_click_status = "false";
                        break;
                    }

                }

            }
            if (all_click_status.equalsIgnoreCase("false")) {
                linearLayout.setVisibility(View.GONE);
            } else {
                linearLayout.setVisibility(View.VISIBLE);
            }


        } else {

        }
    }

}
