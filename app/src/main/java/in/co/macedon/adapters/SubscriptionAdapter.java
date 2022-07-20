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

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subscriptions,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  SubscriptionAdapter.ViewHolder holder, int position) {

        Subscriptions_ModelClass subsc = subscription.get(position);

        holder.planName.setText(subsc.getMembership_name());
        holder.price.setText(subsc.getMembership_price());
        holder.description.setText(Html.fromHtml(subsc.getMembership_description()));

        holder.planName.setChecked(lastSelectedPosition == position);

        holder.planName.setChecked(lastSelectedPosition == position);

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

        RadioButton planName;
        TextView price,description;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);

            description = itemView.findViewById(R.id.description);
            price = itemView.findViewById(R.id.price);
            planName = itemView.findViewById(R.id.planName);

            planName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    lastSelectedPosition = getAdapterPosition();
                    notifyDataSetChanged();

                    String message = planName.getText().toString();

                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
