package in.co.macedon.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.co.macedon.R;
import in.co.macedon.adapters.CompleteSessionAdapter;
import in.co.macedon.models.CompleteSession_ModelClass;

public class CompletedSession extends Fragment {

    RecyclerView recyclerCompleteSession;
    CompleteSessionAdapter completeSessionAdapter;
    LinearLayoutManager linearLayoutManager;
    ArrayList<CompleteSession_ModelClass> comp_session = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater,
                             @Nullable  ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.completedsession_fragment,container,false);

        recyclerCompleteSession = view.findViewById(R.id.recyclerCompleteSession);


        comp_session.add(new CompleteSession_ModelClass("1.16.2021","3:24 PM","Armstrong Training Facility","Patia","3:24 PM","7:20 PM"));
        comp_session.add(new CompleteSession_ModelClass("2.17.2021","8:24 AM","The War House Gym","Sum Hospital Road","8:24 AM","10:20 AM"));
        comp_session.add(new CompleteSession_ModelClass("3.18.2021","5:24 PM","Bhubaneshwar Health Club","NISER Campus","5:24 PM","7:00 PM"));
        comp_session.add(new CompleteSession_ModelClass("4.19.2021","6:24 PM","Stayfit Gym & Slimming Centre","Satyanagar","6:24 PM","9:00 PM"));

        linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        completeSessionAdapter = new CompleteSessionAdapter(comp_session,getActivity());
        recyclerCompleteSession.setLayoutManager(linearLayoutManager);
        recyclerCompleteSession.setHasFixedSize(true);
        recyclerCompleteSession.setItemAnimator(new DefaultItemAnimator());
        recyclerCompleteSession.setAdapter(completeSessionAdapter);




        return view;
    }
}
