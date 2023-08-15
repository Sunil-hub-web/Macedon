package in.co.macedon.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.co.macedon.R;
import in.co.macedon.models.OfferModelClass;
import in.co.macedon.models.SingleCenterActivityModel;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolder> {

    ArrayList<OfferModelClass> offerModelClasses;
    Context context;
    public OfferAdapter(ArrayList<OfferModelClass> offerModelClasses, Context context) {

        this.context = context;
        this.offerModelClasses = offerModelClasses;
    }

    @NonNull
    @Override
    public OfferAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offershowpage,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferAdapter.ViewHolder holder, int position) {

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
