package com.iprismtech.delivery_boy.dao;

import android.view.View;
import android.widget.CheckBox;

public interface AdapterCallback {

   // void onClickCallback(View view, int position);

    //void clickevent(String id, boolean isChecked);

    void clickevent(CheckBox itemcheck, int i, String id, boolean isChecked);
}
