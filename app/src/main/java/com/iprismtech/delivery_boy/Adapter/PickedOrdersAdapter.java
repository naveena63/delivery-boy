package com.iprismtech.delivery_boy.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iprismtech.delivery_boy.Pojos.PickupOrdersPOJO;
import com.iprismtech.delivery_boy.R;
import com.iprismtech.delivery_boy.network.constants.NetworkConstants;

public class PickedOrdersAdapter extends RecyclerView.Adapter<PickedOrdersAdapter.MyViewHolder> {
    LayoutInflater inflater;
    Context context;
    PickupOrdersPOJO pickupOrdersPOJO;

    private OnitemClickListener mListner;

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }

    public PickedOrdersAdapter(PickupOrdersPOJO pickupOrdersPOJO, FragmentActivity activity) {
        this.context = activity;
        inflater = LayoutInflater.from(context);
        this.pickupOrdersPOJO = pickupOrdersPOJO;


    }



    @NonNull
    @Override
    public PickedOrdersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.picked_orders_item, viewGroup, false);
        return new PickedOrdersAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull PickedOrdersAdapter.MyViewHolder holder, int i) {

        String order_id = String.valueOf(pickupOrdersPOJO.getResponse().get(i).getInvoice_id());

        holder.orderid_tv.setText(order_id);
        String status = pickupOrdersPOJO.getResponse().get(i).getWarehouse_items_dropped();

        if(status.equalsIgnoreCase("yes")){

            holder.lay_out_start.setVisibility(View.GONE);
            holder.tick_iv.setVisibility(View.VISIBLE);


        }else{

            holder.lay_out_start.setVisibility(View.VISIBLE);
            holder.tick_iv.setVisibility(View.GONE);

        }



    }

    @Override
    public int getItemCount() {
        return pickupOrdersPOJO.getResponse().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        private ImageView tick_iv;
        private LinearLayout lay_out_start;
        private TextView orderid_tv;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            tick_iv = itemView.findViewById(R.id.tick_iv);
            lay_out_start = itemView.findViewById(R.id.lay_out_start);
            orderid_tv = itemView.findViewById(R.id.orderid_tv);

            lay_out_start.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (mListner != null) {
                mListner.onItemClick(view, getAdapterPosition());

            }
        }
    }
}
