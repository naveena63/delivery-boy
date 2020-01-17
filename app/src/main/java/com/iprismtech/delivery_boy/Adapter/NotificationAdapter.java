package com.iprismtech.delivery_boy.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismtech.delivery_boy.Pojos.DropedsingleitemPOJO;
import com.iprismtech.delivery_boy.Pojos.NotificationsPOJO;
import com.iprismtech.delivery_boy.R;
import com.iprismtech.delivery_boy.dao.AdapterCallback;
import com.iprismtech.delivery_boy.dao.AdapterCallbackQuantity;
import com.iprismtech.delivery_boy.ui.Activity.NotificationActivity;
import com.iprismtech.delivery_boy.utils.GPSTracker;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

    Context context;
    LayoutInflater inflater;
    NotificationsPOJO notificationsPOJO;


    public NotificationAdapter(NotificationsPOJO notificationsPOJO, NotificationActivity notificationActivity) {

        this.context = notificationActivity;
        inflater = LayoutInflater.from(context);
        this.notificationsPOJO = notificationsPOJO; }

    @NonNull
    @Override
    public NotificationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_notification, viewGroup, false);
        return new NotificationAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull final NotificationAdapter.MyViewHolder holder, final int i) {
        holder.tv_msg.setText(notificationsPOJO.getResponse().get(i).getTitle());
        holder.tv_des.setText(notificationsPOJO.getResponse().get(i).getDescription());

    }

    @Override
    public int getItemCount() {
        return notificationsPOJO.getResponse().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_msg,tv_des;
        LinearLayout ll;

        public MyViewHolder(View view) {
            super(view);

            tv_msg = view.findViewById(R.id.tv_msg);
            tv_des = view.findViewById(R.id.tv_des);


        }
    }


}



