package in.co.macedon.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import in.co.macedon.R;
import in.co.macedon.models.ReviewRatingModel;

public class ReviewRatingAdapter extends RecyclerView.Adapter<ReviewRatingAdapter.ViewHolder> {

    Context context;
    ArrayList<ReviewRatingModel> reviewRatingModels;
    public ReviewRatingAdapter(ArrayList<ReviewRatingModel> reviewRatingModels, Context context) {

        this.reviewRatingModels = reviewRatingModels;
        this.context = context;

    }

    @NonNull
    @Override
    public ReviewRatingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reviewratingshow,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewRatingAdapter.ViewHolder holder, int position) {

        ReviewRatingModel review = reviewRatingModels.get(position);

        String image = "https://www.macedon.in/uploads/"+review.getProfile_image();
        Picasso.with(context).load(image).into(holder.nav_profile_image);
        holder.text_showServiceName.setText(review.getService_master_name());
        holder.text_showReview.setText(review.getReview());

        float stringfloat = Float.valueOf(review.getRating());
        holder.showRatingbar.setRating(stringfloat);

    }

    @Override
    public int getItemCount() {
        return reviewRatingModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView nav_profile_image;
        TextView text_showReview,text_showServiceName;
        RatingBar showRatingbar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nav_profile_image = itemView.findViewById(R.id.nav_profile_image);
            text_showReview = itemView.findViewById(R.id.text_showReview);
            text_showServiceName = itemView.findViewById(R.id.text_showServiceName);
            showRatingbar = itemView.findViewById(R.id.showRatingbar);
        }
    }
}
