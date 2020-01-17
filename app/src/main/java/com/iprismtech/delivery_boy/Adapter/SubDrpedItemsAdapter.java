package com.iprismtech.delivery_boy.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismtech.delivery_boy.Pojos.DropedsingleitemPOJO;
import com.iprismtech.delivery_boy.Pojos.PickedItemsPOJO;
import com.iprismtech.delivery_boy.R;
import com.iprismtech.delivery_boy.dao.AdapterCallback;
import com.iprismtech.delivery_boy.sharepref.UserSession;
import com.iprismtech.delivery_boy.utils.Util;

import java.util.List;

public class SubDrpedItemsAdapter extends RecyclerView.Adapter<SubDrpedItemsAdapter.MyViewHolder>{

    String category;

    private Context context;
    UserSession userSession;
    List<DropedsingleitemPOJO.ResponseBean.ShopListBean> shop_list;
    LayoutInflater inflater;
    AdapterCallback adapterCallback;

    public SubDrpedItemsAdapter(Context context, List<DropedsingleitemPOJO.ResponseBean.ShopListBean> shop_list, AdapterCallback adapterCallback) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.shop_list = shop_list;
        this.adapterCallback = adapterCallback;


    }





    @NonNull
    @Override
    public SubDrpedItemsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sub_pick_data, viewGroup, false);
        return new SubDrpedItemsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final SubDrpedItemsAdapter.MyViewHolder holder, final int i) {
        final DropedsingleitemPOJO.ResponseBean.ShopListBean shopListBean = shop_list.get(i);



        holder.tv_quantity.setText(shop_list.get(i).getProduct_quantity());
        holder.tv_productname.setText(shop_list.get(i).getProduct_name());
        String item_status = shop_list.get(i).getHome_items_collected();

        if (item_status.equalsIgnoreCase("yes")) {
            holder.itemcheck.setChecked(true);

        } else {
            holder.itemcheck.setChecked(false);

        }


        holder.itemcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shopListBean.isClicked) {
                    holder.itemcheck.setChecked(true);

                } else {
                    holder.itemcheck.setChecked(false);
                }
                //    notifyDataSetChanged();

            }
        });






        holder.itemcheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String id = String.valueOf(shop_list.get(i).getOrders_details_id());
                // String order_id = String.valueOf(shop_list.get(i).getOrders_details_id());
                if (isChecked) {
                    shop_list.get(i).setClicked(true);
                }else {
                    shop_list.get(i).setClicked(false);
                }

                System.out.println("checked id" + id + "boolean" + isChecked);
                if (adapterCallback != null) {
                    adapterCallback.clickevent(holder.itemcheck, i, id, isChecked);
                } else {
                    Util.getInstance().cusToast(context, "nodata");

                }
                           }
        });

    }

    @Override
    public int getItemCount() {
        return shop_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_productname, tv_quantity;
        LinearLayout ll;
        CheckBox itemcheck;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            tv_productname = itemView.findViewById(R.id.tv_productname);
            tv_quantity = itemView.findViewById(R.id.tv_quantity);
            itemcheck = itemView.findViewById(R.id.itemcheck);

        }
    }
}
