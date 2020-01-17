package com.iprismtech.delivery_boy.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.iprismtech.delivery_boy.R;
import com.iprismtech.delivery_boy.app.constants.AppConstants;
import com.iprismtech.delivery_boy.app.controller.ApplicationController;
import com.iprismtech.delivery_boy.sharepref.UserSession;
import com.iprismtech.delivery_boy.ui.Activity.FirstMainActivity;
import com.iprismtech.delivery_boy.ui.Activity.LoginActivity;
import com.iprismtech.delivery_boy.ui.Activity.NotificationActivity;
import com.iprismtech.delivery_boy.ui.Activity.SplashActivity;


import org.json.JSONObject;

import java.util.Random;


public class FCMMessagingService extends FirebaseMessagingService {

    private static final String TAG = "FCMMessagingService";
    JSONObject notificationResponse;
    private JSONObject messageJsonObject;
    private String showMessage;
    private String date;
    private String language;
    private UserSession userSession;
    private Integer nid;
    int m = 0;
    private NotificationChannel mChannel;
    private NotificationManager notifManager;

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //super.onMessageReceived(remoteMessage);
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getData().get("message"));
        /**
         * {"json":["Dear Customer, your Milk order has been modified by vendor for order dated: 26, May 2018"]}
         */
        String data = remoteMessage.getData().get("message");
        userSession = new UserSession(getApplicationContext());

       /* try {
            messageJsonObject = new JSONObject(data);
            String pushmessage = messageJsonObject.optJSONArray("json").get(0).toString();
            if (data != null) {
                sendNotification(pushmessage);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        if (remoteMessage == null)
            return;

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.e("Notification Body: ", remoteMessage.getNotification().getBody());
            //  Intent intent = new Intent(this, MainActivity.class);
            //  showNotificationMessage("social media", remoteMessage.getNotification().getBody(), intent);


        }

        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Data Payload: " + remoteMessage.getData());

            try {

                JSONObject object = new JSONObject(remoteMessage.getData());

//                String callNumber = object.getString("callNumber");
                JSONObject json = new JSONObject(object.toString());
                Log.e("json object", json.toString());
                System.out.println("dfkdfkjkjg" + json.toString());
                //  JSONObject json12 = new JSONObject(remoteMessage.getData());
                if (userSession.isUserLoggedIn()) {
                    String type = json.getString("type");

                    if(type.equalsIgnoreCase("drop_type")){

                        Intent intent = new Intent(this, FirstMainActivity.class);
                        intent.putExtra("comingfrm", "pushmsgdrop" );
                        showNotificationMessage(json.getString("title"), json.getString("message"), intent);

                    }else {
                        Intent intent = new Intent(this, FirstMainActivity.class);
                        intent.putExtra("comingfrm", "pushmsgpckup" );
                        showNotificationMessage(json.getString("title"), json.getString("message"), intent);

                    }

//                    Intent intent = new Intent(this, NotificationActivity.class);
//                    showNotificationMessage("title", json.getString("message"), intent);

                } else {
                    Intent intent = new Intent(this, LoginActivity.class);
                    showNotificationMessage("title", json.getString("message"), intent);
                }


            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.toString());
            }


        }

    }

    private void showNotificationMessage(String title, String message, Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
// For Oreo add channel
            if (notifManager == null) {
                notifManager = (NotificationManager) getSystemService
                        (Context.NOTIFICATION_SERVICE);
            }

            NotificationCompat.Builder builder;
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent;
            int importance = NotificationManager.IMPORTANCE_HIGH;
            if (mChannel == null) {
                mChannel = new NotificationChannel
                        ("0", title, importance);
                mChannel.setDescription(getString(R.string.app_name));
                mChannel.enableVibration(true);
                notifManager.createNotificationChannel(mChannel);
            }
            builder = new NotificationCompat.Builder(this, "0");

            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(this, 1251, intent, PendingIntent.FLAG_ONE_SHOT);
            builder.setContentTitle(title)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(title)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(message))
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);
            Notification notification = builder.build();
            notifManager.notify(m, notification);
        } else {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, m,
                    intent, PendingIntent.FLAG_ONE_SHOT);
            NotificationCompat.Builder notificationBuilder = new
                    NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(title)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setContentText(message)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(message))
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager =
                    (NotificationManager)
                            getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(m, notificationBuilder.build());
        }
    }

    private void sendNotification(String message) {
        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //UiAppConstant.isNotificationReceived = "yes";
        //send the unique number while getting notification.
        Random random = new Random(System.nanoTime() % 100000);
        int randomNotification = random.nextInt(1000000000);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher).setLargeIcon(largeIcon)
                .setContentTitle("Morning Basket")
                .setSubText("").
                        setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(message))
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(randomNotification, notificationBuilder.build());
    }
}