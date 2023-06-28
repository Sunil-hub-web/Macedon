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

    EditText edit_userNmae, edit_emailId, edit_MobileNo, edit_Gender;
    String str_userNmae, str_emailId, str_MobileNo, str_Gender, userid, selectPaymentOption;
    RadioButton rdio_male, radio_female, selectedRadioButton;
    TextView btn_UpdateAddress, text_editOption;
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
                    str_MobileNo = edit_MobileNo.getText().toString().trim();

                    if (selectPaymentOption.equals("Male")){

                        UpdateUserProfile(userid,str_userNmae,str_emailId,str_MobileNo,"1");

                    }else{

                        UpdateUserProfile(userid,str_userNmae,str_emailId,str_MobileNo,"2");
                    }



                }

            }
        });

        return view;
    }

    public void viewUserProfile(String userId) {

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("View User Details...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURL.view_profile, new Response.Listener<String>() {
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
                        String successstatus = jsonObject_message.getString("status");
                        JSONObject jsonObject_status = new JSONObject(successstatus);
                        String user_id = jsonObject_status.getString("user_id");
                        String fullname = jsonObject_status.getString("fullname");
                        String email = jsonObject_status.getString("email");
                        String contact = jsonObject_status.getString("contact");
                        String profile_image = jsonObject_status.getString("profile_image");
                        String gender = jsonObject_status.getString("gender");

                        if (gender.equals("1")){

                            edit_Gender.setText("Male");

                        }else{

                            edit_Gender.setText("FeMale");
                        }

                        radioGroup.setVisibility(View.GONE);
                        edit_Gender.setVisibility(View.VISIBLE);

                        edit_emailId.setText(email);
                        edit_userNmae.setText(fullname);
                        edit_MobileNo.setText(contact);

                        edit_emailId.setEnabled(false);
                        edit_userNmae.setEnabled(false);
                        edit_Gender.setEnabled(false);
                        edit_MobileNo.setEnabled(false);

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
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();
                params.put("user_id",userId);
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);


    }

    public void UpdateUserProfile(String user_id, String full_name, String e_mail, String contact_number, String gender) {

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Retrive User Details");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.PUT, AppURL.update_profile, new Response.Listener<String>() {
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
                        String statusmessage = jsonObject_message.getString("status");

                        Toast.makeText(getActivity(), statusmessage, Toast.LENGTH_SHORT).show();

                        viewUserProfile(user_id);

                    }else{

                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String statusmessage = jsonObject_message.getString("status");

                        Toast.makeText(getActivity(), statusmessage, Toast.LENGTH_SHORT).show();
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
                params.put("user_id", user_id);
                params.put("full_name", full_name);
                params.put("e_mail", e_mail);
                params.put("contact_number", contact_number);
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
