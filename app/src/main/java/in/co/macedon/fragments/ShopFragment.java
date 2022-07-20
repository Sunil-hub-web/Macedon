package in.co.macedon.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import in.co.macedon.R;
import in.co.macedon.adapters.ShopFilterAdapter;
import in.co.macedon.adapters.ShopItemAdapter;
import in.co.macedon.models.ShopFilterGetSet;
import in.co.macedon.models.ShopGetSet;


public class ShopFragment extends Fragment {

    RecyclerView filteritems, productrecycler;
    ArrayList<ShopFilterGetSet> filterarray = new ArrayList<ShopFilterGetSet>();
    ArrayList<ShopGetSet> shoparray = new ArrayList<ShopGetSet>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);

        filteritems = v.findViewById(R.id.filteritems);
        productrecycler = v.findViewById(R.id.productrecycler);


        setFilter();
        setShopData();
        return v;
    }

    public void setFilter(){

        filterarray = new ArrayList<ShopFilterGetSet>();

        filterarray.add(new ShopFilterGetSet("All Protiens"));
        filterarray.add(new ShopFilterGetSet("Whey protiens"));
        filterarray.add(new ShopFilterGetSet("Beginners"));
        filterarray.add(new ShopFilterGetSet("Casein Protein"));
        filterarray.add(new ShopFilterGetSet("Hemp Protein"));


        ShopFilterAdapter catAdapter = new ShopFilterAdapter(filterarray, getActivity());
        filteritems.setHasFixedSize(true);
        filteritems.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        filteritems.setAdapter(catAdapter);

    }

    public void setShopData(){

        shoparray = new ArrayList<ShopGetSet>();

        shoparray.add(new ShopGetSet(R.drawable.shop_a, "MusclePharma Combat Protien Powder", "3499.00", "3999.00", "12% OFF", "3394.00", "4"));
        shoparray.add(new ShopGetSet(R.drawable.shop_b, "ProJYM Ultra Premium Protien Blend", "3499.00", "3999.00", "12% OFF", "3394.00", "4.5"));
        shoparray.add(new ShopGetSet(R.drawable.shop_c, "Optimum Nutrition Gold Standard WHEY", "3499.00", "3999.00", "12% OFF", "3394.00", "3.6"));
        shoparray.add(new ShopGetSet(R.drawable.shop_d, "Syntha-6 Protien Matrix", "3499.00", "3999.00", "12% OFF", "3394.00", "5"));
        shoparray.add(new ShopGetSet(R.drawable.shop_e, "HealthOxide Womens Protien Powder", "3499.00", "3999.00", "12% OFF", "3394.00", "4.6"));


        ShopItemAdapter adpater = new ShopItemAdapter(shoparray, getActivity());
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getActivity());
        productrecycler.setLayoutManager(gridLayoutManager);
        productrecycler.setItemAnimator(new DefaultItemAnimator());
        productrecycler.setAdapter(adpater);

    }
}
