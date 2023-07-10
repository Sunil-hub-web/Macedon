package in.co.macedon.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import in.co.macedon.R;
import in.co.macedon.adapters.SubscriptionAdapter;
import in.co.macedon.extras.AppURL;
import in.co.macedon.extras.SessionManager;
import in.co.macedon.models.Subscriptions_ModelClass;

public class Subscriptions extends Fragment {

    RecyclerView recyclerShowPlane;
    TextView text_MacPass,text_TrannerPass,text_DieticainPass;
    SubscriptionAdapter subscriptionAdapter;
    LinearLayoutManager linearLayoutManager;
    LinearLayout lin_MacPass,lin_TrannerPass,lin_DieticainPass;
    ArrayList<Subscriptions_ModelClass> subscription_MacPass = new ArrayList<>();
    ArrayList<Subscriptions_ModelClass> subscription_TrannerPass = new ArrayList<>();
    ArrayList<Subscriptions_ModelClass> subscriptionDetails = new ArrayList<>();
    ArrayList<Subscriptions_ModelClass> subscriptionDetails1 = new ArrayList<>();

    SessionManager sessionManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater,
                             @Nullable  ViewGroup container,
                             @Nullable  Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.subscriptionsfragment,container,false);

        text_MacPass = view.findViewById(R.id.text_MacPass);
        text_TrannerPass = view.findViewById(R.id.text_TrannerPass);
        recyclerShowPlane = view.findViewById(R.id.recyclerShowPlane);
        lin_TrannerPass = view.findViewById(R.id.lin_TrannerPass);
        lin_MacPass = view.findViewById(R.id.lin_MacPass);

        sessionManager = new SessionManager(getActivity());

        userSubscription(sessionManager.getUserID());

        text_MacPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                subscriptionAdapter  = new SubscriptionAdapter(getContext(),subscriptionDetails);
                recyclerShowPlane.setLayoutManager(linearLayoutManager);
                recyclerShowPlane.setHasFixedSize(true);
                recyclerShowPlane.setAdapter(subscriptionAdapter);

                lin_MacPass.setBackgroundResource(R.drawable.shop_filter_selected);
                text_TrannerPass.setBackgroundResource(R.drawable.shop_filter_item_bg);

            }
        });

        text_TrannerPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                subscriptionAdapter  = new SubscriptionAdapter(getContext(),subscriptionDetails1);
                recyclerShowPlane.setLayoutManager(linearLayoutManager);
                recyclerShowPlane.setHasFixedSize(true);
                recyclerShowPlane.setAdapter(subscriptionAdapter);

                lin_MacPass.setBackgroundResource(R.drawable.shop_filter_item_bg);
                text_TrannerPass.setBackgroundResource(R.drawable.shop_filter_selected);
            }
        });

        return view;
    }

    public void userSubscription(String user_id){

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Subscription Details Please Wait.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURL.Subscription, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String status = jsonObject.getString("status");

                    if (status.equals("200")){

                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String statusdat = jsonObject_message.getString("status");

                        if (responsecode.equals("00")){


                            JSONObject jsonObject1_status = new JSONObject(statusdat);
                            String membershipplan = jsonObject1_status.getString("membershipplan");
                            JSONArray jsonArray_membershipplan = new JSONArray(membershipplan);

                            for (int i=0;i<jsonArray_membershipplan.length();i++){

                                JSONObject jsonObject_membershipplan = jsonArray_membershipplan.getJSONObject(i);

                                String user_membership_history_id = jsonObject_membershipplan.getString("user_membership_history_id");
                                String package_name = jsonObject_membershipplan.getString("package_name");
                                String package_duration = jsonObject_membershipplan.getString("package_duration");
                                String total_sesson = jsonObject_membershipplan.getString("total_sesson");
                                String package_price = jsonObject_membershipplan.getString("package_price");
                                String purchace_date = jsonObject_membershipplan.getString("purchace_date");
                                String expair_date = jsonObject_membershipplan.getString("expair_date");
                                String paid_amount = jsonObject_membershipplan.getString("paid_amount");
                                String Center_Name = jsonObject_membershipplan.getString("Center_Name");
                                String Center_Id = jsonObject_membershipplan.getString("Center_Id");

                                if (Center_Id.equals("1")){

                                    Subscriptions_ModelClass subscriptions_modelClass = new Subscriptions_ModelClass(
                                            user_membership_history_id,package_name,package_duration,total_sesson,package_price,purchace_date,
                                            expair_date,paid_amount,Center_Id,Center_Name
                                    );

                                    subscriptionDetails.add(subscriptions_modelClass);

                                }else{

                                    Subscriptions_ModelClass subscriptions_modelClass = new Subscriptions_ModelClass(
                                            user_membership_history_id,package_name,package_duration,total_sesson,package_price,purchace_date,
                                            expair_date,paid_amount,Center_Id,Center_Name
                                    );

                                    subscriptionDetails1.add(subscriptions_modelClass);
                                }
                            }

                            lin_MacPass.setBackgroundResource(R.drawable.shop_filter_selected);
                            text_TrannerPass.setBackgroundResource(R.drawable.shop_filter_item_bg);

                            linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                            subscriptionAdapter  = new SubscriptionAdapter(getContext(),subscriptionDetails);
                            recyclerShowPlane.setLayoutManager(linearLayoutManager);
                            recyclerShowPlane.setHasFixedSize(true);
                            recyclerShowPlane.setAdapter(subscriptionAdapter);

                        }else{

                            Toast.makeText(getContext(), statusdat, Toast.LENGTH_SHORT).show();
                        }
                    }else{

                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String statusdat = jsonObject_message.getString("status");
                        Toast.makeText(getContext(), statusdat, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(getActivity(), ""+error, Toast.LENGTH_SHORT).show();
                error.printStackTrace();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("user_id",user_id);
                Log.d("userid",user_id);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000,3,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}
