package in.co.macedon.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import in.co.macedon.R;
import in.co.macedon.models.ShopGetSet;

public class ShopItemAdapter extends RecyclerView.Adapter<ShopItemAdapter.ProgramViewHolder> {

    private ArrayList<ShopGetSet> fooditem;
    Context context;
    private int currentSelectedPosition = RecyclerView.NO_POSITION, oldposition;

    public ShopItemAdapter(ArrayList<ShopGetSet> fooditem, Context context) {
        this.fooditem = fooditem;
        this.context = context;

    }

    @NonNull
    @Override
    public ShopItemAdapter.ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.shop_items, viewGroup, false);

        return new ShopItemAdapter.ProgramViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShopItemAdapter.ProgramViewHolder programViewHolder, final int i) {
        final ShopGetSet My_list = fooditem.get(i);

        programViewHolder.itemname.setText(My_list.getName());
        programViewHolder.salesprice.setText("Rs."+My_list.getSalesprice());
        programViewHolder.regularprice.setText("Rs."+My_list.getRegularprice());
        programViewHolder.discount.setText(My_list.getDiscount());

        programViewHolder.regularprice.setPaintFlags(programViewHolder.regularprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        Glide.with(context).load(My_list.getImage()).into(programViewHolder.itemimage);

        programViewHolder.foodratingbar.setRating(Float.parseFloat(My_list.getRating()));

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
        TextView itemname, salesprice, regularprice, discount;
        ImageView itemimage;
        RatingBar foodratingbar;

        public ProgramViewHolder(@NonNull View itemView) {
            super(itemView);
            itemname = (TextView) itemView.findViewById(R.id.itemname);
            salesprice = (TextView) itemView.findViewById(R.id.salesprice);
            regularprice = (TextView) itemView.findViewById(R.id.regularprice);
            discount = (TextView) itemView.findViewById(R.id.discount);
            itemimage = (ImageView) itemView.findViewById(R.id.itemimage);
            foodratingbar = (RatingBar) itemView.findViewById(R.id.foodratingbar);
        }
    }


}