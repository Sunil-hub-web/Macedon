 package in.co.macedon.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import in.co.macedon.R;
import in.co.macedon.extras.AppURL;
import in.co.macedon.extras.SessionManager;
import in.co.macedon.fragments.UserProfileDetails;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import com.chaos.view.PinView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OTPVerification extends AppCompatActivity {

    TextView verifyotp_btn;
    String phone_number,OTPV;
    SessionManager sessionManager;
    PinView pinView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p_verification);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sessionManager = new SessionManager(this);

        phone_number = sessionManager.getUserMobileno();
        OTPV = sessionManager.getUserOTP();


        verifyotp_btn = findViewById(R.id.verifyotp_btn);
        pinView = findViewById(R.id.pinView);
        verifyotp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pinView.getItemCount() !=6){

                    Toast.makeText(OTPVerification.this, "Enter Your Otp", Toast.LENGTH_SHORT).show();

                }else{

                    String optDet = pinView.getText().toString().trim();

                    if (optDet.equals(OTPV)){

                        getUserDetails(phone_number);

                    }else{

                        Toast.makeText(OTPVerification.this, "Enter Your Valide Otp", Toast.LENGTH_SHORT).show();

                    }

                }

              /*  Intent i = new Intent(getApplicationContext(), DashBoard.class);
                startActivity(i);
                finish();*/

            }
        });
    }

    public void getUserDetails(String mobileNo){

        ProgressDialog progressDialog = new ProgressDialog(OTPVerification.this);
        progressDialog.setMessage("User Details Please Wait...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURL.getUserData, new Response.Listener<String>() {
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
                        String successstatus = jsonObject_message.getString("status");
                        JSONObject jsonObject_status = new JSONObject(successstatus);
                        String user_id = jsonObject_status.getString("user_id");
                        String fullname = jsonObject_status.getString("fullname");
                        String email = jsonObject_status.getString("email");
                        String contact = jsonObject_status.getString("contact");
                        String isLoggedIn = jsonObject_status.getString("isLoggedIn");

                        sessionManager.setUserId(user_id);
                        sessionManager.setUserName(fullname);
                        sessionManager.setUserMobileNO(contact);
                        sessionManager.setuserEmail(email);

                        sessionManager.setLogin();

                        Toast.makeText(OTPVerification.this, "Login In SuccessFully", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(getApplicationContext(), DashBoard.class);
                        i.putExtra("not_updated","not_updated");
                        startActivity(i);
                        finish();

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
                Toast.makeText(OTPVerification.this, ""+error, Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("contact",mobileNo);
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000,3,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(OTPVerification.this);
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i = new Intent(getApplicationContext(), MobileLogin.class);
        startActivity(i);
    }
}
