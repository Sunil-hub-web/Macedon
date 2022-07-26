package in.co.macedon.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import in.co.macedon.R;
import in.co.macedon.models.TrainerFilterGetSet;

public class TrainerFilterAdapter extends RecyclerView.Adapter<TrainerFilterAdapter.ProgramViewHolder> {

    private ArrayList<TrainerFilterGetSet> fooditem;
    Context context;
    private int currentSelectedPosition = RecyclerView.NO_POSITION, oldposition;

    public TrainerFilterAdapter(ArrayList<TrainerFilterGetSet> fooditem, Context context) {
        this.fooditem = fooditem;
        this.context = context;

    }

    @NonNull
    @Override
    public TrainerFilterAdapter.ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.trainer_filter_item, viewGroup, false);

        return new TrainerFilterAdapter.ProgramViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TrainerFilterAdapter.ProgramViewHolder programViewHolder, final int i) {
        final TrainerFilterGetSet My_list = fooditem.get(i);

        programViewHolder.exname.setText(My_list.getName());
        programViewHolder.icon.setText(My_list.getName());

        if(currentSelectedPosition==i){
            Log.d("ghtxzrfgB_a : ",""+ currentSelectedPosition);
            Log.d("ghtxzrfgB_b : ",""+ i);

            programViewHolder.exname.setTextColor(ContextCompat.getColor(context, R.color.white));
            programViewHolder.mainlayout.setBackgroundResource(R.drawable.shop_filter_selected);

        }else{
            programViewHolder.exname.setTextColor(ContextCompat.getColor(context, R.color.textcol));
//            programViewHolder.exname.setBackgroundColor(context.getResources().getColor(R.color.transparent));
            programViewHolder.mainlayout.setBackgroundResource(R.drawable.shop_filter_item_bg);
        }

        programViewHolder.mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentSelectedPosition = programViewHolder.getAdapterPosition();
                Log.d("ghtxzrfgB_c : ",""+ currentSelectedPosition);
                notifyDataSetChanged();
            }
        });

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
        TextView exname;
        LinearLayout mainlayout;
        TextView icon;

        public ProgramViewHolder(@NonNull View itemView) {
            super(itemView);
            exname = (TextView) itemView.findViewById(R.id.name);
            mainlayout = (LinearLayout) itemView.findViewById(R.id.mainlayout);
            icon = (TextView) itemView.findViewById(R.id.icon);
        }
    }


}