package in.co.macedon.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.co.macedon.R;
import in.co.macedon.models.WalletModel;

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.ViewHolder> {

    Context context;
    ArrayList<WalletModel> wallet_Models;
    public WalletAdapter(ArrayList<WalletModel> walletModels, FragmentActivity activity) {

        this.context = activity;
        this.wallet_Models = walletModels;
    }

    @NonNull
    @Override
    public WalletAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wallet_adapter,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WalletAdapter.ViewHolder holder, int position) {

        WalletModel wallet = wallet_Models.get(position);

        holder.date.setText(Html.fromHtml("<font color='#288DE9'>Amount :<br></font>"+wallet.getAmount()));

        if (wallet.getPayment_type().equals("1")){

            holder.time.setText(Html.fromHtml("<font color='#288DE9'>Payment Type :<br></font>"+"Credited"));

        }else{

            holder.time.setText(Html.fromHtml("<font color='#288DE9'>Payment Type :<br></font>"+"Debited"));
        }

        holder.gymName.setText(Html.fromHtml("<font color='#288DE9'>GymName :<br></font>"+wallet.getCreated_date()));
    }

    @Override
    public int getItemCount() {
        return wallet_Models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView date,time,gymName,gymLocation,inTime,outTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            gymName = itemView.findViewById(R.id.gymName);
        }
    }
}
