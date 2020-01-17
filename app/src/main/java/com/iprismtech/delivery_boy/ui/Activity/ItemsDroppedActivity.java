package com.iprismtech.delivery_boy.ui.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iprismtech.delivery_boy.R;
import com.iprismtech.delivery_boy.app.constants.AppConstants;
import com.iprismtech.delivery_boy.app.controller.ApplicationController;
import com.iprismtech.delivery_boy.base.BaseAbstractActivity;
import com.iprismtech.delivery_boy.mvp.Contract.Activity.ItemsDroppedActivityContract;
import com.iprismtech.delivery_boy.mvp.Presenter.Activity.ItemsDroppedActivityImpl;
import com.iprismtech.delivery_boy.network.constants.NetworkConstants;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;
import com.iprismtech.delivery_boy.utils.GPSTracker;
import com.iprismtech.delivery_boy.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ItemsDroppedActivity extends BaseAbstractActivity<ItemsDroppedActivityImpl> implements ItemsDroppedActivityContract.IView, APIResponseCallback {
    ImageView imageView;
    LinearLayout linearLayout;
    ImageView im_map,iv_msg,iv_call;
    GPSTracker gpsTracker;
    TextView invoice_tv,adrs_tv,contact_tv,pincode_tv;
    String order_id,invoice_id,shop_name,lat,lng,contact_no,d_address,d_pincode;
    APIResponseCallback apiResponseCallback;
    private static final int REQUEST_CODE = 100;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_items_dropped_screen);
        presenter.start();

    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_items_dropped_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new ItemsDroppedActivityImpl(this, this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        gpsTracker = new GPSTracker(this);
        imageView = findViewById(R.id.dropped_back);
        linearLayout = findViewById(R.id.dropped_items_lay_out);
        invoice_tv=findViewById(R.id.invoice_tv);

        im_map = findViewById(R.id.im_map);
        iv_msg = findViewById(R.id.iv_msg);
        iv_call = findViewById(R.id.iv_call);
        adrs_tv = findViewById(R.id.adrs_tv);
        contact_tv = findViewById(R.id.contact_tv);
        pincode_tv = findViewById(R.id.pincode_tv);




        final Bundle bundle  =getIntent().getExtras();
        order_id =bundle.getString("order_id","");
        invoice_id =bundle.getString("invoice_id","");

        shop_name =bundle.getString("shop_name","");
        lat =bundle.getString("lat","");
        lng =bundle.getString("lng","");
        contact_no =bundle.getString("contact_no","");
        d_address =bundle.getString("d_address","");
        d_pincode =bundle.getString("d_pincode","");


        if(!bundle.isEmpty()){
            invoice_tv.setText(invoice_id);
            adrs_tv.setText(d_address);
            contact_tv.setText(contact_no);
            pincode_tv.setText(d_pincode);

        }

        iv_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phonecall();

            }
        });


        iv_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendsms();
            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemsdropedWBS();


            }
        });

        im_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?saddr=" + gpsTracker.getLatitude() + "," + gpsTracker.getLongitude() + "&daddr=" + lat + "," + lng));
                    context.startActivity(intent);
                } catch (ActivityNotFoundException ane) {


                } catch (Exception ex) {
                    ex.getMessage();
                }
            }
        });

    }

    private void sendsms() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + Uri.encode(contact_no)));
        startActivity(intent);
    }

    public void phonecall() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE);
        } else {

            new AlertDialog.Builder(context)
                    .setTitle("")
                    .setMessage("Do you want to make a call ?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contact_no));

                            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                return;
                            }
                            context.startActivity(intent);
                        }

                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();
        }
    }


    private void itemsdropedWBS() {

        Map<String, String> request = new HashMap<>();
        request.put("order_id",order_id );
        request.put("home_items_delivered", "yes");//routeID
        presenter.items_delivered(ItemsDroppedActivity.this, this, request);

    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {
            } else if (NetworkConstants.RequestCode.ITEMS_DELIVERD == requestId) {

                if (jsonObject.optBoolean("status") == true) {
                    Util.getInstance().cusToast(ItemsDroppedActivity.this, jsonObject.optString("message"));

                    Bundle bundle = new Bundle();
                    bundle.putString("order_id", order_id);
                    bundle.putString("invoice_id", invoice_id);
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_COMPLETEDORDER_SCREEN, bundle);


                } else {
                    Util.getInstance().cusToast(ItemsDroppedActivity.this, jsonObject.optString("message"));

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
