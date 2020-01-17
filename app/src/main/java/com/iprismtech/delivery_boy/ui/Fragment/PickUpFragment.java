package com.iprismtech.delivery_boy.ui.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.delivery_boy.Adapter.PickedItemsAdapter;
import com.iprismtech.delivery_boy.Adapter.PickedOrdersAdapter;
import com.iprismtech.delivery_boy.Pojos.PickupOrdersPOJO;
import com.iprismtech.delivery_boy.app.constants.AppConstants;
import com.iprismtech.delivery_boy.app.controller.ApplicationController;
import com.iprismtech.delivery_boy.network.constants.NetworkConstants;
import com.iprismtech.delivery_boy.sharepref.UserSession;
import com.iprismtech.delivery_boy.ui.Activity.PickedItemsActivity;
import com.iprismtech.delivery_boy.R;
import com.iprismtech.delivery_boy.mvp.Contract.Fragment.PickUpFragmentContract;
import com.iprismtech.delivery_boy.mvp.Presenter.Fragment.PickUpFragmentImpl;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;
import com.iprismtech.delivery_boy.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class PickUpFragment extends BaseAbstractFragment<PickUpFragmentImpl> implements PickUpFragmentContract.IView, APIResponseCallback {

    LinearLayout linearLayout;
    UserSession userSession;
    String str_emp_id,order_id,invoice_id;
    PickupOrdersPOJO pickupOrdersPOJO;
    PickedOrdersAdapter pickedOrdersAdapter;
    RecyclerView pickorders_recyclerview;
    APIResponseCallback apiResponseCallback;
    TextView no_datatv;


    public PickUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
    }

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_pick_up_frag, null);
        return view;
    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();
        apiResponseCallback = this;

        no_datatv = view.findViewById(R.id.no_datatv);
        userSession = new UserSession(getActivity());
        str_emp_id = userSession.getUserDetails().get(UserSession.KEY_USERID);

        pickorders_recyclerview = view.findViewById(R.id.pickorders_recyclerview);

        pickupordfersWBS();



    }

    private void pickupordfersWBS() {
        Map<String, String> request = new HashMap<>();
        request.put("agent_id", str_emp_id);
        presenter.agent_pickup_orders(getActivity(), this, request);


    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {

            } else if (NetworkConstants.RequestCode.AGENT_PICKUP_ORDERS == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    Util.getInstance().cusToast(getActivity(), jsonObject.optString("message"));
                    pickupOrdersPOJO = new Gson().fromJson(responseString, PickupOrdersPOJO.class);

                    if (pickupOrdersPOJO.getResponse().size()>0) {
                        no_datatv.setVisibility(View.GONE);
                        pickorders_recyclerview.setVisibility(View.VISIBLE);

                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    //setting horizontal list
                    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    pickorders_recyclerview.setLayoutManager(mLayoutManager);
                    pickorders_recyclerview.setItemAnimator(new DefaultItemAnimator());

                    //Adding Adapter to recyclerView
                    pickedOrdersAdapter = new PickedOrdersAdapter(pickupOrdersPOJO, getActivity());
                    pickorders_recyclerview.setAdapter(pickedOrdersAdapter);


                    pickedOrdersAdapter.setOnItemClickListener(new PickedOrdersAdapter.OnitemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            position = position;


                            switch (view.getId()) {
                                case R.id.lay_out_start:

                                     order_id = String.valueOf(pickupOrdersPOJO.getResponse().get(position).getOrder_id());
                                     invoice_id = String.valueOf(pickupOrdersPOJO.getResponse().get(position).getInvoice_id());

                                    Map<String, String> requestBody = new HashMap<>();
                                    requestBody.put("order_id", order_id);
                                    requestBody.put("user_id", str_emp_id);
                                    requestBody.put("warehouse_start", "yes");
                                    presenter.start_warehouse_service(getActivity(),apiResponseCallback , requestBody);
                                 //   Toast.makeText(CartActivity.this, "Cart is Deleted", Toast.LENGTH_LONG).show();
                                    break;
                            }
                        }
                    });

                    }else{
                        no_datatv.setVisibility(View.VISIBLE);
                        pickorders_recyclerview.setVisibility(View.GONE);
                    }
                }

            }else if(NetworkConstants.RequestCode.AGENT_TOSTART_ORDER == requestId){

                if (jsonObject.optBoolean("status") == true) {
                    Util.getInstance().cusToast(getActivity(), jsonObject.optString("message"));



                    Bundle bundle = new Bundle();
                    bundle.putString("order_id", order_id);
                    bundle.putString("invoice_id", invoice_id);

                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_COLLECTED_ITEMS_SCREEN,bundle);



                }else{
                    Util.getInstance().cusToast(getActivity(), jsonObject.optString("message"));

                }



            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }

    @Override
    public void setPresenter() {
        presenter = new PickUpFragmentImpl(this, getActivity());
    }
}
