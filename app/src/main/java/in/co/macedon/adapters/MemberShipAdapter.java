package in.co.macedon.adapters;

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
import in.co.macedon.fragments.SingleProduct_Fragment;
import in.co.macedon.models.MemberShipModel;

public class MemberShipAdapter extends RecyclerView.Adapter<MemberShipAdapter.ViewHolder> {

    Context context;
    ArrayList<MemberShipModel> memberShipModels;
    String SingleProduct;
    public MemberShipAdapter(ArrayList<MemberShipModel> memberShip_models, Context context, String singleProduct) {

        this.context = context;
        this.memberShipModels = memberShip_models;
        this.SingleProduct = SingleProduct;
    }

    @NonNull
    @Override
    public MemberShipAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gymmembership,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberShipAdapter.ViewHolder holder, int position) {

        MemberShipModel memberModel = memberShipModels.get(position);
        holder.packageName.setText(memberModel.getPackage_name());
        holder.priceDetails.setText("RS "+memberModel.getPackage_price()+" /-");
        holder.packagedurtion.setText("Package Duration  "+memberModel.getPackage_duration()+"  Days");
        holder.packageprice.setText("Class Per Week Rs  "+memberModel.getPackage_duration()+" /-");
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
                packageFragment.setArguments(bundle);
                FragmentTransaction transaction =((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, packageFragment); // Add your fragment class
                transaction.addToBackStack(null);
                transaction.commit();
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
