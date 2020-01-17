package com.iprismtech.delivery_boy.ui.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismtech.delivery_boy.R;
import com.iprismtech.delivery_boy.app.constants.AppConstants;
import com.iprismtech.delivery_boy.app.controller.ApplicationController;
import com.iprismtech.delivery_boy.base.BaseAbstractActivity;
import com.iprismtech.delivery_boy.mvp.Contract.Activity.CompletedOrderActivityContract;
import com.iprismtech.delivery_boy.mvp.Presenter.Activity.CompletedOrderActivityImpl;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;
import com.iprismtech.delivery_boy.utils.Util;

public class CompletedOrderActivity extends BaseAbstractActivity<CompletedOrderActivityImpl> implements CompletedOrderActivityContract.IView, APIResponseCallback {

    ImageView imageView;
    LinearLayout linearLayout;
    String order_id,invoice_id;
    TextView tv_order_id,toolbar_invoice_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_completed_order);
        presenter.start();

    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_completed_order, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new CompletedOrderActivityImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();


        final Bundle bundle  =getIntent().getExtras();
        order_id =bundle.getString("order_id","");
        invoice_id =bundle.getString("invoice_id","");



        imageView = findViewById(R.id.completed_back);
        linearLayout = findViewById(R.id.go_to_home_lay_out);
        tv_order_id = findViewById(R.id.tv_order_id);
        toolbar_invoice_id=findViewById(R.id.toolbar_invoice_id);

        if(!bundle.isEmpty()){
            tv_order_id.setText(invoice_id);
            toolbar_invoice_id.setText(invoice_id);
        }else{
            Util.getInstance().cusToast(CompletedOrderActivity.this, "No orderId");

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
               /* Intent intent = new Intent(CompletedOrderActivity.this, FirstMainActivity.class);
                startActivity(intent);
*/
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_FRSTHOME_SCREEN);

            }
        });
    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }
}
