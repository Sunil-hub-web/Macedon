package in.co.macedon.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.Flow;

import in.co.macedon.R;
import in.co.macedon.models.Subscriptions_ModelClass;

public class SubscriptionAdapter extends RecyclerView.Adapter<SubscriptionAdapter.ViewHolder> {

    Context context;
    ArrayList<Subscriptions_ModelClass> subscription;
    int index;
    private int lastSelectedPosition = -1;
    public SubscriptionAdapter(Context context, ArrayList<Subscriptions_ModelClass> subscription_macPass) {

        this.context = context;
        this.subscription = subscription_macPass;
    }

    @NonNull
    @Override
    public SubscriptionAdapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subscription_fragment,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  SubscriptionAdapter.ViewHolder holder, int position) {

        Subscriptions_ModelClass subsc = subscription.get(position);

        holder.text_PackageName.setText(subsc.getPackage_name());
        holder.text_MembershipFor.setText(subsc.getCenter_Name());
        holder.text_Duration.setText(Html.fromHtml(subsc.getPackage_duration()+" Days "+subsc.getTotal_sesson()+" sesson "));
        holder.text_StartDate.setText(Html.fromHtml(subsc.getPurchace_date()));
        holder.text_ExpireDate.setText(Html.fromHtml(subsc.getExpair_date()));
        holder.text_PurchaseDate.setText(Html.fromHtml(subsc.getPurchace_date()));

       // holder.text_StartDate.setChecked(lastSelectedPosition == position);

       // holder.planName.setChecked(lastSelectedPosition == position);

        /*holder.planName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                *//*index = position;
                notifyDataSetChanged();
                holder.planName.setChecked(true);*//*


            }
        });*/

       /* if(index == position){

            holder.planName.setChecked(true);
        }
        else {

            holder.planName.setChecked(false);
        }*/

    }

    @Override
    public int getItemCount() {
        return subscription.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView text_PackageName,text_MembershipFor,text_Duration,text_StartDate,text_ExpireDate,text_PurchaseDate;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);

            text_PackageName = itemView.findViewById(R.id.text_PackageName);
            text_MembershipFor = itemView.findViewById(R.id.text_MembershipFor);
            text_Duration = itemView.findViewById(R.id.text_Duration);
            text_StartDate = itemView.findViewById(R.id.text_StartDate);
            text_ExpireDate = itemView.findViewById(R.id.text_ExpireDate);
            text_PurchaseDate = itemView.findViewById(R.id.text_PurchaseDate);

           /* planName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    lastSelectedPosition = getAdapterPosition();
                    notifyDataSetChanged();

                    String message = planName.getText().toString();

                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                }
            });*/
        }
    }
}
