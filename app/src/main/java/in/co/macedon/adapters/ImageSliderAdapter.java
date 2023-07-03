package in.co.macedon.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.co.macedon.R;
import in.co.macedon.models.Banner_ModelClass;
import in.co.macedon.models.SingleCentergalleryModel;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ViewHolder> {

    Context context;
    ArrayList<SingleCentergalleryModel> showBanner;
    ViewPager2 viewPager2;

    public ImageSliderAdapter(Context context, ArrayList<SingleCentergalleryModel> centergalleryModels,
                              ViewPager2 viewpagerBanner) {

        this.context = context;
        this.showBanner = centergalleryModels;
        this.viewPager2 = viewpagerBanner;
    }

    @NonNull
    @Override
    public ImageSliderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_items,parent,false);
        return new ImageSliderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageSliderAdapter.ViewHolder holder, int position) {

        SingleCentergalleryModel slideImage = showBanner.get(position);

        String image = "https://www.macedon.in/uploads/"+slideImage.getCente_image();
        Picasso.with(context).load(image).into(holder.img_showImage);

        if(position == showBanner.size() - 2){

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
