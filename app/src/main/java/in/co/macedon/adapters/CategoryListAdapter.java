package in.co.macedon.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.co.macedon.R;
import in.co.macedon.fragments.SingleProduct_Fragment;
import in.co.macedon.models.CenterServicesModel;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

    Context context;
    ArrayList<CenterServicesModel> servicesModels;

    public CategoryListAdapter(Context context, ArrayList<CenterServicesModel> servicesModels) {

        this.context = context;
        this.servicesModels = servicesModels;
    }

    @NonNull
    @Override
    public CategoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categorylist,parent,false);
        return new CategoryListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListAdapter.ViewHolder holder, int position) {

        CenterServicesModel centerModel = servicesModels.get(position);

        String image = "https://www.macedon.in/uploads/"+centerModel.getProfile_image();
        Picasso.with(context).load(image).into(holder.img_Category);

        holder.centerName.setText(centerModel.getCenter_name());
        holder.centerLoc.setText(centerModel.getCity_name()+",  "+centerModel.getAreaname());

        holder.cardViewDet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SingleProduct_Fragment singleProductFragment = new SingleProduct_Fragment();
                Bundle bundle = new Bundle();
                bundle.putString("centerId",centerModel.getCenter_id());
                singleProductFragment.setArguments(bundle);
                FragmentTransaction transaction =((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, singleProductFragment); // Add your fragment class
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return servicesModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_Category;
        TextView centerName,centerLoc;
        CardView cardViewDet;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_Category = itemView.findViewById(R.id.img_Category);
            centerName = itemView.findViewById(R.id.centerName);
            centerLoc = itemView.findViewById(R.id.centerLoc);
            cardViewDet = itemView.findViewById(R.id.cardViewDet);
        }
    }
}
