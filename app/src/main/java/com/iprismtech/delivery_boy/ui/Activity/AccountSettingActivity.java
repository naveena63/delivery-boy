package com.iprismtech.delivery_boy.ui.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.iprismtech.delivery_boy.R;
import com.iprismtech.delivery_boy.app.constants.AppConstants;
import com.iprismtech.delivery_boy.app.controller.ApplicationController;
import com.iprismtech.delivery_boy.base.BaseAbstractActivity;
import com.iprismtech.delivery_boy.mvp.Contract.Activity.AccountSettingActivityContract;
import com.iprismtech.delivery_boy.mvp.Presenter.Activity.AccountSettingActivityImpl;
import com.iprismtech.delivery_boy.network.constants.NetworkConstants;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;
import com.iprismtech.delivery_boy.sharepref.UserSession;
import com.iprismtech.delivery_boy.utils.Util;

import org.json.JSONObject;

import java.util.HashMap;

public class AccountSettingActivity extends BaseAbstractActivity<AccountSettingActivityImpl> implements AccountSettingActivityContract.IView, APIResponseCallback {

    ImageView imageView;
    LinearLayout ll_update;
    EditText et_delivery_boy, et_mobile_account, et_city_account;
    String str_delivery = "", str_mobile_account = "", str_city_account = "",str_emp_id;
    APIResponseCallback apiResponseCallback;
    UserSession userSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_account_setting_screen);
        presenter.start();

    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_account_setting_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new AccountSettingActivityImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        apiResponseCallback = this;

        userSession = new UserSession(this);
        str_emp_id = userSession.getUserDetails().get(UserSession.KEY_USERID);

        imageView = findViewById(R.id.setting_back);
        ll_update = findViewById(R.id.ll_update);
        et_delivery_boy = findViewById(R.id.et_delivery_boy);
        et_mobile_account = findViewById(R.id.et_mobile_account);
        et_city_account = findViewById(R.id.et_city_account);

        getagentprofile();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        et_mobile_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AccountSettingActivity.this, "You Cant able to edit Mobilenumber", Toast.LENGTH_SHORT).show();

            }
        });


        ll_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_delivery = et_delivery_boy.getText().toString();
                str_city_account = et_city_account.getText().toString();
                str_mobile_account = et_mobile_account.getText().toString();
                
                if (str_delivery.isEmpty()){
                    Toast.makeText(AccountSettingActivity.this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
                }else if (str_city_account.isEmpty()){
                    Toast.makeText(AccountSettingActivity.this, "Please Enter Your City Name", Toast.LENGTH_SHORT).show();
                }else {

                    HashMap<String, String> requestBody = new HashMap<>();
                    requestBody.put("user_id", str_emp_id);
                    requestBody.put("name", str_delivery);
                    requestBody.put("city_name", str_mobile_account);
                    requestBody.put("profile_image", "");
                    presenter.agent_update_profile(AccountSettingActivity.this, apiResponseCallback, requestBody);


                   /* Intent intent = new Intent(AccountSettingActivity.this, FirstMainActivity.class);
                    startActivity(intent);*/
                }
            }
        });
    }

    private void getagentprofile() {
        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("user_id", str_emp_id);
        presenter.agent_profile(AccountSettingActivity.this, apiResponseCallback, requestBody);




    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {


        try {
            if (NetworkConstants.RequestCode.AGENT_PROFILE == requestId) {
                JSONObject resjsonObject = new JSONObject( responseString );

                if (resjsonObject.optString( "status_code" ).equals( "5000" )) {
                    Util.getInstance().cusToast( context, resjsonObject.optString( "message" ) );
                } else {
                    if (resjsonObject.optString( "status" ).equals( "true" )) {
                        Util.getInstance().cusToast( context, resjsonObject.optString( "message" ) );
                        String agentid = resjsonObject.getJSONObject( "response" ).optString( "id" );
                        String name = resjsonObject.getJSONObject( "response" ).optString( "name" );
                        String username = resjsonObject.getJSONObject( "response" ).optString( "username" );
                        String mobile = resjsonObject.getJSONObject( "response" ).optString( "mobile" );
                        String city_name = resjsonObject.getJSONObject( "response" ).optString( "city_name" );
                        String user_type = resjsonObject.getJSONObject( "response" ).optString( "user_type" );
                        String profile_image = resjsonObject.getJSONObject( "response" ).optString( "profile_image" );


                        if(name.isEmpty()){
                            et_delivery_boy.setText("");
                        }else{
                            et_delivery_boy.setText(name);

                        }

                        if(mobile.isEmpty()){
                            et_mobile_account.setText("");
                        }else{
                            et_mobile_account.setText(mobile);

                        }

                        if(city_name.isEmpty()){
                            et_city_account.setText("");
                        }else{
                            et_city_account.setText(city_name);

                        }

                      /*  userSession.isUserLoggedIn();
                        userSession.StoreUserDetails(agentid,name,username,mobile,city_name,user_type);
                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_FRSTHOME_SCREEN);*/



                    } else {
                        Util.getInstance().cusToast( context, resjsonObject.optString( "message" ) );
                    }
                }
            }else if(NetworkConstants.RequestCode.UPDATE_PROFILE == requestId){
                JSONObject resjsonObject = new JSONObject( responseString );
                if (resjsonObject.optString( "status_code" ).equals( "5000" )) {
                    Util.getInstance().cusToast( context, resjsonObject.optString( "message" ) );
                } else {
                    if (resjsonObject.optString( "status" ).equals( "true" )) {
                        Util.getInstance().cusToast( context, resjsonObject.optString( "message" ) );
                        String agentid = resjsonObject.getJSONObject( "response" ).optString( "id" );
                        String name = resjsonObject.getJSONObject( "response" ).optString( "name" );
                        String username = resjsonObject.getJSONObject( "response" ).optString( "username" );
                        String mobile = resjsonObject.getJSONObject( "response" ).optString( "mobile" );
                        String city_name = resjsonObject.getJSONObject( "response" ).optString( "city_name" );
                        String user_type = resjsonObject.getJSONObject( "response" ).optString( "user_type" );
                        String profile_image = resjsonObject.getJSONObject( "response" ).optString( "profile_image" );

                        userSession.isUserLoggedIn();
                        userSession.StoreUserDetails(agentid,name,username,mobile,city_name,user_type);
                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_FRSTHOME_SCREEN);



                    } else {
                        Util.getInstance().cusToast( context, resjsonObject.optString( "message" ) );
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }
}
