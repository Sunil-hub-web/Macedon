package in.co.macedon.activities;

import androidx.appcompat.app.AppCompatActivity;
import in.co.macedon.R;
import in.co.macedon.extras.AppURL;
import in.co.macedon.extras.SessionManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MobileLogin extends AppCompatActivity {

    TextView sendotp_btn;
    EditText phone_number_ed;
    SessionManager sessionManager;
    String mobileno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_login);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sessionManager = new SessionManager(this);

        sendotp_btn = findViewById(R.id.sendotp_btn);
        phone_number_ed = findViewById(R.id.phone_number_ed);
        sendotp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(phone_number_ed.getText())){

                    phone_number_ed.setError("Enter Mobile No");

                }else if(phone_number_ed.getText().toString().trim().length() !=10){

                    phone_number_ed.setError("Enter 10 digit Mobile No");

                }else{

                    mobileno = phone_number_ed.getText().toString().trim();
                    userLogin(mobileno);

                }

               /* Intent i = new Intent(getApplicationContext(), OTPVerification.class);
                startActivity(i);*/

            }
        });
    }

    public void userLogin(String mobileNo){

        ProgressDialog progressDialog = new ProgressDialog(MobileLogin.this);
        progressDialog.setMessage("OTP Send Your Mobile No");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURL.userLogin, new Response.Listener<String>() {
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
                        String contact_otp = jsonObject_status.getString("contact_otp");
                        String login_otp = jsonObject_status.getString("login_otp");

                        sessionManager.setUserOTP(login_otp);
                        sessionManager.setUserMobileNO(contact_otp);


                        Toast.makeText(MobileLogin.this, "Otp Send Success", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(getApplicationContext(), OTPVerification.class);
                        startActivity(i);

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
                Toast.makeText(MobileLogin.this, ""+error, Toast.LENGTH_SHORT).show();

                Log.d("error",error.toString());

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
        RequestQueue requestQueue = Volley.newRequestQueue(MobileLogin.this);
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i = new Intent(getApplicationContext(), Login.class);
        startActivity(i);
    }
}
