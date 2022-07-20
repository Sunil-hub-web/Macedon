package in.co.macedon.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import in.co.macedon.R;
import in.co.macedon.models.TrainerGetSet;

public class TrainerAdapter extends RecyclerView.Adapter<TrainerAdapter.ProgramViewHolder> {

    private ArrayList<TrainerGetSet> fooditem;
    Context context;
    private int currentSelectedPosition = RecyclerView.NO_POSITION, oldposition;

    public TrainerAdapter(ArrayList<TrainerGetSet> fooditem, Context context) {
        this.fooditem = fooditem;
        this.context = context;

    }

    @NonNull
    @Override
    public TrainerAdapter.ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.trainer_item, viewGroup, false);

        return new TrainerAdapter.ProgramViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TrainerAdapter.ProgramViewHolder programViewHolder, final int i) {
        final TrainerGetSet My_list = fooditem.get(i);

        programViewHolder.itemname.setText(My_list.getName());
        programViewHolder.address.setText(My_list.getPost());
        programViewHolder.time.setText(My_list.getSpeciality());


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
        TextView itemname, address, time, active, addtofavourite;
        ImageView itemimage;

        public ProgramViewHolder(@NonNull View itemView) {
            super(itemView);
            itemname = (TextView) itemView.findViewById(R.id.itemname);
            address = (TextView) itemView.findViewById(R.id.address);
            time = (TextView) itemView.findViewById(R.id.time);
            active = (TextView) itemView.findViewById(R.id.active);
            addtofavourite = (TextView) itemView.findViewById(R.id.addtofavourite);
            itemimage = (ImageView) itemView.findViewById(R.id.itemimage);
        }
    }


}