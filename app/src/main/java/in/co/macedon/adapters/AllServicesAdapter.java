package in.co.macedon.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.co.macedon.R;
import in.co.macedon.fragments.Category_Fragment;
import in.co.macedon.models.AllServicesModel;

public class AllServicesAdapter extends RecyclerView.Adapter<AllServicesAdapter.ViewHolder> {

    Context context;
    ArrayList<AllServicesModel> allServicesModels;
    String cityid;
    public AllServicesAdapter(ArrayList<AllServicesModel> allServicesModels, Context context, String cityid) {

        this.context = context;
        this.allServicesModels = allServicesModels;
        this.cityid = cityid;
    }

    @NonNull
    @Override
    public AllServicesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.showcategoryimage,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllServicesAdapter.ViewHolder holder, int position) {

        AllServicesModel servicesModel = allServicesModels.get(position);

        String image = "https://www.macedon.in/uploads/"+servicesModel.getImage();
        Picasso.with(context).load(image).into(holder.shapeImageView);

        holder.centername.setText(servicesModel.getService_master_name());

        holder.categorydata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Category_Fragment category_fragment = new Category_Fragment();
                Bundle bundle = new Bundle();
                bundle.putString("Service_master_id",servicesModel.getService_master_id());
                bundle.putString("City_id",cityid);
                category_fragment.setArguments(bundle);
                FragmentTransaction transaction =((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, category_fragment); // Add your fragment class
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return allServicesModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ShapeableImageView shapeImageView;
        TextView centername;
        LinearLayout categorydata;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            shapeImageView = itemView.findViewById(R.id.shapeImageView);
            centername = itemView.findViewById(R.id.centername);
            categorydata = itemView.findViewById(R.id.categorydata);
        }
    }
}
