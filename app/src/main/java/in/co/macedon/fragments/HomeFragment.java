package in.co.macedon.fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

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
import in.co.macedon.activities.DashBoard;
import in.co.macedon.activities.Dietician;
import in.co.macedon.activities.ExploreMore;
import in.co.macedon.activities.FitnessCentre;
import in.co.macedon.activities.MobileLogin;
import in.co.macedon.activities.TrainerActivity;
import in.co.macedon.adapters.AllServicesAdapter;
import in.co.macedon.adapters.BannerAdapter;
import in.co.macedon.adapters.CategoryAdapter;
import in.co.macedon.adapters.ExploreMoreAdapters;
import in.co.macedon.extras.AppURL;
import in.co.macedon.extras.SessionManager;
import in.co.macedon.models.AllServicesModel;
import in.co.macedon.models.Banner_ModelClass;
import in.co.macedon.models.Category_ModelClass;
import in.co.macedon.models.CenterServicesModel;

public class HomeFragment extends Fragment {

    ViewPager2 viewpagerBanner;
    BannerAdapter bannerAdapter;
    ArrayList<Banner_ModelClass> banner ;
    RecyclerView categoryRecycler,allservicesRecycler;
    CategoryAdapter categoryAdapter;
    ArrayList<Category_ModelClass> categoryDetails;
    GridLayoutManager gridLayoutManager;
    SwipeRefreshLayout swiprefresh;
    ImageView trainer_img,exploremore_img;
    int currentPossition = 0;
    int arraysize;
    Handler sliderhandler = new Handler();
    Dialog dialogConfirm;
    ArrayList<String> array_City = new ArrayList<>();
    ArrayList<String> array_Area = new ArrayList<>();
    HashMap<String,String> hashMap_City = new HashMap<>();
    HashMap<String,String> hashMap_Area = new HashMap<>();
    AutoCompleteTextView auto_City,auto_Area;
    String cityname,cityid,areaname,areaId,userId;
    SessionManager sessionManager;
    AllServicesAdapter allServicesAdapter;
    ArrayList<AllServicesModel> allServicesModels = new ArrayList<>();
    ArrayList<CenterServicesModel> servicesModelArrayList;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        viewpagerBanner = v.findViewById(R.id.viewpagerBanner);
        categoryRecycler = v.findViewById(R.id.categoryRecycler);
        allservicesRecycler = v.findViewById(R.id.allservicesRecycler);
       // swiprefresh = v.findViewById(R.id.swiprefresh);

        sessionManager = new SessionManager(getContext());
        userId = sessionManager.getUserID();

        viewpagerBanner.setClipToPadding(false);
        viewpagerBanner.setClipChildren(false);
        viewpagerBanner.setOffscreenPageLimit(3);
        viewpagerBanner.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull  View page, float position) {

                float r = 1 - Math.abs(position);
                page.setScaleY(1.0f +  r * 0.90f);

            }
        });

        viewpagerBanner.setPageTransformer(compositePageTransformer);

        Runnable sliderRunable = new Runnable() {
            @Override
            public void run() {

                viewpagerBanner.setCurrentItem(viewpagerBanner.getCurrentItem() + 1);
            }
        };

        viewpagerBanner.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                //super.onPageSelected(position);

                if (currentPossition >= arraysize)
                    currentPossition = 0;
                //selectedIndicatorPosition (currentPossition++);

                sliderhandler.removeCallbacks(sliderRunable);
                sliderhandler.postDelayed(sliderRunable,2000);

            }
        });


        /*swiprefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getHomeDeatils();

                bannerAdapter.notifyDataSetChanged();
                categoryAdapter.notifyDataSetChanged();

                swiprefresh.setRefreshing(false);

            }
        });*/

        showAddress_Dialog();

        return v;
    }

    public void getHomeDeatils(String user_id, String city_id, String area_id){

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Retrive Home Page Details");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURL.user_home, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String status = jsonObject.getString("status");

                    if(status.equals("200")){

                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String data = jsonObject_message.getString("data");
                        JSONObject jsonObject_data = new JSONObject(data);
                        String testimonial_dtl = jsonObject_data.getString("testimonial_dtl");
                        String banner_dtl = jsonObject_data.getString("banner_dtl");
                        String Service_List = jsonObject_data.getString("Service_List");

                        banner = new ArrayList<>();
                        banner.clear();

                        JSONArray jsonArray_banner = new JSONArray(banner_dtl);

                        for (int i = 0;i<jsonArray_banner.length();i++){

                            JSONObject jsonObject_banner = jsonArray_banner.getJSONObject(i);

                            String banner_id = jsonObject_banner.getString("banner_id");
                            String orderby = jsonObject_banner.getString("orderby");
                            String banner_image = jsonObject_banner.getString("image");

                            Banner_ModelClass banner_modelClass = new Banner_ModelClass(
                                    banner_id,orderby,banner_image
                            );

                            banner.add(banner_modelClass);
                        }

                        bannerAdapter = new BannerAdapter(getContext(),banner,viewpagerBanner);
                        viewpagerBanner.setAdapter(bannerAdapter);

                        arraysize = banner.size();

                       /* dots = new TextView[arraysize];
                        dotsIndicator();
                        selectedIndicatorPosition(currentPossition);*/

                        categoryDetails = new ArrayList<>();
                        servicesModelArrayList = new ArrayList<>();

                        JSONArray jsonArray_category = new JSONArray(Service_List);

                        for(int j=0;j<jsonArray_category.length();j++) {

                            JSONObject jsonObject_category = jsonArray_category.getJSONObject(j);

                            String service_master_id = jsonObject_category.getString("service_master_id");
                            String service_master_name = jsonObject_category.getString("service_master_name");
                            String image = jsonObject_category.getString("image");
                            String center_service = jsonObject_category.getString("center_service");

                            JSONArray jsonArray_center = new JSONArray(center_service);

                            for (int i = 0;i<jsonArray_center.length();i++){

                                JSONObject jsonObject_center = jsonArray_center.getJSONObject(i);
                            }

                            /*Category_ModelClass category_modelClass = new Category_ModelClass(
                                    service_master_id,service_master_name,image
                            );*/

                           // categoryDetails.add(category_modelClass);

                            Log.d("categoryDetails",categoryDetails.toString());
                        }

                        gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
                        categoryAdapter = new CategoryAdapter(categoryDetails,getContext());
                        categoryRecycler.setLayoutManager(gridLayoutManager);
                        categoryRecycler.setHasFixedSize(true);
                        categoryRecycler.setAdapter(categoryAdapter);

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
                Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("user_id",user_id);
                params.put("city_id",city_id);
                params.put("area_id",area_id);

                Log.d("paramersdetails",params.toString());

                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000,3,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);

    }

   /* private void dotsIndicator() {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10,10,10,10);

        for(int i=0;i<dots.length;i++){

            dots[i] = new TextView(getContext());
            dots[i].setText(Html.fromHtml("&#9679;"));
            dots[i].setTextSize(20);
            dots[i].setPadding(5, 0, 5, 0);
            dots[i].setLayoutParams(params);
            dots_container.addView(dots[i]);
        }
    }*/

    /*private void selectedIndicatorPosition(int position) {


        for(int i=0;i<dots.length;i++){


            if(i == position){

                dots[i].setTextColor(Color.RED);

            }else{

                dots[i].setTextColor(Color.GREEN);
            }
        }

    }*/


    public void showAddress_Dialog(){

        dialogConfirm = new Dialog(getContext());
        dialogConfirm.getWindow().setWindowAnimations(R.style.DialogAnimation);
        dialogConfirm.setContentView(R.layout.selectaddress);
        dialogConfirm.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        //dialogConfirm.setCanceledOnTouchOutside(true);
        Window window = dialogConfirm.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        getYourcity();

        auto_City = dialogConfirm.findViewById(R.id.auto_City);
        auto_Area = dialogConfirm.findViewById(R.id.auto_Area);

        auto_City.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                auto_City.showDropDown();
            }
        });

        auto_Area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (auto_City.getText().toString().trim().equals("---Select Your City---")){

                    Toast.makeText(getActivity(), "Please Select City", Toast.LENGTH_SHORT).show();

                }else{

                    auto_Area.showDropDown();
                }
            }
        });

        auto_City.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (auto_City.getText().toString().trim().equals("---Select Your City---")){

                    Toast.makeText(getActivity(), "Please Select City", Toast.LENGTH_SHORT).show();

                }else{

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

                if (auto_City.getText().toString().trim().equals("---Select Your City---")){

                    Toast.makeText(getActivity(), "Please Select City", Toast.LENGTH_SHORT).show();

                } else if (auto_City.getText().toString().trim().equals("---Select Your Area---")) {

                    Toast.makeText(getActivity(), "Please Select City", Toast.LENGTH_SHORT).show();

                } else{

                    areaname  = parent.getItemAtPosition(position).toString();
                    areaId = hashMap_Area.get(areaname);

                    getHomeDeatils(userId,cityid,areaId);
                    getAllServices();

                    dialogConfirm.dismiss();
                }
            }
        });

        dialogConfirm.show();

    }
    public void getYourcity(){

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("City Details Pleae Wait....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURL.select_city, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("200")){

                        array_City.clear();
                        hashMap_City.clear();

                        String error = jsonObject.getString("error");
                        String message = jsonObject.getString("message");
                        String data = jsonObject.getString("data");

                        JSONArray jsonArray_data = new JSONArray(data);
                        for (int i=0;i<jsonArray_data.length();i++){

                            JSONObject jsonObject_data = jsonArray_data.getJSONObject(i);
                            String city_id = jsonObject_data.getString("city_id");
                            String city_name = jsonObject_data.getString("city_name");
                            String areas = jsonObject_data.getString("areas");

                            array_City.add(city_name);
                            hashMap_City.put(city_name,city_id);
                        }
                        array_City.add(0,"---Select Your City---");

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_dropdown_item_1line,array_City);
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
                Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();
            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000,3,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }
    public void getArea(String CityId){

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Area Details Pleae Wait....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURL.select_city, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("200")){

                        array_Area.clear();
                        hashMap_Area.clear();

                        String error = jsonObject.getString("error");
                        String message = jsonObject.getString("message");
                        String data = jsonObject.getString("data");

                        JSONArray jsonArray_data = new JSONArray(data);

                        for (int i=0;i<jsonArray_data.length();i++){

                            JSONObject jsonObject_data = jsonArray_data.getJSONObject(i);
                            String city_id = jsonObject_data.getString("city_id");

                            if (CityId.equals(city_id)){

                                String city_name = jsonObject_data.getString("city_name");
                                String areas = jsonObject_data.getString("areas");

                                JSONArray jsonArray_Area = new JSONArray(areas);

                                for (int j = 0;j<jsonArray_Area.length();j++){

                                    JSONObject jsonObject_Area = jsonArray_Area.getJSONObject(j);
                                    String area_id = jsonObject_Area.getString("area_id");
                                    String areaname = jsonObject_Area.getString("areaname");

                                    array_Area.add(areaname);
                                    hashMap_Area.put(areaname,area_id);

                                }
                            }
                        }
                        array_Area.add(0,"---Select Your Area---");

                        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_dropdown_item_1line,array_Area);
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
                Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();
            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000,3,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);

    }
    public void getAllServices(){

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("All Serivices Pleae Wait....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppURL.AllService, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("200")){

                        allServicesModels.clear();

                        String error = jsonObject.getString("error");
                        String message = jsonObject.getString("message");
                        String data = jsonObject.getString("data");

                        JSONArray jsonArray_data = new JSONArray(data);
                        for (int i=0;i<jsonArray_data.length();i++){

                            JSONObject jsonObject_data = jsonArray_data.getJSONObject(i);
                            String service_master_id = jsonObject_data.getString("service_master_id");
                            String service_master_name = jsonObject_data.getString("service_master_name");
                            String image = jsonObject_data.getString("image");

                            AllServicesModel allServices_Model = new AllServicesModel(service_master_id,service_master_name,image);

                            allServicesModels.add(allServices_Model);
                        }

                        AllServicesAdapter adpater = new AllServicesAdapter(allServicesModels, getContext());
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext().getApplicationContext(), 3);
                        allservicesRecycler.setLayoutManager(gridLayoutManager);
                        allservicesRecycler.setItemAnimator(new DefaultItemAnimator());
                        allservicesRecycler.setAdapter(adpater);

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
                Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();
            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000,3,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);

    }

}
