package in.co.macedon.adapters;

import android.content.Context;
import android.content.Intent;
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
import in.co.macedon.activities.DashBoard;
import in.co.macedon.activities.SearchPage;
import in.co.macedon.fragments.SingleProduct_Fragment;
import in.co.macedon.models.CenterServicesModel;
import in.co.macedon.models.SearchModel;

public class SerachPageAdapter extends RecyclerView.Adapter<SerachPageAdapter.ViewHolder> {

    Context context;
    ArrayList<SearchModel> searchModelsArrList;
    public SerachPageAdapter(SearchPage searchPage, ArrayList<SearchModel> servicesModelArrayList) {

        this.context = searchPage;
        this.searchModelsArrList = servicesModelArrayList;


    }

    @NonNull
    @Override
    public SerachPageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.serachpageactivity,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SerachPageAdapter.ViewHolder holder, int position) {

        SearchModel searchModel = searchModelsArrList.get(position);

        String image = "https://www.macedon.in/uploads/"+searchModel.getProfile_image();
        Picasso.with(context).load(image).into(holder.img_Category);

        holder.centerName.setText(searchModel.getCenter_name());
        holder.centerLoc.setText(searchModel.getCity_name()+",  "+searchModel.getAreaname());

        holder.cardViewDet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DashBoard.class);
                intent.putExtra("centerId",searchModel.getId());
                intent.putExtra("message","seracharea");
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return searchModelsArrList.size();
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
