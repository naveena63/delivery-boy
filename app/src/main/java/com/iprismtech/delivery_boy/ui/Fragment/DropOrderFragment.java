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

import com.google.gson.Gson;
import com.iprismtech.delivery_boy.Adapter.CollectedItemAdapter;
import com.iprismtech.delivery_boy.Adapter.DropedOrdersAdapter;
import com.iprismtech.delivery_boy.Adapter.PickedOrdersAdapter;
import com.iprismtech.delivery_boy.Pojos.DropedOrdersPOJO;
import com.iprismtech.delivery_boy.Pojos.PickupOrdersPOJO;
import com.iprismtech.delivery_boy.R;
import com.iprismtech.delivery_boy.app.constants.AppConstants;
import com.iprismtech.delivery_boy.app.controller.ApplicationController;
import com.iprismtech.delivery_boy.mvp.Contract.Fragment.DropOrderFragmentContract;
import com.iprismtech.delivery_boy.mvp.Presenter.Fragment.DropOrderFragmentImpl;
import com.iprismtech.delivery_boy.network.constants.NetworkConstants;
import com.iprismtech.delivery_boy.network.listener.APIResponseCallback;
import com.iprismtech.delivery_boy.sharepref.UserSession;
import com.iprismtech.delivery_boy.ui.Activity.CollectedItemsActivity;
import com.iprismtech.delivery_boy.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class DropOrderFragment extends BaseAbstractFragment<DropOrderFragmentImpl> implements DropOrderFragmentContract.IView, APIResponseCallback {


    LinearLayout linearLayout;
    UserSession userSession;
    String str_emp_id,order_id,invoice_id;
    DropedOrdersAdapter dropedOrdersAdapter;
    RecyclerView droporders_recyclerview;
    APIResponseCallback apiResponseCallback;
    DropedOrdersPOJO dropedOrdersPOJO;
    TextView no_datatv;

    public DropOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // View view= inflater.inflate(R.layout.fragment_drop_order_frag, container, false);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
    }

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_drop_order_frag, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new DropOrderFragmentImpl(this, getActivity());

    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();

      //  linearLayout = view.findViewById(R.id.lay_out_start);

        apiResponseCallback = this;

        userSession = new UserSession(getActivity());
        str_emp_id = userSession.getUserDetails().get(UserSession.KEY_USERID);

        droporders_recyclerview = view.findViewById(R.id.droporders_recyclerview);
        no_datatv = view.findViewById(R.id.no_datatv);

        dropedordfersWBS();




    }

    private void dropedordfersWBS() {
        Map<String, String> request = new HashMap<>();
        request.put("agent_id", str_emp_id);
        presenter.agent_delivery_orders(getActivity(), this, request);
    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {

            } else if (NetworkConstants.RequestCode.ITEMS_DROPED_ORDERS == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    Util.getInstance().cusToast(getActivity(), jsonObject.optString("message"));
                    dropedOrdersPOJO = new Gson().fromJson(responseString, DropedOrdersPOJO.class);

                    if (dropedOrdersPOJO.getResponse().size() > 0) {
                        no_datatv.setVisibility(View.GONE);
                        droporders_recyclerview.setVisibility(View.VISIBLE);

                        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                        //setting horizontal list
                        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        droporders_recyclerview.setLayoutManager(mLayoutManager);
                        droporders_recyclerview.setItemAnimator(new DefaultItemAnimator());

                        //Adding Adapter to recyclerView
                        dropedOrdersAdapter = new DropedOrdersAdapter(dropedOrdersPOJO, getActivity());
                        droporders_recyclerview.setAdapter(dropedOrdersAdapter);


                        dropedOrdersAdapter.setOnItemClickListener(new PickedOrdersAdapter.OnitemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                position = position;


                                switch (view.getId()) {
                                    case R.id.lay_out_start:

                                        order_id = String.valueOf(dropedOrdersPOJO.getResponse().get(position).getOrder_id());
                                        invoice_id = String.valueOf(dropedOrdersPOJO.getResponse().get(position).getInvoice_id());

                                        Map<String, String> requestBody = new HashMap<>();
                                        requestBody.put("order_id", order_id);
                                        requestBody.put("user_id", str_emp_id);
                                        requestBody.put("home_start", "yes");
                                        presenter.start_home_delivery_service(getActivity(),apiResponseCallback , requestBody);
                                        break;
                                }
                            }
                        });

                    } else {
                        no_datatv.setVisibility(View.VISIBLE);
                        droporders_recyclerview.setVisibility(View.GONE);
                    }
                }

            }else if(NetworkConstants.RequestCode.AGENT_TOSTARTDROP_ORDER == requestId){

                if (jsonObject.optBoolean("status") == true) {
                    Util.getInstance().cusToast(getActivity(), jsonObject.optString("message"));



                    Bundle bundle = new Bundle();
                    bundle.putString("order_id", order_id);
                    bundle.putString("invoice_id", invoice_id);

                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PICKEDITEMS_SCREEN,bundle);



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
}
