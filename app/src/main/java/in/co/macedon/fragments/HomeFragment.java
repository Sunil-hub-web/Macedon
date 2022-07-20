package in.co.macedon.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

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
import in.co.macedon.activities.Dietician;
import in.co.macedon.activities.ExploreMore;
import in.co.macedon.activities.FitnessCentre;
import in.co.macedon.activities.MobileLogin;
import in.co.macedon.activities.TrainerActivity;
import in.co.macedon.adapters.BannerAdapter;
import in.co.macedon.adapters.CategoryAdapter;
import in.co.macedon.extras.AppURL;
import in.co.macedon.models.Banner_ModelClass;
import in.co.macedon.models.Category_ModelClass;

public class HomeFragment extends Fragment {


    ViewPager2 viewpagerBanner;
    BannerAdapter bannerAdapter;
    ArrayList<Banner_ModelClass> banner ;
    RecyclerView categoryRecycler;
    CategoryAdapter categoryAdapter;
    ArrayList<Category_ModelClass> categoryDetails;
    GridLayoutManager gridLayoutManager;

    SwipeRefreshLayout swiprefresh;

    ImageView trainer_img,exploremore_img;

    int currentPossition = 0;
    int arraysize;
    Handler sliderhandler = new Handler();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        viewpagerBanner = v.findViewById(R.id.viewpagerBanner);
        categoryRecycler = v.findViewById(R.id.categoryRecycler);
        swiprefresh = v.findViewById(R.id.swiprefresh);


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




        trainer_img = v.findViewById(R.id.trainer);
        trainer_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ExploreMore.class);
                startActivity(i);
            }
        });

       /* fitness_img = v.findViewById(R.id.fitness_img);
        fitness_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), FitnessCentre.class);
                startActivity(i);
            }
        });
        diatician_img = v.findViewById(R.id.diatician_img);
        diatician_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Dietician.class);
                startActivity(i);
            }
        });*/

        exploremore_img = v.findViewById(R.id.exploremore_img);
        exploremore_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), FitnessCentre.class);
                startActivity(i);
            }
        });

        swiprefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getHomeDeatils();

                bannerAdapter.notifyDataSetChanged();
                categoryAdapter.notifyDataSetChanged();

                swiprefresh.setRefreshing(false);

            }
        });

        getHomeDeatils();

        return v;
    }

    public void getHomeDeatils(){

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Retrive Home Page Details");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppURL.getHomePage, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String status = jsonObject.getString("status");

                    if(status.equals("true")){

                        String message = jsonObject.getString("message");
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

                        String Banner = jsonObject.getString("Banner");
                        String category = jsonObject.getString("category");

                        banner = new ArrayList<>();

                        JSONArray jsonArray_banner = new JSONArray(Banner);

                        for (int i = 0;i<jsonArray_banner.length();i++){

                            JSONObject jsonObject_banner = jsonArray_banner.getJSONObject(i);

                            String banner_id = jsonObject_banner.getString("banner_id");
                            String banner_title = jsonObject_banner.getString("banner_title");
                            String banner_image = jsonObject_banner.getString("banner_image");

                            Banner_ModelClass banner_modelClass = new Banner_ModelClass(
                                    banner_id,banner_title,banner_image
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

                        JSONArray jsonArray_category = new JSONArray(category);

                        for(int j=0;j<jsonArray_category.length();j++) {

                            JSONObject jsonObject_category = jsonArray_category.getJSONObject(j);

                            String cat_id = jsonObject_category.getString("cat_id");
                            String cat_name = jsonObject_category.getString("cat_name");
                            String cat_img = jsonObject_category.getString("cat_img");

                            Category_ModelClass category_modelClass = new Category_ModelClass(
                                    cat_id,cat_name,cat_img
                            );

                            categoryDetails.add(category_modelClass);

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
        });

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
}
