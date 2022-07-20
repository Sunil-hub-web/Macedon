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
import in.co.macedon.models.TimeSlotGetSet;

public class TimeslotAdapter extends RecyclerView.Adapter<TimeslotAdapter.ProgramViewHolder> {

    private ArrayList<TimeSlotGetSet> fooditem;
    Context context;
    private int currentSelectedPosition = RecyclerView.NO_POSITION, oldposition;

    public TimeslotAdapter(ArrayList<TimeSlotGetSet> fooditem, Context context) {
        this.fooditem = fooditem;
        this.context = context;

    }

    @NonNull
    @Override
    public TimeslotAdapter.ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.timeslot_item, viewGroup, false);

        return new TimeslotAdapter.ProgramViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TimeslotAdapter.ProgramViewHolder programViewHolder, final int i) {
        final TimeSlotGetSet My_list = fooditem.get(i);

        programViewHolder.exname.setText(My_list.getTimeslots());

        if(currentSelectedPosition==i){
            Log.d("ghtxzrfgB_a : ",""+ currentSelectedPosition);
            Log.d("ghtxzrfgB_b : ",""+ i);

            programViewHolder.exname.setTextColor(ContextCompat.getColor(context, R.color.white));
            programViewHolder.mainlayout.setBackgroundResource(R.drawable.shop_filter_selected);
            programViewHolder.icon.setColorFilter(ContextCompat.getColor(context, R.color.white));
//            programViewHolder.icon.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);

        }else{
            programViewHolder.exname.setTextColor(ContextCompat.getColor(context, R.color.textcol));
//            programViewHolder.exname.setBackgroundColor(context.getResources().getColor(R.color.transparent));
            programViewHolder.mainlayout.setBackgroundResource(R.drawable.selection_drawable);
            programViewHolder.icon.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimaryDark));
//            programViewHolder.icon.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimaryDark), android.graphics.PorterDuff.Mode.MULTIPLY);
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
        ImageView icon;

        public ProgramViewHolder(@NonNull View itemView) {
            super(itemView);
            exname = (TextView) itemView.findViewById(R.id.name);
            mainlayout = (LinearLayout) itemView.findViewById(R.id.mainlayout);
            icon = (ImageView) itemView.findViewById(R.id.icon);
        }
    }


}