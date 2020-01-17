package com.iprismtech.delivery_boy.network.listener;

/**
 * Created by prasad on 05/07/2017.
 */
public interface IDialogCallback {

    public void onPositiveButtonPress(int requestCode);

    public void onNegativeButtonPress(int requestCode);
}
