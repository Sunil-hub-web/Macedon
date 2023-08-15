package in.co.macedon.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import in.co.macedon.adapters.OfferAdapter;
import in.co.macedon.extras.AppURL;
import in.co.macedon.models.OfferModelClass;

public class OfferFragment extends Fragment {

    RecyclerView offerRecycler;
    ArrayList<OfferModelClass> offerModelClasses = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.showofferimagelist,container,false);
        offerRecycler = view.findViewById(R.id.offerRecycler);

        getOfferDetails();

        return view;
    }

    public void getOfferDetails(){

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Offer Details Wait......");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURL.offer, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    String error = jsonObject.getString("error");
                    String data = jsonObject.getString("data");

                    if (status.equals("200")){

                        JSONArray jsonArray_data = new JSONArray(data);

                        for (int i=0;i<jsonArray_data.length();i++){

                            JSONObject jsonObject1_datalist = jsonArray_data.getJSONObject(i);

                            String coupon_code_id = jsonObject1_datalist.getString("coupon_code_id");
                            String name = jsonObject1_datalist.getString("name");
                            String code = jsonObject1_datalist.getString("code");
                            String city_id = jsonObject1_datalist.getString("city_id");
                            String discount_type = jsonObject1_datalist.getString("discount_type");
                            String discount_value = jsonObject1_datalist.getString("discount_value");
                            String valid_uo_to = jsonObject1_datalist.getString("valid_uo_to");
                            String used_up_to = jsonObject1_datalist.getString("used_up_to");
                            String no_of_use_user = jsonObject1_datalist.getString("no_of_use_user");
                            String price_cart = jsonObject1_datalist.getString("price_cart");
                            String img = jsonObject1_datalist.getString("img");

                            OfferModelClass offerModelClass = new OfferModelClass(
                                    coupon_code_id,name,code,city_id,discount_type,discount_value,valid_uo_to,used_up_to,no_of_use_user,
                                    price_cart,img
                            );

                            offerModelClasses.add(offerModelClass);
                        }
                    }

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                    OfferAdapter offerAdapter = new OfferAdapter(offerModelClasses,getContext());
                    offerRecycler.setLayoutManager(linearLayoutManager);
                    offerRecycler.setHasFixedSize(true);
                    offerRecycler.setAdapter(offerAdapter);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), ""+error, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000,3,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }


}
