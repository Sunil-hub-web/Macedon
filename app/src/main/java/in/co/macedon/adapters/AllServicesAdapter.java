package in.co.macedon.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.co.macedon.R;
import in.co.macedon.models.AllServicesModel;

public class AllServicesAdapter extends RecyclerView.Adapter<AllServicesAdapter.ViewHolder> {

    Context context;
    ArrayList<AllServicesModel> allServicesModels;
    public AllServicesAdapter(ArrayList<AllServicesModel> allServicesModels, Context context) {

        this.context = context;
        this.allServicesModels = allServicesModels;
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
    }

    @Override
    public int getItemCount() {
        return allServicesModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ShapeableImageView shapeImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            shapeImageView = itemView.findViewById(R.id.shapeImageView);
        }
    }
}
