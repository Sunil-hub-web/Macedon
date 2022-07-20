package in.co.macedon.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.co.macedon.R;
import in.co.macedon.models.MyOrder_ModelClass;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {

    Context context;
    ArrayList<MyOrder_ModelClass> myorder;

    public MyOrderAdapter(ArrayList<MyOrder_ModelClass> myorder, FragmentActivity activity) {

        this.myorder = myorder;
        this.context = activity;
    }

    @NonNull
    @Override
    public MyOrderAdapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myorderdetails,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  MyOrderAdapter.ViewHolder holder, int position) {

        MyOrder_ModelClass order = myorder.get(position);

        holder.itemimage.setImageResource(order.getImage());
        holder.regularprice.setText(order.getItemPrice());
        holder.text_itemname.setText(order.getItemNmae());
        holder.text_orderId.setText(order.getOrderId());
        holder.text_orderDate.setText(order.getOrderDate());

    }

    @Override
    public int getItemCount() {
        return myorder.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView regularprice,text_itemname,text_orderId,text_orderDate;
        ImageView itemimage;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);

            itemimage = itemView.findViewById(R.id.itemimage);
            regularprice = itemView.findViewById(R.id.regularprice);
            text_itemname = itemView.findViewById(R.id.text_itemname);
            text_orderId = itemView.findViewById(R.id.text_orderId);
            text_orderDate = itemView.findViewById(R.id.text_orderDate);
        }
    }
}
