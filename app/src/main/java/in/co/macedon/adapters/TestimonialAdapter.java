package in.co.macedon.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.co.macedon.R;
import in.co.macedon.models.TestimonialModel;

public class TestimonialAdapter extends RecyclerView.Adapter<TestimonialAdapter.ViewHolder> {

    Context context;
    ArrayList<TestimonialModel> testimonialModels;
    public TestimonialAdapter(ArrayList<TestimonialModel> testimonialModels, Context context) {

        this.context = context;
        this.testimonialModels = testimonialModels;
    }

    @NonNull
    @Override
    public TestimonialAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.testimonial_message,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestimonialAdapter.ViewHolder holder, int position) {

        TestimonialModel riviewMessag = testimonialModels.get(position);
        holder.messageDet.setText(riviewMessag.getMessage());
        holder.reviewName.setText(riviewMessag.getName());

        String image = "https://www.macedon.in/uploads/"+riviewMessag.getImage();
        Picasso.with(context).load(image).into(holder.reviewImage);
    }

    @Override
    public int getItemCount() {
        return testimonialModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView messageDet,reviewName;
        ShapeableImageView reviewImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            messageDet = itemView.findViewById(R.id.messageDet);
            reviewName = itemView.findViewById(R.id.reviewName);
            reviewImage = itemView.findViewById(R.id.reviewImage);
        }
    }
}
