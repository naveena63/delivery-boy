package com.iprismtech.delivery_boy.ui.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.iprismtech.delivery_boy.app.constants.AppConstants;
import com.iprismtech.delivery_boy.app.controller.ApplicationController;
import com.iprismtech.delivery_boy.sharepref.UserSession;
import com.iprismtech.delivery_boy.ui.Fragment.PickUpFragment;
import com.iprismtech.delivery_boy.R;
import com.iprismtech.delivery_boy.base.BaseAbstractActivity;
import com.iprismtech.delivery_boy.custom_fonts.CustomTextViewsemibold;
import com.iprismtech.delivery_boy.mvp.Contract.Activity.MainActivityContract;
import com.iprismtech.delivery_boy.mvp.Presenter.Activity.MainActivityImpl;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;
import com.iprismtech.delivery_boy.ui.Fragment.DropOrderFragment;

import java.util.HashMap;
import java.util.Map;

public class FirstMainActivity extends BaseAbstractActivity<MainActivityImpl> implements MainActivityContract.IView, APIResponseCallback {
    ImageView imageView_one, imageView_two;
    DrawerLayout drawerLayout;
    private ImageView menu;
    Switch aSwitch;
    ImageView imageView, navigation_home_back_but;
    LinearLayout linearLayout_one, linearLayout_two, linearLayout_three, linearLayout_four, linearLayout_five;
    CustomTextViewsemibold picked_order, textViewsemibold_two;
    UserSession userSession;
    String str_emp_id,comingfrm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main_home_screen);
        presenter.start();
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_main_home_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new MainActivityImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        userSession = new UserSession(this);
        str_emp_id=userSession.getUserDetails().get(UserSession.KEY_USERID);



        final Bundle bundle  =getIntent().getExtras();
        comingfrm =bundle.getString("comingfrm","");


        if(comingfrm.equalsIgnoreCase("splash")){
            load_fragment_one();


        }else if(comingfrm.equalsIgnoreCase("signup")){
            load_fragment_one();


        }else if(comingfrm.equalsIgnoreCase("login")){
            load_fragment_one();


        } else if(comingfrm.equalsIgnoreCase("pushmsgpckup")){
            load_fragment_one();

        }else if(comingfrm.equalsIgnoreCase("pushmsgdrop")){
            load_fragment_two();
        }else{
            load_fragment_one();

        }


    menu = findViewById(R.id.menu);
        // aSwitch=findViewById(R.id.s);
        drawerLayout = findViewById(R.id.drawer_lay_out);
        //imageView_one = findViewById(R.id.navigation_home_back_but);
        imageView_two = findViewById(R.id.notification_but);
        picked_order = findViewById(R.id.picked_order);
        textViewsemibold_two = findViewById(R.id.drop_order);
        linearLayout_one = findViewById(R.id.navigation_home);
        linearLayout_two = findViewById(R.id.navigation_account);
        linearLayout_three = findViewById(R.id.navigation_app_info);
        linearLayout_four = findViewById(R.id.navigation_admin);
        linearLayout_five = findViewById(R.id.navigation_log_out);
        imageView = findViewById(R.id.navigation_back_but);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(FirstMainActivity.this, FirstMainActivity.class);
                startActivity(intent);*/
                drawerLayout.closeDrawers();
            }
        });
        linearLayout_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
                Intent intent = new Intent(FirstMainActivity.this, FirstMainActivity.class);
                startActivity(intent);
            }
        });
        linearLayout_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
                Intent intent = new Intent(FirstMainActivity.this, AccountSettingActivity.class);
                startActivity(intent);
            }
        });
        linearLayout_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
                Intent intent = new Intent(FirstMainActivity.this, AppInfoActivity.class);
                startActivity(intent);
            }
        });
        linearLayout_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
                Intent intent = new Intent(FirstMainActivity.this, AdminSupportActivity.class);
                startActivity(intent);
            }
        });




        linearLayout_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("Are you sure, You want to Logout?");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                userSession.logoutUser();
                                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_LOGIN_SCREEN);
                                finishAffinity();

                            }
                        });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                drawerLayout.closeDrawer(Gravity.LEFT);
            }
        });





      /*  menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                } else {
                    drawerLayout.isDrawerOpen(Gravity.LEFT);
                }
            }
        });*/

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                } else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
            }
        });
        imageView_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstMainActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });
        picked_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picked_order.setTextColor(Color.WHITE);
                textViewsemibold_two.setTextColor(getApplication().getColor(R.color.drop_color));
                load_fragment_one();
            }
        });
        textViewsemibold_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewsemibold_two.setTextColor(getApplication().getColor(R.color.white));
                picked_order.setTextColor(getApplication().getColor(R.color.drop_color));
                load_fragment_two();
            }
        });
    }

    private void load_fragment_two() {
        DropOrderFragment drop_order_frag = new DropOrderFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frag, drop_order_frag);
        transaction.commit();
    }

    private void load_fragment_one() {
        PickUpFragment pick_up_frag = new PickUpFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frag, pick_up_frag);
        transaction.commit();


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

    @Override
    public void onBackPressed() {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(FirstMainActivity.this, R.style.AlertDialog);
        builder.setTitle(R.string.app_name);
        // builder.setIcon(R.mipmap.appicon);
        builder.setMessage("Do you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // finish();


                        finishAffinity();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        // AlertDialog alert = builder.create();
        builder.show();

    }

}
