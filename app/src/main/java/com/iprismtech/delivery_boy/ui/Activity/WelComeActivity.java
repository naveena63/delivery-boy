package com.iprismtech.delivery_boy.ui.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.iprismtech.delivery_boy.R;
import com.iprismtech.delivery_boy.base.BaseAbstractActivity;
import com.iprismtech.delivery_boy.custom_fonts.CustomTextViewBold;
import com.iprismtech.delivery_boy.mvp.Contract.Activity.WelComeActivityContract;
import com.iprismtech.delivery_boy.mvp.Presenter.Activity.WelComeActivityImpl;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;

public class WelComeActivity extends BaseAbstractActivity<WelComeActivityImpl> implements WelComeActivityContract.IView, APIResponseCallback {
    CustomTextViewBold customTextViewBold;
    LinearLayout linearLayout;
    private static final int REQUEST_LOCATION_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_wel_come_screen);
        presenter.start();

    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_wel_come_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new WelComeActivityImpl(this, this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();


        linearLayout = findViewById(R.id.welcome_lay_out);
        customTextViewBold = findViewById(R.id.login_welcome_screen);

        checkpermission();
        customTextViewBold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelComeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelComeActivity.this, EmployeeIdActivity.class);
                startActivity(intent);
            }
        });
    }

    public void checkpermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


           /* if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(
                        LoginActivity.this,
                        new String[]{
                                Manifest.permission.ACCESS_FINE_LOCATION

                        },
                        REQUEST_LOCATION_PERMISSION
                );
            }*/

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION

                    },
                    REQUEST_LOCATION_PERMISSION
            );

        } else {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION: {

                // When request is cancelled, the results array are empty
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permissions are granted

                    // cameraIntent();


                    // Toast.makeText(LoginActivity.this, "Permissions granted.", Toast.LENGTH_SHORT).show();
                } else {
                    // Permissions are denied
                    checkpermission();
                    //Toast.makeText(LoginActivity.this, "Permissions denied.", Toast.LENGTH_SHORT).show();
                }
                return;
            }

        }
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
