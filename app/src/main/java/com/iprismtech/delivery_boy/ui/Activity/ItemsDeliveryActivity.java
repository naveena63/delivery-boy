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
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismtech.delivery_boy.Adapter.PickedItemsAdapter;
import com.iprismtech.delivery_boy.Pojos.PickedItemsPOJO;
import com.iprismtech.delivery_boy.R;
import com.iprismtech.delivery_boy.app.constants.AppConstants;
import com.iprismtech.delivery_boy.app.controller.ApplicationController;
import com.iprismtech.delivery_boy.base.BaseAbstractActivity;
import com.iprismtech.delivery_boy.mvp.Contract.Activity.ItemsDeliveryActivityContract;
import com.iprismtech.delivery_boy.mvp.Presenter.Activity.ItemsDeliveryActivityImpl;
import com.iprismtech.delivery_boy.network.constants.NetworkConstants;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;
import com.iprismtech.delivery_boy.utils.GPSTracker;
import com.iprismtech.delivery_boy.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ItemsDeliveryActivity extends BaseAbstractActivity<ItemsDeliveryActivityImpl> implements ItemsDeliveryActivityContract.IView, APIResponseCallback {
    ImageView imageView;
    LinearLayout linearLayout;
    ImageView im_map;
    GPSTracker gpsTracker;
    ImageView im_call, im_sms;
    APIResponseCallback apiResponseCallback;
    String order_id,invoice_id,shop_name,lat,lng,shop_locality,shop_mobile,shoppincode;
    TextView invoice_tv,contact_tv,shop_pincode,shop_adrs,tv_shopname;
    private static final int REQUEST_CODE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_items_delivery_screen);
        presenter.start();


    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_items_delivery_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new ItemsDeliveryActivityImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        apiResponseCallback = this;
        gpsTracker = new GPSTracker(this);

        final Bundle bundle  =getIntent().getExtras();
        order_id =bundle.getString("order_id","");
        invoice_id =bundle.getString("invoice_id","");

        shop_name =bundle.getString("shop_name","");
        lat =bundle.getString("lat","");
        lng =bundle.getString("lng","");
        shop_locality =bundle.getString("shop_locality","");
        shop_mobile =bundle.getString("shop_mobile","");
        shoppincode =bundle.getString("shop_pincode","");












        imageView = findViewById(R.id.delivered_back);
        linearLayout = findViewById(R.id.items_delivered_lay_out);
        im_map = findViewById(R.id.im_map);
        im_call = findViewById(R.id.im_call);
        im_sms = findViewById(R.id.im_sms);
        invoice_tv = findViewById(R.id.invoice_tv);

        contact_tv = findViewById(R.id.contact_tv);
        shop_pincode = findViewById(R.id.shop_pincode);
        shop_adrs = findViewById(R.id.shop_adrs);
        tv_shopname = findViewById(R.id.shop_name);


        if(!bundle.isEmpty()){
            invoice_tv.setText(invoice_id);
            contact_tv.setText(shop_mobile);
            shop_pincode.setText(shoppincode);
            shop_adrs.setText(shop_locality);
            tv_shopname.setText(shop_name);
        }

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
                            Uri.parse("http://maps.google.com/maps?saddr=" + gpsTracker.getLatitude() + "," + gpsTracker.getLongitude() + "&daddr=" +lat + "," + lng));
                    context.startActivity(intent);
                } catch (ActivityNotFoundException ane) {


                } catch (Exception ex) {
                    ex.getMessage();
                }
            }
        });

       /* im_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0123456789"));
                startActivity(intent);
            }
        });

        im_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri sms_uri = Uri.parse("smsto:+0123456789");
                Intent sms_intent = new Intent(Intent.ACTION_SENDTO, sms_uri);
                //sms_intent.putExtra("sms_body", "Good Morning ! how r U ?");
                startActivity(sms_intent);
            }
        });*/


        im_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phonecall();

            }
        });


        im_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendsms();
            }
        });






    }

    private void sendsms() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + Uri.encode(shop_mobile)));
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
                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + shop_mobile));

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
        request.put("warehouse_items_dropped", "yes");//routeID
        presenter.warehouse_items_dropped(ItemsDeliveryActivity.this, this, request);

    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {


        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {
            } else if (NetworkConstants.RequestCode.AGENT_WHOUSE_ITEMS_DROPED == requestId) {

                if (jsonObject.optBoolean("status") == true) {
                    Util.getInstance().cusToast(ItemsDeliveryActivity.this, jsonObject.optString("message"));

                    Bundle bundle = new Bundle();
                    bundle.putString("order_id", order_id);
                    bundle.putString("invoice_id", invoice_id);
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_COMPLETEDORDER_SCREEN, bundle);


                } else {
                    Util.getInstance().cusToast(ItemsDeliveryActivity.this, jsonObject.optString("message"));

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
