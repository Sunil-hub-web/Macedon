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
import in.co.macedon.models.MemberShipModel;
import in.co.macedon.models.SingleCenterActivityModel;

public class MemberShipPlanAdapter extends RecyclerView.Adapter<MemberShipPlanAdapter.ViewHolder> {

    Context context;
    ArrayList<SingleCenterActivityModel> centerActivityModelArrayList;
    ArrayList<MemberShipModel> centerPackageModelArrayList;
    ArrayList<MemberShipModel> centerPackageModelArrayList1;
    ArrayList<String> centerPackageArray;
    Dialog dialogConfirm;
    LinearLayoutManager linearLayoutManager;
    MemberShipAdapter1 memberShipAdapter;
    String center_id;
    public MemberShipPlanAdapter(Context context, ArrayList<SingleCenterActivityModel> centerActivityModels,
                                 ArrayList<MemberShipModel> centerPackageModels, ArrayList<String> centerPackageArray,
                                 ArrayList<CenterTimeingSlot> centerTimeingSlots, String center_id) {

        this.context = context;
        this.centerActivityModelArrayList = centerActivityModels;
        this.centerPackageModelArrayList = centerPackageModels;
        this.centerPackageArray = centerPackageArray;
        this.center_id = center_id;
    }

    @NonNull
    @Override
    public MemberShipPlanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.membershiplan,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberShipPlanAdapter.ViewHolder holder, int position) {

        SingleCenterActivityModel centerActivity = centerActivityModelArrayList.get(position);

        String image = "https://www.macedon.in/uploads/"+centerActivity.getImage();
        Picasso.with(context).load(image).into(holder.img_Category);

        holder.centerName.setText(centerActivity.getService_master_name());

        holder.memberShipPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                centerPackageModelArrayList1 = new ArrayList<>();

                if (centerPackageArray.contains(centerActivity.getService_master_id())){

                    for (MemberShipModel data : centerPackageModelArrayList){

                        if (data.getService_id().equals(centerActivity.getService_master_id())){

                            centerPackageModelArrayList1.add(data);
                        }
                    }

                    dialogConfirm = new Dialog(context);
                    dialogConfirm.getWindow().setWindowAnimations(R.style.DialogAnimation);
                    dialogConfirm.setContentView(R.layout.membershippackage);
                    dialogConfirm.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    // dialogConfirm.setCancelable(false);
                    //dialogConfirm.setCanceledOnTouchOutside(true);
                    Window window = dialogConfirm.getWindow();
                    window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                    RecyclerView memberPackageRecycler = dialogConfirm.findViewById(R.id.memberPackageRecycler);

                    linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
                    memberShipAdapter = new MemberShipAdapter1(centerPackageModelArrayList1,context,
                            "SingleProduct",dialogConfirm,centerActivity.getService_master_id(),center_id);
                    memberPackageRecycler.setLayoutManager(linearLayoutManager);
                    memberPackageRecycler.setHasFixedSize(true);
                    memberPackageRecycler.smoothScrollToPosition(0);
                    memberPackageRecycler.setAdapter(memberShipAdapter);

                    dialogConfirm.show();

                }else{

                    Toast.makeText(context, "Package List Not Found", Toast.LENGTH_SHORT).show();
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
        Button memberShipPlan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_Category = itemView.findViewById(R.id.img_Category);
            centerName = itemView.findViewById(R.id.centerName);
            memberShipPlan = itemView.findViewById(R.id.memberShipPlan);
        }
    }
}
