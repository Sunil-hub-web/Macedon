package in.co.macedon.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.co.macedon.R;
import in.co.macedon.activities.Dietician;
import in.co.macedon.activities.FitnessCentre;
import in.co.macedon.activities.TrainerActivity;
import in.co.macedon.models.Category_ModelClass;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    Context context;
    ArrayList<Category_ModelClass> category_daetails;

    public CategoryAdapter(ArrayList<Category_ModelClass> categoryDetails, Context context) {

        this.context = context;
        this.category_daetails = categoryDetails;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cateogry_list,parent,false);
        return new CategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  CategoryAdapter.ViewHolder holder, int position) {

        Category_ModelClass category = category_daetails.get(position);

        Log.d("category_daetails",category_daetails.toString());

//        String image = "https://www.macedon.in/uploads/"+category.getCat_img();
//        Picasso.with(context).load(image).into(holder.img_Category);

        holder.productName.setText(category.getService_master_name());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        CenterDetailsAdapter centerDetailsAdapter = new CenterDetailsAdapter(context,category.getServicesModels());
        holder.showCenterRecycler.setLayoutManager(linearLayoutManager);
        holder.showCenterRecycler.setHasFixedSize(true);
        holder.showCenterRecycler.setAdapter(centerDetailsAdapter);

    }

    @Override
    public int getItemCount() {
        return category_daetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_Category;
        TextView productName;
        RecyclerView showCenterRecycler;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.productName);
            showCenterRecycler = itemView.findViewById(R.id.showCenterRecycler);
        }
    }
}
