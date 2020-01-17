package com.iprismtech.delivery_boy.Adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
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
import com.iprismtech.delivery_boy.Pojos.DropedOrdersPOJO;
import com.iprismtech.delivery_boy.Pojos.DropedsingleitemPOJO;
import com.iprismtech.delivery_boy.Pojos.PickedItemsPOJO;
import com.iprismtech.delivery_boy.R;
import com.iprismtech.delivery_boy.dao.AdapterCallback;
import com.iprismtech.delivery_boy.dao.AdapterCallbackQuantity;
import com.iprismtech.delivery_boy.network.constants.NetworkConstants;
import com.iprismtech.delivery_boy.ui.Activity.PickedItemsActivity;
import com.iprismtech.delivery_boy.utils.GPSTracker;

public class CollectedItemAdapter extends RecyclerView.Adapter<CollectedItemAdapter.MyViewHolder> implements  AdapterCallback {

    Context context;
    LayoutInflater inflater;

    DropedsingleitemPOJO dropedsingleitemPOJO;
    AdapterCallback adapterCallback;
    AdapterCallbackQuantity adapterCallbackQuantity;
    GPSTracker gpsTracker;

    public CollectedItemAdapter(DropedsingleitemPOJO dropedsingleitemPOJO, PickedItemsActivity pickedItemsActivity, AdapterCallbackQuantity adapterCallbackQuantity) {




        this.context = pickedItemsActivity;
        inflater = LayoutInflater.from(context);
        this.dropedsingleitemPOJO = dropedsingleitemPOJO;
        this.adapterCallbackQuantity=adapterCallbackQuantity;
        adapterCallback = this;
        gpsTracker = new GPSTracker(context);


    }


    @NonNull
    @Override
    public CollectedItemAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_collect_data, viewGroup, false);

        return new CollectedItemAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull final CollectedItemAdapter.MyViewHolder holder, final int i) {

        SubDrpedItemsAdapter itemListDataAdapter = new SubDrpedItemsAdapter(context, dropedsingleitemPOJO.getResponse().get(i).getShop_list(),adapterCallback);
        holder.rv_sub_collected_item.setHasFixedSize(true);
        holder.rv_sub_collected_item.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        holder.rv_sub_collected_item.setAdapter(itemListDataAdapter);


        String shopname = dropedsingleitemPOJO.getResponse().get(i).getShop_name();



        final String lat = String.valueOf(dropedsingleitemPOJO.getResponse().get(i).getD_lat());
        final String lang = String.valueOf(dropedsingleitemPOJO.getResponse().get(i).getD_lng());
        String adress = dropedsingleitemPOJO.getResponse().get(i).getD_address();


        holder.send_img.setOnClickListener(new View.OnClickListener() {
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


        holder.tv_shopname.setText(shopname);

        Glide.with(context)
                .load(NetworkConstants.URL.Imagepath_URL + dropedsingleitemPOJO.getResponse().get(i).getShop_image())
                .into(holder.iv_shop);


    }

    @Override
    public int getItemCount() {
        return dropedsingleitemPOJO.getResponse().size();
    }

    @Override
    public void clickevent(CheckBox itemcheck, int i, String id, boolean isChecked) {
        if (adapterCallbackQuantity != null) {
            adapterCallbackQuantity.clickbox( itemcheck,i,id, isChecked);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView tv_shopname;
        LinearLayout ll;
        ImageView iv_shop,send_img;
        RecyclerView rv_sub_collected_item;

        public MyViewHolder(View view) {
            super(view);

            rv_sub_collected_item = view.findViewById(R.id.rv_sub_collected_item);
            tv_shopname = view.findViewById(R.id.tv_shopname);
            iv_shop = view.findViewById(R.id.iv_shop);
            send_img = view.findViewById(R.id.send_img);


        }
    }


}


