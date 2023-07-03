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
import in.co.macedon.models.SingleCenterAmetiesModel;

public class CenterAmetiesAdapter extends RecyclerView.Adapter<CenterAmetiesAdapter.ViewHolder> {

    Context context;
    ArrayList<SingleCenterAmetiesModel> centerAmetiesModels;

    public CenterAmetiesAdapter(ArrayList<SingleCenterAmetiesModel> centerAmetiesModels, Context context) {

        this.context = context;
        this.centerAmetiesModels = centerAmetiesModels;

    }

    @NonNull
    @Override
    public CenterAmetiesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ametiescenter,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CenterAmetiesAdapter.ViewHolder holder, int position) {

        SingleCenterAmetiesModel centerAmeties = centerAmetiesModels.get(position);

        String image = "https://www.macedon.in/uploads/"+centerAmeties.getImage();
        Picasso.with(context).load(image).into(holder.img_Category);

        holder.centerName.setText(centerAmeties.getFacilities_name());
    }

    @Override
    public int getItemCount() {
        return centerAmetiesModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_Category;
        TextView centerName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_Category = itemView.findViewById(R.id.img_Category);
            centerName = itemView.findViewById(R.id.centerName);
        }
    }
}
