package in.co.macedon.fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import in.co.macedon.R;
import in.co.macedon.activities.DashBoard;
import in.co.macedon.adapters.AddedsAdapter;
import in.co.macedon.adapters.CenterAmetiesAdapter;
import in.co.macedon.adapters.ImageSliderAdapter;
import in.co.macedon.adapters.MemberShipAdapter1;
import in.co.macedon.adapters.MemberShipPlanAdapter;
import in.co.macedon.adapters.MemberShipPlanAdapter1;
import in.co.macedon.adapters.ReviewRatingAdapter;
import in.co.macedon.adapters.SingleProductAdapter;
import in.co.macedon.adapters.TimeslotAdapter;
import in.co.macedon.extras.AppURL;
import in.co.macedon.extras.RecyclerTouchListener;
import in.co.macedon.extras.SessionManager;
import in.co.macedon.models.AddedsModel;
import in.co.macedon.models.CenterTimeingSlot;
import in.co.macedon.models.MemberShipModel;
import in.co.macedon.models.ReviewRatingModel;
import in.co.macedon.models.SingleCenterActivityModel;
import in.co.macedon.models.SingleCenterAmetiesModel;
import in.co.macedon.models.SingleCentergalleryModel;

public class SingleProduct_Fragment extends Fragment {

    RecyclerView memberShipPlanDet, aboutgymRecycler, packagePlanRecycler, timeimgPlanRecycler, reviwRatingRecycler;
    TextView address_Det, pin_code, contactNo, emailName, fitnessName, centerLoc, text_Amenities, text_SheduleClass,
            text_CustomerReview, text_ReviewAndRating,text_Showtimeimg;
    String centerimage, center_name, city_name, address1, pin, email, contact_no, centerId, rateValue = "",
            service_id = "", service_Name = "",userId;
    ArrayList<SingleCentergalleryModel> centergalleryModels = new ArrayList<>();
    ArrayList<SingleCenterActivityModel> centerActivityModels = new ArrayList<>();
    ArrayList<MemberShipModel> centerPackageModels;
    ArrayList<SingleCenterAmetiesModel> centerAmetiesModels = new ArrayList<>();
    ArrayList<CenterTimeingSlot> centerTimeingSlots;
    ArrayList<String> centerPackageArray = new ArrayList<>();
    ArrayList<String> centertimeslot = new ArrayList<>();
    ArrayList<AddedsModel> addedsModels = new ArrayList<>();
    ArrayList<ReviewRatingModel> reviewRatingModels = new ArrayList<>();
    ImageSliderAdapter bannerAdapter;
    MemberShipAdapter1 memberShipAdapter;
    SingleProductAdapter singleProductAdapter;
    MemberShipPlanAdapter1 memberShipPlanAdapter1;
    ReviewRatingAdapter reviewRatingAdapter;
    ViewPager2 viewpagerBanner, viewpagerAddeds;
    AddedsAdapter addedsAdapter;
    int currentPossition = 0;
    int arraysize, arraysize1;
    Handler sliderhandler = new Handler();
    CenterAmetiesAdapter centerAmetiesAdapter;
    TimeslotAdapter timeslotAdapter;
    RelativeLayout timesoltreal;
    Dialog dialogConfirm;
    ArrayList<String> serviceName = new ArrayList<>();
    Map<String, String> paramsServices = new HashMap<>();
    SessionManager sessionManager;
    NestedScrollView scrollView;
    ImageView imageback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.singlepageproduct, container, false);

        memberShipPlanDet = view.findViewById(R.id.memberShipPlanDet);
        aboutgymRecycler = view.findViewById(R.id.aboutgymRecycler);
        address_Det = view.findViewById(R.id.address_Det);
        pin_code = view.findViewById(R.id.pin_code);
        contactNo = view.findViewById(R.id.contactNo);
        emailName = view.findViewById(R.id.emailName);
      //  fitnessName = view.findViewById(R.id.fitnessName);
        viewpagerBanner = view.findViewById(R.id.viewpagerBanner);
        centerLoc = view.findViewById(R.id.centerLoc);
        text_Amenities = view.findViewById(R.id.text_Amenities);
        text_CustomerReview = view.findViewById(R.id.text_CustomerReview);
        viewpagerAddeds = view.findViewById(R.id.viewpagerAddeds);
        text_ReviewAndRating = view.findViewById(R.id.text_ReviewAndRating);
        packagePlanRecycler = view.findViewById(R.id.packagePlanRecycler);
        text_Showtimeimg = view.findViewById(R.id.text_Showtimeimg);
        reviwRatingRecycler = view.findViewById(R.id.reviwRatingRecycler);
        scrollView = view.findViewById(R.id.scrollView);

        DashBoard.locationlayout.setVisibility(View.VISIBLE);
        DashBoard.locationlayout1.setVisibility(View.VISIBLE);
        DashBoard.cart.setVisibility(View.GONE);
        DashBoard.fl.removeAllViews();
        //     DashBoard.userName.setText("Package Detils");

        DashBoard.header.setVisibility(View.GONE);
        DashBoard.header1.setVisibility(View.VISIBLE);

        Bundle arguments = getArguments();
        if (arguments != null) {

            centerId = arguments.getString("centerId");
            singleproduct(centerId, view);

            Log.d("centerId123",centerId);


        }

        scrollView.fullScroll(View.FOCUS_DOWN);
        scrollView.setSmoothScrollingEnabled(true);

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
            public void transformPage(@NonNull View page, float position) {

                float r = 1 - Math.abs(position);
                page.setScaleY(1.0f + r * 0.90f);

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
                sliderhandler.postDelayed(sliderRunable, 2000);

            }
        });

        addedsModels.add(new AddedsModel(R.drawable.details1));
        addedsModels.add(new AddedsModel(R.drawable.details2));
        addedsModels.add(new AddedsModel(R.drawable.details4));
        addedsModels.add(new AddedsModel(R.drawable.details3));

        addedsAdapter = new AddedsAdapter(getContext(), addedsModels, viewpagerAddeds);
        viewpagerAddeds.setAdapter(addedsAdapter);

        arraysize = centergalleryModels.size();

        viewpagerAddeds.setClipToPadding(false);
        viewpagerAddeds.setClipChildren(false);
        viewpagerAddeds.setOffscreenPageLimit(3);
        viewpagerAddeds.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer1 = new CompositePageTransformer();
        compositePageTransformer1.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer1.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {

                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);

            }
        });

        viewpagerAddeds.setPageTransformer(compositePageTransformer1);

        Runnable sliderRunable1 = new Runnable() {
            @Override
            public void run() {

                viewpagerAddeds.setCurrentItem(viewpagerAddeds.getCurrentItem() + 1);
            }
        };

        viewpagerAddeds.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                //super.onPageSelected(position);

                if (currentPossition >= arraysize)
                    currentPossition = 0;
                //selectedIndicatorPosition (currentPossition++);

                sliderhandler.removeCallbacks(sliderRunable1);
                sliderhandler.postDelayed(sliderRunable1, 3000);

            }
        });

        memberShipPlanDet.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), memberShipPlanDet, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {

                SingleCenterActivityModel animal = centerActivityModels.get(position);
                //Toast.makeText(getActivity().getApplicationContext(), animal.getService_master_name() + " is selected!", Toast.LENGTH_LONG).show();

                singleproduct1(centerId, animal.getService_master_id());
                singleproduct2(centerId, animal.getService_master_id());
                Log.d("memberdetails", animal.getService_master_id());

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        text_ReviewAndRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showReviewRating();
            }
        });

        text_Showtimeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog();
            }
        });


        return view;
    }

    public void singleproduct(String center_id, View view) {

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Retrive Center Details Wait......");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURL.Single_center_Page, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");

                    if (status.equals("200")) {

                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_messages = new JSONObject(messages);
                        String responsecode = jsonObject_messages.getString("responsecode");
                        String data = jsonObject_messages.getString("data");
                        JSONObject jsonObject_data = new JSONObject(data);

                        String Single_Center_Data = jsonObject_data.getString("Single_Center_Data");
                        String Single_Center_Ameties = jsonObject_data.getString("Single_Center_Ameties");
                        String Single_Center_gallery = jsonObject_data.getString("Single_Center_gallery");
                        String rating_review = jsonObject_data.getString("rating_review");
                        String Single_Center_Activity = jsonObject_data.getString("Single_Center_Activity");

                        JSONArray jsonArray_Center_Data = new JSONArray(Single_Center_Data);

                        for (int i = 0; i < jsonArray_Center_Data.length(); i++) {

                            JSONObject jsonObject_Center_Data = jsonArray_Center_Data.getJSONObject(0);

                            String center_id = jsonObject_Center_Data.getString("center_id");
                            center_name = jsonObject_Center_Data.getString("center_name");
                            centerimage = jsonObject_Center_Data.getString("centerimage");
                            city_name = jsonObject_Center_Data.getString("city_name");
                            address1 = jsonObject_Center_Data.getString("address1");
                            pin = jsonObject_Center_Data.getString("pin");
                            email = jsonObject_Center_Data.getString("email");
                            contact_no = jsonObject_Center_Data.getString("contact_no");

                        }

                        address_Det.setText(address1);
                        pin_code.setText(pin);
                        contactNo.setText(contact_no);
                        emailName.setText(email);
        //                fitnessName.setText(center_name);
                        centerLoc.setText(city_name);

                        centergalleryModels.clear();

                        JSONArray jsonArray_Center_gallery = new JSONArray(Single_Center_gallery);

                        if (jsonArray_Center_gallery.length() != 0) {

                            for (int j = 0; j < jsonArray_Center_gallery.length(); j++) {

                                JSONObject jsonObject_Center_gallery = jsonArray_Center_gallery.getJSONObject(j);

                                String center_gallery_id = jsonObject_Center_gallery.getString("center_gallery_id");
                                String cente_image = jsonObject_Center_gallery.getString("cente_image");
                                String center_id = jsonObject_Center_gallery.getString("center_id");
                                String created_date = jsonObject_Center_gallery.getString("created_date");

                                SingleCentergalleryModel singleCentergalleryModel = new SingleCentergalleryModel(
                                        center_gallery_id, cente_image, center_id, created_date
                                );

                                centergalleryModels.add(singleCentergalleryModel);

                            }

                            bannerAdapter = new ImageSliderAdapter(getContext(), centergalleryModels, viewpagerBanner);
                            viewpagerBanner.setAdapter(bannerAdapter);

                            arraysize = centergalleryModels.size();

                        } else {

                            SingleCentergalleryModel singleCentergalleryModel = new SingleCentergalleryModel(
                                    "", centerimage, "", ""
                            );

                            centergalleryModels.add(singleCentergalleryModel);
                        }

                        centerActivityModels.clear();
                        serviceName.clear();
                        paramsServices.clear();

                        JSONArray jsonArray_Center_Activity = new JSONArray(Single_Center_Activity);

                        for (int k = 0; k < jsonArray_Center_Activity.length(); k++) {


                            centerTimeingSlots = new ArrayList<>();
                            centerPackageModels = new ArrayList<>();

                            centerTimeingSlots.clear();
                            centerPackageModels.clear();

                            JSONObject jsonObject_Center_Activity = jsonArray_Center_Activity.getJSONObject(k);

                            String service_id = jsonObject_Center_Activity.getString("service_id");
                            String service_name = jsonObject_Center_Activity.getString("service_name");
                            String image = jsonObject_Center_Activity.getString("image");
                            String packages = jsonObject_Center_Activity.getString("packages");
                            String centertiming = jsonObject_Center_Activity.getString("centertiming");


                            JSONArray jsonArray_Center_timing = new JSONArray(centertiming);
                            for (int l = 0; l < jsonArray_Center_timing.length(); l++) {

                                JSONObject jsonObject_Center_timing = jsonArray_Center_timing.getJSONObject(l);
                                String timing_id = jsonObject_Center_timing.getString("timing_id");
                                String center_id = jsonObject_Center_timing.getString("center_id");
                                String fromtime = jsonObject_Center_timing.getString("fromtime");
                                String totime = jsonObject_Center_timing.getString("totime");
                                String day = jsonObject_Center_timing.getString("day");
                                String sequence = jsonObject_Center_timing.getString("sequence");
                                String service_id1 = jsonObject_Center_timing.getString("service_id");
                                String status1 = jsonObject_Center_timing.getString("status");

                                CenterTimeingSlot centerTimeing_Slot = new CenterTimeingSlot(
                                        timing_id, fromtime, totime, day, sequence, service_id1, status1
                                );
                                centerTimeingSlots.add(centerTimeing_Slot);
                                centertimeslot.add(service_id);

                            }


                            JSONArray jsonArray_Center_Package = new JSONArray(packages);

                            for (int l = 0; l < jsonArray_Center_Package.length(); l++) {

                                JSONObject jsonObject_Center_Package = jsonArray_Center_Package.getJSONObject(l);
                                String package_id = jsonObject_Center_Package.getString("package_id");
                                String package_name = jsonObject_Center_Package.getString("package_name");
                                String package_duration = jsonObject_Center_Package.getString("package_duration");
                                String center_id = jsonObject_Center_Package.getString("center_id");
                                String class_week = jsonObject_Center_Package.getString("class_week");
                                String service_id1 = jsonObject_Center_Package.getString("service_id");
                                String package_price = jsonObject_Center_Package.getString("package_price");
                                String package_description = jsonObject_Center_Package.getString("package_description");
                                String status1 = jsonObject_Center_Package.getString("status");

//                            SingleCenterPackageModel singleCenterPackageModel = new SingleCenterPackageModel(
//                                    package_id,package_name,package_duration,class_week,service_id,package_price,package_description
//                            );
//                            centerPackageModels.add(singleCenterPackageModel);

                                MemberShipModel memberShipModel = new MemberShipModel(
                                        package_id, package_name, package_duration, package_price, package_description, center_id, class_week, service_id1
                                );

                                centerPackageModels.add(memberShipModel);

                            }


                            SingleCenterActivityModel singleCenterActivityModel = new SingleCenterActivityModel(
                                    service_id, service_name, image, centerTimeingSlots, centerPackageModels
                            );
                            serviceName.add(service_name);
                            paramsServices.put(service_name, service_id);

                            centerPackageArray.add(service_id);

                            centerActivityModels.add(singleCenterActivityModel);

                        }

                        Log.d("centerActivityModels", centerActivityModels.toString());

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                        singleProductAdapter = new SingleProductAdapter(getContext(), centerActivityModels, centerPackageModels,
                                centerPackageArray, centerTimeingSlots, center_id);
                        memberShipPlanDet.setHasFixedSize(true);
                        memberShipPlanDet.setLayoutManager(linearLayoutManager);
                        //singleProductAdapter.setClickListener(this);
                        memberShipPlanDet.setAdapter(singleProductAdapter);

                        String serviceid_str = centerPackageArray.get(0);
                        singleproduct1(center_id, serviceid_str);
                        singleproduct2(center_id, serviceid_str);

                        centerAmetiesModels.clear();
                        JSONArray jsonArray_Center_Ameties = new JSONArray(Single_Center_Ameties);

                        for (int m = 0; m < jsonArray_Center_Ameties.length(); m++) {

                            JSONObject jsonObject_Center_Ameties = jsonArray_Center_Ameties.getJSONObject(m);
                            String facilities_id = jsonObject_Center_Ameties.getString("facilities_id");
                            String facilities_name = jsonObject_Center_Ameties.getString("facilities_name");
                            String image = jsonObject_Center_Ameties.getString("image");

                            SingleCenterAmetiesModel singleCenterAmetiesModel = new SingleCenterAmetiesModel(
                                    facilities_id, facilities_name, image);

                            centerAmetiesModels.add(singleCenterAmetiesModel);
                        }

                        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                        GridLayoutManager manager = new GridLayoutManager(getActivity(), 4, GridLayoutManager.VERTICAL, false);
                        centerAmetiesAdapter = new CenterAmetiesAdapter(centerAmetiesModels, getContext());
                        aboutgymRecycler.setHasFixedSize(true);
                        aboutgymRecycler.setLayoutManager(manager);
                        aboutgymRecycler.setAdapter(centerAmetiesAdapter);

                        reviewRatingModels.clear();
                        JSONArray jsonArray_rating_review = new JSONArray(rating_review);

                        for (int m = 0; m < jsonArray_rating_review.length(); m++) {

                            JSONObject jsonObject_rating_review = jsonArray_rating_review.getJSONObject(m);
                            String rating_review_id = jsonObject_rating_review.getString("rating_review_id");
                            String review = jsonObject_rating_review.getString("review");
                            String rating = jsonObject_rating_review.getString("rating");
                            String customer_id = jsonObject_rating_review.getString("customer_id");
                            String full_name = jsonObject_rating_review.getString("full_name");
                            String service_master_id = jsonObject_rating_review.getString("service_master_id");
                            String service_master_name = jsonObject_rating_review.getString("service_master_name");
                            String profile_image = jsonObject_rating_review.getString("profile_image");

                            ReviewRatingModel reviewRatingModel = new ReviewRatingModel(
                                    rating_review_id, review, rating, customer_id, full_name, profile_image,
                                    service_master_id, service_master_name);

                            reviewRatingModels.add(reviewRatingModel);
                        }

                        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                        reviewRatingAdapter = new ReviewRatingAdapter(reviewRatingModels, getContext());
                        reviwRatingRecycler.setHasFixedSize(true);
                        reviwRatingRecycler.setLayoutManager(linearLayoutManager2);
                        reviwRatingRecycler.setAdapter(reviewRatingAdapter);

                        //  text_Amenities.setBackgroundResource(R.drawable.textclickcolor_bg);
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("center_id", center_id);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }

    public void singleproduct1(String center_id, String serviceId) {

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Retrive Center Details Wait......");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURL.Single_center_Page, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");

                    if (status.equals("200")) {

                        centerTimeingSlots = new ArrayList<>();
                        centerTimeingSlots.clear();


                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_messages = new JSONObject(messages);
                        String responsecode = jsonObject_messages.getString("responsecode");
                        String data = jsonObject_messages.getString("data");
                        JSONObject jsonObject_data = new JSONObject(data);

                        String Single_Center_Data = jsonObject_data.getString("Single_Center_Data");
                        String Single_Center_Ameties = jsonObject_data.getString("Single_Center_Ameties");
                        String Single_Center_gallery = jsonObject_data.getString("Single_Center_gallery");
                        String rating_review = jsonObject_data.getString("rating_review");
                        String Single_Center_Activity = jsonObject_data.getString("Single_Center_Activity");


                        JSONArray jsonArray_Center_Activity = new JSONArray(Single_Center_Activity);

                        for (int k = 0; k < jsonArray_Center_Activity.length(); k++) {

                            JSONObject jsonObject_Center_Activity = jsonArray_Center_Activity.getJSONObject(k);

                            String service_id = jsonObject_Center_Activity.getString("service_id");
                            String service_name = jsonObject_Center_Activity.getString("service_name");
                            String image = jsonObject_Center_Activity.getString("image");
                            String packages = jsonObject_Center_Activity.getString("packages");
                            String centertiming = jsonObject_Center_Activity.getString("centertiming");

                            if (service_id.equals(serviceId)) {

                                JSONArray jsonArray_Center_timing = new JSONArray(centertiming);

                                for (int l = 0; l < jsonArray_Center_timing.length(); l++) {

                                    JSONObject jsonObject_Center_timing = jsonArray_Center_timing.getJSONObject(l);
                                    String timing_id = jsonObject_Center_timing.getString("timing_id");
                                    String center_id = jsonObject_Center_timing.getString("center_id");
                                    String fromtime = jsonObject_Center_timing.getString("fromtime");
                                    String totime = jsonObject_Center_timing.getString("totime");
                                    String day = jsonObject_Center_timing.getString("day");
                                    String sequence = jsonObject_Center_timing.getString("sequence");
                                    String service_id1 = jsonObject_Center_timing.getString("service_id");
                                    String status1 = jsonObject_Center_timing.getString("status");

                                    CenterTimeingSlot centerTimeing_Slot = new CenterTimeingSlot(
                                            timing_id, fromtime, totime, day, sequence, service_id1, status1
                                    );
                                    centerTimeingSlots.add(centerTimeing_Slot);
                                    centertimeslot.add(service_id);

                                }
                            }
                        }

                        if (centerTimeingSlots.size() != 0) {

                            text_Showtimeimg.setVisibility(View.VISIBLE);

                        }else{

                            Toast.makeText(getActivity(), "Time Solt Not Found", Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("center_id", center_id);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }

    public void singleproduct2(String center_id, String serviceId) {

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Retrive Center Details Wait......");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURL.Single_center_Page, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");

                    if (status.equals("200")) {

                        centerPackageModels = new ArrayList<>();
                        centerPackageModels.clear();

                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_messages = new JSONObject(messages);
                        String responsecode = jsonObject_messages.getString("responsecode");
                        String data = jsonObject_messages.getString("data");
                        JSONObject jsonObject_data = new JSONObject(data);

                        String Single_Center_Data = jsonObject_data.getString("Single_Center_Data");
                        String Single_Center_Ameties = jsonObject_data.getString("Single_Center_Ameties");
                        String Single_Center_gallery = jsonObject_data.getString("Single_Center_gallery");
                        String rating_review = jsonObject_data.getString("rating_review");
                        String Single_Center_Activity = jsonObject_data.getString("Single_Center_Activity");


                        JSONArray jsonArray_Center_Activity = new JSONArray(Single_Center_Activity);

                        for (int k = 0; k < jsonArray_Center_Activity.length(); k++) {


                            JSONObject jsonObject_Center_Activity = jsonArray_Center_Activity.getJSONObject(k);

                            String service_id = jsonObject_Center_Activity.getString("service_id");
                            String service_name = jsonObject_Center_Activity.getString("service_name");
                            String image = jsonObject_Center_Activity.getString("image");
                            String packages = jsonObject_Center_Activity.getString("packages");
                            String centertiming = jsonObject_Center_Activity.getString("centertiming");

                            if (service_id.equals(serviceId)) {

                                JSONArray jsonArray_Center_Package = new JSONArray(packages);

                                for (int l = 0; l < jsonArray_Center_Package.length(); l++) {

                                    JSONObject jsonObject_Center_Package = jsonArray_Center_Package.getJSONObject(l);
                                    String package_id = jsonObject_Center_Package.getString("package_id");
                                    String package_name = jsonObject_Center_Package.getString("package_name");
                                    String package_duration = jsonObject_Center_Package.getString("package_duration");
                                    String center_id = jsonObject_Center_Package.getString("center_id");
                                    String class_week = jsonObject_Center_Package.getString("class_week");
                                    String service_id1 = jsonObject_Center_Package.getString("service_id");
                                    String package_price = jsonObject_Center_Package.getString("package_price");
                                    String package_description = jsonObject_Center_Package.getString("package_description");
                                    String status1 = jsonObject_Center_Package.getString("status");

//                            SingleCenterPackageModel singleCenterPackageModel = new SingleCenterPackageModel(
//                                    package_id,package_name,package_duration,class_week,service_id,package_price,package_description
//                            );
//                            centerPackageModels.add(singleCenterPackageModel);

                                    MemberShipModel memberShipModel = new MemberShipModel(
                                            package_id, package_name, package_duration, package_price, package_description, center_id, class_week, service_id1
                                    );

                                    centerPackageModels.add(memberShipModel);

                                    centerPackageArray.add(service_id);
                                }
                            }
                        }

                        if (centerPackageArray.size() !=0){

                            Log.d("centerActivityModels", centerActivityModels.toString());

                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                            memberShipAdapter = new MemberShipAdapter1(centerPackageModels, getContext(), "SingleProduct",serviceId,centerId);
                            packagePlanRecycler.setLayoutManager(linearLayoutManager);
                          //  scrollView.fullScroll(View.FOCUS_DOWN);
                         //   scrollView.setSmoothScrollingEnabled(true);
                            packagePlanRecycler.setHasFixedSize(true);
                            packagePlanRecycler.setAdapter(memberShipAdapter);

                        }else {

                            Toast.makeText(getActivity(), "Data Not Found", Toast.LENGTH_SHORT).show();
                        }
                        
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("center_id", center_id);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }

    public void showReviewRating() {

        dialogConfirm = new Dialog(getContext());
        dialogConfirm.getWindow().setWindowAnimations(R.style.DialogAnimation);
        dialogConfirm.setContentView(R.layout.reviewdetails_fragment);
        dialogConfirm.getWindow().setBackgroundDrawableResource(android.R.color.white);
        // dialogConfirm.setCancelable(false);
        //dialogConfirm.setCanceledOnTouchOutside(true);
        Window window = dialogConfirm.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        RatingBar ratingBar1 = dialogConfirm.findViewById(R.id.ratingBar1);
        AutoCompleteTextView autocomplate = dialogConfirm.findViewById(R.id.autocomplate);
        EditText addReviewDetails = dialogConfirm.findViewById(R.id.addReviewDetails);
        TextView btn_Submit = dialogConfirm.findViewById(R.id.btn_Submit);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.select_dialog_item, serviceName);
        autocomplate.setThreshold(1);//will start working from first character
        autocomplate.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        autocomplate.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));

        ratingBar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                rateValue = String.valueOf(ratingBar1.getRating());
                System.out.println("Rate for Module is" + rateValue);
            }
        });

        autocomplate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                service_Name = parent.getItemAtPosition(position).toString();

                if (service_Name.equals("")) {

                    service_id = "";

                } else {

                    service_id = paramsServices.get(service_Name);

                    Log.d("sdervicesdetails", service_Name + ", " + service_id);
                }
            }
        });

        autocomplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                autocomplate.showDropDown();
            }
        });

        btn_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rateValue.equals("")){

                    Toast.makeText(getActivity(), "Select Your Rating", Toast.LENGTH_SHORT).show();

                } else if (service_id.equals("")) {

                    Toast.makeText(getActivity(), "Select Your Services", Toast.LENGTH_SHORT).show();

                } else if (addReviewDetails.getText().toString().trim().equals("")) {

                    Toast.makeText(getActivity(), "Plese Enter Your Review", Toast.LENGTH_SHORT).show();
                }else{

                    addRatingReview(userId,centerId,rateValue,addReviewDetails.getText().toString().trim(),service_id);
                }


            }
        });

        dialogConfirm.show();

    }

    public void addRatingReview(String cust_id, String center_id, String rating, String review,
                                String category_id) {

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Add Rating Wait......");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURL.ratreview, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");

                    if (status.equals("200")){

                        String error = jsonObject.getString("error");
                        String message = jsonObject.getString("message");
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

                    }else{

                        String error = jsonObject.getString("error");
                        String message = jsonObject.getString("message");
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(getActivity(), ""+error, Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();
                params.put("cust_id",cust_id);
                params.put("center_id",center_id);
                params.put("rating",rating);
                params.put("review",review);
                params.put("category_id",category_id);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);

    }

    private void showDialog() {

        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.showtimesolat);

        RecyclerView timeimgPlanRecycler = dialog.findViewById(R.id.timeimgPlanRecycler);

        if (centerTimeingSlots.size() != 0) {

            text_Showtimeimg.setVisibility(View.VISIBLE);

            Log.d("centerActivityModelsaa", centerTimeingSlots.toString());

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            //linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
            timeslotAdapter = new TimeslotAdapter(centerTimeingSlots, getContext());
            timeimgPlanRecycler.setLayoutManager(linearLayoutManager);
           // scrollView.fullScroll(View.FOCUS_DOWN);
          //  scrollView.setSmoothScrollingEnabled(true);
            timeimgPlanRecycler.setHasFixedSize(true);
            timeimgPlanRecycler.setAdapter(timeslotAdapter);

        }else{

            Toast.makeText(getActivity(), "Data Not Found", Toast.LENGTH_SHORT).show();
        }


        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }
}
