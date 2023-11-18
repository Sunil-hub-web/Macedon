package in.co.macedon.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import in.co.macedon.R;
import in.co.macedon.activities.DashBoard;
import in.co.macedon.adapters.OfferAdapter;
import in.co.macedon.adapters.PackageOfferAdapter;
import in.co.macedon.extras.AppURL;
import in.co.macedon.extras.RecyclerTouchListener;
import in.co.macedon.extras.SessionManager;
import in.co.macedon.models.OfferModelClass;
import in.co.macedon.models.SingleCenterActivityModel;

public class PackageFragment extends Fragment {

    public static TextView packageName, priceDetails, packagedurtion, messageDet, btn_CouponCode, btn_BuyNow, text_Name,
            text_Email, text_Contact, subTotalPrice, couponPrice, grandTotalPrice, btn_StartDate;
    public static String strpackageName, strpriceDetails, strpackagedurtion, strpackageprice, strmessageDet, userId, date,
            str_coupon = "", data, service_master_id, memberModelId, center_id, messagepass = "", formattedDate, order_id = "",
            statues_url_sat;
    SessionManager sessionManager;
    Dialog dialog;
    int year, month, day, hour, minute;
    Double cr_balance, dr_balance, crdr_balance, totcr_balance = 0.0, totdr_balance = 0.0, price3 = 0.0;
    CheckBox walletamount;
    Dialog dialogConfirm;
    ArrayList<OfferModelClass> offerModelClasses = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.gympackges_fragment, container, false);

        packageName = view.findViewById(R.id.packageName);
        priceDetails = view.findViewById(R.id.priceDetails);
        packagedurtion = view.findViewById(R.id.packagedurtion);
        // packageprice = view.findViewById(R.id.packageprice);
        messageDet = view.findViewById(R.id.messageDet);
        btn_CouponCode = view.findViewById(R.id.btn_CouponCode);
        btn_BuyNow = view.findViewById(R.id.btn_BuyNow);
        text_Name = view.findViewById(R.id.text_Name);
        text_Email = view.findViewById(R.id.text_Email);
        text_Contact = view.findViewById(R.id.text_Contact);
        subTotalPrice = view.findViewById(R.id.subTotalPrice);
        couponPrice = view.findViewById(R.id.couponPrice);
        grandTotalPrice = view.findViewById(R.id.grandTotalPrice);
        btn_StartDate = view.findViewById(R.id.btn_StartDate);
        walletamount = view.findViewById(R.id.walletamount);

        sessionManager = new SessionManager(getContext());

     //   DashBoard.header.setVisibility(View.VISIBLE);

        DashBoard.locationlayout.setVisibility(View.VISIBLE);
        DashBoard.cart.setVisibility(View.GONE);
        DashBoard.fl.removeAllViews();
        DashBoard.userName.setText("Package Detils");
        DashBoard.userName1.setText("Package Detils");

        DashBoard.header.setVisibility(View.GONE);
        DashBoard.header1.setVisibility(View.VISIBLE);

        SharedPreferences pref = getActivity().getSharedPreferences("MyPref", 0);

        strpackageName = pref.getString("packageName", null);
        strpriceDetails = pref.getString("priceDetails", null);
        strpackagedurtion = pref.getString("packagedurtion", null);
        strpackageprice = pref.getString("packageprice", null);
        strmessageDet = pref.getString("messageDet", null);
        service_master_id = pref.getString("service_master_id", null);
        memberModelId = pref.getString("memberModelId", null);
        center_id = pref.getString("center_id", null);
        messagepass = pref.getString("messagepass", null);

        SharedPreferences pref1 = getContext().getSharedPreferences("order_id123", 0);
        order_id = pref1.getString("order_id", null);

        packageName.setText(strpackageName);
        priceDetails.setText("RS " + strpriceDetails + " /-");
        packagedurtion.setText("Package Duration  " + strpackagedurtion + "  Days");
        // packageprice.setText("Class Per Week Rs  "+strpackageprice+" /-");
        messageDet.setText(strmessageDet);
        text_Name.setText(sessionManager.getUserName());
        text_Email.setText(sessionManager.getUserEmail());
        text_Contact.setText(sessionManager.getUserMobileno());

        getOfferDetails();

        if (order_id == null){
        }else{
            paymentmsg(order_id);
        }



        Bundle arguments = getArguments();
        if (arguments != null) {

            strpackageName = arguments.getString("packageName");
            strpriceDetails = arguments.getString("priceDetails");
            strpackagedurtion = arguments.getString("packagedurtion");
            strpackageprice = arguments.getString("packageprice");
            strmessageDet = arguments.getString("messageDet");
            service_master_id = arguments.getString("service_master_id");
            memberModelId = arguments.getString("memberModelId");
            center_id = arguments.getString("center_id");
            messagepass = arguments.getString("messagepass");

            packageName.setText(strpackageName);
            priceDetails.setText("RS " + strpriceDetails + " /-");
            packagedurtion.setText("Package Duration  " + strpackagedurtion + "  Days");
            // packageprice.setText("Class Per Week Rs  "+strpackageprice+" /-");
            messageDet.setText(strmessageDet);
            text_Name.setText(sessionManager.getUserName());
            text_Email.setText(sessionManager.getUserEmail());
            text_Contact.setText(sessionManager.getUserMobileno());

        }

        if (messagepass.equals("gymMemberdetail")) {

            btn_StartDate.setVisibility(View.INVISIBLE);
          //  walletamount.setVisibility(View.INVISIBLE);

        } else {

            btn_StartDate.setVisibility(View.VISIBLE);
           // walletamount.setVisibility(View.VISIBLE);
        }

        String data = "0";

        subTotalPrice.setText("Rs " + strpriceDetails);
        couponPrice.setText("Rs " + "0");
        Double price1 = Double.valueOf(strpriceDetails);
        Double price2 = Double.valueOf(data);
        Double price3 = price1 - price2;
        grandTotalPrice.setText("Rs " + String.valueOf(price3));

        userId = sessionManager.getUserID();
        userwallet(userId);

        btn_CouponCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.couponapplylayout);

                ImageView backimage = dialog.findViewById(R.id.backimage);
                RecyclerView recyclerViewOffer = dialog.findViewById(R.id.recyclerViewOffer);
              //  TextView btn_ApplyCoupon = dialog.findViewById(R.id.btn_ApplyCoupon);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                PackageOfferAdapter packageOfferAdapter = new PackageOfferAdapter(offerModelClasses,getContext());
                recyclerViewOffer.setLayoutManager(linearLayoutManager);
                recyclerViewOffer.setHasFixedSize(true);
                recyclerViewOffer.setAdapter(packageOfferAdapter);

                recyclerViewOffer.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(),
                        recyclerViewOffer, new RecyclerTouchListener.ClickListener() {

                    @Override
                    public void onClick(View view, int position) {

                        OfferModelClass offerModel = offerModelClasses.get(position);
                        //Toast.makeText(getActivity().getApplicationContext(), animal.getService_master_name() + " is selected!", Toast.LENGTH_LONG).show();

                        String strcityid = sessionManager.getCityId();
                        str_coupon = offerModel.getCode();
                        applyCouponCode(userId, str_coupon, strcityid, strpriceDetails);

                        Log.d("sunil123456",userId +"  "+ str_coupon +" "+ strcityid+" "+ strpriceDetails);

                        dialog.dismiss();

                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));


                backimage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });


                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                //window.setBackgroundDrawableResource(R.drawable.dialogback);
            }
        });

        btn_StartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showCalender1();
            }
        });

        btn_BuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (messagepass.equals("gymMemberdetail")) {

                    String strStartDate = btn_StartDate.getText().toString().trim();
                    String paidamount = String.valueOf(price3);

                    Float walletprice = sessionManager.getWalletAmount();
                    Double packageprice = Double.parseDouble(strpriceDetails);
                    Double d_walletprice = Double.parseDouble(String.valueOf(walletprice));

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {

                        formattedDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                    }

                    if (walletamount.isChecked()) {
                        // Do something
                        if (d_walletprice >= packageprice) {

                            buyMembership(userId, memberModelId, strpackageName, strpackagedurtion, "1", strpriceDetails,
                                    strpackageprice, "4", strStartDate, str_coupon, data, paidamount, "1");
                        } else {

                            Toast.makeText(getActivity(), "Please select Online", Toast.LENGTH_SHORT).show();
                        }
                    }else{

                        onlinepayment(userId, memberModelId, strpackageName, strpackagedurtion, "1", strpriceDetails,
                                strpackageprice, "4", strStartDate, str_coupon, data, paidamount, "");
                    }
                }else{

                    if (btn_StartDate.getText().toString().trim().equals("")) {

                        Toast.makeText(getActivity(), "Select Your Start Date", Toast.LENGTH_SHORT).show();

                    } else {

                        String strStartDate = btn_StartDate.getText().toString().trim();
                        String paidamount = String.valueOf(price3);

                        Float walletprice = sessionManager.getWalletAmount();
                        Double packageprice = Double.parseDouble(strpriceDetails);
                        Double d_walletprice = Double.parseDouble(String.valueOf(walletprice));


                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            Date c = Calendar.getInstance().getTime();
                            System.out.println("Current time => " + c);
                            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                            formattedDate = df.format(c);
                        }


                        if (walletamount.isChecked()) {
                            // Do something
                            if (d_walletprice >= packageprice) {

                                buyMembership(userId, memberModelId, strpackageName, strpackagedurtion, center_id, strpriceDetails,
                                        strpackageprice, service_master_id, formattedDate, str_coupon, data, paidamount, "1");
                            } else {

                                Toast.makeText(getActivity(), "Select Pay Online", Toast.LENGTH_SHORT).show();
                            }

                        } else {

                            onlinepayment(userId, memberModelId, strpackageName, strpackagedurtion, center_id, strpriceDetails,
                                    strpackageprice, service_master_id, strStartDate, str_coupon, data, paidamount, "");
                        }

                    }

                }
            }
        });

        return view;
    }

    public void buyMembership(String user_id, String package_id, String package_name, String package_duration,
                              String center_id, String package_price, String totalsesson, String service_id,
                              String start_date, String cupon_code, String cupon_price, String paid_amount,
                              String use_wallet) {

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Buy Membership Wait...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURL.BuyMembership, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");

                    progressDialog.dismiss();

                    if (status.equals("200")) {

                       // String error = jsonObject.getString("error");
                        String message = jsonObject.getString("message");
                        //String data = jsonObject.getString("data");
                        JSONObject jsonObject_data = new JSONObject(data);
                       // order_id = jsonObject_data.getString("order_id");


                        dialogConfirm = new Dialog(getActivity());
                        dialogConfirm.getWindow().setWindowAnimations(R.style.DialogAnimation);
                        dialogConfirm.setContentView(R.layout.successmember);
                        dialogConfirm.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                        // dialogConfirm.setCancelable(false);
                        //dialogConfirm.setCanceledOnTouchOutside(true);
                        Window window = dialogConfirm.getWindow();
                        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                        Button btn_Done = dialogConfirm.findViewById(R.id.btn_Done);

                        btn_Done.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                startActivity(new Intent(getActivity(),DashBoard.class));
                                dialogConfirm.dismiss();
                            }
                        });

                        dialogConfirm.show();

                    } else {

                        String message = jsonObject.getString("message");
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("user_id", user_id);
                params.put("package_id", package_id);
                params.put("package_name", package_name);
                params.put("package_duration", package_duration);
                params.put("center_id", center_id);
                params.put("package_price", package_price);
                params.put("totalsesson", totalsesson);
                params.put("service_id", service_id);
                params.put("start_date", start_date);
                params.put("cupon_code", cupon_code);
                params.put("cupon_price", cupon_price);
                params.put("paid_amount", paid_amount);
                params.put("use_wallet", use_wallet);

                Log.d("details", params.toString());

                return params;


            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);

    }

    public void onlinepayment(String user_id, String package_id, String package_name, String package_duration,
                              String center_id, String package_price, String totalsesson, String service_id,
                              String start_date, String cupon_code, String cupon_price, String paid_amount,
                              String use_wallet) {

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Buy Membership Wait...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURL.onlinepayment, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");

                    progressDialog.dismiss();

                    if (status.equals("200")) {

                        String error = jsonObject.getString("error");
                        String statusurl = jsonObject.getString("url");
                        String data = jsonObject.getString("data");
                        JSONObject jsonObject_data = new JSONObject(data);
                        order_id = jsonObject_data.getString("order_id");

                        SharedPreferences pref = getContext().getSharedPreferences("order_id123", 0); // 0 - for private mode
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("order_id",order_id);
                        editor.commit();

                        Fragment fragment = new WebViewFragment();
                        Bundle args = new Bundle();
                        args.putString("weburl", statusurl);
                        fragment.setArguments(args);
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.nav_host_fragment, fragment, "WebViewFragment");
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

                        statues_url_sat = "1";

                       /* dialogConfirm = new Dialog(getActivity());
                        dialogConfirm.getWindow().setWindowAnimations(R.style.DialogAnimation);
                        dialogConfirm.setContentView(R.layout.successmember);
                        dialogConfirm.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                        // dialogConfirm.setCancelable(false);
                        //dialogConfirm.setCanceledOnTouchOutside(true);
                        Window window = dialogConfirm.getWindow();
                        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                        Button btn_Done = dialogConfirm.findViewById(R.id.btn_Done);

                        btn_Done.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                startActivity(new Intent(getActivity(),DashBoard.class));
                                dialogConfirm.dismiss();
                            }
                        });

                        dialogConfirm.show();
*/

                    } else {

                        String error = jsonObject.getString("error");
                        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("user_id", user_id);
                params.put("package_id", package_id);
                params.put("package_name", package_name);
                params.put("package_duration", package_duration);
                params.put("center_id", center_id);
                params.put("package_price", package_price);
                params.put("totalsesson", totalsesson);
                params.put("service_id", service_id);
                params.put("start_date", start_date);
                params.put("cupon_code", cupon_code);
                params.put("cupon_price", cupon_price);
                params.put("paid_amount", paid_amount);

                Log.d("details", params.toString());

                return params;


            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);

    }

    public void showCalender1() {

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        //txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        // date = fmonth + "/" + fDate + "/" + year;
                        date = year+"-"+(monthOfYear + 1)+"-"+dayOfMonth;
                        btn_StartDate.setText(date);

                    }
                }, year, month, day);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
        datePickerDialog.show();

    }

    public void userwallet(String user_id) {

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Subscription Details Please Wait.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURL.wallet, new Response.Listener<String>() {
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
                        String statusdat = jsonObject_message.getString("status");

                        if (responsecode.equals("00")) {

                            JSONObject jsonObject1_status = new JSONObject(statusdat);
                            String wallet = jsonObject1_status.getString("wallet");
                            JSONArray jsonArray_wallet = new JSONArray(wallet);

                            for (int i = 0; i < jsonArray_wallet.length(); i++) {

                                JSONObject jsonObject_wallet = jsonArray_wallet.getJSONObject(i);

                                String wallet_id = jsonObject_wallet.getString("wallet_id");
                                String user_id = jsonObject_wallet.getString("user_id");
                                String amount = jsonObject_wallet.getString("amount");
                                String payment_type = jsonObject_wallet.getString("payment_type");
                                String wallet_status = jsonObject_wallet.getString("wallet_status");
                                String remark = jsonObject_wallet.getString("remark");
                                String created_date = jsonObject_wallet.getString("created_date");

                                if (payment_type.equals("1")) {

                                    cr_balance = Double.parseDouble(amount);
                                    totcr_balance = cr_balance + totcr_balance;

                                    Log.d("paymentdet", cr_balance + "," + totcr_balance);


                                } else {

                                    dr_balance = Double.parseDouble(amount);
                                    totdr_balance = dr_balance + totdr_balance;

                                    Log.d("paymentdet1", dr_balance + "," + totdr_balance);

                                }
                            }

                            walletamount.setVisibility(View.VISIBLE);

                            crdr_balance = totcr_balance - totdr_balance;
                            sessionManager.setWalletAmount(crdr_balance.floatValue());

                            String str_crdr_balance = String.valueOf(crdr_balance);

                            walletamount.setText("Use Your Wallet Amount Rs. " + str_crdr_balance + " /-");

                        } else {

                            Toast.makeText(getContext(), statusdat, Toast.LENGTH_SHORT).show();

                            walletamount.setVisibility(View.GONE);
                        }
                    } else {

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
                Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();
                error.printStackTrace();

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", user_id);

                Log.d("userid123", user_id);

                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }

    public void paymentmsg(String order_id) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURL.orderstatus, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");

                    if (status.equals("200")) {

                        String error = jsonObject.getString("error");
                        String data = jsonObject.getString("data");

                        JSONArray jsonArray_data = new JSONArray(data);
                        JSONObject jsonObject1_data = jsonArray_data.getJSONObject(0);
                        String order_id = jsonObject1_data.getString("order_id");
                        String user_id = jsonObject1_data.getString("user_id");
                        String order_status = jsonObject1_data.getString("order_status");

                        Log.d("order_statusgg",order_status);

                        if (order_status.equals("Success")) {

                            Fragment fragment = new HomeFragment();
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.nav_host_fragment, fragment, "HomeFragment");
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();

                        }

                    } else {

                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String cart_count = jsonObject_message.getString("status");

                        Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();

/*                if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                    Toast.makeText(getApplicationContext(), "Please check Internet Connection", Toast.LENGTH_SHORT).show();

                } else {

                    Log.d("successresponceVolley", "" + error.networkResponse.statusCode);
                    NetworkResponse networkResponse = error.networkResponse;
                    if (networkResponse != null && networkResponse.data != null) {
                        try {
                            String jError = new String(networkResponse.data);
                            JSONObject jsonError = new JSONObject(jError);

                            String data = jsonError.getString("msg");
                            Toast.makeText(LoginPage.this, data, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("successresponceVolley", "" + e);
                        }


                    }

                }*/
            }
        }) {

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("order_id", order_id);

                Log.d("addressparameterlist", params.toString());

                return params;


            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
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

    public void applyCouponCode(String userid, String couponcode, String city_id, String amount) {

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Apply Coupon Wait...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURL.coupondata, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");

                    progressDialog.dismiss();

                    if (status.equals("200")) {

                        String error = jsonObject.getString("error");
                        String message = jsonObject.getString("message");
                        data = jsonObject.getString("data");

                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

                        subTotalPrice.setText("Rs " + strpriceDetails);
                        couponPrice.setText("Rs " + data);

                        Double price1 = Double.valueOf(strpriceDetails);
                        Double price2 = Double.valueOf(data);
                        price3 = price1 - price2;
                        grandTotalPrice.setText("Rs " + String.valueOf(price3));

                        dialog.dismiss();

                    } else {

                        String error = jsonObject.getString("error");
                        String message = jsonObject.getString("message");
                        String data = "0";

                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

                        subTotalPrice.setText("Rs " + strpriceDetails);
                        couponPrice.setText("Rs " + "0");
                        Double price1 = Double.valueOf(strpriceDetails);
                        Double price2 = Double.valueOf(data);
                        Double price3 = price1 - price2;
                        grandTotalPrice.setText("Rs " + String.valueOf(price3));

                        dialog.dismiss();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("cuponcode", couponcode);
                params.put("city_id", city_id);
                params.put("user_id", userid);
                params.put("amount", amount);

                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);

    }
}
