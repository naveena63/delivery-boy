package com.iprismtech.delivery_boy.dao;

import android.view.View;
import android.widget.CheckBox;

public interface AdapterCallbackQuantity {

   // void clickbox(View view,String id, boolean isChecked);

    void clickbox(CheckBox itemcheck, int i, String id, boolean isChecked);
}
