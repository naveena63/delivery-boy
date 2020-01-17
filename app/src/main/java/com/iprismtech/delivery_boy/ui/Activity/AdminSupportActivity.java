package com.iprismtech.delivery_boy.ui.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
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
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.iprismtech.delivery_boy.R;
import com.iprismtech.delivery_boy.app.constants.AppConstants;
import com.iprismtech.delivery_boy.app.controller.ApplicationController;
import com.iprismtech.delivery_boy.base.BaseAbstractActivity;
import com.iprismtech.delivery_boy.mvp.Contract.Activity.AdminSupportActivityContract;
import com.iprismtech.delivery_boy.mvp.Presenter.Activity.AdminSupportActivityImpl;
import com.iprismtech.delivery_boy.network.constants.NetworkConstants;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;
import com.iprismtech.delivery_boy.sharepref.UserSession;
import com.iprismtech.delivery_boy.utils.Util;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.regex.Pattern;

public class AdminSupportActivity extends BaseAbstractActivity<AdminSupportActivityImpl> implements AdminSupportActivityContract.IView, APIResponseCallback {

    ImageView imageView;
    LinearLayout ll_submit,ll_email,ll_call;
    EditText et_admin_name, et_email, et_message;
    String str_admin_name = "", str_email = "", str_message = "",str_emp_id;
    public String emailpatten = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    APIResponseCallback apiResponseCallback;
    UserSession userSession;
    private static final int REQUEST_CODE = 100;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_admin_support_screen);
        presenter.start();

    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_admin_support_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new AdminSupportActivityImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        apiResponseCallback = this;

        userSession = new UserSession(this);
        str_emp_id = userSession.getUserDetails().get(UserSession.KEY_USERID);

        imageView = findViewById(R.id.admin_back_img);
        ll_submit = findViewById(R.id.ll_submit);
        et_admin_name = findViewById(R.id.et_admin_name);
        et_email = findViewById(R.id.et_email);
        et_message = findViewById(R.id.et_message);
        ll_email = findViewById(R.id.ll_email);
        ll_call = findViewById(R.id.ll_call);


        ll_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phonecall();

            }
        });


        ll_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               sendemail();
            }
        });





        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ll_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_admin_name = et_admin_name.getText().toString();
                str_email = et_email.getText().toString();
                str_message = et_message.getText().toString();

                if (str_admin_name.isEmpty()) {
                    Toast.makeText(AdminSupportActivity.this, "Please Enter Admin Name", Toast.LENGTH_SHORT).show();
                } else if (str_email.isEmpty()) {
                    Toast.makeText(AdminSupportActivity.this, "Please Enter Your Email Id", Toast.LENGTH_SHORT).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(str_email).matches()) {
                    Toast.makeText(AdminSupportActivity.this, "Your Email Id is not valid", Toast.LENGTH_SHORT).show();
                } else if (!str_email.matches(emailpatten) || str_email.contains("..com") || str_email.contains(".com.com")) {
                    Toast.makeText(AdminSupportActivity.this, "Your Email Id is not valid", Toast.LENGTH_SHORT).show();
                } else if (str_message.isEmpty()) {
                    Toast.makeText(AdminSupportActivity.this, "Please Enter Your Issue", Toast.LENGTH_SHORT).show();
                } else {
                    getsupportWBS();



                }
            }
        });
    }

    private void sendemail() {
        Intent intent=new Intent(Intent.ACTION_SEND);
        String[] recipients={"mailto@gmail.com"};
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.setType("text/html");
        intent.setPackage("com.google.android.gm");
        startActivity(Intent.createChooser(intent, "Send mail"));


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
                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "1234567890"));

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

    private void getsupportWBS() {
        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("user_id", str_emp_id);
        requestBody.put("name", str_admin_name);
        requestBody.put("email_id", str_email);
        requestBody.put("description", str_message);
        presenter.customer_support(AdminSupportActivity.this, apiResponseCallback, requestBody);

    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

        try {
            if (NetworkConstants.RequestCode.CUSTOMER_SERVICE == requestId) {
                JSONObject resjsonObject = new JSONObject( responseString );

                if (resjsonObject.optString( "status_code" ).equals( "5000" )) {
                    Util.getInstance().cusToast( context, resjsonObject.optString( "message" ) );
                } else {
                    if (resjsonObject.optString( "status" ).equals( "true" )) {
                        Util.getInstance().cusToast( context, resjsonObject.optString( "message" ) );

                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_FRSTHOME_SCREEN);



                       /* String agentid = resjsonObject.getJSONObject( "response" ).optString( "id" );
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
*/
                      /*  userSession.isUserLoggedIn();
                        userSession.StoreUserDetails(agentid,name,username,mobile,city_name,user_type);
                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_FRSTHOME_SCREEN);*/



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
