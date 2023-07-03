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
import in.co.macedon.models.CenterTimeingSlot;

public class TimeslotAdapter extends RecyclerView.Adapter<TimeslotAdapter.ProgramViewHolder> {

    private ArrayList<CenterTimeingSlot> centerTimeingSlots;
    Context context;
    private int currentSelectedPosition = RecyclerView.NO_POSITION, oldposition;

    public TimeslotAdapter(ArrayList<CenterTimeingSlot> fooditem, Context context) {
        this.centerTimeingSlots = fooditem;
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

        CenterTimeingSlot currenttime = centerTimeingSlots.get(i);
        programViewHolder.exname.setText(currenttime.getFromtime()+"-"+currenttime.getTotime()+"  "+currenttime.getDay());
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {

        return centerTimeingSlots.size();
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