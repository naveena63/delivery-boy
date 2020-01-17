package com.iprismtech.delivery_boy.ui.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.iprismtech.delivery_boy.R;
import com.iprismtech.delivery_boy.app.constants.AppConstants;
import com.iprismtech.delivery_boy.app.controller.ApplicationController;
import com.iprismtech.delivery_boy.base.BaseAbstractActivity;
import com.iprismtech.delivery_boy.custom_fonts.CustomTextViewBold;
import com.iprismtech.delivery_boy.mvp.Contract.Activity.EmployeeIdActivityContract;
import com.iprismtech.delivery_boy.mvp.Presenter.Activity.EmployeeIdActivityImpl;
import com.iprismtech.delivery_boy.network.constants.NetworkConstants;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;
import com.iprismtech.delivery_boy.sharepref.UserSession;
import com.iprismtech.delivery_boy.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class EmployeeIdActivity extends BaseAbstractActivity<EmployeeIdActivityImpl> implements EmployeeIdActivityContract.IView, APIResponseCallback {
    LinearLayout employee_lay_out;
    RadioButton radioButton_one, radioButton_two;
    CustomTextViewBold customTextViewBold;
    EditText et_name, et_mobile, et_city;
    String str_name = "", str_mobile = "", str_city = "";
    APIResponseCallback apiResponseCallback;
    UserSession userSession;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_employee_id);
        presenter.start();

    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_employee_id, null);
        return view;
    }

    @Override
    public void setPresenter() {

        presenter = new EmployeeIdActivityImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        userSession = new UserSession(EmployeeIdActivity.this);

        apiResponseCallback = this;
        employee_lay_out = findViewById(R.id.employee_lay_out);
        radioButton_one = findViewById(R.id.radio_one);
        radioButton_two = findViewById(R.id.radio_two);
        et_name = findViewById(R.id.et_name);
        et_mobile = findViewById(R.id.et_mobile);
        et_city = findViewById(R.id.et_city);

        radioButton_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton_one.setChecked(true);
                radioButton_two.setChecked(false);
            }
        });
        radioButton_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton_one.setChecked(false);
                radioButton_two.setChecked(true);
            }
        });
        customTextViewBold = findViewById(R.id.login_here);
        customTextViewBold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployeeIdActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        employee_lay_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_name = et_name.getText().toString();
                str_mobile = et_mobile.getText().toString();
                str_city = et_city.getText().toString();

                if (str_name.isEmpty()) {
                    Toast.makeText(EmployeeIdActivity.this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
                } else if (str_mobile.isEmpty()) {
                    Toast.makeText(EmployeeIdActivity.this, "Please Enter Your Mobile Number", Toast.LENGTH_SHORT).show();
                } else if (str_mobile.length() < 10) {
                    Toast.makeText(EmployeeIdActivity.this, "Please Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
                } else if (str_city.isEmpty()) {
                    Toast.makeText(EmployeeIdActivity.this, "Please Enter City Name", Toast.LENGTH_SHORT).show();
                } else {

                    HashMap<String, String> requestBody = new HashMap<>();
                    requestBody.put("name", str_name);
                    requestBody.put("mobile", str_mobile);
                    requestBody.put("city_name", str_city);
                    presenter.agent_register(EmployeeIdActivity.this, apiResponseCallback, requestBody);

                }

            }
        });
    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        try {
            Log.d("registerResponse", responseString);
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equalsIgnoreCase("5000")) {
                Util.getInstance().cusToast(EmployeeIdActivity.this, getString(R.string.no_internet));

            } else if (jsonObject.optString("status_code").equals("5001")) {
                Util.getInstance().cusToast(EmployeeIdActivity.this, getString(R.string.no_data_found));

            } else if (NetworkConstants.RequestCode.agent_register == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");


                if (status) {

                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

                    String agentid = jsonObject.getJSONObject( "response" ).optString( "id" );
                    String name = jsonObject.getJSONObject( "response" ).optString( "name" );
                    String username = jsonObject.getJSONObject( "response" ).optString( "username" );
                    String mobile = jsonObject.getJSONObject( "response" ).optString( "mobile" );
                    String city_name = jsonObject.getJSONObject( "response" ).optString( "city_name" );
                    String user_type = jsonObject.getJSONObject( "response" ).optString( "user_type" );

                    userSession.isUserLoggedIn();
                    userSession.StoreUserDetails(agentid,name,username,mobile,city_name,user_type);

                    Bundle b = new Bundle();
                    b.putString("comingfrm","signup");
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_FRSTHOME_SCREEN,b);

                   /* Intent intent = new Intent(EmployeeIdActivity.this, FirstMainActivity.class);
                    startActivity(intent);*/
                } else {
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

                }

            } else {
                Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();

            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, getString(R.string.no_data_found1), Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }
}
