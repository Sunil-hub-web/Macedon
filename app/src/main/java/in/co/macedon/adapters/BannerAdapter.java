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

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.ViewHolder> {

    Context context;
    ArrayList<Banner_ModelClass> showBanner;
    ViewPager2 viewPager2;

    public BannerAdapter(Context context, ArrayList<Banner_ModelClass> banner, ViewPager2 viewpagerBanner) {

        this.context = context;
        this.showBanner = banner;
        viewPager2 = viewpagerBanner;

    }

    @NonNull
    @Override
    public BannerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  BannerAdapter.ViewHolder holder, int position) {

        Banner_ModelClass slideImage = showBanner.get(position);

       /* int image = slideImage.getImage();
        int strImage = Integer.valueOf(image);*/

        String image = "https://www.macedon.in/uploads/"+slideImage.getBanner_image();
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

        public ViewHolder(@NonNull  View itemView) {
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
