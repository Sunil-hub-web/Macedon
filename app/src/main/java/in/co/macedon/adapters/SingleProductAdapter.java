package in.co.macedon.adapters;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.co.macedon.R;
import in.co.macedon.extras.ItemClickListener;
import in.co.macedon.models.CenterTimeingSlot;
import in.co.macedon.models.MemberShipModel;
import in.co.macedon.models.SingleCenterActivityModel;

public class SingleProductAdapter extends RecyclerView.Adapter<SingleProductAdapter.ViewHolder> {


    Context context;
    ArrayList<SingleCenterActivityModel> centerActivityModelArrayList;
    ArrayList<MemberShipModel> centerPackageModelArrayList;
    ArrayList<MemberShipModel> centerPackageModel1;
    ArrayList<CenterTimeingSlot> centerTimeingModel1;
    ArrayList<String> centerPackageArray;
    Dialog dialogConfirm;
    LinearLayoutManager linearLayoutManager;
    MemberShipAdapter1 memberShipAdapter;
    TimeslotAdapter timeslotAdapter;

    private ItemClickListener clickListener;

    ArrayList<CenterTimeingSlot> centerTimeingSlots;
    String center_id;

    public SingleProductAdapter(Context context, ArrayList<SingleCenterActivityModel> centerActivityModels,
                                ArrayList<MemberShipModel> centerPackageModels, ArrayList<String> centerPackageArray,
                                ArrayList<CenterTimeingSlot> centerTimeingSlots, String center_id) {

        this.context = context;
        this.centerActivityModelArrayList = centerActivityModels;
        this.centerPackageModelArrayList = centerPackageModels;
        this.centerTimeingSlots = centerTimeingSlots;
        this.centerPackageArray = centerPackageArray;
        this.center_id = center_id;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public SingleProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.membershipname,parent,false);
        return new SingleProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleProductAdapter.ViewHolder holder, int position) {

        SingleCenterActivityModel centerActivity = centerActivityModelArrayList.get(position);

        Log.d("centerActivityModels12",centerActivityModelArrayList.toString());

        String image = "https://www.macedon.in/uploads/"+centerActivity.getImage();
        Picasso.with(context).load(image).into(holder.img_Category);
        holder.centerName.setText(centerActivity.getService_master_name());

    //    boolean isExpand = centerActivity.isExpanded();
   //     holder.expandableLayout.setVisibility(isExpand ? View.VISIBLE : View.GONE);



        
      /*  holder.memberShipPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                centerActivity.setExpanded(!centerActivity.isExpanded());
                notifyDataSetChanged();


                centerTimeingModel1 = new ArrayList<>();
                centerPackageModel1 = new ArrayList<>();

                centerTimeingModel1.clear();
                centerPackageModel1.clear();

                centerTimeingModel1 = centerActivity.getCenterTimeingSlots();
                centerPackageModel1 = centerActivity.getCenterPackageModels();

                if (centerTimeingModel1.size() != 0){
                    
                    linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
                    timeslotAdapter = new TimeslotAdapter(centerTimeingModel1,context);
                    holder.timeingsRecycler.setLayoutManager(linearLayoutManager);
                    holder.timeingsRecycler.setHasFixedSize(true);
                    holder.timeingsRecycler.smoothScrollToPosition(0);
                    holder.timeingsRecycler.setAdapter(timeslotAdapter);

                }

                if (centerPackageModel1.size() != 0){

                    linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
                    memberShipAdapter = new MemberShipAdapter1(centerPackageModel1,context,
                            "SingleProduct",dialogConfirm,centerActivity.getService_master_id(),center_id);
                    holder.packagesRecycler.setLayoutManager(linearLayoutManager);
                    holder.packagesRecycler.setHasFixedSize(true);
                    holder.packagesRecycler.smoothScrollToPosition(0);
                    holder.packagesRecycler.setAdapter(memberShipAdapter);

                }

            }
        });*/


    }

    @Override
    public int getItemCount() {
       // return centerActivityModelArrayList.size();
        return centerActivityModelArrayList == null ? 0 : centerActivityModelArrayList.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView centerName,memberShipPlan;
        RecyclerView packagesRecycler,timeingsRecycler;
        LinearLayout expandableLayout;

        ImageView img_Category;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            centerName = itemView.findViewById(R.id.centerName);
            img_Category = itemView.findViewById(R.id.img_Category);
         //   memberShipPlan = itemView.findViewById(R.id.memberShipPlan);
         //   packagesRecycler = itemView.findViewById(R.id.packagesRecycler);
          //  timeingsRecycler = itemView.findViewById(R.id.timeingsRecycler);
        //    expandableLayout = itemView.findViewById(R.id.expandableLayout);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }
}
