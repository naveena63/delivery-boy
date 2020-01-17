package com.iprismtech.delivery_boy.ui.Activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismtech.delivery_boy.Adapter.NotificationAdapter;
import com.iprismtech.delivery_boy.Adapter.PickedOrdersAdapter;
import com.iprismtech.delivery_boy.Pojos.NotificationsPOJO;
import com.iprismtech.delivery_boy.Pojos.PickupOrdersPOJO;
import com.iprismtech.delivery_boy.R;
import com.iprismtech.delivery_boy.app.constants.AppConstants;
import com.iprismtech.delivery_boy.app.controller.ApplicationController;
import com.iprismtech.delivery_boy.base.BaseAbstractActivity;
import com.iprismtech.delivery_boy.mvp.Contract.Activity.NotificationActivityContarct;
import com.iprismtech.delivery_boy.mvp.Presenter.Activity.NotificationActivityImpl;
import com.iprismtech.delivery_boy.network.constants.NetworkConstants;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;
import com.iprismtech.delivery_boy.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NotificationActivity extends BaseAbstractActivity<NotificationActivityImpl> implements NotificationActivityContarct.IView, APIResponseCallback {
    ImageView imageView;
    RecyclerView rv_notification;
    NotificationAdapter notificationAdapter;
    NotificationsPOJO notificationsPOJO;
    TextView no_datatv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_notification_screen);
        presenter.start();

    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_notification_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new NotificationActivityImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        imageView = findViewById(R.id.notification_back);
        rv_notification = findViewById(R.id.rv_notification);
        no_datatv = findViewById(R.id.no_datatv);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getnotifications();

       /* RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(NotificationActivity.this);
        rv_notification.setLayoutManager(mLayoutManager);
        notificationAdapter = new NotificationAdapter(this);
        rv_notification.setAdapter(notificationAdapter);*/
    }

    private void getnotifications() {
        Map<String, String> request = new HashMap<>();
       // request.put("dummy", "dummy");
       // request.put("token", refreshedToken);//routeID
        presenter.notifications(NotificationActivity.this, this, request);


    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {

            } else if (NetworkConstants.RequestCode.NOTIFICATIONS == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    Util.getInstance().cusToast(NotificationActivity.this, jsonObject.optString("message"));
                    notificationsPOJO = new Gson().fromJson(responseString, NotificationsPOJO.class);

                    if (notificationsPOJO.getResponse().size()>0) {
                        no_datatv.setVisibility(View.GONE);
                        rv_notification.setVisibility(View.VISIBLE);

                        LinearLayoutManager mLayoutManager = new LinearLayoutManager(NotificationActivity.this);
                        //setting horizontal list
                        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        rv_notification.setLayoutManager(mLayoutManager);
                        rv_notification.setItemAnimator(new DefaultItemAnimator());

                        //Adding Adapter to recyclerView
                        notificationAdapter = new NotificationAdapter(notificationsPOJO, NotificationActivity.this);
                        rv_notification.setAdapter(notificationAdapter);



                    }else{
                        no_datatv.setVisibility(View.VISIBLE);
                        rv_notification.setVisibility(View.GONE);
                    }
                }





            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }
}
