package in.co.macedon.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import in.co.macedon.R;
import in.co.macedon.adapters.FitnessAdapter;
import in.co.macedon.adapters.FitnessFilterAdapter;
import in.co.macedon.adapters.TrainerAdapter;
import in.co.macedon.adapters.TrainerFilterAdapter;
import in.co.macedon.extras.RecyclerTouchListener;
import in.co.macedon.models.FitnessCentreGetSet;
import in.co.macedon.models.FitnessFilterGetSet;
import in.co.macedon.models.TrainerFilterGetSet;
import in.co.macedon.models.TrainerGetSet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class TrainerActivity extends AppCompatActivity {

    RecyclerView filteritems, fitnesscentrerecycler;
    ArrayList<TrainerFilterGetSet> filterarray = new ArrayList<TrainerFilterGetSet>();
    ArrayList<TrainerGetSet> trainersarray = new ArrayList<TrainerGetSet>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer);

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

        fitnesscentrerecycler.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), fitnesscentrerecycler, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {

                Intent i = new Intent(getApplicationContext(), TrainerDetail.class);
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        setFilter();
        setShopData();
    }

    public void setFilter(){

        filterarray = new ArrayList<TrainerFilterGetSet>();

        filterarray.add(new TrainerFilterGetSet("Basic"));
        filterarray.add(new TrainerFilterGetSet("Standard"));
        filterarray.add(new TrainerFilterGetSet("Premium"));

        TrainerFilterAdapter catAdapter = new TrainerFilterAdapter(filterarray, TrainerActivity.this);
        filteritems.setHasFixedSize(true);
        filteritems.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        filteritems.setAdapter(catAdapter);

    }

    public void setShopData(){

        trainersarray = new ArrayList<TrainerGetSet>();

        trainersarray.add(new TrainerGetSet(R.drawable.train_a, "K Ravi Kumar", "Certified Professional Trainer", "Weightlifter/Fitness Coach"));
        trainersarray.add(new TrainerGetSet(R.drawable.train_b, "Mary Kom", "Certified Professional Trainer", "Boxer/Fitness Coach"));
        trainersarray.add(new TrainerGetSet(R.drawable.train_c, "Geeta Phogat", "Certified Professional Trainer", "Freestyle Wrestler/Fitness Coach"));
        trainersarray.add(new TrainerGetSet(R.drawable.train_d, "Vijendra Singh", "Certified Professional Trainer", "Boxer/Fitness Coach"));


        TrainerAdapter adpater = new TrainerAdapter(trainersarray, TrainerActivity.this);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getApplicationContext());
        fitnesscentrerecycler.setLayoutManager(gridLayoutManager);
        fitnesscentrerecycler.setItemAnimator(new DefaultItemAnimator());
        fitnesscentrerecycler.setAdapter(adpater);

    }
}
