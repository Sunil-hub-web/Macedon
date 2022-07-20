package in.co.macedon.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import in.co.macedon.R;
import in.co.macedon.activities.SplashScreen;
import in.co.macedon.models.ExploreMoreGetSet;

public class ExploreMoreAdapters extends RecyclerView.Adapter<ExploreMoreAdapters.ProgramViewHolder> {

    private ArrayList<ExploreMoreGetSet> fooditem;
    Context context;

    public ExploreMoreAdapters(ArrayList<ExploreMoreGetSet> fooditem, Context context) {
        this.fooditem = fooditem;
        this.context = context;

    }

    @NonNull
    @Override
    public ExploreMoreAdapters.ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.explore_item, viewGroup, false);

        return new ExploreMoreAdapters.ProgramViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ExploreMoreAdapters.ProgramViewHolder programViewHolder, final int i) {
        final ExploreMoreGetSet My_list = fooditem.get(i);

            programViewHolder.exname.setText(My_list.getName());

        Glide.with(context).load(My_list.getImage()).into(programViewHolder.eximage);
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
        ImageView eximage;

        public ProgramViewHolder(@NonNull View itemView) {
            super(itemView);
            exname = (TextView) itemView.findViewById(R.id.exname);
            eximage = (ImageView) itemView.findViewById(R.id.eximage);


        }
    }
    

}