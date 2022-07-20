package in.co.macedon.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import in.co.macedon.R;
import in.co.macedon.adapters.ExploreMoreAdapters;
import in.co.macedon.models.ExploreMoreGetSet;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class ExploreMore extends AppCompatActivity {

    RecyclerView explore_recycler;
    ArrayList<ExploreMoreGetSet> explorearray = new ArrayList<ExploreMoreGetSet>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_more);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        explore_recycler = findViewById(R.id.explore_recycler);

        setData();
    }

    public void setData(){

        explorearray = new ArrayList<ExploreMoreGetSet>();

        explorearray.add(new ExploreMoreGetSet(R.drawable.ex_a, "Fitness\nGear"));
        explorearray.add(new ExploreMoreGetSet(R.drawable.ex_b, "Personal\nTraining"));
        explorearray.add(new ExploreMoreGetSet(R.drawable.ex_c, "Indian\nMeals"));
        explorearray.add(new ExploreMoreGetSet(R.drawable.ex_d, "Doctor\nConsultation"));
        explorearray.add(new ExploreMoreGetSet(R.drawable.ex_e, "Therapy\nSession"));
        explorearray.add(new ExploreMoreGetSet(R.drawable.ex_f, "Dietician\nConsultation"));
        explorearray.add(new ExploreMoreGetSet(R.drawable.ex_g, "Lab\nTests"));
        explorearray.add(new ExploreMoreGetSet(R.drawable.ex_h, "Skin &\nHair Care"));
        explorearray.add(new ExploreMoreGetSet(R.drawable.ex_i, "Suppliments"));



        ExploreMoreAdapters adpater = new ExploreMoreAdapters(explorearray, ExploreMore.this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        explore_recycler.setLayoutManager(gridLayoutManager);
        explore_recycler.setItemAnimator(new DefaultItemAnimator());
        explore_recycler.setAdapter(adpater);
    }
}
