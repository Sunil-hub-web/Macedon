package in.co.macedon.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

import in.co.macedon.R;
import in.co.macedon.models.AddedsModel;
import in.co.macedon.models.SingleCentergalleryModel;

public class AddedsAdapter extends RecyclerView.Adapter<AddedsAdapter.ViewHolder> {

    Context context;
    ArrayList<AddedsModel> showBanner;
    ViewPager2 viewPager2;
    public AddedsAdapter(Context context, ArrayList<AddedsModel> addedsModels, ViewPager2 viewpagerAddeds) {

        this.context = context;
        this.showBanner = addedsModels;
        this.viewPager2 = viewpagerAddeds;
    }

    @NonNull
    @Override
    public AddedsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_items,parent,false);
        return new AddedsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddedsAdapter.ViewHolder holder, int position) {

        AddedsModel addedsModel = showBanner.get(position);
        holder.img_showImage.setImageResource(addedsModel.getImage());

        if(position == showBanner.size() - 1){

            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return showBanner.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_showImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_showImage = itemView.findViewById(R.id.img_showImage);
        }
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            showBanner.addAll(showBanner);
            notifyDataSetChanged();
        }
    };
}
