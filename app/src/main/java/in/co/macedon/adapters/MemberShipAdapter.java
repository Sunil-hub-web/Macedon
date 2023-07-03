package in.co.macedon.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.co.macedon.R;
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

    }

    @Override
    public int getItemCount() {
        return memberShipModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView packageName,priceDetails,packagedurtion,packageprice,messageDet;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            packageName = itemView.findViewById(R.id.packageName);
            priceDetails = itemView.findViewById(R.id.priceDetails);
            packagedurtion = itemView.findViewById(R.id.packagedurtion);
            packageprice = itemView.findViewById(R.id.packageprice);
            messageDet = itemView.findViewById(R.id.messageDet);
        }
    }
}
