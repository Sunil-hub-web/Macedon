package in.co.macedon.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
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

import in.co.macedon.R;
import in.co.macedon.activities.MobileLogin;
import in.co.macedon.adapters.SubscriptionAdapter;
import in.co.macedon.extras.AppURL;
import in.co.macedon.models.Subscriptions_ModelClass;

public class Subscriptions extends Fragment {

    RecyclerView recyclerShowPlane;
    TextView text_MacPass,text_TrannerPass,text_DieticainPass;
    SubscriptionAdapter subscriptionAdapter;
    LinearLayoutManager linearLayoutManager;
    LinearLayout lin_MacPass,lin_TrannerPass,lin_DieticainPass;

    ArrayList<Subscriptions_ModelClass> subscription_MacPass = new ArrayList<>();
    ArrayList<Subscriptions_ModelClass> subscription_TrannerPass = new ArrayList<>();
    ArrayList<Subscriptions_ModelClass> subscription_DieticainPass = new ArrayList<>();
    ArrayList<Subscriptions_ModelClass> subscriptionDetails = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater,
                             @Nullable  ViewGroup container,
                             @Nullable  Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.subscriptions_fragment,container,false);

        text_MacPass = view.findViewById(R.id.text_MacPass);
        text_TrannerPass = view.findViewById(R.id.text_TrannerPass);
        text_DieticainPass = view.findViewById(R.id.text_DieticainPass);
        recyclerShowPlane = view.findViewById(R.id.recyclerShowPlane);
        lin_DieticainPass = view.findViewById(R.id.lin_DieticainPass);
        lin_TrannerPass = view.findViewById(R.id.lin_TrannerPass);
        lin_MacPass = view.findViewById(R.id.lin_MacPass);


        //userSubscription();

        text_MacPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                subscriptionAdapter  = new SubscriptionAdapter(getContext(),subscription_MacPass);
                recyclerShowPlane.setLayoutManager(linearLayoutManager);
                recyclerShowPlane.setHasFixedSize(true);
                recyclerShowPlane.setAdapter(subscriptionAdapter);

                lin_MacPass.setBackgroundResource(R.drawable.shop_filter_selected);
                text_TrannerPass.setBackgroundResource(R.drawable.shop_filter_item_bg);
                text_DieticainPass.setBackgroundResource(R.drawable.shop_filter_item_bg);
            }
        });

        text_TrannerPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                subscriptionAdapter  = new SubscriptionAdapter(getContext(),subscription_TrannerPass);
                recyclerShowPlane.setLayoutManager(linearLayoutManager);
                recyclerShowPlane.setHasFixedSize(true);
                recyclerShowPlane.setAdapter(subscriptionAdapter);

                lin_MacPass.setBackgroundResource(R.drawable.shop_filter_item_bg);
                text_TrannerPass.setBackgroundResource(R.drawable.shop_filter_selected);
                text_DieticainPass.setBackgroundResource(R.drawable.shop_filter_item_bg);
            }
        });

        text_DieticainPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                subscriptionAdapter  = new SubscriptionAdapter(getContext(),subscription_DieticainPass);
                recyclerShowPlane.setLayoutManager(linearLayoutManager);
                recyclerShowPlane.setHasFixedSize(true);
                recyclerShowPlane.setAdapter(subscriptionAdapter);

                lin_MacPass.setBackgroundResource(R.drawable.shop_filter_item_bg);
                text_TrannerPass.setBackgroundResource(R.drawable.shop_filter_item_bg);
                text_DieticainPass.setBackgroundResource(R.drawable.shop_filter_selected);
            }
        });

        return view;
    }

   /* public void userSubscription(){

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Subscription Details Please Wait.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppURL.subcription, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String status = jsonObject.getString("status");

                    if(status.equals("true")){

                        String message = jsonObject.getString("message");
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

                        String subscription = jsonObject.getString("subscription");
                        JSONArray jsonArray_subscription = new JSONArray(subscription);

                        for (int i=0;i<jsonArray_subscription.length();i++){

                            JSONObject jsonObject_subscription = jsonArray_subscription.getJSONObject(i);

                            String membership_id = jsonObject_subscription.getString("membership_id");
                            String membership_name = jsonObject_subscription.getString("membership_name");
                            String membership_period = jsonObject_subscription.getString("membership_period");
                            String membership_amount = jsonObject_subscription.getString("membership_amount");
                            String membership_description = jsonObject_subscription.getString("membership_description");
                            String no_of_class = jsonObject_subscription.getString("no_of_class");

                            Subscriptions_ModelClass subscriptions_modelClass = new Subscriptions_ModelClass(
                                    membership_id,membership_name,membership_description,membership_amount,membership_period,no_of_class
                            );

                            subscriptionDetails.add(subscriptions_modelClass);

                            subscription_MacPass.add(subscriptions_modelClass);

                            subscription_TrannerPass.add(subscriptions_modelClass);

                            subscription_DieticainPass.add(subscriptions_modelClass);

                        }

                        linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                        subscriptionAdapter  = new SubscriptionAdapter(getContext(),subscriptionDetails);
                        recyclerShowPlane.setLayoutManager(linearLayoutManager);
                        recyclerShowPlane.setHasFixedSize(true);
                        recyclerShowPlane.setAdapter(subscriptionAdapter);

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
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000,3,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }*/
}
