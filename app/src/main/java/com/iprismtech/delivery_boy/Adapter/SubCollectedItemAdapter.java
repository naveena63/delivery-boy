package com.iprismtech.delivery_boy.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismtech.delivery_boy.R;

public class SubCollectedItemAdapter extends RecyclerView.Adapter<SubCollectedItemAdapter.MyViewHolder> {

    Context context;

    public SubCollectedItemAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public SubCollectedItemAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_sub_collect_data, viewGroup, false);

        return new SubCollectedItemAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull final SubCollectedItemAdapter.MyViewHolder holder, final int i) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView tv_service_name;
        LinearLayout ll;

        public MyViewHolder(View view) {
            super(view);


        }
    }


}


