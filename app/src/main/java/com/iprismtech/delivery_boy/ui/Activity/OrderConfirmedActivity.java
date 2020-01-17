package com.iprismtech.delivery_boy.ui.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.iprismtech.delivery_boy.R;
import com.iprismtech.delivery_boy.base.BaseAbstractActivity;
import com.iprismtech.delivery_boy.mvp.Contract.Activity.OrderConfirmedActivityContract;
import com.iprismtech.delivery_boy.mvp.Presenter.Activity.OrderConfirmedActivityImpl;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;

public class OrderConfirmedActivity extends BaseAbstractActivity<OrderConfirmedActivityImpl> implements OrderConfirmedActivityContract.IView, APIResponseCallback {
    ImageView imageView;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_order_confirmed);
        presenter.start();

    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_order_confirmed, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new OrderConfirmedActivityImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        imageView = findViewById(R.id.order_back);
        linearLayout = findViewById(R.id.drop_lay_out);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderConfirmedActivity.this, FirstMainActivity.class);
                startActivity(intent);
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
