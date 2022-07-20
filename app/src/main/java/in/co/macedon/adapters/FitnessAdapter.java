package in.co.macedon.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import in.co.macedon.R;
import in.co.macedon.models.FitnessCentreGetSet;

public class FitnessAdapter extends RecyclerView.Adapter<FitnessAdapter.ProgramViewHolder> {

    private ArrayList<FitnessCentreGetSet> fooditem;
    Context context;
    private int currentSelectedPosition = RecyclerView.NO_POSITION, oldposition;

    public FitnessAdapter(ArrayList<FitnessCentreGetSet> fooditem, Context context) {
        this.fooditem = fooditem;
        this.context = context;

    }

    @NonNull
    @Override
    public FitnessAdapter.ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.fitness_item, viewGroup, false);

        return new FitnessAdapter.ProgramViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FitnessAdapter.ProgramViewHolder programViewHolder, final int i) {
        final FitnessCentreGetSet My_list = fooditem.get(i);

        programViewHolder.itemname.setText(My_list.getName());
        programViewHolder.address.setText(My_list.getAddress());
        programViewHolder.rating.setText(My_list.getRating());
        programViewHolder.review.setText(My_list.getReview());
        programViewHolder.time.setText(My_list.getTiming());

        if(My_list.getGym().equalsIgnoreCase("1")){
            programViewHolder.gym.setVisibility(View.VISIBLE);
        }else{
            programViewHolder.gym.setVisibility(View.GONE);
        }
        if(My_list.getYoga().equalsIgnoreCase("1")){
            programViewHolder.yoga.setVisibility(View.VISIBLE);
        }else{
            programViewHolder.yoga.setVisibility(View.GONE);
        }
        if(My_list.getZumba().equalsIgnoreCase("1")){
            programViewHolder.zumba.setVisibility(View.VISIBLE);
        }else{
            programViewHolder.zumba.setVisibility(View.GONE);
        }





        Glide.with(context).load(My_list.getImage()).into(programViewHolder.itemimage);



    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {

        return fooditem.size();
    }

    public class ProgramViewHolder extends RecyclerView.ViewHolder {
        TextView itemname, address, rating, review, time, zumba, gym, yoga;
        ImageView itemimage;

        public ProgramViewHolder(@NonNull View itemView) {
            super(itemView);
            itemname = (TextView) itemView.findViewById(R.id.itemname);
            address = (TextView) itemView.findViewById(R.id.address);
            rating = (TextView) itemView.findViewById(R.id.rating);
            review = (TextView) itemView.findViewById(R.id.review);
            time = (TextView) itemView.findViewById(R.id.time);
            zumba = (TextView) itemView.findViewById(R.id.zumba);
            gym = (TextView) itemView.findViewById(R.id.gym);
            yoga = (TextView) itemView.findViewById(R.id.yoga);
            itemimage = (ImageView) itemView.findViewById(R.id.itemimage);
        }
    }


}