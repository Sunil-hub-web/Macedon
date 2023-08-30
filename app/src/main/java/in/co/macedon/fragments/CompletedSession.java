package in.co.macedon.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
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
import java.util.HashMap;
import java.util.Map;

import in.co.macedon.R;
import in.co.macedon.activities.DashBoard;
import in.co.macedon.adapters.CompleteSessionAdapter;
import in.co.macedon.adapters.SubscriptionAdapter;
import in.co.macedon.extras.AppURL;
import in.co.macedon.extras.SessionManager;
import in.co.macedon.models.CompleteSession_ModelClass;
import in.co.macedon.models.Subscriptions_ModelClass;

public class CompletedSession extends Fragment {

    RecyclerView recyclerCompleteSession;
    CompleteSessionAdapter completeSessionAdapter;
    LinearLayoutManager linearLayoutManager;
    ArrayList<CompleteSession_ModelClass> comp_session = new ArrayList<>();
    SessionManager sessionManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater,
                             @Nullable  ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.completedsession_fragment,container,false);

        recyclerCompleteSession = view.findViewById(R.id.recyclerCompleteSession);
        sessionManager = new SessionManager(getActivity());

        DashBoard.header.setVisibility(View.GONE);
        DashBoard.header1.setVisibility(View.VISIBLE);

        userCompletedsession(sessionManager.getUserID());

        return view;
    }

    public void userCompletedsession(String user_id){

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Subscription Details Please Wait.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURL.completedsession, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String status = jsonObject.getString("status");

                    if (status.equals("200")){

                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String statusdat = jsonObject_message.getString("status");

                        if (responsecode.equals("00")){

                            JSONObject jsonObject1_status = new JSONObject(statusdat);
                            String completedsession = jsonObject1_status.getString("completedsession");
                            JSONArray jsonArray_completedsession = new JSONArray(completedsession);

                            for (int i=0;i<jsonArray_completedsession.length();i++){

                                JSONObject jsonObject_completedsession = jsonArray_completedsession.getJSONObject(i);

                                String scan_id = jsonObject_completedsession.getString("scan_id");
                                String center_id = jsonObject_completedsession.getString("center_id");
                                String user_id = jsonObject_completedsession.getString("user_id");
                                String date = jsonObject_completedsession.getString("date");
                                String time = jsonObject_completedsession.getString("time");
                                String user_membership_history_id = jsonObject_completedsession.getString("user_membership_history_id");
                                String commition_amount = jsonObject_completedsession.getString("commition_amount");
                                String center_name = jsonObject_completedsession.getString("center_name");


                                CompleteSession_ModelClass completeSession_modelClass = new CompleteSession_ModelClass(
                                        scan_id,center_id,user_id,date,time,user_membership_history_id,
                                        commition_amount,center_name
                                    );

                                    comp_session.add(completeSession_modelClass);

                            }

                            linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                            completeSessionAdapter = new CompleteSessionAdapter(comp_session,getActivity());
                            recyclerCompleteSession.setLayoutManager(linearLayoutManager);
                            recyclerCompleteSession.setHasFixedSize(true);
                            recyclerCompleteSession.setItemAnimator(new DefaultItemAnimator());
                            recyclerCompleteSession.setAdapter(completeSessionAdapter);

                        }else{

                            Toast.makeText(getContext(), statusdat, Toast.LENGTH_SHORT).show();
                        }
                    }else{

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
                Toast.makeText(getActivity(), ""+error, Toast.LENGTH_SHORT).show();
                error.printStackTrace();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("user_id",user_id);
                Log.d("userid",user_id);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000,3,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}
