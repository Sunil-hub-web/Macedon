package in.co.macedon.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import in.co.macedon.R;
import in.co.macedon.adapters.DieticianAdapter;
import in.co.macedon.adapters.ExploreMoreAdapters;
import in.co.macedon.models.DieticianGetSet;
import in.co.macedon.models.ExploreMoreGetSet;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class Dietician extends AppCompatActivity {

    RecyclerView dietician_recycler;
    ArrayList<DieticianGetSet> dieticianearray = new ArrayList<DieticianGetSet>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dietician);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dietician_recycler = findViewById(R.id.dietician_recycler);

        setData();
    }

    public void setData(){

        dieticianearray = new ArrayList<DieticianGetSet>();

        dieticianearray.add(new DieticianGetSet("1", "SESSION", "250.00", "199.00"));
        dieticianearray.add(new DieticianGetSet("1", "MONTH NUTRITION PACK", "2250.00", "1999.00"));
        dieticianearray.add(new DieticianGetSet("3", "MONTH NUTRITION PACK", "5999.00", "4999.00"));
        dieticianearray.add(new DieticianGetSet("6", "MONTH NUTRITION PACK", "8999.00", "7999.00"));
        dieticianearray.add(new DieticianGetSet("9", "MONTH NUTRITION PACK", "10999.00", "8999.00"));
        dieticianearray.add(new DieticianGetSet("12", "MONTH NUTRITION PACK", "11999.00", "9999.00"));



        DieticianAdapter adpater = new DieticianAdapter(dieticianearray, Dietician.this);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getApplicationContext());
        dietician_recycler.setLayoutManager(gridLayoutManager);
        dietician_recycler.setItemAnimator(new DefaultItemAnimator());
        dietician_recycler.setAdapter(adpater);
    }
}
