package in.co.macedon.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.co.macedon.R;
import in.co.macedon.models.CenterServicesModel;

public class CenterDetailsAdapter extends RecyclerView.Adapter<CenterDetailsAdapter.ViewHolder> {

    Context context;
    ArrayList<CenterServicesModel> servicesModels;
    public CenterDetailsAdapter(Context context, ArrayList<CenterServicesModel> servicesModels) {

        this.context = context;
        this.servicesModels = servicesModels;
    }

    @NonNull
    @Override
    public CenterDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.center_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CenterDetailsAdapter.ViewHolder holder, int position) {

        CenterServicesModel centerModel = servicesModels.get(position);

        String image = "https://www.macedon.in/uploads/"+centerModel.getProfile_image();
        Picasso.with(context).load(image).into(holder.img_Category);

        holder.centerName.setText(centerModel.getCenter_name());
        holder.centerLoc.setText(centerModel.getCity_name()+",  "+centerModel.getAreaname());
    }

    @Override
    public int getItemCount() {
        return servicesModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_Category;
        TextView centerName,centerLoc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_Category = itemView.findViewById(R.id.img_Category);
            centerName = itemView.findViewById(R.id.centerName);
            centerLoc = itemView.findViewById(R.id.centerLoc);
        }
    }
}
