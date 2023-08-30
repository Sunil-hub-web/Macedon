package in.co.macedon.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

import in.co.macedon.R;
import in.co.macedon.activities.DashBoard;
import in.co.macedon.extras.AppURL;

public class AboutFragment extends Fragment {

    TextView text_header,text_details;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.aboutfragment,container,false);

        text_header = view.findViewById(R.id.text_header);
        text_details = view.findViewById(R.id.text_details);

        DashBoard.header.setVisibility(View.GONE);
        DashBoard.header1.setVisibility(View.VISIBLE);

        aboutAsData();

        return view;
    }

    public void aboutAsData(){

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("About As Details");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppURL.cms, new Response.Listener<String>() {
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
                        String statusdata = jsonObject_message.getString("status");
                        JSONObject jsonObject_statusdata = new JSONObject(statusdata);
                        String cms = jsonObject_statusdata.getString("cms");
                        JSONArray jsonArray_cms = new JSONArray(cms);

                        for (int i=0;i<jsonArray_cms.length();i++){

                            JSONObject jsonObject_cms = jsonArray_cms.getJSONObject(i);
                            String page_name = jsonObject_cms.getString("page_name");

                            if (page_name.equals("About Us")){

                                String details = jsonObject_cms.getString("details");

                                text_header.setText(page_name);
                                text_details.setText(Html.fromHtml(details));

                            }
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
                Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();

            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }
}
