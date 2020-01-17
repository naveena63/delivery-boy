package com.iprismtech.delivery_boy.Adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iprismtech.delivery_boy.Pojos.PickedItemsPOJO;
import com.iprismtech.delivery_boy.Pojos.PickupOrdersPOJO;
import com.iprismtech.delivery_boy.R;
import com.iprismtech.delivery_boy.dao.AdapterCallback;
import com.iprismtech.delivery_boy.dao.AdapterCallbackQuantity;
import com.iprismtech.delivery_boy.network.constants.NetworkConstants;
import com.iprismtech.delivery_boy.ui.Activity.CollectedItemsActivity;
import com.iprismtech.delivery_boy.ui.Activity.PickedItemsActivity;
import com.iprismtech.delivery_boy.utils.GPSTracker;

import java.util.ArrayList;

public class PickedItemsAdapter extends RecyclerView.Adapter<PickedItemsAdapter.MyViewHolder> implements  AdapterCallback {
    LayoutInflater inflater;
    Context context;
    PickedItemsPOJO pickedItemsPOJO;
    AdapterCallback adapterCallback;
    AdapterCallbackQuantity adapterCallbackQuantity;
    GPSTracker gpsTracker;



    public PickedItemsAdapter(PickedItemsPOJO pickedItemsPOJO, CollectedItemsActivity collectedItemsActivity, AdapterCallbackQuantity adapterCallbackQuantity) {

        this.context = collectedItemsActivity;
        inflater = LayoutInflater.from(context);
        this.pickedItemsPOJO = pickedItemsPOJO;
        this.adapterCallbackQuantity=adapterCallbackQuantity;
        adapterCallback = this;
        gpsTracker = new GPSTracker(context);



    }

    @NonNull
    @Override
    public PickedItemsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pick_data, viewGroup, false);
        return new PickedItemsAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull final PickedItemsAdapter.MyViewHolder holder, final int i) {


        SubPickedItemAdapter itemListDataAdapter = new SubPickedItemAdapter(context, pickedItemsPOJO.getResponse().get(i).getShop_list(),adapterCallback);
        holder.rv_sub_pick_item.setHasFixedSize(true);
        holder.rv_sub_pick_item.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        holder.rv_sub_pick_item.setAdapter(itemListDataAdapter);

        String shopname = pickedItemsPOJO.getResponse().get(i).getShop_name();
        final String lat = pickedItemsPOJO.getResponse().get(i).getLat();
        final String lang = pickedItemsPOJO.getResponse().get(i).getLng();
        String adress = pickedItemsPOJO.getResponse().get(i).getShop_locality();

        holder.tv_shop.setText(shopname);

        holder.iv_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?saddr=" + gpsTracker.getLatitude() + "," + gpsTracker.getLongitude() + "&daddr=" +lat + "," + lang));
                    context.startActivity(intent);
                } catch (ActivityNotFoundException ane) {


                } catch (Exception ex) {
                    ex.getMessage();
                }
            }
        });


        Glide.with(context)
                .load(NetworkConstants.URL.Imagepath_URL + pickedItemsPOJO.getResponse().get(i).getShop_image())
                .into(holder.iv_shop);
        System.out.println("Imagepath_URL"+NetworkConstants.URL.Imagepath_URL + pickedItemsPOJO.getResponse().get(i).getShop_image());

    }

    @Override
    public int getItemCount() {
        return pickedItemsPOJO.getResponse().size();
    }



    @Override
    public void clickevent(CheckBox itemcheck, int i, String id, boolean isChecked) {
        if (adapterCallbackQuantity != null) {
            adapterCallbackQuantity.clickbox( itemcheck,i,id, isChecked);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView tv_shop;
        ImageView iv_shop,iv_map;
        RecyclerView rv_sub_pick_item;

        public MyViewHolder(View view) {
            super(view);

            rv_sub_pick_item = view.findViewById(R.id.rv_sub_pick_item);
            tv_shop = view.findViewById(R.id.tv_shop);
            iv_shop = view.findViewById(R.id.iv_shop);
            iv_map = view.findViewById(R.id.iv_map);


        }
    }


}


