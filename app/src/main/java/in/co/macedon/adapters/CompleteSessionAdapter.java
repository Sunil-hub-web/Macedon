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
import in.co.macedon.models.CompleteSession_ModelClass;

public class CompleteSessionAdapter extends RecyclerView.Adapter<CompleteSessionAdapter.ViewHolder> {

    Context context;
    ArrayList<CompleteSession_ModelClass> complete;

    public CompleteSessionAdapter(ArrayList<CompleteSession_ModelClass> comp_session, FragmentActivity activity) {

        this.complete = comp_session;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.completesession,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  CompleteSessionAdapter.ViewHolder holder, int position) {

        CompleteSession_ModelClass comp_session = complete.get(position);

        holder.date.setText(Html.fromHtml("<font color='#288DE9'>Date :<br></font>"+comp_session.getDate()));
        holder.time.setText(Html.fromHtml("<font color='#288DE9'>Time :<br></font>"+comp_session.getTime()));
        holder.gymName.setText(Html.fromHtml("<font color='#288DE9'>GymName :<br></font>"+comp_session.getGymName()));
        holder.gymLocation.setText(Html.fromHtml("<font color='#288DE9'>GymLocation :<br></font>"+comp_session.getGymLocation()));
        holder.inTime.setText(Html.fromHtml("<font color='#288DE9'>InTime :<br></font>"+comp_session.getInTime()));
        holder.outTime.setText(Html.fromHtml("<font color='#288DE9'>OutTime :<br></font>"+comp_session.getOutTime()));

    }


    @Override
    public int getItemCount() {
        return complete.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView date,time,gymName,gymLocation,inTime,outTime;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);

            outTime = itemView.findViewById(R.id.outTime);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            gymName = itemView.findViewById(R.id.gymName);
            gymLocation = itemView.findViewById(R.id.gymLocation);
            inTime = itemView.findViewById(R.id.inTime);
        }
    }
}
