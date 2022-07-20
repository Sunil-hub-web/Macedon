package in.co.macedon.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
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
import in.co.macedon.models.DieticianGetSet;

//
public class DieticianAdapter extends RecyclerView.Adapter<DieticianAdapter.ProgramViewHolder> {

    public String[] mColors = {"#6E56AC","#F7C21E","#08A997"};
    private ArrayList<DieticianGetSet> fooditem;
    Context context;

    public DieticianAdapter(ArrayList<DieticianGetSet> fooditem, Context context) {
        this.fooditem = fooditem;
        this.context = context;

    }

    @NonNull
    @Override
    public DieticianAdapter.ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.dietician_item, viewGroup, false);

        return new DieticianAdapter.ProgramViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DieticianAdapter.ProgramViewHolder programViewHolder, final int i) {
        final DieticianGetSet My_list = fooditem.get(i);

        String nm = My_list.getName();
        if(nm.contains(" ")){
            nm= nm.replace(" ", "\n");
        }

        programViewHolder.title.setText(nm);
        programViewHolder.regularprice.setText("Rs."+My_list.getRegularprice());
        programViewHolder.salesprice.setText("Rs."+My_list.getSalesprice());

        programViewHolder.regularprice.setPaintFlags(programViewHolder.regularprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        programViewHolder.payconsult.setBackgroundColor(Color.parseColor(mColors[i % 3]));
        programViewHolder.vw.setBackgroundColor(Color.parseColor(mColors[i % 3]));
        programViewHolder.salesprice.setTextColor(Color.parseColor(mColors[i % 3]));
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
        TextView salesprice, regularprice, title, payconsult;
        View vw;

        public ProgramViewHolder(@NonNull View itemView) {
            super(itemView);
            salesprice = (TextView) itemView.findViewById(R.id.salesprice);
            regularprice = (TextView) itemView.findViewById(R.id.regularprice);
            title = (TextView) itemView.findViewById(R.id.title);
            payconsult = (TextView) itemView.findViewById(R.id.payconsult);
            vw = (View) itemView.findViewById(R.id.vw);


        }
    }


}