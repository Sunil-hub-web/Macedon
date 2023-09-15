package in.co.macedon.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import in.co.macedon.R;
import in.co.macedon.extras.AppURL;
import in.co.macedon.fragments.PackageFragment;
import in.co.macedon.models.OfferModelClass;

public class PackageOfferAdapter extends RecyclerView.Adapter<PackageOfferAdapter.ViewHolder> {

    ArrayList<OfferModelClass> offerModelClasses;
    Context context;

    public PackageOfferAdapter(ArrayList<OfferModelClass> offerModelClasses, Context context) {

        this.context = context;
        this.offerModelClasses = offerModelClasses;
    }

    @NonNull
    @Override
    public PackageOfferAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offershowpage,parent,false);
        return new PackageOfferAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PackageOfferAdapter.ViewHolder holder, int position) {

        OfferModelClass offer = offerModelClasses.get(position);

        String image = "https://www.macedon.in/uploads/"+offer.getImg();
        Picasso.with(context).load(image).into(holder.imageView_Offer);

    }

    @Override
    public int getItemCount() {
        return offerModelClasses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView_Offer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView_Offer = itemView.findViewById(R.id.imageView_Offer);
        }
    }


}
