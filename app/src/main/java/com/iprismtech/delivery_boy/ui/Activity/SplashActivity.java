package com.iprismtech.delivery_boy.ui.Activity;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.iprismtech.delivery_boy.R;
import com.iprismtech.delivery_boy.mvp.Contract.Activity.SpalshActivityContract;
import com.iprismtech.delivery_boy.mvp.Presenter.Activity.SpalshActivityImpl;

public class SplashActivity extends BaseAbstractActivity<SpalshActivityImpl> implements SpalshActivityContract.IView  {
    public static SplashActivity splashActivity;
    String username,password;
    private static final int GRANT_LOC_ACCESS = 800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_splash);
        if (ActivityCompat.checkSelfPermission(SplashActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(SplashActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SplashActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    GRANT_LOC_ACCESS);
        }
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_splash, null);
        return view;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void setPresenter() {
        presenter = new SpalshActivityImpl(this, this);
        presenter.start();
        presenter.launchSplashScreen(SpalshActivityContract.IPermissionIds.FINISH_FLASH_SCREEN, null);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        splashActivity = this;


    }


}
