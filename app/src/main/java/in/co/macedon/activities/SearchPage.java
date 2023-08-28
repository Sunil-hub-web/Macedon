package in.co.macedon.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import in.co.macedon.R;
import in.co.macedon.adapters.AllServicesAdapter;
import in.co.macedon.adapters.CategoryListAdapter;
import in.co.macedon.adapters.SerachPageAdapter;
import in.co.macedon.extras.AppURL;
import in.co.macedon.models.AllServicesModel;
import in.co.macedon.models.CenterServicesModel;
import in.co.macedon.models.SearchModel;

public class SearchPage extends AppCompatActivity {

    ArrayList<String> array_City = new ArrayList<>();
    ArrayList<String> array_Area = new ArrayList<>();
    HashMap<String, String> hashMap_City = new HashMap<>();
    HashMap<String, String> hashMap_Area = new HashMap<>();
    AutoCompleteTextView auto_City, auto_Area, auto_Services;
    ArrayList<AllServicesModel> allServicesModels = new ArrayList<>();
    ArrayList<String> array_allServices = new ArrayList<>();
    HashMap<String, String> hashMap_allServices = new HashMap<>();
    TextView btn_Serach;
    RecyclerView serachRecycler;
    String cityname, cityid = "", areaname, areaId = "", userId, servicesId, servicesName;
    ArrayList<SearchModel> servicesModelArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView back = findViewById(R.id.back);

        auto_City = findViewById(R.id.auto_City);
        auto_Area = findViewById(R.id.auto_Area);
        auto_Services = findViewById(R.id.auto_Services);
        btn_Serach = findViewById(R.id.btn_Serach);
        serachRecycler = findViewById(R.id.serachRecycler);

        getYourcity();
        getAllServices();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        auto_City.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                auto_City.showDropDown();
            }
        });
        auto_Area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                auto_Area.showDropDown();
            }
        });
        auto_Services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                auto_Services.showDropDown();
            }
        });
        auto_City.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (auto_City.getText().toString().trim().equals("---Select Your City---")) {

                    Toast.makeText(SearchPage.this, "Please Select City", Toast.LENGTH_SHORT).show();

                } else {

                    //   Toast.makeText(getActivity(), ""+(String)parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();

                    cityname = parent.getItemAtPosition(position).toString();
                    cityid = hashMap_City.get(cityname);

                    getArea(cityid);

                }
            }
        });
        auto_Area.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (auto_City.getText().toString().trim().equals("---Select Your City---")) {

                    Toast.makeText(SearchPage.this, "Please Select City", Toast.LENGTH_SHORT).show();

                } else {

                    areaname = parent.getItemAtPosition(position).toString();
                    areaId = hashMap_Area.get(areaname);


                }
            }
        });
        auto_Services.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (auto_City.getText().toString().trim().equals("---Select Your City---")) {

                    Toast.makeText(SearchPage.this, "Please Select City", Toast.LENGTH_SHORT).show();

                }else{

                    if (auto_Services.getText().toString().trim().equals("---Select Your Services---")) {

                        Toast.makeText(SearchPage.this, "Please Select Services", Toast.LENGTH_SHORT).show();

                    } else {

                        servicesName = parent.getItemAtPosition(position).toString();
                        servicesId = hashMap_allServices.get(servicesName);

                    }
                }

            }
        });

        btn_Serach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (servicesId.equals("")){

                    Toast.makeText(SearchPage.this, "Select Your Services", Toast.LENGTH_SHORT).show();

                }else {

                    getCategorylist(servicesId,cityid,areaId);
                }
            }
        });
    }

    public void getYourcity() {

        ProgressDialog progressDialog = new ProgressDialog(SearchPage.this);
        progressDialog.setMessage("City Details Pleae Wait....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURL.select_city, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("200")) {

                        array_City.clear();
                        hashMap_City.clear();

                        String error = jsonObject.getString("error");
                        String message = jsonObject.getString("message");
                        String data = jsonObject.getString("data");

                        JSONArray jsonArray_data = new JSONArray(data);
                        for (int i = 0; i < jsonArray_data.length(); i++) {

                            JSONObject jsonObject_data = jsonArray_data.getJSONObject(i);
                            String city_id = jsonObject_data.getString("city_id");
                            String city_name = jsonObject_data.getString("city_name");
                            String areas = jsonObject_data.getString("areas");

                            array_City.add(city_name);
                            hashMap_City.put(city_name, city_id);
                        }
                        array_City.add(0, "---Select Your City---");

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SearchPage.this, android.R.layout.simple_dropdown_item_1line, array_City);
                        auto_City.setThreshold(1);
                        auto_City.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                error.printStackTrace();
                Toast.makeText(SearchPage.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(SearchPage.this);
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }
    public void getArea(String CityId) {

        ProgressDialog progressDialog = new ProgressDialog(SearchPage.this);
        progressDialog.setMessage("Area Details Pleae Wait....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURL.select_city, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("200")) {

                        array_Area.clear();
                        hashMap_Area.clear();

                        String error = jsonObject.getString("error");
                        String message = jsonObject.getString("message");
                        String data = jsonObject.getString("data");

                        JSONArray jsonArray_data = new JSONArray(data);

                        for (int i = 0; i < jsonArray_data.length(); i++) {

                            JSONObject jsonObject_data = jsonArray_data.getJSONObject(i);
                            String city_id = jsonObject_data.getString("city_id");

                            if (CityId.equals(city_id)) {

                                String city_name = jsonObject_data.getString("city_name");
                                String areas = jsonObject_data.getString("areas");

                                JSONArray jsonArray_Area = new JSONArray(areas);

                                for (int j = 0; j < jsonArray_Area.length(); j++) {

                                    JSONObject jsonObject_Area = jsonArray_Area.getJSONObject(j);
                                    String area_id = jsonObject_Area.getString("area_id");
                                    String areaname = jsonObject_Area.getString("areaname");

                                    array_Area.add(areaname);
                                    hashMap_Area.put(areaname, area_id);

                                }
                            }
                        }
                        array_Area.add(0, "---Select Your Area---");

                        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(SearchPage.this, android.R.layout.simple_dropdown_item_1line, array_Area);
                        auto_Area.setThreshold(1);
                        auto_Area.setAdapter(adapter1);
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                error.printStackTrace();
                Toast.makeText(SearchPage.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(SearchPage.this);
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);

    }
    public void getAllServices() {

        ProgressDialog progressDialog = new ProgressDialog(SearchPage.this);
        progressDialog.setMessage("All Serivices Pleae Wait....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppURL.AllService, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("200")) {

                        allServicesModels.clear();

                        String error = jsonObject.getString("error");
                        String message = jsonObject.getString("message");
                        String data = jsonObject.getString("data");

                        JSONArray jsonArray_data = new JSONArray(data);

                        for (int i = 0; i < jsonArray_data.length(); i++) {

                            JSONObject jsonObject_data = jsonArray_data.getJSONObject(i);
                            String service_master_id = jsonObject_data.getString("service_master_id");
                            String service_master_name = jsonObject_data.getString("service_master_name");
                            String image = jsonObject_data.getString("image");

                            AllServicesModel allServices_Model = new AllServicesModel(service_master_id, service_master_name, image);

                            allServicesModels.add(allServices_Model);

                            array_allServices.add(service_master_name);
                            hashMap_allServices.put(service_master_name, service_master_id);
                        }

                        array_allServices.add(0, "---Select Your Services---");

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SearchPage.this, android.R.layout.simple_dropdown_item_1line, array_allServices);
                        auto_Services.setThreshold(1);
                        auto_Services.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                error.printStackTrace();
                Toast.makeText(SearchPage.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(SearchPage.this);
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);

    }

    public void getCategorylist(String service_id,String city_id, String area_id ) {

        ProgressDialog progressDialog = new ProgressDialog(SearchPage.this);
        progressDialog.setMessage("Retrive Center Details Wait....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURL.filter, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String status = jsonObject.getString("status");

                    if (status.equals("200")) {

                        String error = jsonObject.getString("error");
                        String data = jsonObject.getString("data");
                        JSONObject jsonObject_data = new JSONObject(data);
                        String Center_Data = jsonObject_data.getString("center_data");

                        servicesModelArrayList = new ArrayList<>();

                        servicesModelArrayList.clear();

                        JSONArray jsonArray_center = new JSONArray(Center_Data);

                        if (jsonArray_center.length() != 0){


                            for (int i = 0; i < jsonArray_center.length(); i++) {

                                JSONObject jsonObject_center = jsonArray_center.getJSONObject(i);
                                String id = jsonObject_center.getString("id");
                                String center_name = jsonObject_center.getString("center_name");
                                String status1 = jsonObject_center.getString("status");
                                String city_name = jsonObject_center.getString("city_name");
                                String areaname = jsonObject_center.getString("areaname");
                                String hygene = jsonObject_center.getString("hygene");
                                String logo_image = jsonObject_center.getString("logo_image");
                                String profile_image = jsonObject_center.getString("profile_image");
                                String service_id = jsonObject_center.getString("service_id");
                                String details = jsonObject_center.getString("details");

                                SearchModel searchModel = new SearchModel(
                                        id,center_name,status1,city_name,areaname,hygene,logo_image,profile_image,service_id,details
                                );

                                servicesModelArrayList.add(searchModel);

                            }

                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchPage.this,LinearLayoutManager.HORIZONTAL,false);
                            // GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
                            SerachPageAdapter serachPageAdapter = new SerachPageAdapter(SearchPage.this,servicesModelArrayList);
                            serachRecycler.setLayoutManager(linearLayoutManager);
                            serachRecycler.setHasFixedSize(true);
                            serachRecycler.setAdapter(serachPageAdapter);

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
                Toast.makeText(SearchPage.this, "" + error, Toast.LENGTH_SHORT).show();

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("service_id", service_id);
                params.put("city_id", city_id);
                params.put("area_id", area_id);

                Log.d("paramersdetails", params.toString());

                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(SearchPage.this);
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);

    }

}