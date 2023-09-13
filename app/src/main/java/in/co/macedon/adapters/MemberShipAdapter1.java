package in.co.macedon.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
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
        this.SingleProduct = singleProduct;
        this.dialog = dialogConfirm;
        this.service_master_id = service_master_id;
        this.center_id = center_id;
    }

    public MemberShipAdapter1(ArrayList<MemberShipModel> centerPackageModels, Context context, String singleProduct) {

        this.context = context;
        this.memberShipModels = centerPackageModels;
        this.SingleProduct = singleProduct;
    }

    public MemberShipAdapter1(ArrayList<MemberShipModel> centerPackageModels, Context context, String singleProduct, String serviceId, String centerId) {

        this.context = context;
        this.memberShipModels = centerPackageModels;
        this.SingleProduct = singleProduct;
        this.service_master_id = serviceId;
        this.center_id = centerId;
    }

    @NonNull
    @Override
    public MemberShipAdapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlepackagedet,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberShipAdapter1.ViewHolder holder, int position) {

        MemberShipModel memberModel = memberShipModels.get(position);
        holder.packageName.setText(memberModel.getPackage_name());
        holder.priceDetails.setText("RS "+memberModel.getPackage_price()+" /-");
        holder.packagedurtion.setText(memberModel.getPackage_duration()+"  Days");
//        holder.packageprice.setText("Class Per Week Rs  "+memberModel.getClass_week()+" /-");
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
                bundle.putString("messagepass","Memberde");
                packageFragment.setArguments(bundle);
                FragmentTransaction transaction =((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, packageFragment,"packageFragment2"); // Add your fragment class
                transaction.addToBackStack(null);
                transaction.commit();

                SharedPreferences pref = context.getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("packageName",memberModel.getPackage_name());
                editor.putString("priceDetails",memberModel.getPackage_price());
                editor.putString("packagedurtion",memberModel.getPackage_duration());
                editor.putString("packageprice",memberModel.getPackage_duration());
                editor.putString("messageDet",memberModel.getPackage_description());
                editor.putString("service_master_id",service_master_id);
                editor.putString("memberModelId",memberModel.getPackage_id());
                editor.putString("center_id",center_id);
                editor.putString("messagepass","Memberde");
                editor.commit(); // commit changes

              //  dialog.dismiss();
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

