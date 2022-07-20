package in.co.macedon.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import in.co.macedon.R;
import in.co.macedon.adapters.FitnessAdapter;
import in.co.macedon.adapters.FitnessFilterAdapter;
import in.co.macedon.adapters.ShopFilterAdapter;
import in.co.macedon.adapters.ShopItemAdapter;
import in.co.macedon.models.FitnessCentreGetSet;
import in.co.macedon.models.FitnessFilterGetSet;
import in.co.macedon.models.ShopFilterGetSet;
import in.co.macedon.models.FitnessCentreGetSet;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class FitnessCentre extends AppCompatActivity {

    RecyclerView filteritems, fitnesscentrerecycler;
    ArrayList<FitnessFilterGetSet> filterarray = new ArrayList<FitnessFilterGetSet>();
    ArrayList<FitnessCentreGetSet> centresarray = new ArrayList<FitnessCentreGetSet>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_centre);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        filteritems = findViewById(R.id.filteritems);
        fitnesscentrerecycler = findViewById(R.id.fitnesscentrerecycler);

        setFilter();
        setShopData();
    }

    public void setFilter(){

        filterarray = new ArrayList<FitnessFilterGetSet>();

        filterarray.add(new FitnessFilterGetSet(R.drawable.ic_gym_icon, "Gym Workout"));
        filterarray.add(new FitnessFilterGetSet(R.drawable.ic_gym_icon, "Zumba"));
        filterarray.add(new FitnessFilterGetSet(R.drawable.ic_gym_icon, "Yoga"));

        FitnessFilterAdapter catAdapter = new FitnessFilterAdapter(filterarray, FitnessCentre.this);
        filteritems.setHasFixedSize(true);
        filteritems.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        filteritems.setAdapter(catAdapter);

    }

    public void setShopData(){

        centresarray = new ArrayList<FitnessCentreGetSet>();

        centresarray.add(new FitnessCentreGetSet(R.drawable.gym_a, "Vintage GYM", "Kopar Khairane, Navi Mumbai", "4.2", "Excelent", "221", "05:30 AM - 09:00 PM", "1", "1", "0"));
        centresarray.add(new FitnessCentreGetSet(R.drawable.gym_b, "Stay Strong", "Kalyan Phata, Navi Mumbai", "4.5", "Excelent", "103", "05:30 AM - 09:00 PM", "1", "1", "1"));
        centresarray.add(new FitnessCentreGetSet(R.drawable.gym_c, "Muscle MANIA", "Mahavir Nagar-kandivali West, Mumbai", "3.2", "Average", "99", "05:30 AM - 09:00 PM", "1", "0", "0"));
        centresarray.add(new FitnessCentreGetSet(R.drawable.gym_d, "Strong House", "Kandivali West, Mumbai", "4.0", "Excelent", "130", "05:30 AM - 09:00 PM", "0", "1", "0"));
        centresarray.add(new FitnessCentreGetSet(R.drawable.gym_e, "CrossFit FNC", "Mathuradas Road, Navi Mumbai", "3.5", "Good", "0", "05:30 AM - 09:00 PM", "1", "1", "1"));


        FitnessAdapter adpater = new FitnessAdapter(centresarray, FitnessCentre.this);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getApplicationContext());
        fitnesscentrerecycler.setLayoutManager(gridLayoutManager);
        fitnesscentrerecycler.setItemAnimator(new DefaultItemAnimator());
        fitnesscentrerecycler.setAdapter(adpater);

    }

}
