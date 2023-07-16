package in.co.macedon.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import in.co.macedon.R;
import in.co.macedon.adapters.MemberShipAdapter1;
import in.co.macedon.extras.AppURL;
import in.co.macedon.extras.SessionManager;
import in.co.macedon.fragments.ContactUsFragment;
import in.co.macedon.fragments.ScanFragment;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerClass extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final String TAG = "ScannerClass";
    ZXingScannerView zXingScannerView;
    String currentDate,currentTime,scannerText,userId;
    Dialog dialogConfirm;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        zXingScannerView = new ZXingScannerView(this);
        setContentView(zXingScannerView);

        getSupportActionBar().hide();

        sessionManager = new SessionManager(ScannerClass.this);
        userId = sessionManager.getUserID();
        currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

        Dexter.withContext(getApplicationContext()).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                zXingScannerView.startCamera();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                permissionToken.continuePermissionRequest();
            }
        }).check();
    }



    @Override
    protected void onPause() {
        super.onPause();

        zXingScannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();

        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }

    @Override
    public void handleResult(Result rawResult) {

        // Do something with the result here
        Log.v(TAG, rawResult.getText()); // Prints scan results
        Log.v(TAG, rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

        // If you would like to resume scanning, call this method below:
        zXingScannerView.resumeCameraPreview(this);

        scannerText =  rawResult.getText();

        scannerDetails(scannerText,userId,currentDate,currentTime);
    }

    public void scannerDetails(String qrcode_no,String user_id,String date,String time){

        //ProgressDialog progressDialog = new ProgressDialog(ScannerClass.this);
        //progressDialog.setMessage("Scanner Details Details");
       // progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURL.scanqrcode, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

               // progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String status = jsonObject.getString("status");

                    if (status.equals("200")){

                        String error = jsonObject.getString("error");
                        String message = jsonObject.getString("message");
                        String data = jsonObject.getString("data");
                        JSONObject jsonObject1_data = new JSONObject(data);
                        String center_id = jsonObject1_data.getString("center_id");
                        String user_id = jsonObject1_data.getString("user_id");
                        String date = jsonObject1_data.getString("date");
                        String time = jsonObject1_data.getString("time");
                        String commition_amount = jsonObject1_data.getString("commition_amount");
                        String user_membership_history_id = jsonObject1_data.getString("user_membership_history_id");

                        Toast.makeText(ScannerClass.this, message, Toast.LENGTH_SHORT).show();

                        dialogConfirm = new Dialog(ScannerClass.this);
                        dialogConfirm.getWindow().setWindowAnimations(R.style.DialogAnimation);
                        dialogConfirm.setContentView(R.layout.successdialog);
                        dialogConfirm.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                        // dialogConfirm.setCancelable(false);
                        //dialogConfirm.setCanceledOnTouchOutside(true);
                        Window window = dialogConfirm.getWindow();
                        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                        Button btn_Done = dialogConfirm.findViewById(R.id.btn_Done);

                        btn_Done.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                ScanFragment contactUsFragment = new ScanFragment();
                                ft.replace(R.id.nav_host_fragment, contactUsFragment);
                                ft.addToBackStack(null);
                                ft.commit();

                                dialogConfirm.dismiss();
                            }
                        });


                        dialogConfirm.show();

                    }else{

                        String error = jsonObject.getString("error");
                        String message = jsonObject.getString("message");

                        Toast.makeText(ScannerClass.this, message, Toast.LENGTH_SHORT).show();

                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ScanFragment contactUsFragment = new ScanFragment();
                        ft.replace(R.id.nav_host_fragment, contactUsFragment);
                        ft.addToBackStack(null);
                        ft.commit();

                        dialogConfirm.dismiss();
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

               // progressDialog.dismiss();
                Toast.makeText(ScannerClass.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();
                params.put("qrcode_no",qrcode_no);
                params.put("user_id",user_id);
                params.put("date",date);
                params.put("time",time);
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(ScannerClass.this);
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }
}