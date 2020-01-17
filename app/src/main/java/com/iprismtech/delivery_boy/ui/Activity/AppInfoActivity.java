package com.iprismtech.delivery_boy.ui.Activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.iprismtech.delivery_boy.R;
import com.iprismtech.delivery_boy.base.BaseAbstractActivity;
import com.iprismtech.delivery_boy.mvp.Contract.Activity.AppInfoActivityContract;
import com.iprismtech.delivery_boy.mvp.Presenter.Activity.AppInfoActivityImpl;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;

public class AppInfoActivity extends BaseAbstractActivity<AppInfoActivityImpl> implements AppInfoActivityContract.IView, APIResponseCallback {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_app_info_screen);
        presenter.start();

    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_app_info_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new AppInfoActivityImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        imageView = findViewById(R.id.app_info_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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
