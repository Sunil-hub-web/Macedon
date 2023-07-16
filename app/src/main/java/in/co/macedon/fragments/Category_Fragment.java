package in.co.macedon.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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
import in.co.macedon.adapters.BannerAdapter;
import in.co.macedon.adapters.CategoryAdapter;
import in.co.macedon.adapters.CategoryListAdapter;
import in.co.macedon.adapters.CenterDetailsAdapter;
import in.co.macedon.adapters.TestimonialAdapter;
import in.co.macedon.extras.AppURL;
import in.co.macedon.models.Banner_ModelClass;
import in.co.macedon.models.Category_ModelClass;
import in.co.macedon.models.CenterServicesModel;
import in.co.macedon.models.TestimonialModel;

public class Category_Fragment extends Fragment {

    RecyclerView categoryRecycler;
    ArrayList<CenterServicesModel> servicesModelArrayList;
    String Service_master_id,City_id;
    TextView listnotFound;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.categorylistdata, container, false);
        categoryRecycler = view.findViewById(R.id.categoryRecycler);
        listnotFound = view.findViewById(R.id.listnotFound);

        Bundle arguments = getArguments();


        if (arguments!=null){

            Service_master_id = arguments.getString("Service_master_id");
            City_id = arguments.getString("City_id");

            getCategorylist(City_id,Service_master_id);
        }

        return view;
    }

    public void getCategorylist(String city_id, String service_id) {

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Retrive Home Page Details");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURL.Single_Service_Page, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String status = jsonObject.getString("status");

                    if (status.equals("200")) {

                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String data = jsonObject_message.getString("data");
                        JSONObject jsonObject_data = new JSONObject(data);
                        String Center_Data = jsonObject_data.getString("Center_Data");

                        servicesModelArrayList = new ArrayList<>();

                        servicesModelArrayList.clear();

                        JSONArray jsonArray_center = new JSONArray(Center_Data);

                        if (jsonArray_center.length() != 0){

                            listnotFound.setVisibility(View.GONE);
                            categoryRecycler.setVisibility(View.VISIBLE);

                            for (int i = 0; i < jsonArray_center.length(); i++) {

                                JSONObject jsonObject_center = jsonArray_center.getJSONObject(i);
                                String center_id = jsonObject_center.getString("center_id");
                                String center_name = jsonObject_center.getString("center_name");
                                String centerimage = jsonObject_center.getString("centerimage");
                                String city_id = jsonObject_center.getString("city_id");
                                String city_name = jsonObject_center.getString("city_name");
                                String area_id = jsonObject_center.getString("area_id");
                                String areaname = jsonObject_center.getString("areaname");

                                CenterServicesModel centerServicesModel = new CenterServicesModel(
                                        center_id, centerimage, center_name, city_id, city_name, area_id, areaname
                                );

                                servicesModelArrayList.add(centerServicesModel);

                            }

                             LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
                           // GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
                            CategoryListAdapter centerDetailsAdapter = new CategoryListAdapter(getContext(),servicesModelArrayList);
                            categoryRecycler.setLayoutManager(linearLayoutManager);
                            categoryRecycler.setHasFixedSize(true);
                            categoryRecycler.setAdapter(centerDetailsAdapter);

                        }else{

                            listnotFound.setVisibility(View.VISIBLE);
                            categoryRecycler.setVisibility(View.GONE);
                        }


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                error.printStackTrace();
                Toast.makeText(getContext(), "" + error, Toast.LENGTH_SHORT).show();

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("city_id", city_id);
                params.put("service_id", service_id);

                Log.d("paramersdetails", params.toString());

                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);

    }
}
