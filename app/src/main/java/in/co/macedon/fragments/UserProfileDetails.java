package in.co.macedon.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.co.macedon.R;
import in.co.macedon.extras.AppURL;
import in.co.macedon.extras.SessionManager;

public class UserProfileDetails extends Fragment {

    EditText edit_userNmae, edit_emailId, edit_MobileNo,edit_Gender;
    String str_userNmae, str_emailId, str_MobileNo, str_Gender, userid, selectPaymentOption;
    RadioButton rdio_male, radio_female, selectedRadioButton;
    TextView btn_UpdateAddress,text_editOption;
    SessionManager sessionManager;
    RadioGroup radioGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.viewprofile, container, false);

        edit_userNmae = view.findViewById(R.id.edit_userNmae);
        edit_emailId = view.findViewById(R.id.edit_emailId);
        edit_MobileNo = view.findViewById(R.id.edit_MobileNo);
        rdio_male = view.findViewById(R.id.rdio_male);
        radio_female = view.findViewById(R.id.radio_female);
        btn_UpdateAddress = view.findViewById(R.id.btn_UpdateAddress);
        radioGroup = view.findViewById(R.id.radioGroup);
        text_editOption = view.findViewById(R.id.text_editOption);
        edit_Gender = view.findViewById(R.id.edit_Gender);

        sessionManager = new SessionManager(getContext());

        userid = sessionManager.getUserID();

        viewUserProfile(userid);

        text_editOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radioGroup.setVisibility(View.VISIBLE);
                edit_Gender.setVisibility(View.GONE);

                edit_emailId.setEnabled(true);
                edit_emailId.requestFocus();
                edit_userNmae.setEnabled(true);
                edit_userNmae.requestFocus();
                edit_Gender.setEnabled(false);
                edit_MobileNo.setEnabled(false);
            }
        });



        btn_UpdateAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

                if (TextUtils.isEmpty(edit_userNmae.getText())) {

                    edit_userNmae.setError("Fill The User Name");

                } else if (TextUtils.isEmpty(edit_emailId.getText())) {

                    edit_emailId.setError("Fill The EmailId Name");

                } else if (selectedRadioButtonId == -1) {

                    Toast.makeText(getContext(), "Please select RadioButton", Toast.LENGTH_SHORT).show();

                } else if (!isValidEmail(edit_emailId.getText().toString().trim())) {

                    edit_emailId.requestFocus();
                    edit_emailId.setError("Please Enter Valide Email id");

                } else {

                    selectedRadioButton = view.findViewById(selectedRadioButtonId);
                    selectPaymentOption = selectedRadioButton.getText().toString();

                    str_userNmae = edit_userNmae.getText().toString().trim();
                    str_emailId = edit_emailId.getText().toString().trim();


                    UpdateUserProfile(userid,str_userNmae,str_emailId,selectPaymentOption);


                }


            }
        });

        return view;
    }

    public void viewUserProfile(String userId) {

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Retrive User Details");
        progressDialog.show();

        String url = AppURL.viewuserProfile+userId;

        Log.d("url",url);

        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String status = jsonObject.getString("status");

                    if (status.equals("true")) {

                        String message = jsonObject.getString("message");

                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

                        String data = jsonObject.getString("data");

                        JSONArray jsonArray_data = new JSONArray(data);

                        for (int i = 0; i <= jsonArray_data.length(); i++) {

                            JSONObject jsonObject_data = jsonArray_data.getJSONObject(i);

                            String contact_no = jsonObject_data.getString("contact_no");
                            String id = jsonObject_data.getString("id");
                            String email = jsonObject_data.getString("email");
                            String gender = jsonObject_data.getString("gender");
                            String user_name = jsonObject_data.getString("user_name");

                            if(email.equals("null")){

                                radioGroup.setVisibility(View.VISIBLE);
                                edit_Gender.setVisibility(View.GONE);

                            }else{

                                edit_Gender.setVisibility(View.VISIBLE);
                                edit_Gender.setText(gender);
                                edit_emailId.setText(email);
                                edit_userNmae.setText(user_name);
                                radioGroup.setVisibility(View.GONE);

                            }

                            edit_emailId.setEnabled(false);
                            edit_userNmae.setEnabled(false);
                            edit_Gender.setEnabled(false);
                            edit_MobileNo.setEnabled(false);

                            edit_MobileNo.setText(contact_no);

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
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);


    }

    public void UpdateUserProfile(String userId, String userName, String emailId, String gender) {

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Retrive User Details");
        progressDialog.show();

        String url = AppURL.viewuserProfile + userId;

        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {

                    JSONObject jsonObject = new JSONObject(response);

                    String status = jsonObject.getString("status");

                    if (status.equals("true")) {

                        String message = jsonObject.getString("message");

                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

                        viewUserProfile(userId);

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
                params.put("user_name", userName);
                params.put("email", emailId);
                params.put("gender", gender);
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);


    }

    public boolean isValidEmail(final String email) {

        Pattern pattern;
        Matcher matcher;

        //final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";

        pattern = Patterns.EMAIL_ADDRESS;
        matcher = pattern.matcher(email);

        return matcher.matches();

        //return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}
