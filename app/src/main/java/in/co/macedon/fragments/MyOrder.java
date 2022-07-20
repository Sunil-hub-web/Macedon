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
import in.co.macedon.adapters.MyOrderAdapter;
import in.co.macedon.adapters.ShopItemAdapter;
import in.co.macedon.models.MyOrder_ModelClass;
import in.co.macedon.models.ShopGetSet;

public class MyOrder extends Fragment {

    RecyclerView recyclerMyOrder;
    LinearLayoutManager linearLayoutManager;
    MyOrderAdapter myOrderAdapter;
    ArrayList<MyOrder_ModelClass> myorder = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater,
                             @Nullable  ViewGroup container,
                             @Nullable  Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.myorder,container,false);

        recyclerMyOrder = view.findViewById(R.id.recyclerMyOrder);


        myorder.add(new MyOrder_ModelClass(R.drawable.shop_a, "MusclePharma Combat Protien Powder", "dsafgghhh", "25.05.2021", "3999.00"));
        myorder.add(new MyOrder_ModelClass(R.drawable.shop_b, "ProJYM Ultra Premium Protien Blend", "dsafgghhh", "25.06.2021", "3394.00"));
        myorder.add(new MyOrder_ModelClass(R.drawable.shop_c, "Optimum Nutrition Gold Standard WHEY", "dsafgghhh", "25.07.2021", "3394.00"));
        myorder.add(new MyOrder_ModelClass(R.drawable.shop_d, "Syntha-6 Protien Matrix", "dsafgghhh", "25.08.2021",  "3394.00"));
        myorder.add(new MyOrder_ModelClass(R.drawable.shop_e, "HealthOxide Womens Protien Powder", "dsafgghhh", "25.09.2021", "3394.00"));


        myOrderAdapter = new MyOrderAdapter(myorder, getActivity());
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerMyOrder.setLayoutManager(linearLayoutManager);
        recyclerMyOrder.setItemAnimator(new DefaultItemAnimator());
        recyclerMyOrder.setHasFixedSize(true);
        recyclerMyOrder.setAdapter(myOrderAdapter);

        return view;
    }
}
