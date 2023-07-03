package in.co.macedon.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import in.co.macedon.adapters.MemberShipAdapter;
import in.co.macedon.extras.AppURL;
import in.co.macedon.models.MemberShipModel;

public class GymMembership extends Fragment {

    ArrayList<MemberShipModel> memberShip_Models = new ArrayList<>();
    RecyclerView membershipRecycler;
    LinearLayoutManager linearLayoutManager;
    MemberShipAdapter memberShipAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.membership_frag, container, false);
        membershipRecycler = view.findViewById(R.id.membershipRecycler);

        getMemberShip();

        return view;
    }

    public void getMemberShip() {

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Get MemberShip Details Wait......");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURL.macedon_membership, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");

                    if (status.equals("200")) {

                        memberShip_Models.clear();

                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String data = jsonObject_message.getString("data");
                        JSONObject jsonObject_data = new JSONObject(data);
                        String membership_dtl = jsonObject_data.getString("membership_dtl");
                        JSONArray jsonArray_member = new JSONArray(membership_dtl);

                        if (jsonArray_member.length() != 0) {

                            for (int i = 0; i < jsonArray_member.length(); i++) {

                                JSONObject jsonObject_member = jsonArray_member.getJSONObject(i);
                                String package_id = jsonObject_member.getString("package_id");
                                String package_name = jsonObject_member.getString("package_name");
                                String package_duration = jsonObject_member.getString("package_duration");
                                String package_price = jsonObject_member.getString("package_price");
                                String package_description = jsonObject_member.getString("package_description");
                                String service_category = jsonObject_member.getString("service_category");

                                MemberShipModel memberShipModel = new MemberShipModel(
                                        package_id, package_name, package_duration, package_price, package_description, service_category,"",""
                                );

                                memberShip_Models.add(memberShipModel);
                            }

                            linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                            memberShipAdapter = new MemberShipAdapter(memberShip_Models,getContext(), "GyamMemberShip");
                            membershipRecycler.setLayoutManager(linearLayoutManager);
                            membershipRecycler.setHasFixedSize(true);
                            membershipRecycler.smoothScrollToPosition(0);
                            membershipRecycler.setAdapter(memberShipAdapter);
                        }
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

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);

    }
}
