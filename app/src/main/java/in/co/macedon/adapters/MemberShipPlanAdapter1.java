package in.co.macedon.adapters;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.co.macedon.R;
import in.co.macedon.models.CenterTimeingSlot;
import in.co.macedon.models.SingleCenterActivityModel;

public class MemberShipPlanAdapter1 extends RecyclerView.Adapter<MemberShipPlanAdapter1.ViewHolder> {

    Context context;
    ArrayList<SingleCenterActivityModel> centerActivityModelArrayList;
    ArrayList<CenterTimeingSlot> centerTimeingSlots;
    ArrayList<CenterTimeingSlot> centerTimeingSlots1;
    ArrayList<String> centertimeslot;
    Dialog dialogConfirm;
    LinearLayoutManager linearLayoutManager;
    TimeslotAdapter timeslotAdapter;
    public MemberShipPlanAdapter1(Context context, ArrayList<SingleCenterActivityModel> centerActivityModels,
                                  ArrayList<CenterTimeingSlot> centerTimeingSlots, ArrayList<String> centertimeslot) {

        this.context = context;
        this.centerActivityModelArrayList = centerActivityModels;
        this.centerTimeingSlots = centerTimeingSlots;
        this.centertimeslot = centertimeslot;
    }

    @NonNull
    @Override
    public MemberShipPlanAdapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timesloataddet,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberShipPlanAdapter1.ViewHolder holder, int position) {

        SingleCenterActivityModel centerActivity = centerActivityModelArrayList.get(position);

        String image = "https://www.macedon.in/uploads/"+centerActivity.getImage();
        Picasso.with(context).load(image).into(holder.img_Category);

        holder.centerName.setText(centerActivity.getService_master_name());

        holder.timesolatDet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                centerTimeingSlots1 = new ArrayList<>();

                if (centertimeslot.contains(centerActivity.getService_master_id())){

                    for (CenterTimeingSlot data : centerTimeingSlots){

                        if (data.getService_id().equals(centerActivity.getService_master_id())){

                            centerTimeingSlots1.add(data);
                        }
                    }

                    dialogConfirm = new Dialog(context);
                    dialogConfirm.getWindow().setWindowAnimations(R.style.DialogAnimation);
                    dialogConfirm.setContentView(R.layout.membershippackage);
                    dialogConfirm.getWindow().setBackgroundDrawableResource(android.R.color.white);
                    // dialogConfirm.setCancelable(false);
                    //dialogConfirm.setCanceledOnTouchOutside(true);
                    Window window = dialogConfirm.getWindow();
                    window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                    RecyclerView memberPackageRecycler = dialogConfirm.findViewById(R.id.memberPackageRecycler);

                    linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
                    timeslotAdapter = new TimeslotAdapter(centerTimeingSlots1,context);
                    memberPackageRecycler.setLayoutManager(linearLayoutManager);
                    memberPackageRecycler.setHasFixedSize(true);
                    memberPackageRecycler.smoothScrollToPosition(0);
                    memberPackageRecycler.setAdapter(timeslotAdapter);

                    dialogConfirm.show();

                }else{

                    Toast.makeText(context, "Time Solat Not Avilable List", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return centerActivityModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_Category;
        TextView centerName;
        Button timesolatDet;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_Category = itemView.findViewById(R.id.img_Category);
            centerName = itemView.findViewById(R.id.centerName);
            timesolatDet = itemView.findViewById(R.id.timesolatDet);
        }
    }
}

