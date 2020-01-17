package com.iprismtech.delivery_boy.ui.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.iprismtech.delivery_boy.R;
import com.iprismtech.delivery_boy.app.constants.AppConstants;
import com.iprismtech.delivery_boy.app.controller.ApplicationController;
import com.iprismtech.delivery_boy.base.BaseAbstractActivity;
import com.iprismtech.delivery_boy.custom_fonts.CustomTextViewBold;
import com.iprismtech.delivery_boy.mvp.Contract.Activity.LoginActivityContract;
import com.iprismtech.delivery_boy.mvp.Presenter.Activity.LoginActivityImpl;
import com.iprismtech.delivery_boy.network.constants.NetworkConstants;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;
import com.iprismtech.delivery_boy.sharepref.UserSession;
import com.iprismtech.delivery_boy.utils.Util;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseAbstractActivity<LoginActivityImpl> implements LoginActivityContract.IView, APIResponseCallback {
    CustomTextViewBold customTextViewBold;
    LinearLayout ll_login;
    EditText et_emoployee_id, et_password;
    String str_emp_id = "", str_password = "",agentid="", refreshedToken;
    APIResponseCallback apiResponseCallback;

    UserSession userSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_login_screen);
        presenter.start();

    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_login_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new LoginActivityImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        apiResponseCallback = this;
        userSession = new UserSession(LoginActivity.this);


        ll_login = findViewById(R.id.ll_login);
        customTextViewBold = findViewById(R.id.apply_here);
        et_password = findViewById(R.id.et_password);
        et_emoployee_id = findViewById(R.id.et_emoployee_id);

        refreshedToken = FirebaseInstanceId.getInstance().getToken();






        ll_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_emp_id = et_emoployee_id.getText().toString();
                str_password = et_password.getText().toString();

                if (str_emp_id.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please Enter Employee Id", Toast.LENGTH_SHORT).show();
                } else if (str_password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                } else if (str_password.length() < 6) {
                    Toast.makeText(LoginActivity.this, "Please Enter minimum 6 digits", Toast.LENGTH_SHORT).show();
                } else {
                       loginWBS();



                    /*Intent intent = new Intent(LoginActivity.this, FirstMainActivity.class);
                    startActivity(intent);*/
                }

            }
        });
        customTextViewBold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, EmployeeIdActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updatedevicetoken() {

        Map<String, String> request = new HashMap<>();
        request.put("user_id", agentid);
        request.put("token", refreshedToken);//routeID
        presenter.update_device_token(LoginActivity.this, this, request);

    }

    private void loginWBS() {

        Map<String, String> request = new HashMap<>();
        request.put("employee_id", str_emp_id);
        request.put("password", str_password);//routeID
        presenter.agent_login(LoginActivity.this, this, request);
    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {


    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

        try {
            if (NetworkConstants.RequestCode.AGENT_LOGIN == requestId) {
                JSONObject resjsonObject = new JSONObject( responseString );

                if (resjsonObject.optString( "status_code" ).equals( "5000" )) {
                    Util.getInstance().cusToast( context, resjsonObject.optString( "message" ) );
                } else {
                    if (resjsonObject.optString( "status" ).equals( "true" )) {
                        Util.getInstance().cusToast( context, resjsonObject.optString( "message" ) );
                         agentid = resjsonObject.getJSONObject( "response" ).optString( "id" );
                        String name = resjsonObject.getJSONObject( "response" ).optString( "name" );
                        String username = resjsonObject.getJSONObject( "response" ).optString( "username" );
                        String mobile = resjsonObject.getJSONObject( "response" ).optString( "mobile" );
                        String city_name = resjsonObject.getJSONObject( "response" ).optString( "city_name" );
                        String user_type = resjsonObject.getJSONObject( "response" ).optString( "user_type" );


                        userSession.isUserLoggedIn();
                        userSession.StoreUserDetails(agentid,name,username,mobile,city_name,user_type);

                        updatedevicetoken();
                        Bundle b = new Bundle();
                        b.putString("comingfrm","login");
                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_FRSTHOME_SCREEN,b);



                    } else {
                        Util.getInstance().cusToast( context, resjsonObject.optString( "message" ) );
                    }
                }
            }else if(NetworkConstants.RequestCode.AGENT_LOGIN == requestId){

                JSONObject resjsonObject = new JSONObject( responseString );

                if (resjsonObject.optString( "status_code" ).equals( "5000" )) {
                    Util.getInstance().cusToast( context, resjsonObject.optString( "message" ) );
                } else {
                    if (resjsonObject.optString( "status" ).equals( "true" )) {
                        Util.getInstance().cusToast( context, resjsonObject.optString( "message" ) );


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
