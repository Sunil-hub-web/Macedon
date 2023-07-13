package in.co.macedon.adapters;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.co.macedon.R;
import in.co.macedon.fragments.PackageFragment;
import in.co.macedon.models.MemberShipModel;

public class MemberShipAdapter1 extends RecyclerView.Adapter<MemberShipAdapter1.ViewHolder> {

    Context context;
    ArrayList<MemberShipModel> memberShipModels;
    String SingleProduct,service_master_id,center_id;

    Dialog dialog;
    public MemberShipAdapter1(ArrayList<MemberShipModel> memberShip_models, Context context,
                              String singleProduct, Dialog dialogConfirm, String service_master_id, String center_id) {

        this.context = context;
        this.memberShipModels = memberShip_models;
        this.SingleProduct = SingleProduct;
        this.dialog = dialogConfirm;
        this.service_master_id = service_master_id;
        this.center_id = center_id;
    }

    @NonNull
    @Override
    public MemberShipAdapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.memberpackage,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberShipAdapter1.ViewHolder holder, int position) {

        MemberShipModel memberModel = memberShipModels.get(position);
        holder.packageName.setText(memberModel.getPackage_name());
        holder.priceDetails.setText("RS "+memberModel.getPackage_price()+" /-");
        holder.packagedurtion.setText("Package Duration  "+memberModel.getPackage_duration()+"  Days");
        holder.packageprice.setText("Class Per Week Rs  "+memberModel.getClass_week()+" /-");
        holder.messageDet.setText(memberModel.getPackage_description());

        holder.btn_BuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PackageFragment packageFragment = new PackageFragment();
                Bundle bundle = new Bundle();
                bundle.putString("packageName",memberModel.getPackage_name());
                bundle.putString("priceDetails",memberModel.getPackage_price());
                bundle.putString("packagedurtion",memberModel.getPackage_duration());
                bundle.putString("packageprice",memberModel.getPackage_duration());
                bundle.putString("messageDet",memberModel.getPackage_description());
                bundle.putString("service_master_id",service_master_id);
                bundle.putString("memberModelId",memberModel.getPackage_id());
                bundle.putString("center_id",center_id);
                packageFragment.setArguments(bundle);
                FragmentTransaction transaction =((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, packageFragment); // Add your fragment class
                transaction.addToBackStack(null);
                transaction.commit();

                dialog.dismiss();
            }
        });

    }

    @Override
    public int getItemCount() {
        return memberShipModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView packageName,priceDetails,packagedurtion,packageprice,messageDet,btn_BuyNow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            packageName = itemView.findViewById(R.id.packageName);
            priceDetails = itemView.findViewById(R.id.priceDetails);
            packagedurtion = itemView.findViewById(R.id.packagedurtion);
            packageprice = itemView.findViewById(R.id.packageprice);
            messageDet = itemView.findViewById(R.id.messageDet);
            btn_BuyNow = itemView.findViewById(R.id.btn_BuyNow);
        }
    }
}

